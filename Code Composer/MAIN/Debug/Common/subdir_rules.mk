################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Each subdirectory must supply rules for building sources it contributes
Common/DSP2833x_ADC_cal.obj: ../Common/DSP2833x_ADC_cal.asm $(GEN_OPTS) | $(GEN_HDRS)
	@echo 'Building file: $<'
	@echo 'Invoking: C2000 Compiler'
	"/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/bin/cl2000" -v28 -ml -mt --float_support=fpu32 --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/common/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/headers/include" --include_path="/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/include" -g --c99 --display_error_number --diag_warning=225 --diag_wrap=off --preproc_with_compile --preproc_dependency="Common/DSP2833x_ADC_cal.d" --obj_directory="Common" $(GEN_OPTS__FLAG) "$<"
	@echo 'Finished building: $<'
	@echo ' '

Common/DSP2833x_CpuTimers.obj: ../Common/DSP2833x_CpuTimers.c $(GEN_OPTS) | $(GEN_HDRS)
	@echo 'Building file: $<'
	@echo 'Invoking: C2000 Compiler'
	"/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/bin/cl2000" -v28 -ml -mt --float_support=fpu32 --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/common/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/headers/include" --include_path="/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/include" -g --c99 --display_error_number --diag_warning=225 --diag_wrap=off --preproc_with_compile --preproc_dependency="Common/DSP2833x_CpuTimers.d" --obj_directory="Common" $(GEN_OPTS__FLAG) "$<"
	@echo 'Finished building: $<'
	@echo ' '

Common/DSP2833x_DMA.obj: ../Common/DSP2833x_DMA.c $(GEN_OPTS) | $(GEN_HDRS)
	@echo 'Building file: $<'
	@echo 'Invoking: C2000 Compiler'
	"/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/bin/cl2000" -v28 -ml -mt --float_support=fpu32 --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/common/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/headers/include" --include_path="/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/include" -g --c99 --display_error_number --diag_warning=225 --diag_wrap=off --preproc_with_compile --preproc_dependency="Common/DSP2833x_DMA.d" --obj_directory="Common" $(GEN_OPTS__FLAG) "$<"
	@echo 'Finished building: $<'
	@echo ' '

Common/DSP2833x_DefaultIsr.obj: ../Common/DSP2833x_DefaultIsr.c $(GEN_OPTS) | $(GEN_HDRS)
	@echo 'Building file: $<'
	@echo 'Invoking: C2000 Compiler'
	"/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/bin/cl2000" -v28 -ml -mt --float_support=fpu32 --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/common/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/headers/include" --include_path="/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/include" -g --c99 --display_error_number --diag_warning=225 --diag_wrap=off --preproc_with_compile --preproc_dependency="Common/DSP2833x_DefaultIsr.d" --obj_directory="Common" $(GEN_OPTS__FLAG) "$<"
	@echo 'Finished building: $<'
	@echo ' '

Common/DSP2833x_I2C.obj: ../Common/DSP2833x_I2C.c $(GEN_OPTS) | $(GEN_HDRS)
	@echo 'Building file: $<'
	@echo 'Invoking: C2000 Compiler'
	"/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/bin/cl2000" -v28 -ml -mt --float_support=fpu32 --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/common/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/headers/include" --include_path="/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/include" -g --c99 --display_error_number --diag_warning=225 --diag_wrap=off --preproc_with_compile --preproc_dependency="Common/DSP2833x_I2C.d" --obj_directory="Common" $(GEN_OPTS__FLAG) "$<"
	@echo 'Finished building: $<'
	@echo ' '

Common/DSP2833x_Mcbsp.obj: ../Common/DSP2833x_Mcbsp.c $(GEN_OPTS) | $(GEN_HDRS)
	@echo 'Building file: $<'
	@echo 'Invoking: C2000 Compiler'
	"/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/bin/cl2000" -v28 -ml -mt --float_support=fpu32 --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/common/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/headers/include" --include_path="/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/include" -g --c99 --display_error_number --diag_warning=225 --diag_wrap=off --preproc_with_compile --preproc_dependency="Common/DSP2833x_Mcbsp.d" --obj_directory="Common" $(GEN_OPTS__FLAG) "$<"
	@echo 'Finished building: $<'
	@echo ' '

Common/DSP2833x_MemCopy.obj: ../Common/DSP2833x_MemCopy.c $(GEN_OPTS) | $(GEN_HDRS)
	@echo 'Building file: $<'
	@echo 'Invoking: C2000 Compiler'
	"/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/bin/cl2000" -v28 -ml -mt --float_support=fpu32 --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/common/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/headers/include" --include_path="/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/include" -g --c99 --display_error_number --diag_warning=225 --diag_wrap=off --preproc_with_compile --preproc_dependency="Common/DSP2833x_MemCopy.d" --obj_directory="Common" $(GEN_OPTS__FLAG) "$<"
	@echo 'Finished building: $<'
	@echo ' '

