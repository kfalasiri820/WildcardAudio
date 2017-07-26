/*******************************************************INCLUDES*******************************************************/
#include <DSP28x_Project.h>
#include <DSP2833x_Xintf.h>
#include <DSP2833x_XIntrupt.h>
#include "Peripherals/timer.h"
#include "Peripherals/analogToDigitalConverter.h"
#include "Peripherals/digitalToAnalogConverter.h"
#include <DSP2833x_CpuTimers.h>
#include <DSP2833x_SysCtrl.h>
#include "Peripherals/audioCntrl.h"
#include "Peripherals/Sram.h"
#include "Peripherals/digitalToAnalogConverter.h"
#include <DSP2833x_GlobalPrototypes.h>
#include <math.h>
#include <stdio.h>
#include <stdbool.h>
#include "Effects/squares.h"
#include "Effects/ringmod.h"
#include "Effects/noise_gate.h"
#include "Effects/tremolo.h"


/*******************************************************PRAGMAS*******************************************************/
#pragma DATA_SECTION(loop1, ".memory")
#pragma DATA_SECTION(loop2, ".memory")
#pragma DATA_SECTION(inputBuffer, ".memory")
#pragma DATA_SECTION( ping_1  , "DMARAML4" )
#pragma DATA_SECTION( pong_1  , "DMARAML4" )
#pragma DATA_SECTION( ping_2  , "DMARAML5" )
#pragma DATA_SECTION( pong_2  , "DMARAML5" )
#pragma DATA_SECTION( garbage , "DMARAML5" )


/*************************************************DEFINES/GLOBALS*****************************************************/
//////////////////////////////////DEFINE////////////////////////////
#define SRAM_BUFFER 256000  //The maximum size of the loop buffer (in SRAM)
#define INPUT_BUFFER_SIZE 65536
#define BUFFER_SIZE 256 //The general size of the ping and pong buffer

/////////////////////////////////LOOPING BUFFERS////////////////////////////
Uint32 var  = 0;
Uint16 loop1[SRAM_BUFFER];      //The loop one buffer (in SRAM)
Uint32 loopIndex = 0;          //Index used to traverse through loop1
Uint32 loopLength = SRAM_BUFFER;    //The universal length of the loop (starts out as max length)
Uint16 loop1Clicks = 0;             //The number of loop pedal clicks for loop 1
Uint16 recording1 = 0;          //1 if you are recording1, 0 if you are not
Uint16 loop2[SRAM_BUFFER];      //The loop one buffer (in SRAM)
Uint16 recording2 = 0;          //1 if you are recording1, 0 if you are not
volatile Uint32 ping_1[BUFFER_SIZE] = {0};
volatile Uint32 pong_1[BUFFER_SIZE] = {0};
volatile Uint16 ping_2[BUFFER_SIZE] = {0};
volatile Uint16 pong_2[BUFFER_SIZE] = {0};
volatile Uint32 garbage= 0x7BADB015;    //Used to write to the MCBSP B to read in inputs
volatile Uint16 program_state = 0;  //Used to mark whether in ping pong state 0 or 1
volatile Uint16 ready = 1;          //1 when the main is not finished processing data, 0 if is finished
volatile char rxBuffer[100];            //Receive buffer: used to collect information from USART
Uint16 rxBufferIndex = 0;           //Index for receive buffer

/////////////////////////////////////TAPS//////////////////////////////////
Uint32 tapsIndex = 0;//(the number of samples between each tap)
Uint16 taps = 0;//keeps track of number of taps (0, 1, or 2)

/////////////////////////////////////DELAY/////////////////////////////////
Uint16 inputBuffer[INPUT_BUFFER_SIZE];
Uint32 delaySizeInSamples = 10000;
Uint32 delayBufferPrev = INPUT_BUFFER_SIZE - 10000;	//The delayed sample
Uint32 delayBufferCurr = 0;							//Used to write the sample

/////////////////////////////////////RING MOD//////////////////////////////
extern Uint16 ringModCounter;
extern Uint16 ringModRate;
extern float ringModDepth;
extern float ringModBias;

/////////////////////////////////////NOISE GATE//////////////////////////////
Uint16 noiseGate_sample_counter,
               noiseGate_release_time_a,
               noiseGate_release_time_b,
               noiseGate_threshold;


