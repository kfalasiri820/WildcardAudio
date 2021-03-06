/*
 * SoundIn_SoundOutv3.c
 *
 *  Created on: May 31, 2017
 *      Author: Ish's Master Race PC
 */
#include <DSP28x_Project.h>
#include "OneToOneI2CDriver.h"
#include <DSP2833x_Xintf.h>
#include <DSP2833x_XIntrupt.h>
#include "timer.h"
#include "analogToDigitalConverter.h"
#include "digitalToAnalogConverter.h"
#include "DSP2833x_CpuTimers.h"
#include <DSP2833x_SysCtrl.h>
#include "audioCntrl.h"
#include "Sram.h"
#include "digitalToAnalogConverter.h"
#include "hammingWindow.h"
#include "USARTinit.h"
#include <DSP2833x_GlobalPrototypes.h>
#include <math.h>
#include <stdio.h>
#include <stdbool.h>


#pragma DATA_SECTION(loop1, ".memory")
#pragma DATA_SECTION( ping_1  , "DMARAML4" )
#pragma DATA_SECTION( pong_1  , "DMARAML4" )
#pragma DATA_SECTION( ping_2  , "DMARAML5" )
#pragma DATA_SECTION( pong_2  , "DMARAML5" )
#pragma DATA_SECTION( garbage , "DMARAML5" )

//function prototypes
//void init_ButtonInterrupt(void);
void Init_Timer1(void);
void Init_DMA(void);
// methodically guess sizes
#define SRAM_BUFFER 256000
#define BUFFER_SIZE 256
//global variables
Uint16 loop1[SRAM_BUFFER];
Uint32 loop1Index = 0;
Uint32 loopLength = SRAM_BUFFER;//start out at max value
Uint16 loop1Clicks = 0;
Uint16 recording = 0;
volatile Uint32 ping_1[BUFFER_SIZE] = {0};
volatile Uint32 pong_1[BUFFER_SIZE] = {0};
volatile Uint16 ping_2[BUFFER_SIZE] = {0};
volatile Uint16 pong_2[BUFFER_SIZE] = {0};
volatile Uint32 garbage= 0x7BADB015;
volatile Uint16 program_state = 0;
volatile Uint16 ready = 1;
volatile Uint16 count = 0;
volatile char rxBuffer[100];
Uint16 rxBufferIndex = 0;

//void USARTenable(){
//
//  //__interrupt void inCharISR();
//  //interruptInit();
//  timerInit();
//  startUSART();
//
//}

interrupt void Timer1_ISR(void)
{
    EALLOW;
    GpioDataRegs.GPADAT.bit.GPIO0 ^= 1;

}

__interrupt void xint1_isr(void)
{

	EALLOW;

	GpioDataRegs.GPATOGGLE.bit.GPIO7=1; // interrupt LED

	loop1Clicks++;

	recording ^= 1;

	PieCtrlRegs.PIEACK.all = PIEACK_GROUP1; //acknowledges the int

	EINT;
}

/*
 * McBSPB ready ISR: acknowledge the finshed block transfer of DMA
 */
interrupt void MRB_ISR (void)
{
    EALLOW;
    GpioDataRegs.GPADAT.bit.GPIO2 ^= 1;
    PieCtrlRegs.PIEACK.all = PIEACK_GROUP6;
}

interrupt void DMA_CH3 (void)
{
    EALLOW;
    PieCtrlRegs.PIEACK.all = PIEACK_GROUP7;
}

interrupt void DMA_CH2(void)
{
    while(ready)
    {

        if(program_state == 0)
        {
            DMACH2AddrConfig( (volatile Uint16*) &ping_1[0] + 1 , (volatile Uint16*) &McbspbRegs.DRR2.all );
            DMACH3AddrConfig( (volatile Uint16*) &McbspaRegs.DXR1 , (volatile Uint16*) &ping_2[0] );
        }
        else if(program_state == 1)
        {
            DMACH2AddrConfig( (volatile Uint16*) &pong_1[0] + 1 , (volatile Uint16*) &McbspbRegs.DRR2.all );
            DMACH3AddrConfig( (volatile Uint16*) &McbspaRegs.DXR1 , (volatile Uint16*) &pong_2[0] );
        }

        program_state ^= 1;

        EALLOW;
        GpioDataRegs.GPADAT.bit.GPIO1 ^= 1;
        PieCtrlRegs.PIEACK.all = PIEACK_GROUP7;
        ready = 0;

    }

}

