/**********************************NOTES****************************************
Note:
- Oversampling (average values between each sample)?
- Reduce software dependency in loop (use hardware to check)
if loop ended (zero flag, DMA, etc.)

TO DO:
- Split into header files and working .c files (USART, DMA, etc)
    - github that stuff
- USART
    - test in context of full file
    - test receive method
- USART off of android table
- DMA
   	- get timer code working properly (check if enter interrupt)
    - integrate example code into project: get RAM to RAM transfer working
    		- fit to meet our needs
    - get ch1 and ch3 working
    - get ch2 working
    - compare 32bit and 16bit transfer modes
- Buffer system works together (WIRE)
- EFFECTS
*******************************************************************************/


/*********************************INCLUDES*************************************/
#include <DSP28x_Project.h>


/*********************************DEFINES**************************************/
#define pingPongBufferLength 16
#define sampleRate 44100


/*********************************GLOBALS**************************************/
/*
* make 2 ping-pong buffers
* flow is as follows:
* ADC (MCBSP) -- > PING of buffer1 on the DMA channel triggered by a ready interrupt
*/
Uint16 input1[pingPongBufferLength] = {0};
Uint16 output1[pingPongBufferLength] = {0};
Uint16 input2[pingPongBufferLength] = {0};
Uint16 output2[pingPongBufferLength] = {0};

/*
* declare four pointers to switch accordingly, a read and a write for each buffer
*/
Uint16* pingData = input1;
Uint32 pingDataIndex = 0;
Uint16* pingCalc = input2;
Uint32 pingCalcIndex = 0;
Uint16* pongData = output1;
Uint32 pongDataIndex = 0;
Uint16* pongCalc = output2;
Uint32 pongCalcIndex = 0;

Uint16 garbage[2] = {69, 420};//for mcbsp, dawg
Uint16 test1[10] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
Uint16 test2[10] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

Uint16 rxBufferIndex = 0;//rx buffer index
char rxBuffer[100];//rx buffer test for USART


/********************************PROTOTYPES************************************/
void interruptInit();
void DMAInit();
void SCIInit();
void initBuffers();
void GPIOInit();
void timerInit();
void outChar(char c);
void outWord(char* word);


/********************************INTERRUPTS************************************/
__interrupt void sample_DINTCH1_ISR(){


	// Remove after inserting ISR Code
	__asm ("      ESTOP0");


    //Increment sampling pointer
    pingDataIndex++;
    if(pingDataIndex >= pingPongBufferLength)
        pingDataIndex = 0;

    //If at end of calculation, swap
    PieCtrlRegs.PIEACK.all = PIEACK_GROUP7;
}

__interrupt void swap_function(){

    //Switch pointers if finished computation in ______ array
    EALLOW;
	PieCtrlRegs.PIEACK.bit.ACK7 = 1;

	// need to be able to swap the pingData with the pongData
	// and the pingCalc with pongCalc

	Uint16* temp = pingData;
	pingData = pingCalc;
	pingCalc = temp;
	temp = pongData;
	pongData = pongCalc;
	pongCalc = temp;


	 DmaRegs.CH2.DST_ADDR_SHADOW = (Uint32) &pingData[1];
	 DmaRegs.CH3.SRC_ADDR_SHADOW = (Uint32) &pingCalc[0];

	 // TODO:
	 //toggle the main flag for interrupts?? (refer to manual)
}

__interrupt void inChar_ISR(){
	ScibRegs.SCICTL1.bit.SLEEP = 0;
	while(ScibRegs.SCIRXST.bit.RXRDY != 1);//wait until ready

	rxBuffer[rxBufferIndex] = ScibRegs.SCIRXBUF.bit.RXDT;
	rxBufferIndex++;

	PieCtrlRegs.PIEACK.all|=0x100; //a wild interrupt appeared!
}