/******************************************************PROTOTYPES*****************************************************/
void mainEffectsAndLoop(volatile Uint32 in[256], volatile Uint16 out[256]);
void init_ButtonInterrupt(void);
void initSampleRateTimer(void);
void Init_DMA(void);
void init_ButtonInterrupt(void);


/****************************************************INTERRUPTS*******************************************************/

/**************************************************************
* Name:     sampleRate_ISR
* Purpose:  Timer 1 runs at samples rate. Toggles LED
**************************************************************/
interrupt void sampleRate_ISR(void){
    EALLOW;
    GpioDataRegs.GPADAT.bit.GPIO0 ^= 1;
}

/**************************************************************
* Name:     loop1Click_ISR
* Purpose:  Interrupt triggered by the press of button 1.
*   First click: starts counting how many samples; starts recording
*   Second click: stops counting, now has the number of samples for looplength,
*       stops recording
*   Future clicks: toggles recording
**************************************************************/
__interrupt void loop1Click_ISR(void){
    EALLOW;
    GpioDataRegs.GPATOGGLE.bit.GPIO7=1; // interrupt LED

    loop1Clicks++;

    ////////////////////Looping index handling////////////////////
	if(loop1Clicks == 1)//If you pressed loop 1 once (first record)...
		loopIndex = 0;//Start the loopIndex at 0
	if(loop1Clicks == 2)//If you pressed the loop 1 twice (clicks = 3)
		loopLength = loopIndex;//mark the loop length

    recording1 ^= 1;
    PieCtrlRegs.PIEACK.all = PIEACK_GROUP1; //acknowledges the int
    EINT;
}

/**************************************************************
* Name:     loop2Click_ISR
* Purpose:  Same as loop1Click_ISR
**************************************************************/
__interrupt void loop2Click_ISR(void){
    EALLOW;
    GpioDataRegs.GPATOGGLE.bit.GPIO6=1; // interrupt LED

    loop1Clicks++;

    if(loop1Clicks == 1)//If you pressed loop 1 once (first record)...
		loopIndex = 0;//Start the loopIndex at 0
	if(loop1Clicks == 2)//If you pressed the loop 1 twice (clicks = 3)
		loopLength = loopIndex;//mark the loop length
    recording2 ^= 1;
    PieCtrlRegs.PIEACK.all = PIEACK_GROUP1; //acknowledges the int
    EINT;
}

/**************************************************************
* Name:     MRB_ISR
* Purpose:  McBSPB ready ISR: acknowledge the finished block transfer of DMA
**************************************************************/
interrupt void MRB_ISR (void){
    EALLOW;
    GpioDataRegs.GPADAT.bit.GPIO2 ^= 1;
    PieCtrlRegs.PIEACK.all = PIEACK_GROUP6;
}

/**************************************************************
* Name:     DMAch3_ISR
* Purpose:  Acknowledge the output of the appropriate ping/pong buffer to MCBSP A (DAC)
**************************************************************/
interrupt void DMAch3_ISR (void){
    EALLOW;
    PieCtrlRegs.PIEACK.all = PIEACK_GROUP7;
}

/**************************************************************
* Name:     DMAch2_ISR
* Purpose:  Depending on program state, transfers samples from MCBSPB to
*           ping_1 and ping_2 to MCBSPA or MCBSPB to pong_1 and pong_2 to MCBSPA.
*           Triggered when transfer is complete from ADC to MCBSPB and MCBSPA to DAC.
**************************************************************/
interrupt void DMAch2_ISR(void){
    if(ready){
        if(program_state == 0){
            DMACH2AddrConfig( (volatile Uint16*) &ping_1[0] + 1 , (volatile Uint16*) &McbspbRegs.DRR2.all );
            DMACH3AddrConfig( (volatile Uint16*) &McbspaRegs.DXR1 , (volatile Uint16*) &ping_2[0] );
        }
        else if(program_state == 1){
            DMACH2AddrConfig( (volatile Uint16*) &pong_1[0] + 1 , (volatile Uint16*) &McbspbRegs.DRR2.all );
            DMACH3AddrConfig( (volatile Uint16*) &McbspaRegs.DXR1 , (volatile Uint16*) &pong_2[0] );
        }
        program_state ^= 1;//flip the program state
        EALLOW;
        GpioDataRegs.GPADAT.bit.GPIO1 ^= 1;//flash an LED
        PieCtrlRegs.PIEACK.all = PIEACK_GROUP7;//acknowledge the interrupt
        ready = 0;//not ready (just started a new block transfer)
    }
}

