#include <DSP28x_Project.h>
#include <math.h>

#define DELAY_BUFFER_SIZE 65536

extern Uint16 delayBuffer[DELAY_BUFFER_SIZE];
extern Uint32 delayBufferPrev,
			  delayBufferCurr,
			  delayTimeInSamples;

extern float delayDryWet,
             delayFeedback;


Uint16 delay(Uint16 live) {
	//save to the input buffer to use for feedback;
	Uint16 delayAndLive = (Uint16)(delayDryWet*(float)live +
		                           (1-delayDryWet)*delayFeedback*(float)delayBuffer[delayBufferPrev]);
	delayBuffer[delayBufferCurr] = delayAndLive;

	//increment current
	delayBufferCurr++;
	if(delayBufferCurr >= DELAY_BUFFER_SIZE) {
		delayBufferCurr = 0;
	}

	//delay previous
	delayBufferPrev = delayBufferCurr - delayTimeInSamples; //add delay
	delayBufferPrev = delayBufferPrev & (DELAY_BUFFER_SIZE - 1); //check if negative

	return delayAndLive;
}
