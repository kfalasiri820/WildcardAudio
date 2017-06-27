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
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import usbserial.driver.UsbSerialDriver;
import usbserial.driver.UsbSerialPort;
import usbserial.driver.UsbSerialProber;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public UsbSerialPort port = null;
    private SeekBar volumeSeekbar = null;
    TextView myText;
    //final byte outBuffer[] = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myText = (TextView) findViewById(R.id.textView5);

        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
        if (availableDrivers.isEmpty()) {
            Log.d(TAG, "chosen juan 2");
            myText.setText(" driver empty\n");
            return;

        }

        // Open a connection to the first available driver.
        UsbSerialDriver driver = availableDrivers.get(0);
        UsbDeviceConnection connection = manager.openDevice(driver.getDevice());
        if (connection == null) {
            myText.setText(" null connection");
            return;

            // You probably need to call UsbManager.requestPermission(driver.getDevice(), ..)
        }

        // Read some data! Most have just one port (port 0).
        port = driver.getPorts().get(0);
        //Log.d(TAG, "chosen juan 4");
        myText.setText(" connected before try\n");

        try {
            port.open(connection);
            port.setParameters(9600, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);

            //            byte buffer[] = new byte[16];
            //            int numBytesRead = port.read(buffer, 1000);
            //            Log.d(TAG, "Read " + numBytesRead + " bytes.");
            //            Log.d(TAG, "chosen juan 5");
            myText.append("connected, port open in try\n");

        } catch (IOException e) {
            myText.append(" caught an error in port open");
            e.printStackTrace();


        }




        final Button MLG = (Button) findViewById(R.id.MLG);
        MLG.setOnClickListener(new View.OnClickListener() {
            final MediaPlayer mPlayer = MediaPlayer.create(MainActivity.this, R.raw.redbone);
            public void onClick(View v) {

                byte btn1[] = {0b01001011};
                sendserial(port, btn1);

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

                byte btn2[] = {0b01001011};
                sendserial(port, btn2);

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

                byte btn3[] = {0b01001011};
                sendserial(port, btn3);


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

                byte btn4[] = {0b01001011};
                sendserial(port, btn4);

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


                byte btn5[] = {0b01001011};
                sendserial(port, btn5);

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


                byte btn6[] = {0b01001011};
                sendserial(port, btn6);


//                if(mPlayer.isPlaying())
//                {
//                    mPlayer.pause();
//                }
//                else{
//                    mPlayer.start();
//                }
            }

        });

        initControls(port);
    }

    public void sendserial(UsbSerialPort port, byte outBuffer[]){
//            myText.append("button pressed2\n");
        try{
            myText.append(" before wrote stuff\n");
            int numBytesWritten = port.write(outBuffer, 1000);
            //Log.d(TAG, "Wrote " + numBytesWritten + " bytes.");
            myText.append(" after wrote stuff\n");
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

    private void initControls(final UsbSerialPort port)
    {
        try
        {
            volumeSeekbar = (SeekBar)findViewById(R.id.volume);
            volumeSeekbar.setMax(100);

            volumeSeekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
            {
                @Override
                public void onStopTrackingTouch(SeekBar arg0)
                {
//                    byte value = (byte) volumeSeekbar.getProgress();
                    //byte outBuffer[] = new byte[5];
//                    byte outBuffer[] = {0b01001011};
//                    sendserial(port, outBuffer);
                    //myText.setText(outBuffer[0]);
                    //myText.append(" end of method volume change");
                }

                @Override
                public void onStartTrackingTouch(SeekBar arg0)
                {
                }

                @Override
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2)
                {
                    myText.setText(String.valueOf(progress));
//                    byte v = valueOf(progress);
                    //int value = volumeSeekbar.getProgress();
                    //myText.setText("value of" + value);
                    //byte v = volumeSeekbar.valueOf(progress);
//                    byte send = (byte) value;
//                    byte outBuffer[] = {send};
                    byte value = (byte) (volumeSeekbar.getProgress() & 0xff);
                    byte outBuffer[] = {value};
                    sendserial(port, outBuffer);

                }
//                byte value = (byte) volumeSeekbar.getProgress();
//                byte outBuffer[] = {};
//                outBuffer[0] = value;
//                myText.setText(outBuffer[0]);
//                myText.append(" end of method volume change");
//                sendserial(port, outBuffer);
            });

//            byte value = (byte) volumeSeekbar.getProgress();
//            byte outBuffer[] = {};
//            outBuffer[0] = value;
//            myText.setText(outBuffer[0]);
//            myText.append(" end of method volume change");
//            sendserial(port, outBuffer);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}


