/*
 * Ring_Modulation.h
 *
 *  Created on: Jul 21, 2017
 *      Author: Matt Marder
 */

#ifndef RING_MODULATION_H_
#define RING_MODULATION_H_

extern Uint16 ringModCounter = 0;
extern Uint16 ringModRate = 600;
extern float ringModDepth = 1;
extern float ringModBias = 0;

Uint16 ringModulation(float sample);

#endif /* RING_MODULATION_H_ */
