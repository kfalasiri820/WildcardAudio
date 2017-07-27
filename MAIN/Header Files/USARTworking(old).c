//https://docs.google.com/document/d/14jbxx4Dkm-FK4I4QC5oyY_XEVexosU3Q-Oc-GAnnIzM/edit?usp=sharing
/*
Test:
- USART
- DMA in
- DMA out
- Buffer system works together (WIRE)
- EFFECTS
*/


/*********************************INCLUDES************************************/
#include <DSP28x_Project.h>


/*********************************DEFINES*************************************/
#define pingPongBufferLength 16
#define sampleRate 44100


/*********************************GLOBALS*************************************/
/*
* make 2 ping-pong buffers
* flow is as follows:
* ADC (MCBSP) -- > PING of buffer1 on the DMA channel triggered by a ready interrupt
*/
Uint16 input1[pingPongBufferLength] = {0};
Uint16 output1[pingPongBufferLength] = {0};
Uint16 input2[pingPongBufferLength] = {0};
Uint16 output2[pingPongBufferLength] = {0};

Uint16 garbage[2] = {69, 420};

Uint16 test1[10] = {1, 2, 3, 4, 5, 245, 6, 7, 8, 10};
Uint16 test2[10] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

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

Uint16 i = 0;
char rxBuffer[100];

//bool swap = true;


/********************************PROTOTYPES***********************************/
//void interruptInit();
//void DMAInit();
//void SCIInit();
//void initBuffers();
//void GPIOInit();


/********************************INTERRUPTS***********************************/
__interrupt void sample(){

    //Increment sampling pointer
    pingDataIndex++;
    if(pingDataIndex >= pingPongBufferLength)
        pingDataIndex = 0;

    //If at end of calculation, swap
}

