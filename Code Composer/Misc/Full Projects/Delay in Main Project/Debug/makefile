################################################################################
# Automatically-generated file. Do not edit!
################################################################################

CG_TOOL_ROOT := /Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS

GEN_OPTS__FLAG := 
GEN_CMDS__FLAG := 

ORDERED_OBJS += \
"./Sram.obj" \
"./analogToDigitalConverter.obj" \
"./audioCntrl.obj" \
"./digitalToAnalogConverter.obj" \
"./james_delay_in_MAIN.obj" \
"./timer.obj" \
"./Common/DSP2833x_ADC_cal.obj" \
"./Common/DSP2833x_CpuTimers.obj" \
"./Common/DSP2833x_DMA.obj" \
"./Common/DSP2833x_DefaultIsr.obj" \
"./Common/DSP2833x_I2C.obj" \
"./Common/DSP2833x_Mcbsp.obj" \
"./Common/DSP2833x_MemCopy.obj" \
"./Common/DSP2833x_PieCtrl.obj" \
"./Common/DSP2833x_PieVect.obj" \
"./Common/DSP2833x_Sci.obj" \
"./Common/DSP2833x_Spi.obj" \
"./Common/DSP2833x_SysCtrl.obj" \
"./Common/DSP2833x_Xintf.obj" \
"./Common/DSP2833x_usDelay.obj" \
"./Headers/DSP2833x_GlobalVariableDefs.obj" \
"../28335_RAM_lnk.cmd" \
"../Headers/DSP2833x_Headers_nonBIOS.cmd" \
$(GEN_CMDS__FLAG) \
-llibc.a \

-include ../makefile.init

RM := rm -rf
RMDIR := rm -rf

# All of the sources participating in the build are defined here
-include sources.mk
-include subdir_vars.mk
-include Common/subdir_vars.mk
-include Headers/subdir_vars.mk
-include subdir_rules.mk
-include Common/subdir_rules.mk
-include Headers/subdir_rules.mk
-include objects.mk

ifneq ($(MAKECMDGOALS),clean)
ifneq ($(strip $(C55_DEPS)),)
-include $(C55_DEPS)
endif
ifneq ($(strip $(C_UPPER_DEPS)),)
-include $(C_UPPER_DEPS)
endif
ifneq ($(strip $(S67_DEPS)),)
-include $(S67_DEPS)
endif
ifneq ($(strip $(S62_DEPS)),)
-include $(S62_DEPS)
endif
ifneq ($(strip $(S_DEPS)),)
-include $(S_DEPS)
endif
ifneq ($(strip $(OPT_DEPS)),)
-include $(OPT_DEPS)
endif
ifneq ($(strip $(C??_DEPS)),)
-include $(C??_DEPS)
endif
ifneq ($(strip $(ASM_UPPER_DEPS)),)
-include $(ASM_UPPER_DEPS)
endif
ifneq ($(strip $(S??_DEPS)),)
-include $(S??_DEPS)
endif
ifneq ($(strip $(C64_DEPS)),)
-include $(C64_DEPS)
endif
ifneq ($(strip $(CXX_DEPS)),)
-include $(CXX_DEPS)
endif
ifneq ($(strip $(S64_DEPS)),)
-include $(S64_DEPS)
endif
ifneq ($(strip $(INO_DEPS)),)
-include $(INO_DEPS)
endif
ifneq ($(strip $(CLA_DEPS)),)
-include $(CLA_DEPS)
endif
ifneq ($(strip $(S55_DEPS)),)
-include $(S55_DEPS)
endif
ifneq ($(strip $(SV7A_DEPS)),)
-include $(SV7A_DEPS)
endif
ifneq ($(strip $(C62_DEPS)),)
-include $(C62_DEPS)
endif
ifneq ($(strip $(C67_DEPS)),)
-include $(C67_DEPS)
endif
ifneq ($(strip $(PDE_DEPS)),)
-include $(PDE_DEPS)
endif
ifneq ($(strip $(K_DEPS)),)
-include $(K_DEPS)
endif
ifneq ($(strip $(C_DEPS)),)
-include $(C_DEPS)
endif
ifneq ($(strip $(CC_DEPS)),)
-include $(CC_DEPS)
endif
ifneq ($(strip $(C++_DEPS)),)
-include $(C++_DEPS)
endif
ifneq ($(strip $(C43_DEPS)),)
-include $(C43_DEPS)
endif
ifneq ($(strip $(S43_DEPS)),)
-include $(S43_DEPS)
endif
ifneq ($(strip $(ASM_DEPS)),)
-include $(ASM_DEPS)
endif
ifneq ($(strip $(S_UPPER_DEPS)),)
-include $(S_UPPER_DEPS)
endif
ifneq ($(strip $(CPP_DEPS)),)
-include $(CPP_DEPS)
endif
ifneq ($(strip $(SA_DEPS)),)
-include $(SA_DEPS)
endif
endif

