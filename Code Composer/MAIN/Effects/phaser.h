/*
 * phaserBoy.h
 *
 *  Created on: Jul 25, 2017
 *      Author: Matt Marder
 */

#ifndef PHASER_H_
#define PHASER_H_

//extern float phaserQ = 0.4;           // Can be manually varied between .3 <--> .8  "Resonance"
extern float phaserRate;   // Can be manually varied between 11,000 <--> 44,100 "Rate"
extern float phaserCenterFreq; // Must be greater than 1600. Can be manually varied between 1000 - 3000 "Tone"
extern float phaserRange;      // Fixed at 1600

Uint16 phaser(float sample);
#endif /* PHASERBOY_H_ */
