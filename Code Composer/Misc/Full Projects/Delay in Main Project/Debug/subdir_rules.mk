################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Each subdirectory must supply rules for building sources it contributes
Sram.obj: ../Sram.c $(GEN_OPTS) | $(GEN_HDRS)
	@echo 'Building file: $<'
	@echo 'Invoking: C2000 Compiler'
	"/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/bin/cl2000" -v28 -ml -mt --float_support=fpu32 --include_path="/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/headers/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/common/include" --advice:performance=all -g --c99 --display_error_number --diag_warning=225 --diag_wrap=off --preproc_with_compile --preproc_dependency="Sram.d" $(GEN_OPTS__FLAG) "$<"
	@echo 'Finished building: $<'
	@echo ' '

analogToDigitalConverter.obj: ../analogToDigitalConverter.c $(GEN_OPTS) | $(GEN_HDRS)
	@echo 'Building file: $<'
	@echo 'Invoking: C2000 Compiler'
	"/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/bin/cl2000" -v28 -ml -mt --float_support=fpu32 --include_path="/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/headers/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/common/include" --advice:performance=all -g --c99 --display_error_number --diag_warning=225 --diag_wrap=off --preproc_with_compile --preproc_dependency="analogToDigitalConverter.d" $(GEN_OPTS__FLAG) "$<"
	@echo 'Finished building: $<'
	@echo ' '

audioCntrl.obj: ../audioCntrl.c $(GEN_OPTS) | $(GEN_HDRS)
	@echo 'Building file: $<'
	@echo 'Invoking: C2000 Compiler'
	"/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/bin/cl2000" -v28 -ml -mt --float_support=fpu32 --include_path="/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/headers/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/common/include" --advice:performance=all -g --c99 --display_error_number --diag_warning=225 --diag_wrap=off --preproc_with_compile --preproc_dependency="audioCntrl.d" $(GEN_OPTS__FLAG) "$<"
	@echo 'Finished building: $<'
	@echo ' '

digitalToAnalogConverter.obj: ../digitalToAnalogConverter.c $(GEN_OPTS) | $(GEN_HDRS)
	@echo 'Building file: $<'
	@echo 'Invoking: C2000 Compiler'
	"/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/bin/cl2000" -v28 -ml -mt --float_support=fpu32 --include_path="/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/headers/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/common/include" --advice:performance=all -g --c99 --display_error_number --diag_warning=225 --diag_wrap=off --preproc_with_compile --preproc_dependency="digitalToAnalogConverter.d" $(GEN_OPTS__FLAG) "$<"
	@echo 'Finished building: $<'
	@echo ' '

james_delay_in_MAIN.obj: ../james_delay_in_MAIN.c $(GEN_OPTS) | $(GEN_HDRS)
	@echo 'Building file: $<'
	@echo 'Invoking: C2000 Compiler'
	"/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/bin/cl2000" -v28 -ml -mt --float_support=fpu32 --include_path="/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/headers/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/common/include" --advice:performance=all -g --c99 --display_error_number --diag_warning=225 --diag_wrap=off --preproc_with_compile --preproc_dependency="james_delay_in_MAIN.d" $(GEN_OPTS__FLAG) "$<"
	@echo 'Finished building: $<'
	@echo ' '

timer.obj: ../timer.c $(GEN_OPTS) | $(GEN_HDRS)
	@echo 'Building file: $<'
	@echo 'Invoking: C2000 Compiler'
	"/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/bin/cl2000" -v28 -ml -mt --float_support=fpu32 --include_path="/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/headers/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/common/include" --advice:performance=all -g --c99 --display_error_number --diag_warning=225 --diag_wrap=off --preproc_with_compile --preproc_dependency="timer.d" $(GEN_OPTS__FLAG) "$<"
	@echo 'Finished building: $<'
	@echo ' '


