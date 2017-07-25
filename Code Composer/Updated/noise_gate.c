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

/*
  parameters required:

  unsigned int noiseGate_sample_counter = 0,
               noiseGate_release_time_a = 44,
               noiseGate_release_time_b = 44100,
               noiseGate_threshold = 500;
*/

extern Uint16 noiseGate_sample_counter,
               noiseGate_release_time_a,
               noiseGate_release_time_b,
               noiseGate_threshold;

Uint16 noiseGate(Uint16 input_sample) {
    Uint16 output_sample;

	if(abs(input_sample-0x7FFF) < noiseGate_threshold) {
	  //make sure we don't overflow the counter
	  if(noiseGate_sample_counter < noiseGate_release_time_b) {
		noiseGate_sample_counter += 1;
	  }
	}
	else {
		noiseGate_sample_counter = 0;
	}

	if(noiseGate_sample_counter >= noiseGate_release_time_a) {
	  output_sample = ((float)input_sample-0x7FFF)*(1.0-(float)noiseGate_sample_counter/(float)noiseGate_release_time_b) + 0x7FFF;
	}
	else {
	  output_sample = input_sample;
	}

	return output_sample;
}