/***********************************INITS**************************************/
void initBuffers(){
    for(int i = 0; i < pingPongBufferLength; i++)
	    input1[i] = 0;
    for(int i = 0; i < pingPongBufferLength; i++)
    		input2[i] = 0;
    for(int i = 0; i < pingPongBufferLength; i++)
    		output1[i] = 0;
    for(int i = 0; i < pingPongBufferLength; i++)
    		output2[i] = 0;
}

void GPIOInit(){//LEDS/Switches
    GpioCtrlRegs.GPAMUX1.all = 0x0000;
	GpioCtrlRegs.GPADIR.all  = 0xFF;
	GpioCtrlRegs.GPAPUD.all = 0xFF;
}

void interruptInit(){
	EALLOW;

	//General reset
	DINT;
	InitPieCtrl();
	IER = 0x0000;
	IFR = 0x0000;
	InitPieVectTable();

	//Sampling Timer Interrupt
	PieVectTable.DINTCH1 = &sample_DINTCH1_ISR;//map the ISR
	IER = M_INT7;//Enable INT7 (7.1 DMA Ch1)

	//Rx Interrupt
	PieVectTable.SCIRXINTB = &inChar_ISR;
	PieCtrlRegs.PIECTRL.bit.ENPIE = 1;//enable vector fetching
	PieCtrlRegs.PIEIER9.bit.INTx3 |= 1;//??     // PIE Group 9, INT3
	IER = 0x100;	// Enable CPU INT

	//Set up array full interrupt

    EnableInterrupts();
    CpuTimer1Regs.TCR.bit.TSS  = 1;       //Stop Timer1 for now
	EDIS;
}

void timerInit(){
	EALLOW;
	//Sampling timer init
	InitCpuTimers();
	ConfigCpuTimer(&CpuTimer1, 150, 22.67);
	CpuTimer1Regs.TCR.bit.TSS  = 1;       //Stop Timer0 for now

	//According to example file
	// load low value so we can start the DMA quickly
//	CpuTimer1Regs.TIM.half.LSW = 512;	//low 16 bits of the current count
//	CpuTimer1Regs.TCR.bit.SOFT = 1;      //free run
//	CpuTimer1Regs.TCR.bit.FREE = 1;
//	CpuTimer1Regs.TCR.bit.TIE  = 1;      //CPU timer1 interrupt signal

	EDIS;
}

