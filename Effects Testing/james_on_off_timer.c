/*
 * timer.c
 *
 *  Created on: Oct 14, 2016
 *      Author: Ish's Master Race PC
 */
#include "james_on_off_timer.h"

#include <DSP28x_Project.h>
#include <DSP2833x_XIntrupt.h>
#include <DSP2833x_CpuTimers.h>
#include <DSP2833x_SysCtrl.h>
#include <DSP2833x_XIntrupt.h>
#include <DSP2833x_GlobalPrototypes.h>
#include "analogToDigitalConverter.h"
#include "digitalToAnalogConverter.h"
#include <stdbool.h>

#define MAX_SRAM_SIZE 0x100000
#define LOOP_MAX 512000
#define DELAY_BUFFER_MAX 40000

Uint32 i = 0;
Uint32 l = 0;
Uint32 p = 0;
Uint32 t = 0;
int temp = 0;
int startAgain = 1;
Uint32 mixedSample = 0;

// variables that we copied from main
//Uint32 iterator = 0;
//Uint32 j = 0;
//Uint32 echoPointer;
//Uint32 endLoop;
float feedback = 0.4;
extern loop1[];
extern delayBuffer[];
Uint32 delayBufferCurr = 0;
Uint32 delayBufferPrev = DELAY_BUFFER_MAX - 10000;

/*takes in two parameters, the first specifies the system clock, the next is the duration of the clock in us (approx)*/
__interrupt void sample() {


	/********************************EFECT ON OR OFF -> switch 2************************/
	if (GpioDataRegs.GPADAT.bit.GPIO9 == 1) {//ON
		//Write to Effects buffer
		delayBuffer[delayBufferCurr] = (Uint16)
					(
					(1 - feedback)* (float) analogToDigitalConverter_send() +		//live signal
					(feedback) * (float) delayBuffer[delayBufferPrev]//effected signal
					);

		//Output the current sample
		digitalToAnalogConverter_send(delayBuffer[delayBufferCurr]);

		//Increment pointers
		delayBufferCurr++;
		delayBufferPrev++;
		if(delayBufferCurr >= DELAY_BUFFER_MAX)//if out of bounds
			delayBufferCurr = 0;//reset to 0
		if(delayBufferPrev >= DELAY_BUFFER_MAX)//if out of bounds
			delayBufferPrev = 0;//reset to 0

		GpioDataRegs.GPADAT.bit.GPIO0 = 0;//turn on LED
	}

	else {//OFF
		GpioDataRegs.GPADAT.bit.GPIO0 = 0;//turn off LED
		digitaltoAnalogConverter_send(analogToDigitalConverter_send());//live signal
	}


//		LOOP, THEN ECHO
//    if (GpioDataRegs.GPADAT.bit.GPIO8 == 1) {
//        GpioDataRegs.GPADAT.bit.GPIO0 = 1;
//            Buffer1[iterator] = (analogToDigitalConverter_send()-0x7FFF);
//            iterator++;
//            if (iterator >= END_INDEX)
//                iterator = 0;
//            endLoop = iterator;
//            echoPointer = endLoop - 10000;
//    }
//    else {
//
//    	digitalToAnalogConverter_send((Uint16)(((0.6) * (float)(Buffer1[j])))+((0.4) * (float)(Buffer1[echoPointer]))+0x7FFF);
//        j++;
//        echoPointer++;
//        if (j >= endLoop)
//            j = 0;
//        if(echoPointer >= endLoop)
//            echoPointer = 0;
//    }
//
//    GpioDataRegs.GPADAT.bit.GPIO0 = 0;

}

void timer_init(float a, float b) {
	EALLOW;
	InitCpuTimers();
	ConfigCpuTimer(&CpuTimer1,a,b);
	EDIS;
	// end of timers
}

void enable_timer_interrupt() {

		EALLOW;
		PieVectTable.XINT13 = sample;
		IER |=  M_INT13;
		PieCtrlRegs.PIECTRL.all |= 0x01; // acknowledge address passed
		EINT;                           // enable global interrupts
		CpuTimer1Regs.TCR.bit.TSS = 0;	// start the timer
		EDIS;
}


