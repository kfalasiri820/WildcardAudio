/*
 * Ring_Modulation.h
 *
 *  Created on: Jul 21, 2017
 *      Author: Matt Marder
 */

#ifndef RING_MODULATION_H_
#define RING_MODULATION_H_

extern Uint16 ringModCounter;
extern Uint16 ringModRate;
extern float ringModDepth;
extern float ringModBias;

Uint16 ringModulation(float sample);

#endif /* RING_MODULATION_H_ */