void DMAInit(){
    DMAInitialize();

    /***********************do this**********************/
    //Test using 2 different interrupts for Ch1 and Ch3 or using same interrupt

//	//DMA CH1: kick start DMA?
//	DMACH1AddrConfig(&McbspbRegs.DRR2.all, &garbage[0]);
//	DMACH1BurstConfig(1, 1, 1);//1 word length transfer, src+1, dest+1
//	DMACH1TransferConfig(0, -1, -1);//burst/transfer, no dest+x
//    DmaRegs.CH1.MODE.bit.PERINTSEL = 12;  //INT13, timer 1
//    DmaRegs.CH1.MODE.bit.PERINTE = 1; // enable for peripheral interrupts
//    DmaRegs.CH1.MODE.bit.CONTINUOUS = 1; // transfer is always continous
//    DmaRegs.CH1.MODE.bit.DATASIZE = 0; // data is 16-bit size
//
//    /***********************redo this**********************/
//	//DMA CH2: ISR
//	DMACH2AddrConfig(&pingData[0], &McbspbRegs.DRR2.all);
//	DMACH2BurstConfig(1, 1, 1);//1 word length transfer, src+1, dest+1
//	DMACH2TransferConfig(pingPongBufferLength, 0, 0);//burst/transfer, no dest+x
//	DMACH2WrapConfig(0, 0, pingPongBufferLength, -pingPongBufferLength);//wrap after _______ bursts, reset
//	DMACH2ModeConfig(______, PERINT_DISABLE, ONESHOT_DISABLE,
//			CONT_ENABLE, SYNC_DISABLE, SYNC_SRC, OVRFLOW_DISABLE,
//			SIXTEEN_BIT, CHINT_END, CHINT_ENABLE);
//
//
//	//DMA CH3: ARRAY TO DAC at sample rate
//	DMACH3AddrConfig(&McbspaRegs.DRR2.all, &pingCalc[0]);
//	DMACH3BurstConfig(1, 1, 1);//1 word length transfer, src+1, dest+1
//	DMACH3TransferConfig(0, -1, -1);//burst/transfer, no dest+x
//    DmaRegs.CH3.MODE.bit.PERINTSEL = 12;  //INT13, timer 1
//    DmaRegs.CH3.MODE.bit.PERINTE = 1; // enable for peripheral interrupts
//    DmaRegs.CH3.MODE.bit.CONTINUOUS = 1; // transfer is always continous
//    DmaRegs.CH3.MODE.bit.DATASIZE = 0; // data is 16-bit size


	//DMA CH4: Test
    DMACH4AddrConfig(&test1[0],&test2[0]);
    DMACH4BurstConfig(31,2,2);			// Will set up to use 32-bit datasize, pointers are based on 16-bit words
    DMACH4TransferConfig(31,2,2);		// so need to increment by 2 to grab the correct location
    DMACH4WrapConfig(0xFFFF,0,0xFFFF,0);
    DMACH4ModeConfig(DMA_TINT1,PERINT_ENABLE,ONESHOT_ENABLE,CONT_DISABLE,
                     SYNC_DISABLE,SYNC_SRC,OVRFLOW_DISABLE,THIRTYTWO_BIT,
                     CHINT_END,CHINT_ENABLE);

	//Start the 4 DMA channels
	//StartDMACH1();
    //StartDMACH2();
    //StartDMACH3();
    StartDMACH4();
}

void SCIInit(){//SCIB
	GpioCtrlRegs.GPAMUX2.bit.GPIO22 = 0x3;//GPIO 22 transfer, internal register
    GpioCtrlRegs.GPAMUX2.bit.GPIO23 = 0x3;//GPIO 23 receive, internal register

    SysCtrlRegs.PCLKCR0.bit.SCIBENCLK = 0x1;//Board is sending 37.5 mHz lowspeed clock to SCI

    ScibRegs.SCICCR.all = 0x7; //set 8 bits, 1 stop bit, no parity and idle line mode.
    ScibRegs.SCILBAUD = 0xE8; //Set Baud using Equation
    ScibRegs.SCIHBAUD = 0x01;

    ScibRegs.SCICTL1.bit.TXENA = 1;
    ScibRegs.SCICTL1.bit.RXENA = 1;
    ScibRegs.SCICTL1.bit.SWRESET = 1;


    SysCtrlRegs.LOSPCP.all = 0x1;
    DINT;
    ScibRegs.SCICTL2.bit.RXBKINTENA = 1;//enable sci receive interrupt
}


/***********************************FUNCTIONS**********************************/
void outChar(char c){
	while(ScibRegs.SCICTL2.bit.TXRDY != 1);//wait until ready
	ScibRegs.SCITXBUF = c;
}

void outWord(char* word){
	for(char c = *word; c; c= *(++word))
		outChar(c);
}


/************************************MAIN**************************************/
void main(){
    //Start up stuff
	EALLOW;
	DisableDog();
	InitPll(10, 3);
	initBuffers();
	GPIOInit();
	//Init MCBSP stuff*********************************************************
	interruptInit();//NOTE: INITIALIZE SWAP INTERRUPT**************************
    SCIInit();//must be checked************************************************
    DMAInit();//must be checked************************************************
    timerInit();

    //Enable global interrupts
    EINT;
	CpuTimer1Regs.TCR.bit.TSS = 0;    //start sample rate timer

	//SCI test
	outWord("enter a number ");

	while(1){

	}
}

