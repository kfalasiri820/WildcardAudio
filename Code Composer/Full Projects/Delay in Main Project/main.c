#include "DSP28x_Project.h"
#include "DSP2833x_Mcbsp.h"

Uint16 sampleFlag = 0; //interrupt flag that goes 1 when time to sample

Uint16 adc_in(){

	return 0;
}

void dac_out(Uint16 y){

}

__interrupt void sample(Uint16 y){
	sampleFlag = 1;
}

Uint16 main(void) {
	EALLOW;
	DisableDog();
	InitPll(10, 3);

	//GPIO
	GpioCtrlRegs.GPAMUX1.all = 0;//set to GPIOs
	GpioCtrlRegs.GPADIR.all = 0;//set to be in?
	GpioCtrlRegs.GPAPUD.all = 0xFF;//set 0-7 to be pull up disabled

	//timer init
	InitCpuTimers();
	ConfigCpuTimer(&CpuTimer1, 150, 2.2675736961);//address of timer 1, rate, period

	//Timer Interrupt init
	PieVectTable.XINT13 = sample;//map interrupt to timer 1 vector table
	IER |= M_INT13;
	PieCtrlRegs.PIECTRL.all |= 1; //acknowledge address passed
	EINT;//enable interrupts

	//Start timer
	CpuTimer1Regs.TCR.bit.TSS = 0;

	//init buffer
	Uint16 buffSize = 10000;

	Uint16 buffer[buffSize];
	for(Uint16 i = 0; i < buffSize; i++)
		buffer[i] = 0;

	Uint16 n = 0;
	Uint16 y = 0;//output
	Uint16 a = 0.3;//amplitude of echo
	Uint16 p = ; //delay time in samples, = [t * Fs] (delay time, sample rate in hz)
	int32 echoPointer = buffSize - p;


	while(1){
		//Reverb
		if(sampleFlag == 1){
			//write to buffer from mcBSP


			//if end of buffer, start over
			if(n == buffSize)
				n = 0;
			//if end of buffer, start over
			if(echoPointer == buffSize)
				echoPointer = 0;

			//if(switch is on)
				y = (1-a) * buffer[n] + a * buffer[echoPointer];
			//else
				//y = buffer[echoPointer];

			//output y
			dac_out(y);

			n++;//increment buffer pointer
			echoPointer++;
			sampleFlag = 0;//reset flag
		}
	}


}



