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
import android.widget.ImageButton;
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
    Boolean isPlay = true;
    Boolean isPlay2 = true;
    Boolean isPlay3 = true;
    Boolean isPlay4 = true;
    Boolean isPlay5 = true;
    Boolean isPlay6 = true;

    Boolean isPlay7 = true;
    Boolean isPlay8 = true;
    Boolean isPlay9 = true;
    Boolean isPlay10 = true;
    Boolean isPlay11 = true;
    Boolean isPlay12 = true;

    ////////////////////////////////////////////On Create//////////////////////////////////////////
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mixer_fragment, container, false);
        myText = (TextView) view.findViewById(R.id.debugTextView);
        serialInit();
        serialSliderInit();//initialize button

        final ImageButton mutebutton1 = (ImageButton) view.findViewById(R.id.sliderOneMute);
        mutebutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlay) {
                    mutebutton1.setBackgroundResource(R.drawable.mutebuttonpressed2);
                }
                else {
                    mutebutton1.setBackgroundResource(R.drawable.mutebutton2);
                }

                isPlay = !isPlay;
            }
        });

        final ImageButton mutebutton2 = (ImageButton) view.findViewById(R.id.sliderTwoMute);
        mutebutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlay2) {
                    mutebutton2.setBackgroundResource(R.drawable.mutebuttonpressed2);
                }
                else {
                    mutebutton2.setBackgroundResource(R.drawable.mutebutton2);
                }
                isPlay2 = !isPlay2;
            }
        });


        final ImageButton mutebutton3 = (ImageButton) view.findViewById(R.id.sliderThreeMute);
        mutebutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isPlay3) {
                    mutebutton3.setBackgroundResource(R.drawable.mutebuttonpressed2);
                }
                else {
                    mutebutton3.setBackgroundResource(R.drawable.mutebutton2);
                }
                isPlay3 = !isPlay3;
            }
        });


        final ImageButton mutebutton4 = (ImageButton) view.findViewById(R.id.sliderFourMute);
        mutebutton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isPlay4) {
                    mutebutton4.setBackgroundResource(R.drawable.mutebuttonpressed2);
                }
                else {
                    mutebutton4.setBackgroundResource(R.drawable.mutebutton2);
                }

                isPlay4 = !isPlay4;
            }
        });


        final ImageButton mutebutton5 = (ImageButton) view.findViewById(R.id.sliderFiveMute);
        mutebutton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isPlay5) {
                    mutebutton5.setBackgroundResource(R.drawable.mutebuttonpressed2);
                }
                else {
                    mutebutton5.setBackgroundResource(R.drawable.mutebutton2);
                }

                isPlay5 = !isPlay5;
            }
        });

        final ImageButton mutebutton6 = (ImageButton) view.findViewById(R.id.sliderSixMute);
        mutebutton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isPlay6) {
                    mutebutton6.setBackgroundResource(R.drawable.mutebuttonpressed2);
                }
                else {
                    mutebutton6.setBackgroundResource(R.drawable.mutebutton2);
                }

                isPlay6 = !isPlay6;
            }
        });

        final ImageButton solobutton1 = (ImageButton) view.findViewById(R.id.sliderOneSolo);
        solobutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isPlay7) {
                    solobutton1.setBackgroundResource(R.drawable.solobuttonpressed2);
                }
                else {
                    solobutton1.setBackgroundResource(R.drawable.solobutton2);
                }

                isPlay7 = !isPlay7;
            }
        });

        final ImageButton solobutton2 = (ImageButton) view.findViewById(R.id.sliderTwoSolo);
        solobutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isPlay8) {
                    solobutton2.setBackgroundResource(R.drawable.solobuttonpressed2);
                }
                else {
                    solobutton2.setBackgroundResource(R.drawable.solobutton2);
                }

                isPlay8 = !isPlay8;
            }
        });

        final ImageButton solobutton3 = (ImageButton) view.findViewById(R.id.sliderThreeSolo);
        solobutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isPlay9) {
                    solobutton3.setBackgroundResource(R.drawable.solobuttonpressed2);
                }
                else {
                    solobutton3.setBackgroundResource(R.drawable.solobutton2);
                }

                isPlay9 = !isPlay9;
            }
        });

        final ImageButton solobutton4 = (ImageButton) view.findViewById(R.id.sliderFourSolo);
        solobutton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isPlay10) {
                    solobutton4.setBackgroundResource(R.drawable.solobuttonpressed2);
                }
                else {
                    solobutton4.setBackgroundResource(R.drawable.solobutton2);
                }

                isPlay10 = !isPlay10;
            }
        });


        final ImageButton solobutton5 = (ImageButton) view.findViewById(R.id.sliderFiveSolo);
        solobutton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isPlay11) {
                    solobutton5.setBackgroundResource(R.drawable.solobuttonpressed2);
                }
                else {
                    solobutton5.setBackgroundResource(R.drawable.solobutton2);
                }

                isPlay11 = !isPlay11;
            }
        });


        final ImageButton solobutton6 = (ImageButton) view.findViewById(R.id.sliderSixSolo);
        solobutton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isPlay12) {
                    solobutton6.setBackgroundResource(R.drawable.solobuttonpressed2);
                }
                else {
                    solobutton6.setBackgroundResource(R.drawable.solobutton2);
                }

                isPlay12 = !isPlay12;
            }
        });



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

