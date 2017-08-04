/*
 * i2s.c
 *
 *  Created on: Jul 19, 2017
 *      Author: Ish's Master Race PC
 */
#include <DSP28x_Project.h>
#include <DSP2833x_Mcbsp.h>
#include "timer.h"


Uint32 sample_L;
Uint32 sample_R;
Uint16 johns = 0xFFFF;
Uint16 johns2 = 0x7777;
int ch_sel = 0;

void InitMcbspaI2S(void);

void main(){
    DisableDog();
    InitPll(10,2);
    InitPeripheralClocks();
    InitMcbspaGpio();
    InitMcbspaI2S();
    //timer_init(150.0,22.6757);
    //enable_timer_interrupt_DFT();
    //GpioCtrlRegs.GPAMUX2.bit.GPIO23 = 0; // demphasis 0
    GpioCtrlRegs.GPAMUX2.bit.GPIO24 = 0; // demphasis 0
    GpioCtrlRegs.GPAMUX2.bit.GPIO25 = 0; // demphasis 1
    GpioCtrlRegs.GPAMUX2.bit.GPIO26 = 0; // pdwn
    GpioDataRegs.GPADAT.bit.GPIO24 = 0;
    GpioDataRegs.GPADAT.bit.GPIO25 = 0;
    GpioDataRegs.GPADAT.bit.GPIO26 = 1;
    while(1) {
        McbspaRegs.DXR2.all = johns;
        McbspaRegs.DXR1.all = johns2;
    }
}

void InitMcbspaI2S(void){
    /*
     * Connections
     * MCLKXA <- BCLK from DAC for transmit clock
     * MFSXA <- WCLK from DAC for frame sync signal
     */

	//Bit Clock set up
    		//word size * fs * 2 = 24 * 44100 * 2 = 2,116,800
    //Word Clock set up
    		//fs

// XCLKOUT SETUP
	EALLOW;
    SysCtrlRegs.PCLKCR0.bit.MCBSPAENCLK = 1;
    SysCtrlRegs.PCLKCR3.bit.XINTFENCLK = 1; //Enable external clock
    XintfRegs.XINTCNF2.bit.XTIMCLK = 1; //XCLKOUT is SYSCLKOUT/4
    XintfRegs.XINTCNF2.bit.CLKMODE = 1;//This makes for a 37.5MHz DAC MCLK
    XintfRegs.XINTCNF2.bit.CLKOFF = 0;
    EDIS;


// RESETTING ALL REGISTERS
	EALLOW;
	McbspaRegs.SPCR2.all=0x0000;// Reset FS generator, sample rate generator & transmitter
	McbspaRegs.SPCR1.all=0x0000; // Reset Receiver, Right justify word
	McbspaRegs.SPCR1.bit.RJUST = 2; // left-justify word in DRR and zero-fill LSBs
	McbspaRegs.MFFINT.all=0x0; // Disable all interrupts
	McbspaRegs.SPCR1.bit.RINTM = 0; // McBSP interrupt flag - RRDY, data ready to read
	McbspaRegs.SPCR2.bit.XINTM = 0; // McBSP interrupt flag - XRDY, data ready to accept new transfer
	McbspaRegs.RCR2.all=0x0; // Clear Receive Control Registers
	McbspaRegs.RCR1.all=0x0;
	McbspaRegs.XCR2.all=0x0; // Clear Transmit Control Registers
	McbspaRegs.XCR1.all=0x0;
	McbspaRegs.PCR.all=0x0000;


// CLOCKING
	//Source Clock
	McbspaRegs.PCR.bit.SCLKME = 0; //Sample rate from LSPCLK
	McbspaRegs.SRGR2.bit.CLKSM = 1; // LSPCLK is clock source for SRG
	McbspaRegs.SRGR1.bit.CLKGDV = 20; //Dividing down the LSPCLK
	McbspaRegs.PCR.bit.CLKXM = 1; // Internal transmit clock
	McbspaRegs.PCR.bit.CLKRM = 1; // Internal receive clock
	McbspbRegs.SRGR2.bit.FSGM = 1; //Frame synch pulses generated by sample rate generator
	McbspaRegs.PCR.bit.FSXM = 1; //Receive Frame sync is supplied by sample rate gen
	McbspaRegs.PCR.bit.FSRM = 1; //Transfer Frame sync is supplied by sample rate gen

    //TX, RX CLOCKS EDGES
    	McbspaRegs.PCR.bit.CLKRP = 1; // Rcvd data sampled on rising edge of CLKR
    	McbspaRegs.PCR.bit.CLKXP = 0; // Tx data sampled on falling edge of CLKX
    	//FS Clock Edge
    	McbspaRegs.PCR.bit.FSRP = 0; // Receive Frame synch pulse is active high (R-channel first)
    	McbspaRegs.PCR.bit.FSXP = 0; // Transmit Frame synch pulse is active high (R-channel first)


// FRAME SYNC WIDTHS
    //Frame Sync Width
    McbspaRegs.SRGR2.bit.FPER = 47; //Period is 47 + 1
    McbspaRegs.SRGR1.bit.FWID = 23; //Pulse width is 23 + 1

    // 2 Receive phases, each is 1 word (24 bits)
    McbspaRegs.RCR1.bit.RWDLEN1 = 4; // receive word 1 is 24 bit data
	McbspaRegs.RCR1.bit.RFRLEN1 = 0; //receive frame length 1: 1 word
    McbspaRegs.RCR2.bit.RWDLEN2 = 4; // receive word 2 is 24 bit data
    McbspaRegs.RCR2.bit.RFRLEN2 = 0; //receive frame length 2: 1 word
    McbspaRegs.RCR2.bit.RPHASE =  1; //2 receive phase (left and right)
    // Data delays
	McbspaRegs.RCR2.bit.RDATDLY = 1;//length of data delay for receive (1 bit)

    // 2 Transfer phases, each is 1 word (24 bits)
    McbspaRegs.XCR1.bit.XWDLEN1 = 4; // tx word 1 is 24 bits
    McbspaRegs.XCR1.bit.XFRLEN1 = 0;//transfer frame length 2: 1 word
    McbspaRegs.XCR2.bit.XWDLEN2 = 4; // transmit 24 bit data
    McbspaRegs.XCR2.bit.XFRLEN2 = 0;//transfer frame length 2: 1 word
    McbspaRegs.XCR2.bit.XPHASE = 1;//2 transfer phase (left and right?)
    //Data Delay
	McbspaRegs.XCR2.bit.XDATDLY = 1;//length of data delay for transfer (1 bit)


// ENABLING
    //delay_loop();
    //McbspaRegs.MFFINT.bit.XINT = 1; // Enable Transmit Interrupt
    clkg_delay_loop();
    McbspaRegs.SPCR2.bit.GRST   = 1;
    clkg_delay_loop();
    McbspaRegs.SPCR2.bit.XRST   = 1;
    McbspaRegs.SPCR1.bit.RRST   = 1;
    clkg_delay_loop();                      // need to wait at least 2*(1/100mHz)
    McbspaRegs.SPCR2.bit.FRST = 1;
    clkg_delay_loop();
    EDIS;
}

__interrupt void Mcbsp_TxINTA_ISR(void){
    if(0 == ch_sel){
        ch_sel = 1;
        McbspaRegs.DXR2.all = sample_L;
        McbspaRegs.DXR1.all = 0x0000;
    }
    else{
        ch_sel = 0;
        McbspaRegs.DXR2.all = sample_R;
        McbspaRegs.DXR1.all = 0x0000;
    }
    PieCtrlRegs.PIEACK.all = PIEACK_GROUP6;
}