__interrupt void swap_function(){

    //Switch pointers if finished computation in ______ array
    EALLOW;
	PieCtrlRegs.PIEACK.bit.ACK7 = 1;

	// need to be able to swap the pingData with the pongData
	// and the pingCalc with pongCalc

//	if (swap) {
//		swap = false;
//
//		pingData = output1;
//		pongData = input1;
//		pingCalc = output2;
//		pongCalc = input2;
//	}
//	else {
//		swap = true;
//
//		pingData = input1;
//		pongData = output1;
//		pingCalc = input2;
//		pongCalc = output2;
//	}

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


/***********************************INITS*************************************/
void initBuffers(){
    for(Uint32 i = 0; i < pingPongBufferLength; i++)
	    input1[i] = 0;
    for(Uint32 i = 0; i < pingPongBufferLength; i++)
    	input2[i] = 0;
    for(Uint32 i = 0; i < pingPongBufferLength; i++)
    	output1[i] = 0;
    for(Uint32 i = 0; i < pingPongBufferLength; i++)
    	output2[i] = 0;
}

void GPIOInit(){//LEDS/Switches
    GpioCtrlRegs.GPAMUX1.all = 0x0000;
	GpioCtrlRegs.GPADIR.all  = 0xFF;
	GpioCtrlRegs.GPAPUD.all = 0xFF;
}

void interruptInit(){
	//Sampling interrupt init
	EALLOW;
	PieVectTable.XINT13 = sample;//interrupt
	IER |= M_INT13;
	PieCtrlRegs.PIECTRL.all |= 0x01;

	//Set up array full interrupt

	EDIS;
}

void timerInit(){
	EALLOW;
	//Sampling timer init
	InitCpuTimers();
	ConfigCpuTimer(&CpuTimer1, 150, 22.67);
	EDIS;
}

void DMAInit(){
    DMAInitialize();//NOTE: 2^16 is max for one full wrap

//	//DMA CH1: kick start DMA?
//	DMACH1AddrConfig(&McbspbRegs.DRR2.all, garbage );
//	DMACH1BurstConfig(1, 0, 1);//1 word length transfer, src+0, dest+1
//	DMACH1TransferConfig(2, 0, 0);//burst/transfer, no dest+x
//	DMACH1WrapConfig(0, 0, 2, -2);//wrap after _______ bursts, reset
//	//using timer 1 as interrupt source, CHINT????
//	DMACH1ModeConfig(12, PERINT_ENABLE, ONESHOT_DISABLE,
//			CONT_ENABLE, SYNC_DISABLE, SYNC_SRC, OVRFLOW_DISABLE,
//			SIXTEEN_BIT, CHINT_END, CHINT_ENABLE);
//
//	//DMA CH2: ISR
//	DMACH2AddrConfig(pingData, &McbspbRegs.DRR2.all);
//	DMACH2BurstConfig(1, 0, 1);//1 word length transfer, src+0, dest+1
//	DMACH2TransferConfig(pingPongBufferLength, 0, 0);//burst/transfer, no dest+x
//	DMACH2WrapConfig(0, 0, pingPongBufferLength, -pingPongBufferLength);//wrap after _______ bursts, reset
//	//using timer 1 as interrupt source, CHINT????
//	DMACH2ModeConfig(DMA_XINT13, PERINT_ENABLE, ONESHOT_DISABLE,
//			CONT_ENABLE, SYNC_DISABLE, SYNC_SRC, OVRFLOW_DISABLE,
//			SIXTEEN_BIT, CHINT_END, CHINT_ENABLE);
//
//
//	//DMA CH3: ARRAY TO DAC at sample rate
//	DMACH3AddrConfig(&McbspaRegs.DRR2.all, pingCalc);
//	DMACH3BurstConfig(1, 1, 0);//1 word length transfer, src+1, dest+0
//	DMACH3TransferConfig(pingPongBufferLength, 0, 0);//burst/transfer, no dest+x
//	DMACH3WrapConfig(pingPongBufferLength, -pingPongBufferLength, 0, 0);//wrap after _______ bursts, reset
//	//using timer 1 as interrupt source, CHINT????
//	DMACH3ModeConfig(12, PERINT_ENABLE, ONESHOT_DISABLE,
//			CONT_ENABLE, SYNC_DISABLE, SYNC_SRC, OVRFLOW_DISABLE,
//			SIXTEEN_BIT, CHINT_END, CHINT_ENABLE);
	
	//DMA CH4: Test
	DMACH4AddrConfig(&(test2[0]), &(test1[0]));
	DMACH4BurstConfig(0, 1, 1);//1 word length transfer, src+1, dest+1
	DMACH4TransferConfig(10, -10, -10);//burst/transfer, no dest+x
//    DmaRegs.CH4.MODE.bit.PERINTSEL = 12;  //INT13, timer 1
//    DmaRegs.CH4.MODE.bit.PERINTE = 1; // enable for peripheral interrupts
//    DmaRegs.CH4.MODE.bit.CONTINUOUS = 1; // transfer is always continous
//    DmaRegs.CH4.MODE.bit.DATASIZE = 0; // data is 16-bit size
	DMACH4ModeConfig(12, PERINT_ENABLE, ONESHOT_DISABLE,
				CONT_ENABLE, SYNC_DISABLE, SYNC_SRC, OVRFLOW_DISABLE,
				SIXTEEN_BIT, CHINT_END, CHINT_ENABLE);

	//Start the 3 DMA channels
//	StartDMACH1();
//    StartDMACH2();
//    StartDMACH3();

}

void SCIInit(){//SCIB
	ScibRegs.SCICTL1.bit.SWRESET = 0;//software reset

	InitScibGpio();

	//low speed clock to SCI, 150 mhz
	SysCtrlRegs.PCLKCR0.bit.SCIBENCLK = 0x1;

	ScibRegs.SCICCR.all = 0x7; //8 bits, 1 stop bit, no parity and idle

	//Baud rate
	ScibRegs.SCILBAUD = 0xA0; //Arun's number:
	ScibRegs.SCIHBAUD = 0x07;

	ScibRegs.SCICTL1.bit.TXENA = 1;//enable transfer
	ScibRegs.SCICTL1.bit.RXENA = 1;//receive transfer
	ScibRegs.SCICTL1.bit.SWRESET = 1;//software reset

	SysCtrlRegs.LOSPCP.all = 0x1;//prescaler
}


/***********************************FUNCTIONS*********************************/
void outChar(char c){
	while(ScibRegs.SCICTL2.bit.TXRDY != 1);//wait until ready
	ScibRegs.SCITXBUF = c;
}

__interrupt void inCharISR(){
	char c;
	ScibRegs.SCICTL1.bit.SLEEP = 0;
	while(ScibRegs.SCIRXST.bit.RXRDY != 1);//wait until ready

	rxBuffer[i] = ScibRegs.SCIRXBUF.bit.RXDT;
    i++;

	PieCtrlRegs.PIEACK.all|=0x100; //a wild interrupt appeared!

}

void outWord(char* word){
	for(char c = *word; c; c= *(++word))
		outChar(c);
}


/************************************MAIN**************************************/
void main(){
    InitSysCtrl();
    EALLOW;

    //SCITXDB - SCI-B transmit (O)
     //SCIRXDB - SCI-B receive (I/O)

    GpioCtrlRegs.GPAMUX2.bit.GPIO22 = 0x3;
    GpioCtrlRegs.GPAMUX2.bit.GPIO23 = 0x3;

    SysCtrlRegs.PCLKCR0.bit.SCIBENCLK = 0x1;

    ScibRegs.SCICCR.all = 0x7; //set 8 bits, 1 stop bit, no parity and idle line mode.
    ScibRegs.SCILBAUD = 0xE7; //Set Baud using Equation
    ScibRegs.SCIHBAUD = 0x01;

    ScibRegs.SCICTL1.bit.TXENA = 1;
    ScibRegs.SCICTL1.bit.RXENA = 1;
    ScibRegs.SCICTL1.bit.SWRESET = 1;

    SysCtrlRegs.LOSPCP.all = 0x1;

    /* copied */

    DINT;

    	ScibRegs.SCICTL2.bit.RXBKINTENA = 1;

    	InitPieCtrl();

    	IER = 0x0000;
    	IFR = 0x0000;

    	InitPieVectTable();

    	EALLOW;
    	PieVectTable.SCIRXINTB = &inCharISR;

    	//enable PIE
    	PieCtrlRegs.PIECTRL.bit.ENPIE = 1;

    	PieCtrlRegs.PIEIER9.bit.INTx3=1;     // PIE Group 9, INT3
    	IER = 0x100;	// Enable CPU INT
    	EINT;

    /* from alex whatever */

    outWord("enter a number ");

    for(;;)
    {
       //outWord("send nudes \n");
    	//outWord("enter a number ");
    	//rxBuffer[i] = inChar();
    	//i++;
    }
}