/**************************************************************
* Name:     taps_ISR
* Purpose:  Triggered by tap button. Takes the number of samples between the last
*           two taps after an even number of taps.
**************************************************************/
__interrupt void taps_ISR (void){
	EALLOW;
	GpioDataRegs.GPATOGGLE.bit.GPIO7=1; // interrupt LED

	//if not first click and less than 5 seconds passed between clicks
 	if(tapsIndex != 0 && tapsIndex < 225000)
 		delaySizeInSamples = tapsIndex;
 	tapsIndex = 0;

	////////////////////Looping index handling////////////////////
//	if(taps == 1)//If you pressed loop 1 once (first record)...
//		tapsIndex = 0;//Start the loopIndex at 0
//	if(taps == 2){//If you pressed the loop 1 twice (clicks = 3)
//		delaySizeInSamples = tapsIndex;//mark the loop length
//		taps = 0;
//	}

	PieCtrlRegs.PIEACK.all = PIEACK_GROUP1; //acknowledges the interrupt
	EINT;
}


///////////////////////////////////////////////////////////////MAIN///////////////////////////////////////////////////////////////
int main(void){
    DisableDog();//Disable the watchdog
    InitPll(10,3);//Set up some stuff
    InitSysCtrl();//wtf... check what clocks we actually need
    Sram_init();//Initialize the SRAM to save loop information

    //Writing buffers to zero
    for ( var  = 0;  var  < SRAM_BUFFER; var++ ){
    		loop1[var] = 0x0000;
    		loop2[var] = 0x0000;
    }
	for ( var  = 0;  var  < 100; var++ )
		  rxBuffer[var] = 0x0000;

    analogToDigitalConverter_init();//Init MCBSP B (ADC)
    digitalToAnalogConverter_init();//Init MCBSP A (DAC)
    useGpio();//Init LED and switche
    //startUSART();
    Init_DMA();//Init the DMA
    initSampleRateTimer();//Init timer 1 (run at sample rate)
    init_ButtonInterrupt();//Init the loop1 button interrupt

    //outWord("enter a number ");

    /*
     * try the single read and write in one loop instead of alternating
     */
    /* MAIN LOOP: batch calculates from ping_1 to ping_2 or pong_1 to pong_2
     * FX
     * Loop
     */
    while(1){
        while(!ready){//Batch process while waiting for DMA to finish
            GpioDataRegs.GPADAT.bit.GPIO3 ^= 1; //DEBUG: toggle LED
            if(program_state == 0)
        			mainEffectsAndLoop(ping_1, ping_2);
            else
        			mainEffectsAndLoop(pong_1, pong_2);
            ready = 1;
            GpioDataRegs.GPADAT.bit.GPIO3 ^= 1;
        }
    }
}

/****************************************************FUNCTIONS*******************************************************/

