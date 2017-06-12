/* WorkingUSART.c */

/*********************************INCLUDES************************************/
#include <DSP28x_Project.h>
#include <USARTinit.h>

/**********************************USART**************************************/
void USARTenable(){

  //__interrupt void inCharISR();
  interruptInit();
  timerInit();
  startUSART();

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
