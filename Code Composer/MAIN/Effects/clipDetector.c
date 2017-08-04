/*
 * ClipDetector.c
 *
 *  Created on: Aug 1, 2017
 *      Author: JungFu Jenny
 */

/*********************************INCLUDES************************************/
#include <DSP28x_Project.h>
#include <math.h>

/********************************** functions **************************************/
Uint16 ClipDetector(Uint16 input) {
	
	if (input >= 0xFFF0 || input <= 0x00F0){//||(output == 0x0000))
	   GpioDataRegs.GPBDAT.bit.GPIO57 = 1;//NOTE: must change these GPIOs to PCB LEDS
	}
	else {
		GpioDataRegs.GPBDAT.bit.GPIO57 = 0;//NOTE: must change these GPIOs to PCB LEDS
	}
	return input;
}