/**************************************************************
* Name:     mainEffectsAndLoop
* Purpose:  Output live + loop/effects to the DAC
* Inputs: in[256], out[256]
* Output:
**************************************************************/
void mainEffectsAndLoop(volatile Uint32 in[256], volatile Uint16 out[256]){
	for(int i = 0; i < BUFFER_SIZE; i++){//Batch process through 256 samples

		///////////////////////////////////LIVE SIGNAL//////////////////////////////////
		//NOTE: ping_1 is the input, ping_2 is the output
		Uint16 live = (Uint16)((in[i] >> 2) & 0xFFFF);//define the live signal
        Uint16 effectedSample = live;//the sample to be effected


		//////////////////////////////////////Effects//////////////////////////////////
            //effectedSample = effectFunction(effectedSample);
    		/*********************************SQUARES*****************************/

//            effectedSample = ringModulation((float)effectedSample);

//            effectedSample = squares(effectedSample);

            effectedSample = tremolo(effectedSample);


            /**************Outlive live signal and effected Sample**************/
    		out[i] =  (Uint16)/*(0.5 * (float) live +*/ (0.5 * (float) effectedSample);//Always output the live signal


		////////////////////////////Loop/Overdub implementation////////////////////////////
    		//if you aren't muted, add the loop to the live signal
    		//if(!muteLoop)
    			out[i] += (Uint16)(0.2 * (float)loop1[loopIndex]) + (Uint16)(0.2 * (float)loop2[loopIndex]);

    		//if you are recording, OVERWRITE the live to the loop
    		if (recording1)
    			loop1[loopIndex] = (Uint16)(0.5 * (float) live) + (Uint16)(0.5 * (float)loop1[loopIndex]);
    		if (recording2)
    			loop2[loopIndex] = (Uint16)(0.5 * (float) live) + (Uint16)(0.5 * (float)loop2[loopIndex]);

    		//loopIndex and check for overflow
    		loopIndex++;
    		if(loopIndex >= loopLength)
    			loopIndex = 0;

    		//only increment tap variable if less than 225000
    		if(tapsIndex < 225000)
    			tapsIndex++;//increment the number of samples between taps
	}
}

/**************************************************************
* Name:     initSampleRateTimer
* Purpose:  Initialize the timer to run at the sample rate
**************************************************************/
void initSampleRateTimer(void){
    EALLOW;
    InitCpuTimers();//config timers
    ConfigCpuTimer( &CpuTimer1 , 150 , 22);//set the sample rate
    PieVectTable.XINT13 = sampleRate_ISR;//map the ISR
    PieCtrlRegs.PIECTRL.bit.ENPIE = 1;
    IER |= M_INT13;
    EINT;
    StartCpuTimer1();
}

/**************************************************************
* Name:     init_ButtonInterrupt
* Purpose:  Initialize the buttons used for loop recording
**************************************************************/
void init_ButtonInterrupt(void) {
    // led to check
    EALLOW;
    //GpioCtrlRegs.GPAMUX1.bit.GPIO7 = 0;
    //GpioCtrlRegs.GPADIR.bit.GPIO7 = 1;
    //GPIO10 as a switch

    // B U T T O N 1
    GpioCtrlRegs.GPAMUX2.bit.GPIO21 = 0; // GPIO 21
    GpioCtrlRegs.GPADIR.bit.GPIO21 = 0; // input
    GpioCtrlRegs.GPAQSEL2.bit.GPIO21 = 0; // Xint1 Synch to SYSCLKOUT only
    GpioIntRegs.GPIOXINT1SEL.bit.GPIOSEL = 21; // Xint1 is GPIO21
    XIntruptRegs.XINT1CR.bit.POLARITY = 1 ; // XINT1 Polarity configuration
    XIntruptRegs.XINT1CR.bit.ENABLE = 1; //enable Xint1
    PieVectTable.XINT1 = &loop1Click_ISR;
    PieCtrlRegs.PIECTRL.bit.ENPIE = 1;          // Enable the PIE block
    PieCtrlRegs.PIEIER1.bit.INTx4 = 1;          // Enable PIE Group 1 INT4

    // B U T T O N 2
    GpioCtrlRegs.GPAMUX2.bit.GPIO24 = 0; // GPIO 24
    GpioCtrlRegs.GPADIR.bit.GPIO24 = 0; // input
    GpioCtrlRegs.GPAQSEL2.bit.GPIO24 = 0; // Xint1 Synch to SYSCLKOUT only
    GpioIntRegs.GPIOXINT2SEL.bit.GPIOSEL = 24;
    XIntruptRegs.XINT2CR.bit.POLARITY = 1 ; // XINT2 Polarity configuration
    XIntruptRegs.XINT2CR.bit.ENABLE = 1; //enable Xint2
    PieVectTable.XINT2 = taps_ISR;//&loop2Click_ISR;
    PieCtrlRegs.PIEIER1.bit.INTx5 = 1;

    // T A P S	B U T T O N
//	GpioCtrlRegs.GPAMUX2.bit.GPIO24 = 0; // GPIO 24
//	GpioCtrlRegs.GPADIR.bit.GPIO24 = 0; // input
//	GpioCtrlRegs.GPAQSEL2.bit.GPIO24 = 0; // Xint1 Synch to SYSCLKOUT only
//	GpioIntRegs.GPIOXINT3SEL.bit.GPIOSEL = 24;
//	XIntruptRegs.XINT3CR.bit.POLARITY = 1 ; // XINT3 Polarity configuration
//	XIntruptRegs.XINT3CR.bit.ENABLE = 1; //enable Xint3
//	PieVectTable.XINT3 = &taps_ISR;
//	PieCtrlRegs.PIEIER1.bit.INTx6 = 1;

    //GPIO10 is XINT1
    IER |= M_INT1; // Enable CPU int1
    IER |= M_INT2; // Enable CPU int2
    EINT; // Enable Global Interrupts
    EDIS;
}

