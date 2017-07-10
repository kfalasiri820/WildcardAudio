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
    private static final String TAG = "EffectsFragment";
    View view;
    BottomSheetBehavior micBehaviorBottom, lineBehaviorBottom;

    ////////////////////////////////////////////On Create//////////////////////////////////////////
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.effects_fragment, container, false);
        bottomEffectsInit();
        return view;
    }

    public void bottomEffectsInit(){
        final View micEffectsView = view.findViewById(R.id.micEffect1Bottom);
        micBehaviorBottom = BottomSheetBehavior.from(micEffectsView);
        micBehaviorBottom.setPeekHeight(0);
        final Button micEffect1Button = (Button) view.findViewById(R.id.MicrophoneEffect1Button);
        micEffect1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(micBehaviorBottom.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    micBehaviorBottom.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                else {
                    micBehaviorBottom.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        final View instrumentEffectsView = view.findViewById(R.id.lineEffect1Bottom);
        lineBehaviorBottom = BottomSheetBehavior.from(instrumentEffectsView);
        lineBehaviorBottom.setPeekHeight(0);
        final Button lineEffect1Button = (Button) view.findViewById(R.id.LineEffect1Button);
        lineEffect1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lineBehaviorBottom.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    lineBehaviorBottom.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                else {
                    lineBehaviorBottom.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
    }
}
