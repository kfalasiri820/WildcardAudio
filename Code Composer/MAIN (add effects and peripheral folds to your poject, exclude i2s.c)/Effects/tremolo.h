#ifndef TREMOLO_H_
#define TREMOLO_H_

extern unsigned int tremolo_counter = 0,
                    tremolo_time = 4000;

Uint16 tremolo(Uint16 input_sample);

#endif
