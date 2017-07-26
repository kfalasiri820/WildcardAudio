/*
 * phaser.h
 *
 *  Created on: Jul 25, 2017
 *      Author: Matt Marder
 */

#ifndef PHASER_H_
#define PHASER_H_

extern float phaserQ = 0.6;           // Can be manually varied between .3 <--> .8  "Resonance"
extern Uint16 phaserRate = 30000;   // Can be manually varied between 11,000 <--> 44,100 "Rate"
extern float phaserCenterFreq = 1600; // Can be manually varied between 1000 - 3000 "Tone"
extern float phaserRange = 3200;      // Fixed at 3200

Uint16 phaser(Uint16 sample);

#endif /* PHASER_H_ */