int main(void){

    DisableDog();
    InitPll(10,3);
    InitSysCtrl();//wtf... check what clocks we actually need
    Sram_init();
    analogToDigitalConverter_init();
    digitalToAnalogConverter_init();
    useGpio();
    //startUSART();
    Init_DMA();
    Init_Timer1();
//    for (Uint32 var  = 0;  var  < SRAM_BUFFER; var++ )
//		loop1[var] = 0x0000;
    for (Uint32 var  = 0;  var  < 100; var++ )
		rxBuffer[var] = 0x0000;

    //init_ButtonInterrupt();
    //outWord("enter a number ");
    //try 2 if statements in the sampling interrupt

    Uint16 live = 0;
/*
 * try the single read and write in one loop instead of alternating
 */

    while(1)
    {
        while(!ready)
        {
            GpioDataRegs.GPADAT.bit.GPIO3 ^= 1;

            if(program_state == 0)
            {

                for(int i = 0; i < BUFFER_SIZE; i++)
                {
                	if(loop1Clicks == 1) {
                		loop1Index = 0;
                		loop1Clicks++;
                	}
                	if(loop1Clicks == 3)	{
                		loopLength = loop1Index;
                		//loop1Clicks++;
                	}

                	live = (Uint16)((ping_1[i] >> 2) & 0xFFFF);//define the live signal
                	ping_2[i] = (Uint16)(0.5 * (float) live);//always add the live signal to output
                	//if(!muteLoop)//if you aren't muted, add the loop to the live signal
                		ping_2[i] += (Uint16)(0.5 * (float)loop1[loop1Index]);
                	if (recording)//if you are recording, overwrite the live to the loop
						loop1[loop1Index] = live;
                	loop1Index++;

                	 if(loop1Index >= loopLength) {
					   loop1Index = 0;
				   }

                	//OLD STUFF
                	//ping_2[i] = (Uint16)((0.5*(float)live) + (0.5*(float)loop1[loop1Index]));

//                	if (GpioDataRegs.GPADAT.bit.GPIO21 == 1) {
//
//                		ping_2[i] = loop1[loop1Index];
//                	}
//                	else {
//                		ping_2[i] = live;
//
//                	}
                	//loop1Index++;
                }

            }
            else if(program_state == 1)
            {

                for(int i = 0; i < BUFFER_SIZE; i++)
                {
                	if(loop1Clicks == 1) {
						loop1Index = 0;
                		loop1Clicks++;
                	}

                	if(loop1Clicks == 3) {
						loopLength = loop1Index;
                		loop1Clicks++;
                	}

                	live = (Uint16)((pong_1[i] >> 2) & 0xFFFF);
                	pong_2[i] = (Uint16)(0.5 * (float) live);//always add the live signal to output
					//if(!muteLoop)//if you aren't muted, add the loop to the live signal
						pong_2[i] += (Uint16)(0.5 * (float)loop1[loop1Index]);
					if (recording)//if you are recording, overwrite the live to the loop
						loop1[loop1Index] = live;
					loop1Index++;

					 if(loop1Index >= loopLength) {
						 loop1Index = 0;
					          }
//                	//pong_2[i] = (Uint16)((0.5*(float)live) + (0.5*(float)loop1[loop1Index]));
//                	if (GpioDataRegs.GPADAT.bit.GPIO21 == 1) {
//						pong_2[i] = loop1[loop1Index];
//					}
//                	else {
//                		pong_2[i] = live;
//                	}
//                	loop1[loop1Index] = live;
//                	loop1Index++;
                }

            }
           ready = 1;
           GpioDataRegs.GPADAT.bit.GPIO3 ^= 1;
        }

    }

}

