package com.example.juanv.sample;

import android.content.Context;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import java.io.IOException;
import java.util.List;

import usbserial.driver.UsbSerialDriver;
import usbserial.driver.UsbSerialPort;
import usbserial.driver.UsbSerialProber;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public UsbSerialPort port = null;
    private SeekBar volumeSeekbar = null;
    private AudioManager audioManager = null;
    //final byte outBuffer[] = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initControls();

        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
        if (availableDrivers.isEmpty()) {
            Log.d(TAG, "chosen juan 2");
            //myText.append(" driver empty\n");
            return;

        }

        // Open a connection to the first available driver.
        UsbSerialDriver driver = availableDrivers.get(0);
        UsbDeviceConnection connection = manager.openDevice(driver.getDevice());
        if (connection == null) {
            Log.d(TAG, "chosen juan 3");
            //myText.setText(" null connection");
            return;

            // You probably need to call UsbManager.requestPermission(driver.getDevice(), ..)
        }

        // Read some data! Most have just one port (port 0).
        port = driver.getPorts().get(0);
        //Log.d(TAG, "chosen juan 4");
        //myText.append(" connected before try\n");

        try {
            port.open(connection);
            port.setParameters(9600, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);

            //            byte buffer[] = new byte[16];
            //            int numBytesRead = port.read(buffer, 1000);
            //            Log.d(TAG, "Read " + numBytesRead + " bytes.");
            //            Log.d(TAG, "chosen juan 5");
            //myText.append("connected, port open in try\n");

        } catch (IOException e) {
            //myText.append(" caught an error in port open");
            e.printStackTrace();


        }




        final Button MLG = (Button) findViewById(R.id.MLG);
        MLG.setOnClickListener(new View.OnClickListener() {
            final MediaPlayer mPlayer = MediaPlayer.create(MainActivity.this, R.raw.redbone);
            public void onClick(View v) {

                byte outBuffer[] = {0b01001011};
                sendserial(port, outBuffer);

//                if(mPlayer.isPlaying())
//                {
//                    mPlayer.pause();
//                }
//                else{
//                    mPlayer.start();
//                }
            }

        });

        final Button echo = (Button) findViewById(R.id.echo);
        echo.setOnClickListener(new View.OnClickListener() {
            final MediaPlayer mPlayer = MediaPlayer.create(MainActivity.this, R.raw.redboneecho);
            public void onClick(View v) {

                byte outBuffer[] = {0b01001011};
                sendserial(port, outBuffer);

//                if(mPlayer.isPlaying())
//                {
//                    mPlayer.pause();
//                }
//                else{
//                    mPlayer.start();
//                }
            }

        });

        final Button wahwah = (Button) findViewById(R.id.wahwah);
        wahwah.setOnClickListener(new View.OnClickListener() {
            final MediaPlayer mPlayer = MediaPlayer.create(MainActivity.this, R.raw.redbonewah);
            public void onClick(View v) {

                byte outBuffer[] = {0b01001011};
                sendserial(port, outBuffer);


//                if(mPlayer.isPlaying())
//                {
//                    mPlayer.pause();
//                }
//                else{
//                    mPlayer.start();
//                }
            }

        });

        final Button phasor = (Button) findViewById(R.id.phasor);
        phasor.setOnClickListener(new View.OnClickListener() {
            final MediaPlayer mPlayer = MediaPlayer.create(MainActivity.this, R.raw.redbonephasor);
            public void onClick(View v) {

                byte outBuffer[] = {0b01001011};
                sendserial(port, outBuffer);

//                if(mPlayer.isPlaying())
//                {
//                    mPlayer.pause();
//                }
//                else{
//                    mPlayer.start();
//                }
            }

        });

        final Button reverb = (Button) findViewById(R.id.reverb);
        reverb.setOnClickListener(new View.OnClickListener() {
            final MediaPlayer mPlayer = MediaPlayer.create(MainActivity.this, R.raw.redbonereverb);
            public void onClick(View v) {


                byte outBuffer[] = {0b01001011};
                sendserial(port, outBuffer);

//                if(mPlayer.isPlaying())
//                {
//                    mPlayer.pause();
//                }
//                else{
//                    mPlayer.start();
//                }
            }

        });

        final Button tremolo = (Button) findViewById(R.id.tremolo);
        tremolo.setOnClickListener(new View.OnClickListener() {
            final MediaPlayer mPlayer = MediaPlayer.create(MainActivity.this, R.raw.redbonetrem);
            public void onClick(View v) {


                byte outBuffer[] = {0b01001011};
                sendserial(port, outBuffer);


//                if(mPlayer.isPlaying())
//                {
//                    mPlayer.pause();
//                }
//                else{
//                    mPlayer.start();
//                }
            }

        });
    }

    public void sendserial(UsbSerialPort port, byte outBuffer[]){
//            myText.append("button pressed2\n");
        try{
            //myText.append(" before wrote stuff\n");
            int numBytesWritten = port.write(outBuffer, 1000);
            //Log.d(TAG, "Wrote " + numBytesWritten + " bytes.");
            //myText.append(" after wrote stuff\n");
        }catch (IOException e) {
            e.printStackTrace();
            //myText.append(" crashed bitch\n");
        }
    }

    public void onDestroy(UsbSerialPort port){
        try {
            port.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void initControls()
//    {
//        try
//        {
//            volumeSeekbar = (SeekBar)findViewById(R.id.volume);
//            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//            volumeSeekbar.setMax(audioManager
//                    .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
//            volumeSeekbar.setProgress(audioManager
//                    .getStreamVolume(AudioManager.STREAM_MUSIC));
//
//
//            volumeSeekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
//            {
//                @Override
//                public void onStopTrackingTouch(SeekBar arg0)
//                {
//                }
//
//                @Override
//                public void onStartTrackingTouch(SeekBar arg0)
//                {
//                }
//
//                @Override
//                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2)
//                {
//                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
//                            progress, 0);
//                    //byte Progress = volumeSeekbar.getProgess();
//                }
//            });
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }

}


