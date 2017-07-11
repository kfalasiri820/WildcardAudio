package com.example.kfalasiri.mixers;

import android.content.Context;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;
import com.hoho.android.usbserial.driver.UsbSerialDriver;

import java.io.IOException;
import java.util.List;


public class MixerFragment extends Fragment {
    private static final String TAG = "MixerFragment";

    ////////////////////////////////////////////GLOBALS////////////////////////////////////////////
    public UsbSerialPort port = null;
    private SeekBar volumeSeekbar = null;
    View view;
    TextView myText;

    ////////////////////////////////////////////On Create//////////////////////////////////////////
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mixer_fragment, container, false);
        myText = (TextView) view.findViewById(R.id.debugTextView);

        serialInit();
        serialSliderInit();//initialize button
        return view;
    }

    /////////////////////////////////////////////serial/////////////////////////////////////////////
    //Sends a serial message
    public void sendserial(byte outBuffer[]) {
        try {
            myText.append(" before wrote stuff\n");
            port.write(outBuffer, 1000);
            myText.append(" after wrote stuff\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void serialInit(){
        UsbManager manager = (UsbManager) getActivity().getSystemService(Context.USB_SERVICE);

        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
        if (availableDrivers.isEmpty()) {
            myText.setText(" driver empty\n");
            return;
        }

        // Open a connection to the first available driver.
        UsbSerialDriver driver = availableDrivers.get(0);

        //manager.requestPermission(driver.getDevice(), );
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

    //Code for initializing serial communications
    private void serialButtonInit() {
        Button serialButton = (Button) view.findViewById(R.id.serialComms);

        serialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serialInit();
            }
        });
    }

    //Code for slider change
    private void serialSliderInit() {
        try {
            volumeSeekbar = (SeekBar) view.findViewById(R.id.volume);
            volumeSeekbar.setMax(100);

            volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onStopTrackingTouch(SeekBar arg0) {
                }

                @Override
                public void onStartTrackingTouch(SeekBar arg0) {
                }

                @Override
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                    byte value = (byte) (volumeSeekbar.getProgress() & 0xFF);
                    byte outBuffer[] = {value};
                    myText.setText("Value is " + value + "\n");

                    if(port == null)
                        myText.append("null :(\n");
                    else
                        sendserial(outBuffer);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

