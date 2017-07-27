/* Ring Modulation By: Christopher Sfalanga & Matt Marder*/

/*
 * The ranges for ring modulation parameters are
 * 1) ringModDepth: 0.1 <--> 1.0
 * 2) ringModBias:  0.3 <--> 0.6  *(To be determined by further testing)
 * 3) ringModRate:  200 <--> 2000 *(To be determined by further testing)
 */

#include <DSP28x_Project.h>
#include <math.h>

extern Uint16 ringModCounter;
extern Uint16 ringModRate;
extern float ringModDepth;
extern float ringModBias;

Uint16 ringModulation (float sample)
{

    float M_PI = 3.1415926535897932384626433832795028;

    sample = sample - 32767.0; //setting sampleSample variable to be ADC sampleSample, centered around zero
    float output = 0;
    ringModRate = 600;
    float n = 44100/ringModRate; //samples per second multiplied by modulator period
    output = sample * ( ringModDepth * ( sinf(2 * M_PI * ringModCounter / n) + ringModBias )); // inout * (modulator + bias)

    ringModCounter++;

    if(ringModCounter >= n) {
        ringModCounter = 0;
    }


    return((Uint16)output+32767.00);

}
