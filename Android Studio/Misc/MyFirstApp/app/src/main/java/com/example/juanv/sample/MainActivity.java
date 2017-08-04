package com.example.juanv.sample;

import android.content.Context;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import java.io.IOException;
import java.util.List;
import usbserial.driver.UsbSerialDriver;
import usbserial.driver.UsbSerialPort;
import usbserial.driver.UsbSerialProber;

public class MainActivity extends AppCompatActivity {

    //Global Variables
    private static final String TAG = "MainActivity";
    public UsbSerialPort port = null;
    private SeekBar volumeSeekbar = null;
    TextView myText;
    BottomSheetBehavior echoBehavior, reverbBehavior;

    //Executes on creation of app
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myText = (TextView) findViewById(R.id.textView5);


        bottomEffectsInit();//init bottom button
        serialButtonInit();//initialize serial comms

        serialSliderInit(port);//initialize button
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

    //Sends a serial message
    public void sendserial(UsbSerialPort port, byte outBuffer[]){
        try{
            myText.append(" before wrote stuff\n");
            port.write(outBuffer, 1000);
            myText.append(" after wrote stuff\n");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bottomEffectsInit(){
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

    //Code for initializing serial communications
    private void serialButtonInit(){
        Button serialButton = (Button) findViewById(R.id.serialComms);

        serialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    myText.append("connected, port open in try\n");

                } catch (IOException e) {
                    myText.append(" caught an error in port open");
                    e.printStackTrace();
                }
            }
        });
    }

    //Code for slider change
    private void serialSliderInit(final UsbSerialPort port){
        try {
            volumeSeekbar = (SeekBar)findViewById(R.id.volume);
            volumeSeekbar.setMax(100);

            volumeSeekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(SeekBar arg0) {
                }

                @Override
                public void onStartTrackingTouch(SeekBar arg0) {
                }

                @Override
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                    byte value = (byte)(volumeSeekbar.getProgress() & 0xFF);
                    byte outBuffer[] = {value};
                    myText.setText("Value is " + value + "\n");
                    sendserial(port, outBuffer);
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

//    //@Override
//    protected void onDestroy(UsbSerialPort port){
//        super.onDestroy();
//        try {
//            port.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}


