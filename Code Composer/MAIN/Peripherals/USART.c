#include <DSP28x_Project.h>
#include <math.h>

#define NUMBER_OF_EFFECTS 18

extern volatile char rxBuffer[100];
extern Uint16 rxBufferIndex;
extern Uint16 main_volume;
extern Uint16 effects[NUMBER_OF_EFFECTS];
extern Uint16 effectSelected;

/***********************************INTERRUPTS********************************/
__interrupt void inCharISR(){
	ScibRegs.SCICTL1.bit.SLEEP = 0;
	while(ScibRegs.SCIRXST.bit.RXRDY != 1);//wait until ready

	//Do code here
	rxBuffer[rxBufferIndex] = (char) ScibRegs.SCIRXBUF.bit.RXDT;
	main_volume = (Uint16) rxBuffer[rxBufferIndex];
	rxBufferIndex++;



	/*************************UPDATING EFFECTS PARAMETERS***********************/
	//NOT YET TESTED!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	//Read the buffer
	char input = (char) ScibRegs.SCIRXBUF.bit.RXDT;
	Uint16 effectID = (Uint16)(input & 0b00011111);

	//SELECTED
	if((input & 0b01000000) >> 7){//If effect selected
		effects[effectID-1] |= 0x4000; //(0100)000
		effectSelected = effectID;
	}
	else//If effect is not selected
		effects[effectID-1] &= 0xBFFF; //(1011)FFF

	//ON/OFF
	if((input & 0b00100000) >> 6){//if effect is on
		effects[effectID-1] |= 0x8000;//turn effect on

		//Read new parameter values
		Uint16 param1 = 0; //ADC READ IN;
		Uint16 param2 = 0; //ADC READ IN;
		Uint16 param3 = 0; //ADC READ IN;

		//Update parameters in effects Array
		effects[effectID-1] |=
				(Uint32)(
					(Uint32)(param3 & 0xFFFF) &
					((Uint32)(param2 & 0xFFFF) << 8) &
					((Uint32)(param1 & 0xFFFF) << 16)
				);
	}
	else//if effect is off
		effects[effectID-1] &= 0x7FFF;//turn effect off





	PieCtrlRegs.PIEACK.all|=0x100; //a wild interrupt appeared!
}

/***********************************INITS*************************************/

//void interruptInit(){
//  //Sampling interrupt init
//  EALLOW;
//  PieVectTable.XINT13 = sample;//interrupt
//  IER |= M_INT13;
//  PieCtrlRegs.PIECTRL.all |= 0x01;
//
//  //Set up array full interrupt
//
//  EDIS;
//}

void timerInit(){
  EALLOW;
  //Sampling timer init
  InitCpuTimers();
  ConfigCpuTimer(&CpuTimer1, 150, 22.67);
  EDIS;
}

/***********************************FUNCTIONS*********************************/
void outChar(char c){
  while(ScibRegs.SCICTL2.bit.TXRDY != 1);//wait until ready
  ScibRegs.SCITXBUF = c;
}

void outWord(char* word){
  for(char c = *word; c; c= *(++word))
    outChar(c);
}

void startUSART() {

	InitSysCtrl();
	EALLOW;

	//SCITXDB - SCI-B transmit (O)
	//SCIRXDB - SCI-B receive (I/O)

	GpioCtrlRegs.GPAMUX1.bit.GPIO9 = 0x2;
	GpioCtrlRegs.GPAMUX1.bit.GPIO11 = 0x2;

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
}
