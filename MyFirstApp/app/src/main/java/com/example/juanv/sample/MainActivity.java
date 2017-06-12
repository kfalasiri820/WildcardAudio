package com.example.juanv.sample;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button MLG = (Button) findViewById(R.id.MLG);
        MLG.setOnClickListener(new View.OnClickListener() {
            final MediaPlayer mPlayer = MediaPlayer.create(MainActivity.this, R.raw.redbone);
            public void onClick(View v) {
                if(mPlayer.isPlaying())
                {
                    mPlayer.pause();
                }
                else{
                    mPlayer.start();
                }
            }

        });

        final Button echo = (Button) findViewById(R.id.echo);
        echo.setOnClickListener(new View.OnClickListener() {
            final MediaPlayer mPlayer = MediaPlayer.create(MainActivity.this, R.raw.redboneecho);
            public void onClick(View v) {
                if(mPlayer.isPlaying())
                {
                    mPlayer.pause();
                }
                else{
                    mPlayer.start();
                }
            }

        });

        final Button wahwah = (Button) findViewById(R.id.wahwah);
        wahwah.setOnClickListener(new View.OnClickListener() {
            final MediaPlayer mPlayer = MediaPlayer.create(MainActivity.this, R.raw.redbonewah);
            public void onClick(View v) {
                if(mPlayer.isPlaying())
                {
                    mPlayer.pause();
                }
                else{
                    mPlayer.start();
                }
            }

        });

        final Button phasor = (Button) findViewById(R.id.phasor);
        phasor.setOnClickListener(new View.OnClickListener() {
            final MediaPlayer mPlayer = MediaPlayer.create(MainActivity.this, R.raw.redbonephasor);
            public void onClick(View v) {
                if(mPlayer.isPlaying())
                {
                    mPlayer.pause();
                }
                else{
                    mPlayer.start();
                }
            }

        });

        final Button reverb = (Button) findViewById(R.id.reverb);
        reverb.setOnClickListener(new View.OnClickListener() {
            final MediaPlayer mPlayer = MediaPlayer.create(MainActivity.this, R.raw.redbonereverb);
            public void onClick(View v) {
                if(mPlayer.isPlaying())
                {
                    mPlayer.pause();
                }
                else{
                    mPlayer.start();
                }
            }

        });

        final Button tremolo = (Button) findViewById(R.id.tremolo);
        tremolo.setOnClickListener(new View.OnClickListener() {
            final MediaPlayer mPlayer = MediaPlayer.create(MainActivity.this, R.raw.redbonetrem);
            public void onClick(View v) {
                if(mPlayer.isPlaying())
                {
                    mPlayer.pause();
                }
                else{
                    mPlayer.start();
                }
            }

        });
    }
}


