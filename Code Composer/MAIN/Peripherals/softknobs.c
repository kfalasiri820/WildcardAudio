/* Software knobs initialization */
#include <DSP28x_Project.h>

Uint16 softKnob1 = 0x0000;
Uint16 softKnob2 = 0x0000;
Uint16 softKnob3 = 0x0000;

interrupt void readADC(void){

    EALLOW;
    Uint16 softKnob1 = AdcRegs.ADCRESULT0;
    Uint16 softKnob2 = AdcRegs.ADCRESULT1;
    Uint16 softKnob3 = AdcRegs.ADCRESULT2;
    PieCtrlRegs.PIEACK.all = PIEACK_GROUP1;
    AdcRegs.ADCST.bit.INT_SEQ1_CLR=1;
    EDIS;

}

//interrupt void softKnobChange(void){ //triggered by changed in software Knob
//
//    EALLOW;
//    AdcRegs.ADCTRL2.bit.RST_SEQ1 = 1; //reset the conversion to an initial "pretriggered" state
//    AdcRegs.ADCTRL2.bit.SOC_SEQ1 = 1; //start the conversion
//    PieCtrlRegs.PIEACK.all = PIEACK_GROUP1;
//    EDIS;
//
//}


void softKnobsInit(){

    EALLOW;

    SysCtrlRegs.HISPCP.all = 0x03; // Make high speed clock equal to 25MHz for ADC
    PieVectTable.SEQ1INT = readADC;
    PieCtrlRegs.PIECTRL.bit.ENPIE = 1;
    PieCtrlRegs.PIEIER1.bit.INTx1 = 1;


    IER |= M_INT1;

    EINT;

    AdcRegs.ADCTRL3.bit.ADCPWDN  = 1; // Power up, sequencer mode

//    __delay_cycles(250,000); //delay of 5 ms before the first conversion

    AdcRegs.ADCTRL1.all      = 0x28C0;  //Prescalar, ACQ size, continuous

    AdcRegs.ADCMAXCONV.all   = 0x0002; // 2+1 = 3 ADC conversions
    AdcRegs.ADCREFSEL.all    = 0x0000; // Use internal reference voltage

    AdcRegs.ADCCHSELSEQ1.bit.CONV00 = 0x0; //ADCIN0
    AdcRegs.ADCCHSELSEQ1.bit.CONV01 = 0x1; //ADCIN1
    AdcRegs.ADCCHSELSEQ1.bit.CONV02 = 0x2; //ADCIN2


    AdcRegs.ADCTRL2.bit.INT_ENA_SEQ1 = 1; //enable the seq1

    AdcRegs.ADCTRL2.bit.SOC_SEQ1 = 1; //start the conversion

    EDIS;

}
