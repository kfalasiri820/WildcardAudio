/*
 * main.c
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
#include "Sram.h"
#include "digitalToAnalogConverter.h"
#define END_INDEX 512000
#pragma DATA_SECTION(Buffer1, ".memory")
Uint32 iterator = 0;

Uint32 j = 0;
Uint32 echoPointer = 4000;
Uint32 endLoop;
float amp = 0.4;
Uint16 Buffer1[END_INDEX] = {0};
Uint16 temp_var;
Uint32 var = 0;

void useGpio() {
    GpioCtrlRegs.GPAMUX1.all = 0x0000; // sets pins as GPIO
    GpioCtrlRegs.GPADIR.all  = 0xFF;  // output GPIO 0-7  those are LED's
    GpioCtrlRegs.GPAPUD.all = 0xFF; // pull ups disabled for switches 8-11
}

int main(void) {
    DisableDog();
    InitPll(10,3);
    timer_init(150,22.7);
    Sram_init();
    useGpio();
    digitalToAnalogConverter_init();
    analogToDigitalConverter_init();
    enable_timer_interrupt();
    
    while(var != END_INDEX){
        Buffer1[var] = 0x0000;
        var++;
    }
    
    while(1) {
        /*
         *  Audio straight
         */
        //temp_var = analogToDigitalConverter_send() - 0x7FFF;
        // do stuff
        //digitalToAnalogConverter_send(temp_var + 0x7FFF);
        // "WIRE"
        // interruptTimerFlag
        
        while(GpioDataRegs.GPADAT.bit.GPIO8 == 1) {
            GpioDataRegs.GPADAT.bit.GPIO0 = 1;
            if (interruptTimerFlag) {
                Buffer1[iterator] = (analogToDigitalConverter_send() - 0x7FFF);
                iterator++;
                interruptTimerFlag = 0;
                if (iterator >= END_INDEX)
                    iterator = 0;
                endLoop = iterator;
            }
        }
        GpioDataRegs.GPADAT.bit.GPIO0 = 0;
        if (interruptTimerFlag) {
            digitalToAnalogConverter_send( (1-amp)*Buffer1[j] + amp*Buffer1[echoPointer] + 0x7FFF);
            j++;
            echoPointer++;
            interruptTimerFlag = 0;
            if (j >= endLoop)
                j = 0;
            if(echoPointer >= endLoop)
                echoPointer = 0;
        }
    }
    
    return 0;
}

