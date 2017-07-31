#ifndef NOISEGATE_H_
#define NOISEGATE_H_

extern Uint16 noiseGate_sample_counter,
               noiseGate_release_time_a,
               noiseGate_release_time_b,
               noiseGate_threshold;

Uint16 noiseGate(Uint16 input_sample);

#endif
