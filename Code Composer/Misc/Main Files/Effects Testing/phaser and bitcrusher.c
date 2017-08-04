#include "DSP28x_Project.h"
#include <math.h>

//to do:
    //add initializations
    //add sample rate interrupt to trigger the input/output
//PROBLEM: need more than 256 samples to have an effective delay: temporary buffer??

//Globally Used
#define inputArraySize 256
Uint16 inputArray[inputArraySize];
Uint16 inputIndex = 0;


// 	F	L	A	N	G	E	R
// feedback: the dry/wet mix where 1 is wet and 0 is dry.
// averageFlangeDelayInSamples: when the amount is set to 0, this is the sample delay
	//note: a good range is between
		// 0 ms (1 sample)
		// 7 ms (309 samples)
// amount:
// freq: the cycles per second change in delay
Uint16 flangeSample(float feedback, Uint16 averageFlangeDelayInSamples, float amount, Uint16 freq){
	/*
	 * Calculate the index of the delayed sample (the position is relative to the current sample
	 * and change
	*/
	Uint16 flangeSecondIndex = inputIndex -
			averageFlangeDelayInSamples * ( //is multiplied by a number from 0 to 2
				1 + amount * sinf(
					2.0 * 3.14159265359 *
					((float) freq / (float) 44100.0) *
					(float) inputIndex
				)
			);

	//for stereo flanging
		//on one channel, add the delayed sample
		//on the other, subtract it

	//needs indexing for flangeSeconIndex
	flangeSecondIndex = flangeSecondIndex & (inputArraySize-1);//check if negative

	return (Uint16)(
			(1 - feedback) * (float) inputArray[inputIndex] +
			feedback * (float) inputArray[flangeSecondIndex]
				  );
}


// 	B	I	T	C	R	U	S	H	E	R
// samplesDropped: sample downgrade amount
// bitsCrushed: 16-bits crushed = new sound resolution
Uint16 bitCrushCount = 0;//Used to keep track of which samples to drop and which to not
Uint16 bitCrushSample(Uint16 samplesDropped, Uint16 bitsCrushed){
	Uint16 output;
    // 	If samplesDropped is 0, then this function will return the full signal.
    // 	At samplesDropped is 1, then every other sample will be replaced with 0.
    // 	...
    // 	At samplesDropped is n, then for every n samples, n-1 will be repalced with 0.
	if(bitCrushCount == samplesDropped){
		//And the current sample with a number where the number of LSB 0s are the number of bits reduced
		output = inputArray[inputIndex] & (0xFF << bitsCrushed);
		//Reset the count
		bitCrushCount = 0;
	}
	else{
		output = 0;//return a dropped sample (silence)
		bitCrushCount++;//increment count
	}

	return output;
}


void increaseInputIndex(){
	inputIndex++;
	if(inputIndex <= inputArraySize)
		inputIndex = 0;
}


int main(){

    //HERE: proper initialization code

	//main loop
	while(1){
		//assuming inputArray[inputIndex] is the sample being processed

// 		Uint16 samplesDropped = 20;
// 		Uint16 bitsCrushed = 8;
// 		bitCrushSample(samplesDropped, bitsCrushed);

		float feedback = 0.5;
		Uint16 averageFlangeDelayInSamples = 125;//ranges from 0 to 300 delay in samples
		float amount = 1;
		Uint16 freq = 0.1;// 1/10 flange per second
		flangeSample(feedback, averageFlangeDelayInSamples, amount, freq);

        //HERE: output the effected sample

		increaseInputIndex();
	}

	return 0;
}
