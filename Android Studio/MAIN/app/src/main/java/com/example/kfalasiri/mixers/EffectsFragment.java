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
import android.widget.ImageButton;
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

        final ImageButton lineReverbButton = (ImageButton) view.findViewById(R.id.line_reverb_button);
        final ImageButton lineChorusButton = (ImageButton) view.findViewById(R.id.line_chorus_button);
        final ImageButton lineCrusherButton = (ImageButton) view.findViewById(R.id.line_bitcrusher_button);
        final ImageButton lineFlangerButton = (ImageButton) view.findViewById(R.id.line_flanger_button);
        final ImageButton linePhaserButton = (ImageButton) view.findViewById(R.id.line_phaser_button);
        final ImageButton lineDelayButton = (ImageButton) view.findViewById(R.id.line_delay_button);
        final ImageButton lineTremoloButton = (ImageButton) view.findViewById(R.id.line_tremolo_button);
        final ImageButton lineNoisegateButton = (ImageButton) view.findViewById(R.id.line_noisegate_button);
        final ImageButton lineAtbButton = (ImageButton) view.findViewById(R.id.line_atb_button);
        final ImageButton lineRingmodButton = (ImageButton) view.findViewById(R.id.line_ringmod_button);
        final ImageButton lineDistortionButton = (ImageButton) view.findViewById(R.id.line_distortion_button);
        final ImageButton lineAutowahButton = (ImageButton) view.findViewById(R.id.line_autowah_button);


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


        View lineTremoloView = view.findViewById(R.id.lineTremoloBottom);
        lineTremoloBehavior = BottomSheetBehavior.from(lineTremoloView);
        lineTremoloBehavior.setPeekHeight(0);
        lineTremoloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if (lineTremoloBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if (!lineTremoloSwitch.isChecked()) {
//                        lineTremoloButton.setBackgroundColor(Color.parseColor("#82dcff"));
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
//                        lineTremoloButton.setBackgroundColor(Color.WHITE);
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
                        lineTremoloButton.setBackgroundColor(Color.parseColor("#82dcff"));
                    }
                    else
                    {
                        lineTremoloButton.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        });

        View lineAtbView = view.findViewById(R.id.lineAtbBottom);
        lineAtbBehavior = BottomSheetBehavior.from(lineAtbView);
        lineAtbBehavior.setPeekHeight(0);
        lineAtbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if (lineAtbBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if (!lineAtbSwitch.isChecked()) {
                        lineAtbButton.setBackgroundColor(Color.parseColor("#82dcff"));
                    }

                    lineAtbBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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
                    if(!lineAtbSwitch.isChecked()) {
                        lineAtbButton.setBackgroundColor(Color.WHITE);
                    }
                    lineAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!lineDistortionSwitch.isChecked())
                {
                    lineDistortionButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineTremoloSwitch.isChecked())
                {
                    lineTremoloButton.setBackgroundColor(Color.WHITE);
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

        lineAtbSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lineAtbButton.setBackgroundColor(Color.BLUE);
                } else {
                    if(lineAtbBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !lineAtbSwitch.isChecked())
                    {
                        lineAtbButton.setBackgroundColor(Color.parseColor("#82dcff"));
                    }
                    else
                    {
                        lineAtbButton.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        });

        View lineRingmodView = view.findViewById(R.id.lineRingmodBottom);
        lineRingmodBehavior = BottomSheetBehavior.from(lineRingmodView);
        lineRingmodBehavior.setPeekHeight(0);
        lineRingmodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if (lineRingmodBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if (!lineRingmodSwitch.isChecked()) {
                        lineRingmodButton.setBackgroundColor(Color.parseColor("#82dcff"));
                    }

                    lineRingmodBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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
                    if(!lineRingmodSwitch.isChecked()) {
                        lineRingmodButton.setBackgroundColor(Color.WHITE);
                    }
                    lineRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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
                if(!lineTremoloSwitch.isChecked())
                {
                    lineTremoloButton.setBackgroundColor(Color.WHITE);
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

        lineRingmodSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lineRingmodButton.setBackgroundColor(Color.BLUE);
                } else {
                    if(lineRingmodBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !lineRingmodSwitch.isChecked())
                    {
                        lineRingmodButton.setBackgroundColor(Color.parseColor("#82dcff"));
                    }
                    else
                    {
                        lineRingmodButton.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        });

        View lineDistortionView = view.findViewById(R.id.lineDistortionBottom);
        lineDistortionBehavior = BottomSheetBehavior.from(lineDistortionView);
        lineDistortionBehavior.setPeekHeight(0);
        lineDistortionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if (lineDistortionBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if (!lineDistortionSwitch.isChecked()) {
                        lineDistortionButton.setBackgroundColor(Color.parseColor("#82dcff"));
                    }

                    lineDistortionBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else {
                    if(!lineDistortionSwitch.isChecked()) {
                        lineDistortionButton.setBackgroundColor(Color.WHITE);
                    }
                    lineDistortionBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!lineTremoloSwitch.isChecked())
                {
                    lineTremoloButton.setBackgroundColor(Color.WHITE);
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

        lineDistortionSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lineDistortionButton.setBackgroundColor(Color.BLUE);
                } else {
                    if(lineDistortionBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !lineDistortionSwitch.isChecked())
                    {
                        lineDistortionButton.setBackgroundColor(Color.parseColor("#82dcff"));
                    }
                    else
                    {
                        lineDistortionButton.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        });


        View lineNoisegateView = view.findViewById(R.id.lineNoisegateBottom);
        lineNoisegateBehavior = BottomSheetBehavior.from(lineNoisegateView);
        lineNoisegateBehavior.setPeekHeight(0);
        lineNoisegateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if (lineNoisegateBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if (!lineNoisegateSwitch.isChecked()) {
                        lineNoisegateButton.setBackgroundColor(Color.parseColor("#82dcff"));
                    }

                    lineNoisegateBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDistortionBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else {
                    if(!lineNoisegateSwitch.isChecked()) {
                        lineNoisegateButton.setBackgroundColor(Color.WHITE);
                    }
                    lineNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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
                if(!lineTremoloSwitch.isChecked())
                {
                    lineTremoloButton.setBackgroundColor(Color.WHITE);
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

        lineNoisegateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lineNoisegateButton.setBackgroundColor(Color.BLUE);
                } else {
                    if(lineNoisegateBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !lineNoisegateSwitch.isChecked())
                    {
                        lineNoisegateButton.setBackgroundColor(Color.parseColor("#82dcff"));
                    }
                    else
                    {
                        lineNoisegateButton.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        });

        View lineAutowahView = view.findViewById(R.id.lineAutowahBottom);
        lineAutowahBehavior = BottomSheetBehavior.from(lineAutowahView);
        lineAutowahBehavior.setPeekHeight(0);
        lineAutowahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if (lineAutowahBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if (!lineAutowahSwitch.isChecked()) {
                        lineAutowahButton.setBackgroundColor(Color.parseColor("#82dcff"));
                    }

                    lineAutowahBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDistortionBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else {
                    if(!lineAutowahSwitch.isChecked()) {
                        lineAutowahButton.setBackgroundColor(Color.WHITE);
                    }
                    lineAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!lineDistortionSwitch.isChecked())
                {
                    lineDistortionButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineAtbSwitch.isChecked())
                {
                    lineAtbButton.setBackgroundColor(Color.WHITE);
                }
                if(!lineTremoloSwitch.isChecked())
                {
                    lineTremoloButton.setBackgroundColor(Color.WHITE);
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

        lineAutowahSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lineAutowahButton.setBackgroundColor(Color.BLUE);
                } else {
                    if(lineAutowahBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !lineAutowahSwitch.isChecked())
                    {
                        lineAutowahButton.setBackgroundColor(Color.parseColor("#82dcff"));
                    }
                    else
                    {
                        lineAutowahButton.setBackgroundColor(Color.WHITE);
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
                    lineAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDistortionBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    if(!lineReverbSwitch.isChecked()) {
                        lineReverbButton.setBackgroundColor(Color.WHITE);
                    }
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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
                    if(lineReverbBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !lineReverbSwitch.isChecked())
                    {
                        lineReverbButton.setBackgroundColor(Color.parseColor("#82dcff"));
                    }
                    else
                    {
                        lineReverbButton.setBackgroundColor(Color.WHITE);
                    }
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
                    lineAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDistortionBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    if(!lineChorusSwitch.isChecked()) {
                        lineChorusButton.setBackgroundColor(Color.WHITE);
                    }
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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
                    if(lineChorusBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !lineChorusSwitch.isChecked())
                    {
                        lineChorusButton.setBackgroundColor(Color.parseColor("#82dcff"));
                    }
                    else
                    {
                        lineChorusButton.setBackgroundColor(Color.WHITE);
                    }
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
                    lineAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDistortionBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    if(!lineCrusherSwitch.isChecked()) {
                        lineCrusherButton.setBackgroundColor(Color.WHITE);
                    }
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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
                    if(lineCrusherBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !lineCrusherSwitch.isChecked())
                    {
                        lineCrusherButton.setBackgroundColor(Color.parseColor("#82dcff"));
                    }
                    else
                    {
                        lineCrusherButton.setBackgroundColor(Color.WHITE);
                    }
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
                    lineAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDistortionBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    if(!lineFlangerSwitch.isChecked()) {
                    lineFlangerButton.setBackgroundColor(Color.WHITE);
                }
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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
                    if(lineFlangerBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !lineFlangerSwitch.isChecked())
                    {
                        lineFlangerButton.setBackgroundColor(Color.parseColor("#82dcff"));
                    }
                    else
                    {
                        lineFlangerButton.setBackgroundColor(Color.WHITE);
                    }
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
                    lineAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDistortionBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    if(!linePhaserSwitch.isChecked()) {
                        linePhaserButton.setBackgroundColor(Color.WHITE);
                    }
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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
                    if(linePhaserBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !linePhaserSwitch.isChecked())
                    {
                        linePhaserButton.setBackgroundColor(Color.parseColor("#82dcff"));
                    }
                    else
                    {
                        linePhaserButton.setBackgroundColor(Color.WHITE);
                    }
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
                    lineAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDistortionBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    if(!lineDelaySwitch.isChecked()) {
                        lineDelayButton.setBackgroundColor(Color.WHITE);
                    }
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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
                    if(lineDelayBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !lineDelaySwitch.isChecked())
                    {
                        lineDelayButton.setBackgroundColor(Color.parseColor("#82dcff"));
                    }
                    else
                    {
                        lineDelayButton.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        });
    }

    BottomSheetBehavior micReverbBehavior, micChorusBehavior, micCrusherBehavior,
            micFlangerBehavior, micPhaserBehavior, micDelayBehavior, micTremoloBehavior, micAutowahBehavior, micBassemulatorBehavior,
     micRingmodBehavior, micNoisegateBehavior, micAtbBehavior;
    public void bottomMicEffectsInit(){

        final ImageButton micDelayButton = (ImageButton) view.findViewById(R.id.mic_delay_button);
        final ImageButton micReverbButton = (ImageButton) view.findViewById(R.id.mic_reverb_button);
        final ImageButton micChorusButton = (ImageButton) view.findViewById(R.id.mic_chorus_button);
        final ImageButton micPhaserButton = (ImageButton) view.findViewById(R.id.mic_phaser_button);
        final ImageButton micFlangerButton = (ImageButton) view.findViewById(R.id.mic_flanger_button);
        final ImageButton micCrusherButton = (ImageButton) view.findViewById(R.id.mic_bitcrusher_button);
        final ImageButton micTremoloButton = (ImageButton) view.findViewById(R.id.mic_tremolo_button);
        final ImageButton micAutowahButton = (ImageButton) view.findViewById(R.id.mic_autowah_button);
        final ImageButton micBassemulatorButton = (ImageButton) view.findViewById(R.id.mic_bassemulator_button);
        final ImageButton micRingmodButton = (ImageButton) view.findViewById(R.id.mic_ringmod_button);
        final ImageButton micNoisegateButton = (ImageButton) view.findViewById(R.id.mic_noisegate_button);
        final ImageButton micAtbButton = (ImageButton) view.findViewById(R.id.mic_atb_button);

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





        final View micBassemulatorView = view.findViewById(R.id.micBassemulatorBottom);
        micBassemulatorBehavior = BottomSheetBehavior.from(micBassemulatorView);
        micBassemulatorBehavior.setPeekHeight(0);
        micBassemulatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(micBassemulatorBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    if(!micBassemulatorSwitch.isChecked())
                    {
                        micBassemulatorButton.setBackgroundResource(R.drawable.bassemulatorbuttoninbetween);
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
                        micBassemulatorButton.setBackgroundResource(R.drawable.bassemulatorbutton);
                    }
                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });



        micBassemulatorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micBassemulatorButton.setBackgroundResource(R.drawable.bassemulatorbuttonpressed);
                }
                else {
                    if(micBassemulatorBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micBassemulatorSwitch.isChecked())
                    {
                        micBassemulatorButton.setBackgroundResource(R.drawable.bassemulatorbuttoninbetween);
                    }
                    else
                    {
                        micBassemulatorButton.setBackgroundResource(R.drawable.bassemulatorbutton);
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
                        micNoisegateButton.setBackgroundResource(R.drawable.noisegatebuttoninbetween);
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
                        micNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                    }
                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundResource(R.drawable.bassemulatorbutton);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });



        micNoisegateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micNoisegateButton.setBackgroundResource(R.drawable.noisegatebuttonpressed);
                }
                else {
                    if(micNoisegateBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micNoisegateSwitch.isChecked())
                    {
                        micNoisegateButton.setBackgroundResource(R.drawable.noisegatebuttoninbetween);
                    }
                    else
                    {
                        micNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
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
                        micAtbButton.setBackgroundResource(R.drawable.analog_tb_buttoninbetween);
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
                        micAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                    }
                    micAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }


                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundResource(R.drawable.bassemulatorbutton);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });



        micAtbSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micAtbButton.setBackgroundResource(R.drawable.analog_tb_buttonpressed);
                }
                else {
                    if(micAtbBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micAtbSwitch.isChecked())
                    {
                        micAtbButton.setBackgroundResource(R.drawable.analog_tb_buttoninbetween);
                    }
                    else
                    {
                        micAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
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
                        micRingmodButton.setBackgroundResource(R.drawable.ringmodbuttoninbetween);
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
                        micRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                    }
                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundResource(R.drawable.bassemulatorbutton);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });



        micRingmodSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micRingmodButton.setBackgroundResource(R.drawable.ringmodbuttonpressed);
                }
                else {
                    if(micRingmodBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micRingmodSwitch.isChecked())
                    {
                        micRingmodButton.setBackgroundResource(R.drawable.ringmodbuttoninbetween);
                    }
                    else
                    {
                        micRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
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
                        micAutowahButton.setBackgroundResource(R.drawable.autowahbuttoninbetween);
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
                        micAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                    }
                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundResource(R.drawable.bassemulatorbutton);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });



        micAutowahSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micAutowahButton.setBackgroundResource(R.drawable.autowahbuttonpressed);
                }
                else {
                    if(micAutowahBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micAutowahSwitch.isChecked())
                    {
                        micAutowahButton.setBackgroundResource(R.drawable.autowahbuttoninbetween);
                    }
                    else
                    {
                        micAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
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
                        micTremoloButton.setBackgroundResource(R.drawable.tremolobuttoninbetween);
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
                        micTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                    }
                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundResource(R.drawable.bassemulatorbutton);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });



        micTremoloSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micTremoloButton.setBackgroundResource(R.drawable.tremolobuttonpressed);
                }
                else {
                    if(micTremoloBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micTremoloSwitch.isChecked())
                    {
                        micTremoloButton.setBackgroundResource(R.drawable.tremolobuttoninbetween);
                    }
                    else
                    {
                        micTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
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
                        micReverbButton.setBackgroundResource(R.drawable.reverbbuttoninbetween);
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
                        micReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                    }
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundResource(R.drawable.bassemulatorbutton);
                }


                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });



        micReverbSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micReverbButton.setBackgroundResource(R.drawable.reverbbuttonpressed);
                }
                else {
                    if(micReverbBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micReverbSwitch.isChecked())
                    {
                        micReverbButton.setBackgroundResource(R.drawable.reverbbuttoninbetween);
                    }
                    else
                    {
                        micReverbButton.setBackgroundResource(R.drawable.reverbbutton);
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
                        micChorusButton.setBackgroundResource(R.drawable.chorusbuttoninbetween);
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
                        micChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                    }
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundResource(R.drawable.bassemulatorbutton);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }

                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });

        micChorusSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micChorusButton.setBackgroundResource(R.drawable.chorusbuttonpressed);
                }
                else {
                    if(micChorusBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micChorusSwitch.isChecked())
                    {
                        micChorusButton.setBackgroundResource(R.drawable.chorusbuttoninbetween);
                    }
                    else
                    {
                        micChorusButton.setBackgroundResource(R.drawable.chorusbutton);
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
                        micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbuttoninbetween);
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
                        micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                    }
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundResource(R.drawable.bassemulatorbutton);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });

        micCrusherSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbuttonpressed);
                }
                else {
                    if(micCrusherBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micCrusherSwitch.isChecked())
                    {
                        micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbuttoninbetween);
                    }
                    else
                    {
                        micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
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
                        micFlangerButton.setBackgroundResource(R.drawable.flangerbuttoninbetween);
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
                        micFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                    }
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundResource(R.drawable.bassemulatorbutton);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });

        micFlangerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micFlangerButton.setBackgroundResource(R.drawable.flangerbuttonpressed);
                }
                else {
                    if(micFlangerBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micFlangerSwitch.isChecked())
                    {
                        micFlangerButton.setBackgroundResource(R.drawable.flangerbuttoninbetween);
                    }
                    else
                    {
                        micFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
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
                        micPhaserButton.setBackgroundResource(R.drawable.phaserbuttoninbetween);
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
                        micPhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                    }
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundResource(R.drawable.bassemulatorbutton);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }

            }
        });

        micPhaserSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micPhaserButton.setBackgroundResource(R.drawable.phaserbuttonpressed);
                }
                else {
                    if(micPhaserBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micPhaserSwitch.isChecked())
                    {
                        micPhaserButton.setBackgroundResource(R.drawable.phaserbuttoninbetween);
                    }
                    else
                    {
                        micPhaserButton.setBackgroundResource(R.drawable.phaserbutton);
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
                        micDelayButton.setBackgroundResource(R.drawable.delaybuttoninbetween);
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
                        micDelayButton.setBackgroundResource(R.drawable.delaybutton);
                    }
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundResource(R.drawable.bassemulatorbutton);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
            }
        });

        micDelaySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micDelayButton.setBackgroundResource(R.drawable.delaybuttonpressed);
                }
                else {
                    if(micDelayBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micDelaySwitch.isChecked())
                    {
                        micDelayButton.setBackgroundResource(R.drawable.delaybuttoninbetween);
                    }
                    else
                    {
                        micDelayButton.setBackgroundResource(R.drawable.delaybutton);
                    }
                }
            }
        });


    }
}
