#ifndef USART_H_
#define USART_H_

/*** p r o t o t y p e s ***/

extern volatile char rxBuffer[100];
extern Uint16 rxBufferIndex;
extern Uint16 main_volume;

__interrupt void inCharISR();

//void interruptInit();
void timerInit();
void outChar(char c);
void outWord(char* word);

void USARTinit();
void USARTtest();
void startUSART();

#endif
