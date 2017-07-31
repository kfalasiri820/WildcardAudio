#ifndef DELAY_H_
#define DELAY_H_

#define DELAY_BUFFER_SIZE 65536

extern Uint16 delayBuffer[DELAY_BUFFER_SIZE];
extern Uint32 delayBufferPrev,
			  delayBufferCurr,
			  delayTimeInSamples;

extern float delayDryWet,
             delayFeedback;


Uint16 delay(Uint16 live);

#endif
