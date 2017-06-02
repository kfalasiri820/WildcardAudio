/* WorkingUSART.c */

/*********************************INCLUDES************************************/
#include <DSP28x_Project.h>
#include <USARTinit.h>

/**********************************USART**************************************/
void USARTenable(){

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
  __interrupt void inCharISR();

  void interruptInit();
  void timerInit();
  void outChar(char c);
  void outWord(char* word);
  void startUSART();

}

void USARTtest(){

  outWord("enter a number ");

  for(;;)
  {
     //outWord("send nudes \n");
    //outWord("enter a number ");
    rxBuffer[i] = inChar();
    i++;
  }
}
