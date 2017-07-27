/* Noise Gate by Christopher Sfalanga */

#include <DSP28x_Project.h>
#include "timer.h"
#include "analogToDigitalConverter.h"
#include "digitalToAnalogConverter.h"
#include "audioCntrl.h"
#include "Sram.h"
#include <DSP2833x_Xintf.h>
#include <DSP2833x_XIntrupt.h>
#include <math.h>

float input, output;
unsigned int counter = 0;
unsigned int release_time_a = 44, release_time_b = 44100 //counting by samples
unsigned int threshold = 500;

int main () {

   //dsp housekeeping
   DisableDog();
   InitPll(10,3); // (10,4) 1/4 of fper
   analogToDigitalConverter_init();
   digitalToAnalogConverter_init();
   timer_init(150.0,20);
   enable_timer_interrupt();
   EALLOW;
   useGpio();


   EALLOW;

   while(1) {
       while(!interruptTimerFlag);

       input = analogToDigitalConverter_send(); //setting input variable to be ADC input, centered around zero

       if(GpioDataRegs.GPADAT.bit.GPIO10 == 0x01) {
           if(abs(input-0x7FFF) < threshold) {
               //make sure we don't overflow the counter
               if(counter < release_time_b) {
                   counter += 1;
               }
           }
           else {
               counter = 0;
           }

           if(counter >= release_time_a) {
               output = (input-0x7FFF)*(1.0-(float)counter/(float)release_time_b) + 0x7FFF;
           }
           else {
               output = input;
           }

           digitalToAnalogConverter_send((Uint16)(output));
       }
       else {
           digitalToAnalogConverter_send((Uint16)(input));
       }

       interruptTimerFlag = 1;
   }
}