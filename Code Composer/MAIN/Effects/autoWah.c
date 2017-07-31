/*
 * autoWahBoy.c
 *
 *  Created on: Jul 25, 2017
 *      Author: Matt Marder
 */

#include <DSP28x_Project.h>
#include <math.h>

extern float autoWahRate;   // Can be manually varied between 11,000 <--> 44,100 "Rate"
extern float autoWahCenterFreq; // Can be manually varied between 1000 - 3000 "Tone"
extern float autoWahRange;      // Fixed at 3200


float M_PI_auto = 3.141592653;
float fc_auto;
float fs_auto = 44100.0;
float c_auto=0;
float d_auto=0;
float sample_auto_minusTwo=0;
float sample_auto_minusOne=0;
float filterOut_auto=0;
float filterOut_auto_minusTwo=0;
float filterOut_auto_minusOne=0;

float autoWahCounter = 0;



Uint16 autoWah(float sample_auto) {

    autoWahCounter++;
        if(autoWahCounter > autoWahRate) {
            autoWahCounter = 0;
        }
        float autoWahLowFreq = autoWahCenterFreq - (autoWahRange/2);
        float autoWahHighFreq = autoWahCenterFreq + (autoWahRange/2);
        fc_auto = ( autoWahLowFreq + autoWahHighFreq ) / 2.0 + ( autoWahHighFreq - autoWahLowFreq ) * sinf (2*M_PI_auto*autoWahCounter/autoWahRate)/2.0; //LFO

        c_auto = (tanf(2 * M_PI_auto * (fc_auto/fs_auto)) - 1)/(tanf(2 * M_PI_auto * (fc_auto/fs_auto)) + 1);
        d_auto = -1 * cosf(2 * M_PI_auto * (fc_auto/fs_auto));

        //2 apply discrete time filter formula
                        filterOut_auto = (-1*c_auto*sample_auto+d_auto*(1-c_auto)*sample_auto_minusOne+sample_auto_minusTwo-
                                d_auto*(1-c_auto)*filterOut_auto_minusOne+c_auto*filterOut_auto_minusTwo);

        //3 set recursive feedback
                                sample_auto_minusTwo = sample_auto_minusOne;
                                sample_auto_minusOne = sample_auto;
                                filterOut_auto_minusTwo = filterOut_auto_minusOne;
                                filterOut_auto_minusOne = filterOut_auto;
        // 4 output sample_auto + filterOut_auto

        return ((Uint16)(0.6*sample_auto -  0.4*filterOut_auto));
}// Output using the notch filter
