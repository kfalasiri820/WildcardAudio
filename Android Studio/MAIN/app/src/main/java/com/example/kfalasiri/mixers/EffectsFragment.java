package com.example.kfalasiri.mixers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
            lineFlangerBehavior, linePhaserBehavior, lineDelayBehavior, lineBehaviorBottom;
    public void bottomLineEffectsInit(){
        View lineReverbView = view.findViewById(R.id.lineReverbBottom);
        lineReverbBehavior = BottomSheetBehavior.from(lineReverbView);
        lineReverbBehavior.setPeekHeight(0);
        Button lineReverbButton = (Button) view.findViewById(R.id.line_reverb_button);
        lineReverbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(lineReverbBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });


        View lineChorusView = view.findViewById(R.id.lineChorusBottom);
        lineChorusBehavior = BottomSheetBehavior.from(lineChorusView);
        lineChorusBehavior.setPeekHeight(0);
        Button lineChorusButton = (Button) view.findViewById(R.id.line_chorus_button);
        lineChorusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(lineChorusBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        View lineCrusherView = view.findViewById(R.id.lineCrusherBottom);
        lineCrusherBehavior = BottomSheetBehavior.from(lineCrusherView);
        lineCrusherBehavior.setPeekHeight(0);
        Button lineCrusherButton = (Button) view.findViewById(R.id.line_bitcrusher_button);
        lineCrusherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(lineCrusherBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        View lineFlangerView = view.findViewById(R.id.lineFlangerBottom);
        lineFlangerBehavior = BottomSheetBehavior.from(lineFlangerView);
        lineFlangerBehavior.setPeekHeight(0);
        Button lineFlangerButton = (Button) view.findViewById(R.id.line_flanger_button);
        lineFlangerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(lineFlangerBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        View linePhaserView = view.findViewById(R.id.linePhaserBottom);
        linePhaserBehavior = BottomSheetBehavior.from(linePhaserView);
        linePhaserBehavior.setPeekHeight(0);
        Button linePhaserButton = (Button) view.findViewById(R.id.line_phaser_button);
        linePhaserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(linePhaserBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        View lineDelayView = view.findViewById(R.id.lineDelayBottom);
        lineDelayBehavior = BottomSheetBehavior.from(lineDelayView);
        lineDelayBehavior.setPeekHeight(0);
        Button lineDelayButton = (Button) view.findViewById(R.id.line_delay_button);
        lineDelayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(lineDelayBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
    }

    BottomSheetBehavior micReverbBehavior, micChorusBehavior, micCrusherBehavior,
            micFlangerBehavior, micPhaserBehavior, micDelayBehavior;
    public void bottomMicEffectsInit(){
        final View micReverbView = view.findViewById(R.id.micReverbBottom);
        micReverbBehavior = BottomSheetBehavior.from(micReverbView);
        micReverbBehavior.setPeekHeight(0);
        final Button micReverbButton = (Button) view.findViewById(R.id.mic_reverb_button);
        micReverbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(micReverbBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });


        final View micChorusView = view.findViewById(R.id.micChorusBottom);
        micChorusBehavior = BottomSheetBehavior.from(micChorusView);
        micChorusBehavior.setPeekHeight(0);
        final Button micChorusButton = (Button) view.findViewById(R.id.mic_chorus_button);
        micChorusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(micChorusBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        final View micCrusherView = view.findViewById(R.id.micCrusherBottom);
        micCrusherBehavior = BottomSheetBehavior.from(micCrusherView);
        micCrusherBehavior.setPeekHeight(0);
        final Button micCrusherButton = (Button) view.findViewById(R.id.mic_bitcrusher_button);
        micCrusherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(micCrusherBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        final View micFlangerView = view.findViewById(R.id.micFlangerBottom);
        micFlangerBehavior = BottomSheetBehavior.from(micFlangerView);
        micFlangerBehavior.setPeekHeight(0);
        final Button micFlangerButton = (Button) view.findViewById(R.id.mic_flanger_button);
        micFlangerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(micFlangerBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        final View micPhaserView = view.findViewById(R.id.micPhaserBottom);
        micPhaserBehavior = BottomSheetBehavior.from(micPhaserView);
        micPhaserBehavior.setPeekHeight(0);
        final Button micPhaserButton = (Button) view.findViewById(R.id.mic_phaser_button);
        micPhaserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(micPhaserBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        final View micDelayView = view.findViewById(R.id.micDelayBottom);
        micDelayBehavior = BottomSheetBehavior.from(micDelayView);
        micDelayBehavior.setPeekHeight(0);
        final Button micDelayButton = (Button) view.findViewById(R.id.mic_delay_button);
        micDelayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(micDelayBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
    }
}
