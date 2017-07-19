// AutoWah effect by Christopher Sfalanga, Matt Marder, and Juan Valbuena
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
*
* declare variables below
*
*/

float fc = 500; //cutoff frequency
float fs = 44100; //sampling frequency

float F1; //initializing coefficient
float Q1 = 0.4;  //depth or gain which can be an input

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

float Y_H = 0;  //initializing high pass
float Y_L = 0; //initializing low pass
float Y_B = 0; //initializing band pass
float Y_N = 0;
float bandpass= 0;;
float Y_l;
float Y_h;
float notch = 0;

float input;
float M_PI = 3.141592653;

int main () {

        DisableDog();
        InitPll(10,3); // (10,4) 1/4 of fper
        analogToDigitalConverter_init();
        digitalToAnalogConverter_init();
        timer_init(150.0,20);
        enable_timer_interrupt();
        EALLOW;
        useGpio();

    //int modifier = 1;
    unsigned int counter = 0;
    for(;1;++counter) {

        if(counter > 35280) {
            counter = 0;
        }

        float lowf = 80, highf = 3000;
        //fc = (lowf+highf)*sinf(2*M_PI*(float)counter/44100.0)/2.0+(lowf+highf);
        fc = (lowf+highf)/2.0 + (highf-lowf)*sinf(2*M_PI*(float)counter/35280.0)/2.0;

        F1 = ((tanf(2 * 3.141592653589793238462643 * (fc/fs)))); //coefficient used in filter formulas dependent on the cut off frequency fc and sampling fs

        Ho = Vo - 1;
        ac = (tanf(2 * 3.141592653589793238462643 * (fc/fs)) - Vo)/(tanf(2 * 3.141592653589793238462643 * (fc/fs)) + Vo);
        c = (tanf(2 * 3.141592653589793238462643 * (fc/fs)) - 1)/(tanf(2 * 3.141592653589793238462643 * (fc/fs)) + 1);
        d = -1 * cosf(2 * 3.141592653589793238462643 * (fc/fs));

        EALLOW;
        while(!interruptTimerFlag);


        input = analogToDigitalConverter_send(); //setting input variable to be ADC input
        Y_H = (input) - Y_Ldelay - (Q1 * Y_Bdelay); //formula for high pass filter
        Y_B = (F1 * Y_H) + (Y_Bdelay); //formula for band pass filter
        Y_L = (F1 * Y_B) + (Y_Ldelay); //formula for low pass filter
        Y_N = (Y_H + Y_L);
        Y_Ldelay = Y_L; //setting the delay of the low pass filter to the immediate previous sample
        Y_Bdelay = Y_B; //setting the delay of the band pass filter to the immediate previous sample

        y1b = (-1*c*input+d*(1-c)*inputdelay+inputdelay2-d*(1-c)*y1bdelay+c*y1bdelay2); //this handles the band pass and band reject filter
//      y1l = ((c/ac)*input) + inputdelay - ((c/ac)*y1ldelay);
        inputdelay2 = inputdelay;
        inputdelay = input;
//      y1ldelay = y1l;
        y1bdelay2 = y1bdelay;
        y1bdelay = y1b;


        if (GpioDataRegs.GPADAT.bit.GPIO8 == 0x01){ //setting first switch to activate low pass filter
            GpioDataRegs.GPADAT.bit.GPIO1 = 1; //turning on led to indicate visually its working
            digitalToAnalogConverter_send((Uint16)(0.8*Y_L + 0.2*input)); //output using the low pass filter
        }
        else if (GpioDataRegs.GPADAT.bit.GPIO9 == 0x01){ //setting the second switch to activate high pass filter
            GpioDataRegs.GPADAT.bit.GPIO2 = 1; //turning on led to indicate visually the high pass is activated
            digitalToAnalogConverter_send((Uint16)Y_H); //output using the high pass filter
        }
        else if (GpioDataRegs.GPADAT.bit.GPIO10 == 0x01){ //setting the third switch to activate the bandpass filter
            GpioDataRegs.GPADAT.bit.GPIO3 = 1; //turning on led to indicate visually the band pass filter
            bandpass = (0.5 * (input - y1b));
            digitalToAnalogConverter_send((Uint16)(0.8*Y_B + 0.2*input)); //output using the band pass filter
        }
        else if (GpioDataRegs.GPADAT.bit.GPIO11 == 0x01){
            GpioDataRegs.GPADAT.bit.GPIO4 = 1;
            notch = 0.5 * (input + y1b);
            digitalToAnalogConverter_send((Uint16)notch);
        }
        else{
            digitalToAnalogConverter_send((Uint16)(input)); //if no switch is activate act as a wire
            GpioDataRegs.GPADAT.bit.GPIO1 = 0; //turn off all leds so theres no confusion if a filter is working or not
            GpioDataRegs.GPADAT.bit.GPIO2 = 0;
            GpioDataRegs.GPADAT.bit.GPIO3 = 0;
            GpioDataRegs.GPADAT.bit.GPIO4 = 0;
        }

        interruptTimerFlag = 1;
    }
}