/* Tremolo By: Christopher Sfalanga & Juan Valbuena & Matteus Martyr*/

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
Uint16 ison = 1;
unsigned int counter = 0;

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

	float M_PI = 3.1415926535897932384626433832795028;

	//sample-effect loop
    for(;1;++counter) {

    	EALLOW;
		while(!interruptTimerFlag);

		input = analogToDigitalConverter_send(); //setting input variable to be ADC input

		output = input*sinf((2*M_PI*counter)/11025);

		if(counter >= 11025) {
			counter = 0;
		}

		if (GpioDataRegs.GPADAT.bit.GPIO8 == 0x01){
			GpioDataRegs.GPADAT.bit.GPIO1 = 1;
			digitalToAnalogConverter_send((Uint16)output);
		}
		else {
			GpioDataRegs.GPADAT.bit.GPIO1 = 0;
			digitalToAnalogConverter_send((Uint16)input);
		}
    	interruptTimerFlag = 1;
    }
}