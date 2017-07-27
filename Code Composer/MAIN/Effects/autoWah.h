/*
 * phaserBoy.h
 *
 *  Created on: Jul 25, 2017
 *      Author: Matt Marder
 */

#ifndef AUTOWAH_H_
#define AUTOWAH_H_

extern float autoWahRate;   // Can be manually varied between 11,000 <--> 44,100 "Rate"
extern float autoWahCenterFreq; // Must be greater than 1600. Can be manually varied between 1000 - 3000 "Tone"
extern float autoWahRange;      // Fixed at 1600

Uint16 autoWah(float sample);
#endif /* PHASERBOY_H_ */
