/*
 * phaserBoy.c
 *
 *  Created on: Jul 25, 2017
 *      Author: Matt Marder
 */

#include <DSP28x_Project.h>
#include <math.h>

extern float phaserRate;   // Can be manually varied between 11,000 <--> 44,100 "Rate"
extern float phaserCenterFreq; // Can be manually varied between 1000 - 3000 "Tone"
extern float phaserRange;      // Fixed at 3200


float M_PI = 3.141592653;
float fc;
float fs = 44100.0;
float c=0;
float d=0;
float sample_minusTwo=0;
float sample_minusOne=0;
float filterOut=0;
float filterOut_minusTwo=0;
float filterOut_minusOne=0;

float phaserCounter = 0;



Uint16 phaser(float sample) {
    phaserCounter++;
	if(phaserCounter > phaserRate) {
		phaserCounter = 0;
	}
	float phaserLowFreq = phaserCenterFreq - (phaserRange/2);
	float phaserHighFreq = phaserCenterFreq + (phaserRange/2);
	fc = ( phaserLowFreq + phaserHighFreq ) / 2.0 + ( phaserHighFreq - phaserLowFreq ) * sinf (2*M_PI*phaserCounter/phaserRate)/2.0; //LFO

	c = (tanf(2 * M_PI * (fc/fs)) - 1)/(tanf(2 * M_PI * (fc/fs)) + 1);
	d = -1 * cosf(2 * M_PI * (fc/fs));

	//2 apply discrete time filter formula
	filterOut = (-1*c*sample+d*(1-c)*sample_minusOne+sample_minusTwo-d*(1-c)*filterOut_minusOne+c*filterOut_minusTwo);

	//3 set recursive feedback
	sample_minusTwo = sample_minusOne;
	sample_minusOne = sample;
	filterOut_minusTwo = filterOut_minusOne;
	filterOut_minusOne = filterOut;
	// 4 output sample + filterOut

	return ((Uint16)(0.6*sample +  0.4*filterOut)); // Output using the notch filter
}
