#include "USARTinit.h"

/***********************************INTERRUPTS********************************/

__interrupt void inCharISR(){
  char c;
  ScibRegs.SCICTL1.bit.SLEEP = 0;
  while(ScibRegs.SCIRXST.bit.RXRDY != 1);//wait until ready

  rxBuffer[i] = ScibRegs.SCIRXBUF.bit.RXDT;
    i++;

  PieCtrlRegs.PIEACK.all|=0x100; //a wild interrupt appeared!

}

/***********************************INITS*************************************/

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
}
