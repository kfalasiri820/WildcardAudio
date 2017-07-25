/*
 * squares.c
 *
 *  Created on: Jul 24, 2017
 *      Author: Ish's Master Race PC
 */

#include <DSP28x_Project.h>
#include "timer.h"
#include "analogToDigitalConverter.h"
#include "digitalToAnalogConverter.h"
#include "audioCntrl.h"
#include "Sram.h"
#include <DSP2833x_Xintf.h>
#include <DSP2833x_XIntrupt.h>
#include <math.h>

Uint16 squares (Uint16 input_sample) {
	//subtract 0x7FFF from input_sample to center input at 0
	return 0.1 * (input_sample) + (0.9)*(32767.00 * ((float)input_sample-32767.00)/abs((float)input_sample-32767.00));
}


