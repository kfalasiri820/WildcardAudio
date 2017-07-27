/* Squares By: Christopher Sfalanga & Juan Valbuena */

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

	//sample-effect loop
    while(1) {

    	EALLOW;
		while(!interruptTimerFlag);

		//subtract 0x7FFF to center input at 0
		input = (analogToDigitalConverter_send()-0x7FFF); //setting input variable to be ADC input

		output = 0x7FFF*input/abs(input);

		if (GpioDataRegs.GPADAT.bit.GPIO8 == 0x01){
			GpioDataRegs.GPADAT.bit.GPIO1 = 1;
			digitalToAnalogConverter_send((Uint16)output + 0x7FFF);
		}
		else {
			GpioDataRegs.GPADAT.bit.GPIO1 = 0;
			digitalToAnalogConverter_send((Uint16)input + 0x7FFF);
		}
    	interruptTimerFlag = 1;
    }
}
