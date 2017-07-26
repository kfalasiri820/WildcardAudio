/*
 * phaser.c
 *
 *  Created on: Jul 25, 2017
 *      Author: Matt Marder

*/
// phaser by Juan Valbuena and Matt Marder
// Phaser effect is implemented using a notch filter with center frequency controlled by a low frequency sinusoidal oscillator.

#include <DSP28x_Project.h>
#include <math.h>
/*
  parameters required: set these as global in the main
*/
extern float phaserQ;           // Can be manually varied between .3 <--> .8  "Resonance"
extern Uint16 phaserRate;   // Can be manually varied between 11,000 <--> 44,100 "Rate"
extern float phaserCenterFreq; // Can be manually varied between 1000 - 3000 "Tone"
extern float phaserRange;      // Fixed at 3200

float fc = 500; //cutoff frequency
float fs = 44100; //sampling frequency
float F1; //initializing coefficient
float Y_Ldelay = 0; //setting first number of low pass output delay to 0
float Y_Bdelay = 0; //setting first number of band pass output delay to 0
float inputdelay = 0;
float inputdelay2 = 0;
float y1bdelay = 0;
float y1bdelay2 = 0;
float y1ldelay = 0;
float ac;
float c;
float d;
float y1l;
float y1b;
//float gain = 10;
float Ho;
float Vo = 3.1235463467;
float Y_H = 0; //initializing high pass
float Y_L = 0; //initializing low pass
float Y_B = 0; //initializing bandpass
float Y_N = 0; //initializing notch

float Y_l;
float Y_h;
float notch = 0;
float M_PI = 3.141592653;
Uint16 phaserCounter = 0;



Uint16 phaser(Uint16 sample) {

    phaserCounter++;
        if(phaserCounter > phaserRate) {
            phaserCounter = 0;
        }
        float phaserLowFreq = phaserCenterFreq - (phaserRange/2);
        float phaserHighFreq = phaserCenterFreq + (phaserRange/2);
        fc = ( phaserLowFreq + phaserHighFreq ) / 2.0 + ( phaserHighFreq - phaserLowFreq ) * sinf (2*M_PI*(float)phaserCounter/phaserRate)/2.0; //LFO

        F1 = ((tanf(2 * M_PI * (fc/fs))));              //coefficient used in filter formulas dependent on the cut off frequency fc and sampling fs
        Ho = Vo - 1;
        ac = (tanf(2 * M_PI * (fc/fs)) - Vo)/(tanf(2 * M_PI * (fc/fs)) + Vo);
        c = (tanf(2 * M_PI * (fc/fs)) - 1)/(tanf(2 * M_PI * (fc/fs)) + 1);
        d = -1 * cosf(2 * M_PI * (fc/fs));


        Y_H = (sample) - Y_Ldelay - (phaserQ * Y_Bdelay); //formula for high pass filter
        Y_B = (F1 * Y_H) + (Y_Bdelay);                    //formula for band pass filter
        Y_L = (F1 * Y_B) + (Y_Ldelay);                    //formula for low pass filter
        Y_N = (Y_H + Y_L);
        Y_Ldelay = Y_L;                                   //setting the delay of the low pass filter to the immediate previous sample
        Y_Bdelay = Y_B;                                   //setting the delay of the band pass filter to the immediate previous sample
        y1b = (-1*c*sample+d*(1-c)*inputdelay+inputdelay2-d*(1-c)*y1bdelay+c*y1bdelay2); //this handles the band pass and notch filters

        inputdelay2 = inputdelay;
        inputdelay = sample;

        y1bdelay2 = y1bdelay;
        y1bdelay = y1b;


        notch = 0.5 * (sample + y1b);

        return ((Uint16)(0.8*notch + 0.2*sample)); // Output using the notch filter
}
