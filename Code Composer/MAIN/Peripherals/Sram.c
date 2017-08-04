/*
 * Sram.c
 *
 *  Created on: Oct 14, 2016
 *      Author: Ish's Master Race PC
 */
#include <DSP28x_Project.h>
//float circularBuffer[32627];
void Sram_init() {
	EALLOW;

	GpioCtrlRegs.GPAMUX2.all |= 0xFF000000;// /XZCS6, XA19, XA18, XA17
	GpioCtrlRegs.GPBMUX1.all |= 0xFFFFF0A0;// XA7-XA1, XA0/XWE1, XA16, XWE0, XREADY, XR/W
	GpioCtrlRegs.GPCMUX1.all |= 0xFFFFFFFF;// XD15-0
	GpioCtrlRegs.GPCMUX2.all |= 0x0000FFFF;// XA8-XA5
	SysCtrlRegs.PCLKCR3.bit.XINTFENCLK = 1;


	//USEREADY
	XintfRegs.XINTCNF2.bit.CLKMODE = 0;
	XintfRegs.XINTCNF2.bit.XTIMCLK = 0;
	XintfRegs.XTIMING6.bit.XWRTRAIL = 1;
	XintfRegs.XTIMING6.bit.XWRACTIVE = 1;
	XintfRegs.XTIMING6.bit.XWRLEAD = 1;
	XintfRegs.XTIMING6.bit.XRDTRAIL = 1;
	XintfRegs.XTIMING6.bit.XRDACTIVE = 1;
	XintfRegs.XTIMING6.bit.XRDLEAD = 1;
}