void Init_Timer1(void)
{
    EALLOW;
    InitCpuTimers();

    ConfigCpuTimer( &CpuTimer1 , 150 , 22);

    PieVectTable.XINT13 = Timer1_ISR;
    PieCtrlRegs.PIECTRL.bit.ENPIE = 1;

    IER |= M_INT13;

    EINT;

    StartCpuTimer1();
}

void init_ButtonInterrupt(void) {
	// led to check
	EALLOW;
	//GpioCtrlRegs.GPAMUX1.bit.GPIO7 = 0;
	//GpioCtrlRegs.GPADIR.bit.GPIO7 = 1;

	//GPIO10 as a switch

	GpioCtrlRegs.GPAMUX2.bit.GPIO21 = 0; // GPIO
	GpioCtrlRegs.GPADIR.bit.GPIO21 = 0; // input
	GpioCtrlRegs.GPAQSEL2.bit.GPIO21 = 0; // Xint1 Synch to SYSCLKOUT only

	//GPIO10 is XINT1


	GpioIntRegs.GPIOXINT1SEL.bit.GPIOSEL = 21; // Xint1 is GPIO21
	XIntruptRegs.XINT1CR.bit.POLARITY = 1 ; // XINT1 Polarity configuration
	XIntruptRegs.XINT1CR.bit.ENABLE = 1; //enable Xint1
	PieVectTable.XINT1 = &xint1_isr;
	PieCtrlRegs.PIECTRL.bit.ENPIE = 1;          // Enable the PIE block
	PieCtrlRegs.PIEIER1.bit.INTx4 = 1;          // Enable PIE Group 1 INT4
	IER |= M_INT1;                              // Enable CPU int1
	EINT;                                       // Enable Global Interrupts

	EDIS;
}

void Init_DMA(void)
{
    EALLOW;

    SysCtrlRegs.HISPCP.all = 0;                 //Hi-Speed Clk = SYSCLK
    SysCtrlRegs.PCLKCR3.bit.DMAENCLK = 1;       // DMA Clock

    DMAInitialize();

    //setup DMA1 (triggered on Timer1 overflow)
    DMACH1AddrConfig( (volatile Uint16*) &McbspbRegs.DXR2 , (volatile Uint16*) &garbage + 1 );
    DMACH1BurstConfig( 1, -1, 1 ); //send 2 words per burst, decrement source address, increment destination address by 1 (32 bits little endian)
    DMACH1TransferConfig( 0, 0, 0 );
    DMACH1WrapConfig( 0, 0, 0, 0 );
    DMACH1ModeConfig( DMA_TINT1, 1, 0, 1, 0, 0, 0, 0, 0, 0 );

    //setup DMA2 (triggered on McBSPB RRDY flag)
    DMACH2AddrConfig( (volatile Uint16*) &pong_1[0] + 1 , (volatile Uint16*) &McbspbRegs.DRR2);
    DMACH2BurstConfig( 1, 1, -1 ); //send 2 words per burst, increment source address, decrement destination address by 1 (32 bits little endian)
    DMACH2TransferConfig( BUFFER_SIZE - 1, 0, 3 );  //get N = BUFFER_SIZE ADC values, wrap to same McBSP values, increment Destination by 3
    DMACH2WrapConfig( 0, 0, BUFFER_SIZE - 1, 0 );
    DMACH2ModeConfig(DMA_MREVTB, 1, 0, 1, 0, 0, 0, 0, 1, 1 );

    //setup DMA3 (triggered on Timer1 overflow)
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
    PieVectTable.DINTCH2 = DMA_CH2;
    PieVectTable.DINTCH3 = DMA_CH3;

    IER |= M_INT6;   //enable McBSPB RRDY interrupt
    IER |= M_INT7;  //enable "buffer full" interrupt DMACH2

    StartDMACH1();
    StartDMACH2();
    StartDMACH3();
}