Common/DSP2833x_PieCtrl.obj: ../Common/DSP2833x_PieCtrl.c $(GEN_OPTS) | $(GEN_HDRS)
	@echo 'Building file: $<'
	@echo 'Invoking: C2000 Compiler'
	"/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/bin/cl2000" -v28 -ml -mt --float_support=fpu32 --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/common/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/headers/include" --include_path="/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/include" -g --c99 --display_error_number --diag_warning=225 --diag_wrap=off --preproc_with_compile --preproc_dependency="Common/DSP2833x_PieCtrl.d" --obj_directory="Common" $(GEN_OPTS__FLAG) "$<"
	@echo 'Finished building: $<'
	@echo ' '

Common/DSP2833x_PieVect.obj: ../Common/DSP2833x_PieVect.c $(GEN_OPTS) | $(GEN_HDRS)
	@echo 'Building file: $<'
	@echo 'Invoking: C2000 Compiler'
	"/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/bin/cl2000" -v28 -ml -mt --float_support=fpu32 --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/common/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/headers/include" --include_path="/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/include" -g --c99 --display_error_number --diag_warning=225 --diag_wrap=off --preproc_with_compile --preproc_dependency="Common/DSP2833x_PieVect.d" --obj_directory="Common" $(GEN_OPTS__FLAG) "$<"
	@echo 'Finished building: $<'
	@echo ' '

Common/DSP2833x_Sci.obj: ../Common/DSP2833x_Sci.c $(GEN_OPTS) | $(GEN_HDRS)
	@echo 'Building file: $<'
	@echo 'Invoking: C2000 Compiler'
	"/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/bin/cl2000" -v28 -ml -mt --float_support=fpu32 --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/common/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/headers/include" --include_path="/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/include" -g --c99 --display_error_number --diag_warning=225 --diag_wrap=off --preproc_with_compile --preproc_dependency="Common/DSP2833x_Sci.d" --obj_directory="Common" $(GEN_OPTS__FLAG) "$<"
	@echo 'Finished building: $<'
	@echo ' '

Common/DSP2833x_Spi.obj: ../Common/DSP2833x_Spi.c $(GEN_OPTS) | $(GEN_HDRS)
	@echo 'Building file: $<'
	@echo 'Invoking: C2000 Compiler'
	"/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/bin/cl2000" -v28 -ml -mt --float_support=fpu32 --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/common/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/headers/include" --include_path="/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/include" -g --c99 --display_error_number --diag_warning=225 --diag_wrap=off --preproc_with_compile --preproc_dependency="Common/DSP2833x_Spi.d" --obj_directory="Common" $(GEN_OPTS__FLAG) "$<"
	@echo 'Finished building: $<'
	@echo ' '

Common/DSP2833x_SysCtrl.obj: ../Common/DSP2833x_SysCtrl.c $(GEN_OPTS) | $(GEN_HDRS)
	@echo 'Building file: $<'
	@echo 'Invoking: C2000 Compiler'
	"/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/bin/cl2000" -v28 -ml -mt --float_support=fpu32 --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/common/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/headers/include" --include_path="/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/include" -g --c99 --display_error_number --diag_warning=225 --diag_wrap=off --preproc_with_compile --preproc_dependency="Common/DSP2833x_SysCtrl.d" --obj_directory="Common" $(GEN_OPTS__FLAG) "$<"
	@echo 'Finished building: $<'
	@echo ' '

Common/DSP2833x_Xintf.obj: ../Common/DSP2833x_Xintf.c $(GEN_OPTS) | $(GEN_HDRS)
	@echo 'Building file: $<'
	@echo 'Invoking: C2000 Compiler'
	"/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/bin/cl2000" -v28 -ml -mt --float_support=fpu32 --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/common/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/headers/include" --include_path="/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/include" -g --c99 --display_error_number --diag_warning=225 --diag_wrap=off --preproc_with_compile --preproc_dependency="Common/DSP2833x_Xintf.d" --obj_directory="Common" $(GEN_OPTS__FLAG) "$<"
	@echo 'Finished building: $<'
	@echo ' '

Common/DSP2833x_usDelay.obj: ../Common/DSP2833x_usDelay.asm $(GEN_OPTS) | $(GEN_HDRS)
	@echo 'Building file: $<'
	@echo 'Invoking: C2000 Compiler'
	"/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/bin/cl2000" -v28 -ml -mt --float_support=fpu32 --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/common/include" --include_path="/Applications/ti/c2000/C2000Ware_1_00_00_00/device_support/f2833x/headers/include" --include_path="/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/include" -g --c99 --display_error_number --diag_warning=225 --diag_wrap=off --preproc_with_compile --preproc_dependency="Common/DSP2833x_usDelay.d" --obj_directory="Common" $(GEN_OPTS__FLAG) "$<"
	@echo 'Finished building: $<'
	@echo ' '


