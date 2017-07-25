/* Tremolo By: Christopher Sfalanga & Juan Valbuena & Matteus Martyr*/

#include <DSP28x_Project.h>
#include <math.h>

extern unsigned int tremolo_counter,
                    tremolo_time;

Uint16 tremolo(Uint16 input_sample) {
	Uint16 output_sample;

	float M_PI = 3.1415926535897932384626433832795028;

	output_sample = input_sample*sinf((2*M_PI*tremolo_counter)/tremolo_time);

	if(tremolo_counter >= tremolo_time) {
		tremolo_counter = 0;
	}
	else {
		tremolo_counter += 1;
	}

	return (Uint16)output_sample;
}