-include ../makefile.defs

# Add inputs and outputs from these tool invocations to the build variables 
EXE_OUTPUTS += \
firstEffect.out \

EXE_OUTPUTS__QUOTED += \
"firstEffect.out" \

BIN_OUTPUTS += \
firstEffect.hex \

BIN_OUTPUTS__QUOTED += \
"firstEffect.hex" \


# All Target
all: firstEffect.out

# Tool invocations
firstEffect.out: $(OBJS) $(CMD_SRCS) $(GEN_CMDS)
	@echo 'Building target: $@'
	@echo 'Invoking: C2000 Linker'
	"/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/bin/cl2000" -v28 -ml -mt --float_support=fpu32 --advice:performance=all -g --c99 --display_error_number --diag_warning=225 --diag_wrap=off -z -m"firstEffect.map" --stack_size=0x300 --warn_sections -i"/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/lib" -i"/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/include" --reread_libs --display_error_number --diag_wrap=off --xml_link_info="firstEffect_linkInfo.xml" --rom_model -o "firstEffect.out" $(ORDERED_OBJS)
	@echo 'Finished building target: $@'
	@echo ' '

firstEffect.hex: $(EXE_OUTPUTS)
	@echo 'Invoking: C2000 Hex Utility'
	"/Applications/ti/ccsv6/tools/compiler/c2000_15.12.3.LTS/bin/hex2000"  -o "firstEffect.hex" $(EXE_OUTPUTS__QUOTED)
	@echo 'Finished building: $@'
	@echo ' '

# Other Targets
clean:
	-$(RM) $(BIN_OUTPUTS__QUOTED)$(EXE_OUTPUTS__QUOTED)
	-$(RM) "Sram.obj" "analogToDigitalConverter.obj" "audioCntrl.obj" "digitalToAnalogConverter.obj" "james_delay_in_MAIN.obj" "timer.obj" "Common/DSP2833x_ADC_cal.obj" "Common/DSP2833x_CpuTimers.obj" "Common/DSP2833x_DMA.obj" "Common/DSP2833x_DefaultIsr.obj" "Common/DSP2833x_I2C.obj" "Common/DSP2833x_Mcbsp.obj" "Common/DSP2833x_MemCopy.obj" "Common/DSP2833x_PieCtrl.obj" "Common/DSP2833x_PieVect.obj" "Common/DSP2833x_Sci.obj" "Common/DSP2833x_Spi.obj" "Common/DSP2833x_SysCtrl.obj" "Common/DSP2833x_Xintf.obj" "Common/DSP2833x_usDelay.obj" "Headers/DSP2833x_GlobalVariableDefs.obj" 
	-$(RM) "Sram.d" "analogToDigitalConverter.d" "audioCntrl.d" "digitalToAnalogConverter.d" "james_delay_in_MAIN.d" "timer.d" "Common/DSP2833x_CpuTimers.d" "Common/DSP2833x_DMA.d" "Common/DSP2833x_DefaultIsr.d" "Common/DSP2833x_I2C.d" "Common/DSP2833x_Mcbsp.d" "Common/DSP2833x_MemCopy.d" "Common/DSP2833x_PieCtrl.d" "Common/DSP2833x_PieVect.d" "Common/DSP2833x_Sci.d" "Common/DSP2833x_Spi.d" "Common/DSP2833x_SysCtrl.d" "Common/DSP2833x_Xintf.d" "Headers/DSP2833x_GlobalVariableDefs.d" 
	-$(RM) "Common/DSP2833x_ADC_cal.d" "Common/DSP2833x_usDelay.d" 
	-@echo 'Finished clean'
	-@echo ' '

.PHONY: all clean dependents
.SECONDARY:

-include ../makefile.targets

