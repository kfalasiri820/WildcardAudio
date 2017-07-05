/*
 * Chorus.c
 *
 *  Created on: Jun 22, 2017
 *      Author: Ish's Master Race PC
 */


#include <DSP28x_Project.h>
#include "OneToOneI2CDriver.h"
#include <DSP2833x_Xintf.h>
#include <DSP2833x_XIntrupt.h>
#include "timer.h"
#include "analogToDigitalConverter.h"
#include "digitalToAnalogConverter.h"
#include "DSP2833x_CpuTimers.h"
#include <DSP2833x_SysCtrl.h>
#include "audioCntrl.h"
#include "Sram.h"
#include "digitalToAnalogConverter.h"
#include "hammingWindow.h"
#include "iir.h"
#include "Lab7_IIR.h"
#include <DSP2833x_GlobalPrototypes.h>
#include "timer2.h"
#define N 32768
#define LP 42
#pragma DATA_SECTION(y, ".johns");
#pragma DATA_SECTION(chorus, ".johns");
float y[N];
float chorus[N];
extern float f;
float debug;
float outputOfFIR;

Uint32 j = 0;
Uint16 LFO_value = 0;
Uint32 lfo = 0;

float filter_taps_LP_72[] = {0.006960,0.004870,0.006450,0.008260,0.010291,0.012532,0.014961,0.017540,0.020242,0.023029,0.025833,0.028627,
0.031341,0.033929,0.036335,0.038508,0.040398,0.041964,0.043171,0.043988,0.044403,0.044403,0.043988,0.043171,0.041964,0.040398,0.038508,
0.036335,0.033929,0.031341,0.028627,0.025833,0.023029,0.020242,0.017540,0.014961,0.012532,0.010291,0.008260,0.006450,0.004870,
0.006960};

int main () {

			DisableDog();
			InitPll(10,3); // (10,4) 1/4 of fper
			Sram_init();
			analogToDigitalConverter_init();
			digitalToAnalogConverter_init();

			timer_init(150.0,22.6757);
			enable_timer_interrupt();
			timer_init2(150,20000); // be faster was 10000
			enable_timer_interrupt2();
			EALLOW;
			useGpio();
			//internalADC_init();

			for (Uint32 var = 0;  var < N; ++var) {
				y[var] = 0;
			}

	while(1) {
		EALLOW;
		//GpioDataRegs.GPATOGGLE.bit.GPIO1 = 0;
		while(!interruptTimerFlag);
				// keep track of index below

				j   %= N;
				LFO_value = (Uint16) (f * 40000); //(123811) normalize to 20-40 ms LFO rate on a sine wave
				// sample input

				x_n = (((float)analogToDigitalConverter_send()));
				y[j] = x_n;
				debug = 0.6 * y[j] + 0.4* y[(j - LFO_value)& (0x7FFF)];
				if (GpioDataRegs.GPADAT.bit.GPIO8) {
				// add a lowpass filter here
//					for (int a = 0; a < 42; a++) {
//						outputOfFIR += filter_taps_LP_72[a] * chorus[(j-a) & (0x00FF)];
//					}
				digitalToAnalogConverter_send((Uint16)(debug)); // assign debug variable to output chorus to debug sound-out
				}
				else {
					digitalToAnalogConverter_send(analogToDigitalConverter_send());
				}
				j++;
				// check for max length
				if(j >= 65536) {
					j = 0;
				}
				// toggle flag
				interruptTimerFlag = 0;
				// turn on LED
	}
}