/**************************************************************
* Name:     Init_DMA
* Purpose:  Initialize DMA ch1, ch2, ch3 to transfer sound data throughout program
**************************************************************/
void Init_DMA(void){
    EALLOW;
    SysCtrlRegs.HISPCP.all = 0;                 //Hi-Speed Clk = SYSCLK
    SysCtrlRegs.PCLKCR3.bit.DMAENCLK = 1;       // DMA Clock
    DMAInitialize();
    //setup DMA1 (triggered on Timer1 overflow)
    //garbage to MCBSPB
    DMACH1AddrConfig( (volatile Uint16*) &McbspbRegs.DXR2 , (volatile Uint16*) &garbage + 1 );
    DMACH1BurstConfig( 1, -1, 1 ); //send 2 words per burst, decrement source address, increment destination address by 1 (32 bits little endian)
    DMACH1TransferConfig( 0, 0, 0 );
    DMACH1WrapConfig( 0, 0, 0, 0 );
    DMACH1ModeConfig( DMA_TINT1, 1, 0, 1, 0, 0, 0, 0, 0, 0 );
    //setup DMA2 (triggered on McBSPB RRDY flag)
    //MCBSPB to pong_1
    DMACH2AddrConfig( (volatile Uint16*) &pong_1[0] + 1 , (volatile Uint16*) &McbspbRegs.DRR2);
    DMACH2BurstConfig( 1, 1, -1 ); //send 2 words per burst, increment source address, decrement destination address by 1 (32 bits little endian)
    DMACH2TransferConfig( BUFFER_SIZE - 1, 0, 3 );  //get N = BUFFER_SIZE ADC values, wrap to same McBSP values, increment Destination by 3
    DMACH2WrapConfig( 0, 0, BUFFER_SIZE - 1, 0 );
    DMACH2ModeConfig(DMA_MREVTB, 1, 0, 1, 0, 0, 0, 0, 1, 1 );
    //setup DMA3 (triggered on Timer1 overflow)
    //pong_2 to MCBSPA
    DMACH3AddrConfig( (volatile Uint16*) &McbspaRegs.DXR1, (volatile Uint16*) &pong_2[0] );
    DMACH3BurstConfig( 0, 0, 0 );
    DMACH3TransferConfig( BUFFER_SIZE - 1, 1, 0 );
    DMACH3WrapConfig( 0xFFFF, 0, 0, 0 );
    DMACH3ModeConfig( DMA_TINT1, 1, 0, 1, 0, 0, 0, 0, 1, 1 );
    /*
     *  assign the interrupt levels to the CPU interrupts
     */
    EALLOW;
    PieCtrlRegs.PIEIER7.bit.INTx1 = 1; //enable McBSPB RRDY int
    PieCtrlRegs.PIEIER7.bit.INTx2 = 1; //enable DMACH2 int
    PieCtrlRegs.PIEIER7.bit.INTx3 = 1; //enable DMACH2 int
    PieCtrlRegs.PIEIER6.bit.INTx3 = 1; //enable McBSPB RRDY int
    PieCtrlRegs.PIEIFR6.bit.INTx3 = 1; //enable return pie
    PieVectTable.MRINTB  = MRB_ISR;
    PieVectTable.DINTCH2 = DMAch2_ISR;
    PieVectTable.DINTCH3 = DMAch3_ISR;
    IER |= M_INT6;   //enable McBSPB RRDY interrupt
    IER |= M_INT7;  //enable "buffer full" interrupt DMACH2
    //Start the DMA channels
    StartDMACH1();
    StartDMACH2();
    StartDMACH3();
}
