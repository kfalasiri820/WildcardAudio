package com.example.jamessafko.usart_new;

import android.content.Context;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.List;

import usbserial.driver.UsbSerialDriver;
import usbserial.driver.UsbSerialPort;
import usbserial.driver.UsbSerialProber;
import usbserial.util.HexDump;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
        if (availableDrivers.isEmpty()) {

            Log.d(TAG, "///////////////////////////No available drivers detected");
            return;
        }
        else{
            for(int i = 0; i < availableDrivers.size(); i++)
                Log.d(TAG, availableDrivers.get(i) + "");
        }

        // Open a connection to the first available driver.
        UsbSerialDriver driver = availableDrivers.get(0);
        UsbDeviceConnection connection = manager.openDevice(driver.getDevice());
        if (connection == null) {
            // You probably need to call UsbManager.requestPermission(driver.getDevice(), ..)
            Log.d(TAG, "///////////////////////////Null connection");
            return;
        }

        // Apply settings to port 0
        final UsbSerialPort port = driver.getPorts().get(0);
        try{
            port.open(connection);
            port.setParameters(9600, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);
        }catch (IOException e) {
            e.printStackTrace();
        }

//        byte inBuffer[] = new byte[16];
        final byte outBuffer[] = {0b01010101};

//        //READ
//        try {
//            int numBytesRead = port.read(inBuffer, 1000);
//            Log.d(TAG, "Read " + numBytesRead + " bytes.");
//            for(int i = 0; i < numBytesRead; i++){
//                Log.d(TAG, inBuffer[i] + "");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //Wire up a button to turn delay On
        //1. get the button
        Button delayOn = (Button) findViewById(R.id.delayOn);
        //2. set what happens when click
        delayOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Button pressed");
                //WRITE, when you push the button
                try{
                    int numBytesWritten = port.write(outBuffer, 1000);
                    Log.d(TAG, "Wrote " + numBytesWritten + " bytes.");
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        //Close the port
        try {
            port.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
