package com.example.kfalasiri.mixers;

import android.graphics.Color;
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

        final Button lineReverbButton = (Button) view.findViewById(R.id.line_reverb_button);
        final Button lineChorusButton = (Button) view.findViewById(R.id.line_chorus_button);
        final Button lineCrusherButton = (Button) view.findViewById(R.id.line_bitcrusher_button);
        final Button lineFlangerButton = (Button) view.findViewById(R.id.line_flanger_button);
        final Button linePhaserButton = (Button) view.findViewById(R.id.line_phaser_button);
        final Button lineDelayButton = (Button) view.findViewById(R.id.line_delay_button);

        View lineReverbView = view.findViewById(R.id.lineReverbBottom);
        lineReverbBehavior = BottomSheetBehavior.from(lineReverbView);
        lineReverbBehavior.setPeekHeight(0);
        lineReverbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(lineReverbBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    lineReverbButton.setBackgroundColor(Color.BLUE);
                    lineChorusButton.setBackgroundColor(Color.WHITE);
                    lineDelayButton.setBackgroundColor(Color.WHITE);
                    lineFlangerButton.setBackgroundColor(Color.WHITE);
                    lineCrusherButton.setBackgroundColor(Color.WHITE);
                    linePhaserButton.setBackgroundColor(Color.WHITE);

                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
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
            }
        });


        View lineChorusView = view.findViewById(R.id.lineChorusBottom);
        lineChorusBehavior = BottomSheetBehavior.from(lineChorusView);
        lineChorusBehavior.setPeekHeight(0);
        lineChorusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(lineChorusBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    lineChorusButton.setBackgroundColor(Color.BLUE);
                    lineDelayButton.setBackgroundColor(Color.WHITE);
                    lineReverbButton.setBackgroundColor(Color.WHITE);
                    lineFlangerButton.setBackgroundColor(Color.WHITE);
                    lineCrusherButton.setBackgroundColor(Color.WHITE);
                    linePhaserButton.setBackgroundColor(Color.WHITE);

                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
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
            }
        });

        View lineCrusherView = view.findViewById(R.id.lineCrusherBottom);
        lineCrusherBehavior = BottomSheetBehavior.from(lineCrusherView);
        lineCrusherBehavior.setPeekHeight(0);
        lineCrusherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(lineCrusherBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    lineCrusherButton.setBackgroundColor(Color.BLUE);
                    lineChorusButton.setBackgroundColor(Color.WHITE);
                    lineReverbButton.setBackgroundColor(Color.WHITE);
                    lineFlangerButton.setBackgroundColor(Color.WHITE);
                    lineDelayButton.setBackgroundColor(Color.WHITE);
                    linePhaserButton.setBackgroundColor(Color.WHITE);

                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
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
            }
        });

        View lineFlangerView = view.findViewById(R.id.lineFlangerBottom);
        lineFlangerBehavior = BottomSheetBehavior.from(lineFlangerView);
        lineFlangerBehavior.setPeekHeight(0);
        lineFlangerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(lineFlangerBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    lineFlangerButton.setBackgroundColor(Color.BLUE);
                    lineChorusButton.setBackgroundColor(Color.WHITE);
                    lineReverbButton.setBackgroundColor(Color.WHITE);
                    lineDelayButton.setBackgroundColor(Color.WHITE);
                    lineCrusherButton.setBackgroundColor(Color.WHITE);
                    linePhaserButton.setBackgroundColor(Color.WHITE);

                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
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
            }
        });

        View linePhaserView = view.findViewById(R.id.linePhaserBottom);
        linePhaserBehavior = BottomSheetBehavior.from(linePhaserView);
        linePhaserBehavior.setPeekHeight(0);
        linePhaserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(linePhaserBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    linePhaserButton.setBackgroundColor(Color.BLUE);
                    lineChorusButton.setBackgroundColor(Color.WHITE);
                    lineReverbButton.setBackgroundColor(Color.WHITE);
                    lineFlangerButton.setBackgroundColor(Color.WHITE);
                    lineCrusherButton.setBackgroundColor(Color.WHITE);
                    lineDelayButton.setBackgroundColor(Color.WHITE);

                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
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
            }
        });

        View lineDelayView = view.findViewById(R.id.lineDelayBottom);
        lineDelayBehavior = BottomSheetBehavior.from(lineDelayView);
        lineDelayBehavior.setPeekHeight(0);
        lineDelayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(lineDelayBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    lineDelayButton.setBackgroundColor(Color.BLUE);
                    lineChorusButton.setBackgroundColor(Color.WHITE);
                    lineReverbButton.setBackgroundColor(Color.WHITE);
                    lineFlangerButton.setBackgroundColor(Color.WHITE);
                    lineCrusherButton.setBackgroundColor(Color.WHITE);
                    linePhaserButton.setBackgroundColor(Color.WHITE);

                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
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
            }
        });
    }

    BottomSheetBehavior micReverbBehavior, micChorusBehavior, micCrusherBehavior,
            micFlangerBehavior, micPhaserBehavior, micDelayBehavior;
    public void bottomMicEffectsInit(){

        final Button micDelayButton = (Button) view.findViewById(R.id.mic_delay_button);
        final Button micReverbButton = (Button) view.findViewById(R.id.mic_reverb_button);
        final Button micChorusButton = (Button) view.findViewById(R.id.mic_chorus_button);
        final Button micPhaserButton = (Button) view.findViewById(R.id.mic_phaser_button);
        final Button micFlangerButton = (Button) view.findViewById(R.id.mic_flanger_button);
        final Button micCrusherButton = (Button) view.findViewById(R.id.mic_bitcrusher_button);

        final View micReverbView = view.findViewById(R.id.micReverbBottom);
        micReverbBehavior = BottomSheetBehavior.from(micReverbView);
        micReverbBehavior.setPeekHeight(0);
        micReverbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(micReverbBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    micReverbButton.setBackgroundColor(Color.GREEN);
                    micChorusButton.setBackgroundColor(Color.WHITE);
                    micDelayButton.setBackgroundColor(Color.WHITE);
                    micCrusherButton.setBackgroundColor(Color.WHITE);
                    micFlangerButton.setBackgroundColor(Color.WHITE);
                    micPhaserButton.setBackgroundColor(Color.WHITE);


                    micReverbBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    micReverbButton.setBackgroundColor(Color.WHITE);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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
                    micChorusButton.setBackgroundColor(Color.GREEN);
                    micDelayButton.setBackgroundColor(Color.WHITE);
                    micReverbButton.setBackgroundColor(Color.WHITE);
                    micCrusherButton.setBackgroundColor(Color.WHITE);
                    micFlangerButton.setBackgroundColor(Color.WHITE);
                    micPhaserButton.setBackgroundColor(Color.WHITE);


                    micChorusBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    micChorusButton.setBackgroundColor(Color.WHITE);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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
                    micCrusherButton.setBackgroundColor(Color.GREEN);
                    micChorusButton.setBackgroundColor(Color.WHITE);
                    micReverbButton.setBackgroundColor(Color.WHITE);
                    micDelayButton.setBackgroundColor(Color.WHITE);
                    micFlangerButton.setBackgroundColor(Color.WHITE);
                    micPhaserButton.setBackgroundColor(Color.WHITE);


                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    micCrusherButton.setBackgroundColor(Color.WHITE);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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
                    micFlangerButton.setBackgroundColor(Color.GREEN);
                    micChorusButton.setBackgroundColor(Color.WHITE);
                    micReverbButton.setBackgroundColor(Color.WHITE);
                    micCrusherButton.setBackgroundColor(Color.WHITE);
                    micDelayButton.setBackgroundColor(Color.WHITE);
                    micPhaserButton.setBackgroundColor(Color.WHITE);


                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    micFlangerButton.setBackgroundColor(Color.WHITE);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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
                    micPhaserButton.setBackgroundColor(Color.GREEN);
                    micChorusButton.setBackgroundColor(Color.WHITE);
                    micReverbButton.setBackgroundColor(Color.WHITE);
                    micCrusherButton.setBackgroundColor(Color.WHITE);
                    micFlangerButton.setBackgroundColor(Color.WHITE);
                    micDelayButton.setBackgroundColor(Color.WHITE);


                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    micPhaserButton.setBackgroundColor(Color.WHITE);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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
                    micDelayButton.setBackgroundColor(Color.GREEN);
                    micChorusButton.setBackgroundColor(Color.WHITE);
                    micReverbButton.setBackgroundColor(Color.WHITE);
                    micCrusherButton.setBackgroundColor(Color.WHITE);
                    micFlangerButton.setBackgroundColor(Color.WHITE);
                    micPhaserButton.setBackgroundColor(Color.WHITE);


                    micDelayBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    micDelayButton.setBackgroundColor(Color.WHITE);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
    }
}
