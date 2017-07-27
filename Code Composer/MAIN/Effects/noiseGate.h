#ifndef NOISEGATE_H_
#define NOISEGATE_H_

extern Uint16 noiseGate_sample_counter = 0,
               noiseGate_release_time_a = 44,
               noiseGate_release_time_b = 100,
               noiseGate_threshold = 4000;

Uint16 noiseGate(Uint16 input_sample);

#endif
