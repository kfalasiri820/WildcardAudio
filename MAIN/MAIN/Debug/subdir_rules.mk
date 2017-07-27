################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Each subdirectory must supply rules for building sources it contributes
MAIN.obj: ../MAIN.c $(GEN_OPTS) | $(GEN_HDRS)
	@echo 'Building file: $<'
	@echo 'Invoking: C2000 Compiler'
	"/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/bin/cl2000" -v28 -ml -mt --float_support=fpu32 --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/common/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/headers/include" --include_path="/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/include" -g --c99 --display_error_number --diag_warning=225 --diag_wrap=off --preproc_with_compile --preproc_dependency="MAIN.d" $(GEN_OPTS__FLAG) "$<"
	@echo 'Finished building: $<'
	@echo ' '


