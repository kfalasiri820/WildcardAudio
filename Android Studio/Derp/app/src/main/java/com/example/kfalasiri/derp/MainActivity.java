//package com.example.kfalasiri.derp;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//}

package com.example.kfalasiri.derp;

import android.hardware.usb.UsbAccessory;
import android.hardware.usb.UsbDeviceConnection;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import usbserial.driver.CdcAcmSerialDriver;
import usbserial.driver.ProbeTable;
import usbserial.driver.UsbSerialDriver;
import usbserial.driver.UsbSerialPort;
import usbserial.driver.UsbSerialProber;
import usbserial.util.HexDump;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
    public class MainActivity extends AppCompatActivity {
        private static final String TAG = "MyActivity";
        private TextView myText = null;
        public UsbSerialPort port = null;
        final byte outBuffer[] = {0b01001011, 0b01000001, 0b01011001, 0b01000001, 0b01011001, 0b01011001, 0b01011001, 0b01011001, 0b01011001, 0b01011001, 0b01011001, 0b01011001, 0b01011001, 0b01011001, 0b01011001, 0b01011001, 0b01011001, 0b01011001, 0b01011001, 0b01011001, 0b01011001, 0b01011001, 0b01011001, 0b01011001, 0b01011001, 0b01011001, 0b01011001, 0b01011001};

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            LinearLayout lView = new LinearLayout(this);
            myText = new TextView(this);
            myText.append("My Text\n");
            lView.addView(myText);
            //final Button button = (Button) findViewById(R.id.button);
            Button button = new Button(this);
            button.setText("serial");

            //        button.setOnClickListener(new View.OnClickListener() {
            //            public void onClick(View v) {
            //                Log.d(TAG, "button works?");
            //            }
            //        });
            lView.addView(button);
            setContentView(lView);
            UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
            List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
            if (availableDrivers.isEmpty()) {
                Log.d(TAG, "chosen juan 2");
                myText.append(" driver empty\n");
                return;

            }

            // Open a connection to the first available driver.
            UsbSerialDriver driver = availableDrivers.get(0);
            UsbDeviceConnection connection = manager.openDevice(driver.getDevice());
            if (connection == null) {
                Log.d(TAG, "chosen juan 3");
                myText.setText(" null connection");
                return;

                // You probably need to call UsbManager.requestPermission(driver.getDevice(), ..)
            }

            // Read some data! Most have just one port (port 0).
            port = driver.getPorts().get(0);
            //Log.d(TAG, "chosen juan 4");
            myText.append(" connected before try\n");

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

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myText.append("button pressed\n");
                sendserial(port);
//                myText.append(" we pressed a button\n");
                //Log.d(TAG, "button works?");
//            try {
//                myText.append(" before wrote stuff\n");
//                int numBytesWritten = port.write(outBuffer, 1000);
//                Log.d(TAG, "Wrote " + numBytesWritten + " bytes.");
//                myText.append(" after wrote stuff\n");
//            } catch (IOException e) {
//                e.printStackTrace();
//                myText.append(" crashed bitch");
//            }
            }
        });
//            try {
//                port.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }

        public void sendserial(UsbSerialPort port){
//            myText.append("button pressed2\n");
            try{
                myText.append(" before wrote stuff\n");
                int numBytesWritten = port.write(outBuffer, 1000);
                //Log.d(TAG, "Wrote " + numBytesWritten + " bytes.");
                myText.append(" after wrote stuff\n");
            }catch (IOException e) {
                e.printStackTrace();
                myText.append(" crashed bitch\n");
            }
        }

        public void onDestroy(UsbSerialPort port){
            try {
                port.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
