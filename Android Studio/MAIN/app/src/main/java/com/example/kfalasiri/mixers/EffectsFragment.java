package com.example.kfalasiri.mixers;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class EffectsFragment extends Fragment{
    ////////////////////////////////////////////GLOBALS////////////////////////////////////////////
    private static final String TAG = "EffectsMicFragment";
    View view;


    ////////////////////////////////////////////On Create//////////////////////////////////////////
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.effects_fragment, container, false);
        bottomLineEffectsInit();
        bottomMicEffectsInit();
        return view;
    }

    BottomSheetBehavior lineReverbBehavior, lineChorusBehavior, lineCrusherBehavior,
            lineFlangerBehavior, linePhaserBehavior, lineDelayBehavior, lineTremoloBehavior, lineBehaviorBottom,
        lineAutowahBehavior, lineDistortionBehavior, lineNoisegateBehavior, lineAtbBehavior, lineRingmodBehavior;
    public void bottomLineEffectsInit() {

        final Button lineReverbButton = (Button) view.findViewById(R.id.line_reverb_button);
        final Button lineChorusButton = (Button) view.findViewById(R.id.line_chorus_button);
        final Button lineCrusherButton = (Button) view.findViewById(R.id.line_bitcrusher_button);
        final Button lineFlangerButton = (Button) view.findViewById(R.id.line_flanger_button);
        final Button linePhaserButton = (Button) view.findViewById(R.id.line_phaser_button);
        final Button lineDelayButton = (Button) view.findViewById(R.id.line_delay_button);
        final Button lineTremoloButton = (Button) view.findViewById(R.id.line_tremolo_button);
        final Button lineNoisegateButton = (Button) view.findViewById(R.id.line_noisegate_button);
        final Button lineAtbButton = (Button) view.findViewById(R.id.line_atb_button);
        final Button lineRingmodButton = (Button) view.findViewById(R.id.line_ringmod_button);
        final Button lineDistortionButton = (Button) view.findViewById(R.id.line_distortion_button);
        final Button lineAutowahButton = (Button) view.findViewById(R.id.line_autowah_button);


        final ToggleButton lineReverbSwitch = (ToggleButton) view.findViewById(R.id.LineReverbSwitch);
        final ToggleButton lineChorusSwitch = (ToggleButton) view.findViewById(R.id.LineChorusSwitch);
        final ToggleButton lineCrusherSwitch = (ToggleButton) view.findViewById(R.id.LineCrusherSwitch);
        final ToggleButton lineFlangerSwitch = (ToggleButton) view.findViewById(R.id.LineFlangerSwitch);
        final ToggleButton linePhaserSwitch = (ToggleButton) view.findViewById(R.id.LinePhaserSwitch);
        final ToggleButton lineDelaySwitch = (ToggleButton) view.findViewById(R.id.LineDelaySwitch);
        final ToggleButton lineTremoloSwitch = (ToggleButton) view.findViewById(R.id.LineTremoloSwitch);
        final ToggleButton lineAutowahSwitch = (ToggleButton) view.findViewById(R.id.LineAutowahSwitch);
        final ToggleButton lineDistortionSwitch = (ToggleButton) view.findViewById(R.id.LineDistortionSwitch);
        final ToggleButton lineNoisegateSwitch = (ToggleButton) view.findViewById(R.id.LineNoisegateSwitch);
        final ToggleButton lineAtbSwitch = (ToggleButton) view.findViewById(R.id.LineAtbSwitch);
        final ToggleButton lineRingmodSwitch = (ToggleButton) view.findViewById(R.id.LineRingmodSwitch);

        lineChorusButton.setBackgroundColor(Color.WHITE);
        lineReverbButton.setBackgroundColor(Color.WHITE);
        lineCrusherButton.setBackgroundColor(Color.WHITE);
        lineFlangerButton.setBackgroundColor(Color.WHITE);
        lineDelayButton.setBackgroundColor(Color.WHITE);
        linePhaserButton.setBackgroundColor(Color.WHITE);
        lineTremoloButton.setBackgroundColor(Color.WHITE);
        lineNoisegateButton.setBackgroundColor(Color.WHITE);
        lineAtbButton.setBackgroundColor(Color.WHITE);
        lineRingmodButton.setBackgroundColor(Color.WHITE);
        lineDistortionButton.setBackgroundColor(Color.WHITE);
        lineAutowahButton.setBackgroundColor(Color.WHITE);

        View lineTremoloView = view.findViewById(R.id.lineTremoloBottom);
        lineTremoloBehavior = BottomSheetBehavior.from(lineTremoloView);
        lineTremoloBehavior.setPeekHeight(0);
        lineTremoloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if (lineTremoloBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if (!lineTremoloSwitch.isChecked()) {
                        lineTremoloButton.setBackgroundColor(Color.parseColor("#82dcff"));
                    }

                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDistortionBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else {
                    if(!lineTremoloSwitch.isChecked()) {
                        lineTremoloButton.setBackgroundColor(Color.WHITE);
                    }
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!lineDistortionSwitch.isChecked())
                {
                    lineDistortionButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineAtbSwitch.isChecked())
                {
                    lineAtbButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineAutowahSwitch.isChecked())
                {
                    lineAutowahButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineRingmodSwitch.isChecked())
                {
                    lineRingmodButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineNoisegateSwitch.isChecked())
                {
                    lineNoisegateButton.setBackgroundColor(Color.WHITE);
                }
                if (!lineReverbSwitch.isChecked()) {
                    lineReverbButton.setBackgroundColor(Color.WHITE);
                }
                if (!lineChorusSwitch.isChecked()) {
                    lineChorusButton.setBackgroundColor(Color.WHITE);
                }
                if (!lineCrusherSwitch.isChecked()) {
                    lineCrusherButton.setBackgroundColor(Color.WHITE);
                }
                if (!linePhaserSwitch.isChecked()) {
                    linePhaserButton.setBackgroundColor(Color.WHITE);
                }
                if (!lineFlangerSwitch.isChecked()) {
                    lineFlangerButton.setBackgroundColor(Color.WHITE);
                }
                if (!lineDelaySwitch.isChecked()) {
                    lineDelayButton.setBackgroundColor(Color.WHITE);
                }
            }
        });

        lineTremoloSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lineTremoloButton.setBackgroundColor(Color.BLUE);
                } else {
                    if(lineTremoloBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !lineTremoloSwitch.isChecked())
                    {
                        lineTremoloButton.setBackgroundColor(Color.parseColor("#82ffa4"));
                    }
                    else
                    {
                        lineTremoloButton.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        });



        View lineReverbView = view.findViewById(R.id.lineReverbBottom);
        lineReverbBehavior = BottomSheetBehavior.from(lineReverbView);
        lineReverbBehavior.setPeekHeight(0);
        lineReverbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(lineReverbBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if(!lineReverbSwitch.isChecked())
                    {
                        lineReverbButton.setBackgroundColor(Color.parseColor("#82dcff"));
                    }

                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    lineReverbButton.setBackgroundColor(Color.WHITE);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!lineTremoloSwitch.isChecked())
                {
                    lineTremoloButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineChorusSwitch.isChecked())
                {
                    lineChorusButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineCrusherSwitch.isChecked())
                {
                    lineCrusherButton.setBackgroundColor(Color.WHITE);
                }
                if(!linePhaserSwitch.isChecked())
                {
                    linePhaserButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineFlangerSwitch.isChecked())
                {
                    lineFlangerButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineDelaySwitch.isChecked())
                {
                    lineDelayButton.setBackgroundColor(Color.WHITE);
                }
            }
        });

        lineReverbSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lineReverbButton.setBackgroundColor(Color.BLUE);
                } else {
                    lineReverbButton.setBackgroundColor(Color.WHITE);
                }
            }
        });


        View lineChorusView = view.findViewById(R.id.lineChorusBottom);
        lineChorusBehavior = BottomSheetBehavior.from(lineChorusView);
        lineChorusBehavior.setPeekHeight(0);
        lineChorusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(lineChorusBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if(!lineChorusSwitch.isChecked())
                    {
                        lineChorusButton.setBackgroundColor(Color.parseColor("#82dcff"));
                    }

                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    lineChorusButton.setBackgroundColor(Color.WHITE);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!lineTremoloSwitch.isChecked())
                {
                    lineTremoloButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineReverbSwitch.isChecked())
                {
                    lineReverbButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineCrusherSwitch.isChecked())
                {
                    lineCrusherButton.setBackgroundColor(Color.WHITE);
                }
                if(!linePhaserSwitch.isChecked())
                {
                    linePhaserButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineFlangerSwitch.isChecked())
                {
                    lineFlangerButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineDelaySwitch.isChecked())
                {
                    lineDelayButton.setBackgroundColor(Color.WHITE);
                }
            }
        });

        lineChorusSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lineChorusButton.setBackgroundColor(Color.BLUE);
                } else {
                    lineChorusButton.setBackgroundColor(Color.WHITE);
                }
            }
        });

        View lineCrusherView = view.findViewById(R.id.lineCrusherBottom);
        lineCrusherBehavior = BottomSheetBehavior.from(lineCrusherView);
        lineCrusherBehavior.setPeekHeight(0);
        lineCrusherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(lineCrusherBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if(!lineCrusherSwitch.isChecked())
                    {
                        lineCrusherButton.setBackgroundColor(Color.parseColor("#82dcff"));
                    }

                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    lineCrusherButton.setBackgroundColor(Color.WHITE);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!lineTremoloSwitch.isChecked())
                {
                    lineTremoloButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineReverbSwitch.isChecked())
                {
                    lineReverbButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineChorusSwitch.isChecked())
                {
                    lineChorusButton.setBackgroundColor(Color.WHITE);
                }
                if(!linePhaserSwitch.isChecked())
                {
                    linePhaserButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineFlangerSwitch.isChecked())
                {
                    lineFlangerButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineDelaySwitch.isChecked())
                {
                    lineDelayButton.setBackgroundColor(Color.WHITE);
                }
            }
        });


        lineCrusherSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lineCrusherButton.setBackgroundColor(Color.BLUE);
                } else {
                    lineCrusherButton.setBackgroundColor(Color.WHITE);
                }
            }
        });

        View lineFlangerView = view.findViewById(R.id.lineFlangerBottom);
        lineFlangerBehavior = BottomSheetBehavior.from(lineFlangerView);
        lineFlangerBehavior.setPeekHeight(0);
        lineFlangerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(lineFlangerBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if(!lineFlangerSwitch.isChecked())
                    {
                        lineFlangerButton.setBackgroundColor(Color.parseColor("#82dcff"));
                    }

                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    lineFlangerButton.setBackgroundColor(Color.WHITE);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!lineTremoloSwitch.isChecked())
                {
                    lineTremoloButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineReverbSwitch.isChecked())
                {
                    lineReverbButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineChorusSwitch.isChecked())
                {
                    lineChorusButton.setBackgroundColor(Color.WHITE);
                }
                if(!linePhaserSwitch.isChecked())
                {
                    linePhaserButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineCrusherSwitch.isChecked())
                {
                    lineCrusherButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineDelaySwitch.isChecked())
                {
                    lineDelayButton.setBackgroundColor(Color.WHITE);
                }
            }
        });

        lineFlangerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lineFlangerButton.setBackgroundColor(Color.BLUE);
                } else {
                    lineFlangerButton.setBackgroundColor(Color.WHITE);
                }
            }
        });

        View linePhaserView = view.findViewById(R.id.linePhaserBottom);
        linePhaserBehavior = BottomSheetBehavior.from(linePhaserView);
        linePhaserBehavior.setPeekHeight(0);
        linePhaserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(linePhaserBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if(!linePhaserSwitch.isChecked())
                    {
                        linePhaserButton.setBackgroundColor(Color.parseColor("#82dcff"));
                    }

                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    linePhaserButton.setBackgroundColor(Color.WHITE);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!lineTremoloSwitch.isChecked())
                {
                    lineTremoloButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineReverbSwitch.isChecked())
                {
                    lineReverbButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineChorusSwitch.isChecked())
                {
                    lineChorusButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineCrusherSwitch.isChecked())
                {
                    lineCrusherButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineFlangerSwitch.isChecked())
                {
                    lineFlangerButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineDelaySwitch.isChecked())
                {
                    lineDelayButton.setBackgroundColor(Color.WHITE);
                }
            }
        });

        linePhaserSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    linePhaserButton.setBackgroundColor(Color.BLUE);
                } else {
                    linePhaserButton.setBackgroundColor(Color.WHITE);
                }
            }
        });

        View lineDelayView = view.findViewById(R.id.lineDelayBottom);
        lineDelayBehavior = BottomSheetBehavior.from(lineDelayView);
        lineDelayBehavior.setPeekHeight(0);
        lineDelayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(lineDelayBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if(!lineDelaySwitch.isChecked())
                    {
                        lineDelayButton.setBackgroundColor(Color.parseColor("#82dcff"));
                    }

                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    lineDelayButton.setBackgroundColor(Color.WHITE);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                if(!lineTremoloSwitch.isChecked())
                {
                    lineTremoloButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineReverbSwitch.isChecked())
                {
                    lineReverbButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineChorusSwitch.isChecked())
                {
                    lineChorusButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineCrusherSwitch.isChecked())
                {
                    lineCrusherButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineFlangerSwitch.isChecked())
                {
                    lineFlangerButton.setBackgroundColor(Color.WHITE);
                }
                if(!linePhaserSwitch.isChecked())
                {
                    linePhaserButton.setBackgroundColor(Color.WHITE);
                }
            }
        });
        lineDelaySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lineDelayButton.setBackgroundColor(Color.BLUE);
                } else {
                    lineDelayButton.setBackgroundColor(Color.WHITE);
                }
            }
        });
    }

    BottomSheetBehavior micReverbBehavior, micChorusBehavior, micCrusherBehavior,
            micFlangerBehavior, micPhaserBehavior, micDelayBehavior, micTremoloBehavior, micAutowahBehavior, micBassemulatorBehavior,
     micRingmodBehavior, micNoisegateBehavior, micAtbBehavior;
    public void bottomMicEffectsInit(){

        final Button micDelayButton = (Button) view.findViewById(R.id.mic_delay_button);
        final Button micReverbButton = (Button) view.findViewById(R.id.mic_reverb_button);
        final Button micChorusButton = (Button) view.findViewById(R.id.mic_chorus_button);
        final Button micPhaserButton = (Button) view.findViewById(R.id.mic_phaser_button);
        final Button micFlangerButton = (Button) view.findViewById(R.id.mic_flanger_button);
        final Button micCrusherButton = (Button) view.findViewById(R.id.mic_bitcrusher_button);
        final Button micTremoloButton = (Button) view.findViewById(R.id.mic_tremolo_button);
        final Button micAutowahButton = (Button) view.findViewById(R.id.mic_autowah_button);
        final Button micBassemulatorButton = (Button) view.findViewById(R.id.mic_bassemulator_button);
        final Button micRingmodButton = (Button) view.findViewById(R.id.mic_ringmod_button);
        final Button micNoisegateButton = (Button) view.findViewById(R.id.mic_noisegate_button);
        final Button micAtbButton = (Button) view.findViewById(R.id.mic_atb_button);

        final ToggleButton micReverbSwitch = (ToggleButton) view.findViewById(R.id.MicReverbSwitch);
        final ToggleButton micChorusSwitch = (ToggleButton) view.findViewById(R.id.MicChorusSwitch);
        final ToggleButton micCrusherSwitch = (ToggleButton) view.findViewById(R.id.MicCrusherSwitch);
        final ToggleButton micFlangerSwitch = (ToggleButton) view.findViewById(R.id.MicFlangerSwitch);
        final ToggleButton micPhaserSwitch = (ToggleButton) view.findViewById(R.id.MicPhaserSwitch);
        final ToggleButton micDelaySwitch = (ToggleButton) view.findViewById(R.id.MicDelaySwitch);
        final ToggleButton micNoisegateSwitch = (ToggleButton) view.findViewById(R.id.MicNoisegateSwitch);
        final ToggleButton micAtbSwitch = (ToggleButton) view.findViewById(R.id.MicAtbSwitch);
        final ToggleButton micRingmodSwitch = (ToggleButton) view.findViewById(R.id.MicRingmodSwitch);
        final ToggleButton micTremoloSwitch = (ToggleButton) view.findViewById(R.id.MicTremoloSwitch);
        final ToggleButton micAutowahSwitch = (ToggleButton) view.findViewById(R.id.MicAutowahSwitch);
        final ToggleButton micBassemulatorSwitch = (ToggleButton) view.findViewById(R.id.MicBassemulatorSwitch);


        micChorusButton.setBackgroundColor(Color.WHITE);
        micReverbButton.setBackgroundColor(Color.WHITE);
        micCrusherButton.setBackgroundColor(Color.WHITE);
        micFlangerButton.setBackgroundColor(Color.WHITE);
        micDelayButton.setBackgroundColor(Color.WHITE);
        micPhaserButton.setBackgroundColor(Color.WHITE);
        micTremoloButton.setBackgroundColor(Color.WHITE);
        micAutowahButton.setBackgroundColor(Color.WHITE);
        micBassemulatorButton.setBackgroundColor(Color.WHITE);
        micRingmodButton.setBackgroundColor(Color.WHITE);
        micNoisegateButton.setBackgroundColor(Color.WHITE);
        micAtbButton.setBackgroundColor(Color.WHITE);


        final View micBassemulatorView = view.findViewById(R.id.micBassemulatorBottom);
        micBassemulatorBehavior = BottomSheetBehavior.from(micBassemulatorView);
        micBassemulatorBehavior.setPeekHeight(0);
        micBassemulatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(micBassemulatorBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    if(!micBassemulatorSwitch.isChecked())
                    {
                        micBassemulatorButton.setBackgroundColor(Color.parseColor("#82ffa4"));
                    }

                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                }
                else {
                    if(!micBassemulatorSwitch.isChecked()) {
                        micBassemulatorButton.setBackgroundColor(Color.WHITE);
                    }
                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundColor(Color.WHITE);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundColor(Color.WHITE);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundColor(Color.WHITE);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundColor(Color.WHITE);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundColor(Color.WHITE);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundColor(Color.WHITE);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundColor(Color.WHITE);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundColor(Color.WHITE);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundColor(Color.WHITE);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundColor(Color.WHITE);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundColor(Color.WHITE);
                }
            }
        });



        micBassemulatorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micBassemulatorButton.setBackgroundColor(Color.GREEN);
                }
                else {
                    if(micBassemulatorBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micBassemulatorSwitch.isChecked())
                    {
                        micBassemulatorButton.setBackgroundColor(Color.parseColor("#82ffa4"));
                    }
                    else
                    {
                        micBassemulatorButton.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        });


        final View micNoisegateView = view.findViewById(R.id.micNoisegateBottom);
        micNoisegateBehavior = BottomSheetBehavior.from(micNoisegateView);
        micNoisegateBehavior.setPeekHeight(0);
        micNoisegateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(micNoisegateBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    if(!micNoisegateSwitch.isChecked())
                    {
                        micNoisegateButton.setBackgroundColor(Color.parseColor("#82ffa4"));
                    }

                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                }
                else {
                    if(!micNoisegateSwitch.isChecked()) {
                        micNoisegateButton.setBackgroundColor(Color.WHITE);
                    }
                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundColor(Color.WHITE);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundColor(Color.WHITE);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundColor(Color.WHITE);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundColor(Color.WHITE);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundColor(Color.WHITE);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundColor(Color.WHITE);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundColor(Color.WHITE);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundColor(Color.WHITE);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundColor(Color.WHITE);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundColor(Color.WHITE);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundColor(Color.WHITE);
                }
            }
        });



        micNoisegateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micNoisegateButton.setBackgroundColor(Color.GREEN);
                }
                else {
                    if(micNoisegateBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micNoisegateSwitch.isChecked())
                    {
                        micNoisegateButton.setBackgroundColor(Color.parseColor("#82ffa4"));
                    }
                    else
                    {
                        micNoisegateButton.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        });

        final View micAtbView = view.findViewById(R.id.micAtbBottom);
        micAtbBehavior = BottomSheetBehavior.from(micAtbView);
        micAtbBehavior.setPeekHeight(0);
        micAtbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(micAtbBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    if(!micAtbSwitch.isChecked())
                    {
                        micAtbButton.setBackgroundColor(Color.parseColor("#82ffa4"));
                    }

                    micAtbBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                }
                else {
                    if(!micAtbSwitch.isChecked()) {
                        micAtbButton.setBackgroundColor(Color.WHITE);
                    }
                    micAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundColor(Color.WHITE);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundColor(Color.WHITE);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundColor(Color.WHITE);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundColor(Color.WHITE);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundColor(Color.WHITE);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundColor(Color.WHITE);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundColor(Color.WHITE);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundColor(Color.WHITE);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundColor(Color.WHITE);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundColor(Color.WHITE);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundColor(Color.WHITE);
                }
            }
        });



        micAtbSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micAtbButton.setBackgroundColor(Color.GREEN);
                }
                else {
                    if(micAtbBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micAtbSwitch.isChecked())
                    {
                        micAtbButton.setBackgroundColor(Color.parseColor("#82ffa4"));
                    }
                    else
                    {
                        micAtbButton.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        });


        final View micRingmodView = view.findViewById(R.id.micRingmodBottom);
        micRingmodBehavior = BottomSheetBehavior.from(micRingmodView);
        micRingmodBehavior.setPeekHeight(0);
        micRingmodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(micRingmodBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    if(!micRingmodSwitch.isChecked())
                    {
                        micRingmodButton.setBackgroundColor(Color.parseColor("#82ffa4"));
                    }

                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                }
                else {
                    if(!micRingmodSwitch.isChecked()) {
                        micRingmodButton.setBackgroundColor(Color.WHITE);
                    }
                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundColor(Color.WHITE);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundColor(Color.WHITE);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundColor(Color.WHITE);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundColor(Color.WHITE);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundColor(Color.WHITE);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundColor(Color.WHITE);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundColor(Color.WHITE);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundColor(Color.WHITE);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundColor(Color.WHITE);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundColor(Color.WHITE);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundColor(Color.WHITE);
                }
            }
        });



        micRingmodSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micRingmodButton.setBackgroundColor(Color.GREEN);
                }
                else {
                    if(micRingmodBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micRingmodSwitch.isChecked())
                    {
                        micRingmodButton.setBackgroundColor(Color.parseColor("#82ffa4"));
                    }
                    else
                    {
                        micRingmodButton.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        });


        final View micAutowahView = view.findViewById(R.id.micAutowahBottom);
        micAutowahBehavior = BottomSheetBehavior.from(micAutowahView);
        micAutowahBehavior.setPeekHeight(0);
        micAutowahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(micAutowahBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    if(!micAutowahSwitch.isChecked())
                    {
                        micAutowahButton.setBackgroundColor(Color.parseColor("#82ffa4"));
                    }

                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                }
                else {
                    if(!micAutowahSwitch.isChecked()) {
                        micAutowahButton.setBackgroundColor(Color.WHITE);
                    }
                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundColor(Color.WHITE);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundColor(Color.WHITE);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundColor(Color.WHITE);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundColor(Color.WHITE);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundColor(Color.WHITE);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundColor(Color.WHITE);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundColor(Color.WHITE);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundColor(Color.WHITE);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundColor(Color.WHITE);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundColor(Color.WHITE);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundColor(Color.WHITE);
                }
            }
        });



        micAutowahSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micAutowahButton.setBackgroundColor(Color.GREEN);
                }
                else {
                    if(micAutowahBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micAutowahSwitch.isChecked())
                    {
                        micAutowahButton.setBackgroundColor(Color.parseColor("#82ffa4"));
                    }
                    else
                    {
                        micAutowahButton.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        });


        final View micTremoloView = view.findViewById(R.id.micTremoloBottom);
        micTremoloBehavior = BottomSheetBehavior.from(micTremoloView);
        micTremoloBehavior.setPeekHeight(0);
        micTremoloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(micTremoloBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    if(!micTremoloSwitch.isChecked())
                    {
                        micTremoloButton.setBackgroundColor(Color.parseColor("#82ffa4"));
                    }

                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                }
                else {
                    if(!micTremoloSwitch.isChecked()) {
                        micTremoloButton.setBackgroundColor(Color.WHITE);
                    }
                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundColor(Color.WHITE);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundColor(Color.WHITE);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundColor(Color.WHITE);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundColor(Color.WHITE);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundColor(Color.WHITE);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundColor(Color.WHITE);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundColor(Color.WHITE);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundColor(Color.WHITE);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundColor(Color.WHITE);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundColor(Color.WHITE);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundColor(Color.WHITE);
                }
            }
        });



        micTremoloSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micTremoloButton.setBackgroundColor(Color.GREEN);
                }
                else {
                    if(micTremoloBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micTremoloSwitch.isChecked())
                    {
                        micTremoloButton.setBackgroundColor(Color.parseColor("#82ffa4"));
                    }
                    else
                    {
                        micTremoloButton.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        });


        final View micReverbView = view.findViewById(R.id.micReverbBottom);
        micReverbBehavior = BottomSheetBehavior.from(micReverbView);
        micReverbBehavior.setPeekHeight(0);
        micReverbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(micReverbBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    if(!micReverbSwitch.isChecked())
                    {
                        micReverbButton.setBackgroundColor(Color.parseColor("#82ffa4"));
                    }

                    micReverbBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                }
                else {
                    if(!micReverbSwitch.isChecked()) {
                        micReverbButton.setBackgroundColor(Color.WHITE);
                    }
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundColor(Color.WHITE);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundColor(Color.WHITE);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundColor(Color.WHITE);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundColor(Color.WHITE);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundColor(Color.WHITE);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundColor(Color.WHITE);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundColor(Color.WHITE);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundColor(Color.WHITE);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundColor(Color.WHITE);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundColor(Color.WHITE);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundColor(Color.WHITE);
                }
            }
        });



        micReverbSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micReverbButton.setBackgroundColor(Color.GREEN);
                }
                else {
                    if(micReverbBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micReverbSwitch.isChecked())
                    {
                        micReverbButton.setBackgroundColor(Color.parseColor("#82ffa4"));
                    }
                    else
                    {
                        micReverbButton.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        });




        final View micChorusView = view.findViewById(R.id.micChorusBottom);
        micChorusBehavior = BottomSheetBehavior.from(micChorusView);
        micChorusBehavior.setPeekHeight(0);
        micChorusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {

                if(micChorusBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if(!micChorusSwitch.isChecked())
                    {
                        micChorusButton.setBackgroundColor(Color.parseColor("#82ffa4"));
                    }

                    micChorusBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                }
                else {
                    if(!micChorusSwitch.isChecked()) {
                        micChorusButton.setBackgroundColor(Color.WHITE);
                    }
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundColor(Color.WHITE);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundColor(Color.WHITE);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundColor(Color.WHITE);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundColor(Color.WHITE);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundColor(Color.WHITE);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundColor(Color.WHITE);
                }

                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundColor(Color.WHITE);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundColor(Color.WHITE);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundColor(Color.WHITE);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundColor(Color.WHITE);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundColor(Color.WHITE);
                }
            }
        });

        micChorusSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micChorusButton.setBackgroundColor(Color.GREEN);
                }
                else {
                    if(micChorusBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micChorusSwitch.isChecked())
                    {
                        micChorusButton.setBackgroundColor(Color.parseColor("#82ffa4"));
                    }
                    else
                    {
                        micChorusButton.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        });

        final View micCrusherView = view.findViewById(R.id.micCrusherBottom);
        micCrusherBehavior = BottomSheetBehavior.from(micCrusherView);
        micCrusherBehavior.setPeekHeight(0);
        micCrusherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(micCrusherBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if(!micCrusherSwitch.isChecked())
                    {
                        micCrusherButton.setBackgroundColor(Color.parseColor("#82ffa4"));
                    }


                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                }
                else {
                    if(!micCrusherSwitch.isChecked()) {
                        micCrusherButton.setBackgroundColor(Color.WHITE);
                    }
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundColor(Color.WHITE);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundColor(Color.WHITE);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundColor(Color.WHITE);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundColor(Color.WHITE);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundColor(Color.WHITE);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundColor(Color.WHITE);
                }
                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundColor(Color.WHITE);
                }
                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundColor(Color.WHITE);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundColor(Color.WHITE);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundColor(Color.WHITE);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundColor(Color.WHITE);
                }
            }
        });

        micCrusherSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micCrusherButton.setBackgroundColor(Color.GREEN);
                }
                else {
                    if(micCrusherBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micCrusherSwitch.isChecked())
                    {
                        micCrusherButton.setBackgroundColor(Color.parseColor("#82ffa4"));
                    }
                    else
                    {
                        micCrusherButton.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        });

        final View micFlangerView = view.findViewById(R.id.micFlangerBottom);
        micFlangerBehavior = BottomSheetBehavior.from(micFlangerView);
        micFlangerBehavior.setPeekHeight(0);
        micFlangerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(micFlangerBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if(!micFlangerSwitch.isChecked())
                    {
                        micFlangerButton.setBackgroundColor(Color.parseColor("#82ffa4"));
                    }

                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                else {
                    if(!micFlangerSwitch.isChecked()) {
                        micFlangerButton.setBackgroundColor(Color.WHITE);
                    }
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundColor(Color.WHITE);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundColor(Color.WHITE);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundColor(Color.WHITE);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundColor(Color.WHITE);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundColor(Color.WHITE);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundColor(Color.WHITE);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundColor(Color.WHITE);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundColor(Color.WHITE);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundColor(Color.WHITE);
                }
                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundColor(Color.WHITE);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundColor(Color.WHITE);
                }
            }
        });

        micFlangerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micFlangerButton.setBackgroundColor(Color.GREEN);
                }
                else {
                    if(micFlangerBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micFlangerSwitch.isChecked())
                    {
                        micFlangerButton.setBackgroundColor(Color.parseColor("#82ffa4"));
                    }
                    else
                    {
                        micFlangerButton.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        });

        final View micPhaserView = view.findViewById(R.id.micPhaserBottom);
        micPhaserBehavior = BottomSheetBehavior.from(micPhaserView);
        micPhaserBehavior.setPeekHeight(0);
        micPhaserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(micPhaserBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if(!micPhaserSwitch.isChecked())
                    {
                        micPhaserButton.setBackgroundColor(Color.parseColor("#82ffa4"));
                    }

                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                }

                else {
                    if(!micPhaserSwitch.isChecked()) {
                        micPhaserButton.setBackgroundColor(Color.WHITE);
                    }
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundColor(Color.WHITE);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundColor(Color.WHITE);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundColor(Color.WHITE);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundColor(Color.WHITE);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundColor(Color.WHITE);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundColor(Color.WHITE);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundColor(Color.WHITE);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundColor(Color.WHITE);
                }
                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundColor(Color.WHITE);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundColor(Color.WHITE);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundColor(Color.WHITE);
                }

            }
        });

        micPhaserSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micPhaserButton.setBackgroundColor(Color.GREEN);
                }
                else {
                    if(micPhaserBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micPhaserSwitch.isChecked())
                    {
                        micPhaserButton.setBackgroundColor(Color.parseColor("#82ffa4"));
                    }
                    else
                    {
                        micPhaserButton.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        });

        final View micDelayView = view.findViewById(R.id.micDelayBottom);
        micDelayBehavior = BottomSheetBehavior.from(micDelayView);
        micDelayBehavior.setPeekHeight(0);
        micDelayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(micDelayBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if(!micDelaySwitch.isChecked())
                    {
                        micDelayButton.setBackgroundColor(Color.parseColor("#82ffa4"));
                    }

                    micDelayBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                }

                else {
                    if(!micDelaySwitch.isChecked()) {
                        micDelayButton.setBackgroundColor(Color.WHITE);
                    }
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundColor(Color.WHITE);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundColor(Color.WHITE);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundColor(Color.WHITE);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundColor(Color.WHITE);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundColor(Color.WHITE);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundColor(Color.WHITE);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundColor(Color.WHITE);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundColor(Color.WHITE);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundColor(Color.WHITE);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundColor(Color.WHITE);
                }
                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundColor(Color.WHITE);
                }
            }
        });

        micDelaySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micDelayButton.setBackgroundColor(Color.GREEN);
                }
                else {
                    if(micDelayBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micDelaySwitch.isChecked())
                    {
                        micDelayButton.setBackgroundColor(Color.parseColor("#82ffa4"));
                    }
                    else
                    {
                        micDelayButton.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        });


    }
}
