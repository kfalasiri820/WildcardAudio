/*
 * main.c
 */
#include <DSP28x_Project.h>
#include "OneToOneI2CDriver.h"
#include <DSP2833x_Xintf.h>
#include <DSP2833x_XIntrupt.h>
#include "analogToDigitalConverter.h"
#include "digitalToAnalogConverter.h"
#include "DSP2833x_CpuTimers.h"
#include <DSP2833x_SysCtrl.h>
#include "Sram.h"
#include "digitalToAnalogConverter.h"
#include "james_on_off_timer.h"


#pragma DATA_SECTION(Buffer1, ".memory")

#define LOOP_MAX 512000
#define DELAY_BUFFER_MAX 40000

Uint16 delayBuffer[DELAY_BUFFER_MAX] = {0};
Uint16 loop1[LOOP_MAX] = {0};

void useGpio() {
	GpioCtrlRegs.GPAMUX1.all = 0x0000; // sets pins as GPIO
	GpioCtrlRegs.GPADIR.all  = 0xFF;  // output GPIO 0-7  those are LED's
	GpioCtrlRegs.GPAPUD.all = 0xFF; // pull ups disabled for switches 8-11
}

void main(void) {
	DisableDog();
	InitPll(10,3);
	timer_init(150,22.7);
	Sram_init();
	useGpio();
	digitalToAnalogConverter_init();
	analogToDigitalConverter_init();
	enable_timer_interrupt();

	//init buffers to 0
	for(int i = 0; i < DELAY_BUFFER_MAX; i++)
		delayBuffer[i] = 0;
	for(int i = 0; i < LOOP_MAX; i++)
		loop1[i] = 0;

	while(1);
}

