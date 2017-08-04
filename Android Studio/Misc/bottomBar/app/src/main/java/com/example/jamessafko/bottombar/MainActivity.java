package com.example.jamessafko.bottombar;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    BottomSheetBehavior echoBehavior, reverbBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final View echoView = findViewById(R.id.echoBottom);
        echoBehavior = BottomSheetBehavior.from(echoView);
        echoBehavior.setPeekHeight(0);
        final Button echoButtonMain = (Button) findViewById(R.id.echoButton);
        echoButtonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(echoBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    echoBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                else {
                    echoBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        final View reverbView = findViewById(R.id.reverbBottom);
        reverbBehavior = BottomSheetBehavior.from(reverbView);
        reverbBehavior.setPeekHeight(0);
        final Button reverbButtonMain = (Button) findViewById(R.id.reverbButton);
        reverbButtonMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(reverbBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    reverbBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                else {
                    reverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
    }

    //Enable full screen mode
    @Override
    public void onWindowFocusChanged(boolean hasFocas) {
        super.onWindowFocusChanged(hasFocas);
        View decorView = getWindow().getDecorView();
        if(hasFocas) {
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    }
}
