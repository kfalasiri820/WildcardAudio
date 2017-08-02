package com.example.kfalasiri.mixers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.PaintDrawable;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ToggleButton;
import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;

import java.io.IOException;
import java.util.List;

import static android.R.attr.port;

public class EffectsFragment extends Fragment{
    ////////////////////////////////////////////GLOBALS////////////////////////////////////////////
    private static final String TAG = "EffectsMicFragment";
    public UsbSerialPort port = null;
    View view;


    ////////////////////////////////////////////On Create//////////////////////////////////////////
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.effects_fragment, container, false);
        bottomLineEffectsInit();
        bottomMicEffectsInit();
        serialInit();
        return view;
    }


    private void serialInit(){
        UsbManager manager = (UsbManager) getActivity().getSystemService(Context.USB_SERVICE);

        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
        if (availableDrivers.isEmpty()) {
            return;
        }

        // Open a connection to the first available driver.
        UsbSerialDriver driver = availableDrivers.get(0);

        //manager.requestPermission(driver.getDevice(), );
        UsbDeviceConnection connection = manager.openDevice(driver.getDevice());
        if (connection == null) {
            return;
            // You probably need to call UsbManager.requestPermission(driver.getDevice(), ..)
        }

        // Read some data! Most have just one port (port 0).
        port = driver.getPorts().get(0);

        try {
            port.open(connection);
            port.setParameters(9600, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendserial(byte outBuffer[]) {
        try {
            port.write(outBuffer, 1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    BottomSheetBehavior lineReverbBehavior, lineChorusBehavior, lineCrusherBehavior,
            lineFlangerBehavior, linePhaserBehavior, lineDelayBehavior, lineTremoloBehavior, lineBehaviorBottom,
        lineAutowahBehavior, lineDistortionBehavior, lineNoisegateBehavior, lineAtbBehavior, lineRingmodBehavior;
    public void bottomLineEffectsInit() {

        final ImageButton lineReverbButton = (ImageButton) view.findViewById(R.id.line_reverb_button);
        final ImageButton lineChorusButton = (ImageButton) view.findViewById(R.id.line_chorus_button);
        final ImageButton lineCrusherButton = (ImageButton) view.findViewById(R.id.line_bitcrusher_button);
        final ImageButton lineFlangerButton = (ImageButton) view.findViewById(R.id.line_flanger_button);
        final ImageButton linePhaserButton = (ImageButton) view.findViewById(R.id.line_phaser_button);
        final ImageButton lineDelayButton = (ImageButton) view.findViewById(R.id.line_delay_button);
        final ImageButton lineTremoloButton = (ImageButton) view.findViewById(R.id.line_tremolo_button);
        final ImageButton lineNoisegateButton = (ImageButton) view.findViewById(R.id.line_noisegate_button);
        final ImageButton lineAtbButton = (ImageButton) view.findViewById(R.id.line_atb_button);
        final ImageButton lineRingmodButton = (ImageButton) view.findViewById(R.id.line_ringmod_button);
        final ImageButton lineDistortionButton = (ImageButton) view.findViewById(R.id.line_distortion_button);
        final ImageButton lineAutowahButton = (ImageButton) view.findViewById(R.id.line_autowah_button);


        final ToggleButton lineReverbSwitch = (ToggleButton) view.findViewById(R.id.LineReverbSwitch);
        final ToggleButton lineChorusSwitch = (ToggleButton) view.findViewById(R.id.LineChorusSwitch);
        final ToggleButton lineCrusherSwitch = (ToggleButton) view.findViewById(R.id.LineCrusherSwitch);
        final ToggleButton lineFlangerSwitch = (ToggleButton) view.findViewById(R.id.LineFlangerSwitch);
        final ToggleButton linePhaserSwitch = (ToggleButton) view.findViewById(R.id.LinePhaserSwitch);
        final ToggleButton lineDelaySwitch = (ToggleButton) view.findViewById(R.id.LineDelaySwitch);
        final ToggleButton lineTremoloSwitch = (ToggleButton) view.findViewById(R.id.LineTremoloSwitch);
        final ToggleButton lineAutowahSwitch = (ToggleButton) view.findViewById(R.id.LineAutowahSwitch);
        final ToggleButton lineDistortionSwitch = (ToggleButton) view.findViewById(R.id.LineDistortionSwitch);
        final ToggleButton lineNoisegateSwitch = (ToggleButton) view.findViewById(R.id.LineNoisegateSwitch);
        final ToggleButton lineAtbSwitch = (ToggleButton) view.findViewById(R.id.LineAtbSwitch);
        final ToggleButton lineRingmodSwitch = (ToggleButton) view.findViewById(R.id.LineRingmodSwitch);


        View lineTremoloView = view.findViewById(R.id.lineTremoloBottom);
        lineTremoloBehavior = BottomSheetBehavior.from(lineTremoloView);
        lineTremoloBehavior.setPeekHeight(0);
        lineTremoloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(!lineTremoloSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b11001011};
                    sendserial(outBuffer);
                }
                else if(lineTremoloSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b11101011};
                    sendserial(outBuffer);
                }



                if (lineTremoloBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if (!lineTremoloSwitch.isChecked()) {
                        lineTremoloButton.setBackgroundResource(R.drawable.tremolobuttoninbetween);
                    }

                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDistortionBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else {
                    if(!lineTremoloSwitch.isChecked()) {
                        lineTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                    }
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!lineDistortionSwitch.isChecked())
                {
                    lineDistortionButton.setBackgroundResource(R.drawable.analogdistbutton);
                }
                if(!lineAtbSwitch.isChecked())
                {
                    lineAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!lineAutowahSwitch.isChecked())
                {
                    lineAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!lineRingmodSwitch.isChecked())
                {
                    lineRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!lineNoisegateSwitch.isChecked())
                {
                    lineNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if (!lineReverbSwitch.isChecked()) {
                    lineReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }
                if (!lineChorusSwitch.isChecked()) {
                    lineChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if (!lineCrusherSwitch.isChecked()) {
                    lineCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if (!linePhaserSwitch.isChecked()) {
                    linePhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if (!lineFlangerSwitch.isChecked()) {
                    lineFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if (!lineDelaySwitch.isChecked()) {
                    lineDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });

        lineTremoloSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lineTremoloButton.setBackgroundResource(R.drawable.tremolobuttonpressed);
                    if(port!=null) {
                        byte outBuffer[] = {(byte) 0b11101011};
                        sendserial(outBuffer);
                    }
                } else {
                    if(lineTremoloBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !lineTremoloSwitch.isChecked())
                    {
                        lineTremoloButton.setBackgroundResource(R.drawable.tremolobuttoninbetween);

                        if(port!=null) {
                            byte outBuffer[] = {(byte) 0b11001011};
                            sendserial(outBuffer);
                        }
                    }
                    else
                    {
                        lineTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                    }
                }
            }
        });

        View lineAtbView = view.findViewById(R.id.lineAtbBottom);
        lineAtbBehavior = BottomSheetBehavior.from(lineAtbView);
        lineAtbBehavior.setPeekHeight(0);
        lineAtbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(!lineAtbSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b11000000};
                    sendserial(outBuffer);
                }
                else if(lineAtbSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b11100000};
                    sendserial(outBuffer);
                }



                if (lineAtbBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if (!lineAtbSwitch.isChecked()) {
                        lineAtbButton.setBackgroundResource(R.drawable.analog_tb_buttoninbetween);
                    }

                    lineAtbBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDistortionBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else {
                    if(!lineAtbSwitch.isChecked()) {
                        lineAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                    }
                    lineAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!lineDistortionSwitch.isChecked())
                {
                    lineDistortionButton.setBackgroundResource(R.drawable.analogdistbutton);
                }
                if(!lineTremoloSwitch.isChecked())
                {
                    lineTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!lineAutowahSwitch.isChecked())
                {
                    lineAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!lineRingmodSwitch.isChecked())
                {
                    lineRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!lineNoisegateSwitch.isChecked())
                {
                    lineNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if (!lineReverbSwitch.isChecked()) {
                    lineReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }
                if (!lineChorusSwitch.isChecked()) {
                    lineChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if (!lineCrusherSwitch.isChecked()) {
                    lineCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if (!linePhaserSwitch.isChecked()) {
                    linePhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if (!lineFlangerSwitch.isChecked()) {
                    lineFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if (!lineDelaySwitch.isChecked()) {
                    lineDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });

        lineAtbSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lineAtbButton.setBackgroundResource(R.drawable.analog_tb_buttonpressed);

                    if(port != null) {
                        byte outBuffer[] = {(byte) 0b11100000};
                        sendserial(outBuffer);
                    }
                } else {
                    if(lineAtbBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !lineAtbSwitch.isChecked())
                    {
                        lineAtbButton.setBackgroundResource(R.drawable.analog_tb_buttoninbetween);

                        if(port != null) {
                            byte outBuffer[] = {(byte) 0b11000000};
                            sendserial(outBuffer);
                        }
                    }
                    else
                    {
                        lineAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                    }
                }
            }
        });

        View lineRingmodView = view.findViewById(R.id.lineRingmodBottom);
        lineRingmodBehavior = BottomSheetBehavior.from(lineRingmodView);
        lineRingmodBehavior.setPeekHeight(0);
        lineRingmodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(!lineRingmodSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b11001001};
                    sendserial(outBuffer);
                }
                else if(lineRingmodSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b11101001};
                    sendserial(outBuffer);
                }

                if (lineRingmodBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if (!lineRingmodSwitch.isChecked()) {
                        lineRingmodButton.setBackgroundResource(R.drawable.ringmodbuttoninbetween);
                    }

                    lineRingmodBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDistortionBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else {
                    if(!lineRingmodSwitch.isChecked()) {
                        lineRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                    }
                    lineRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!lineDistortionSwitch.isChecked())
                {
                    lineDistortionButton.setBackgroundResource(R.drawable.analogdistbutton);
                }
                if(!lineAtbSwitch.isChecked())
                {
                    lineAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!lineAutowahSwitch.isChecked())
                {
                    lineAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!lineTremoloSwitch.isChecked())
                {
                    lineTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!lineNoisegateSwitch.isChecked())
                {
                    lineNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if (!lineReverbSwitch.isChecked()) {
                    lineReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }
                if (!lineChorusSwitch.isChecked()) {
                    lineChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if (!lineCrusherSwitch.isChecked()) {
                    lineCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if (!linePhaserSwitch.isChecked()) {
                    linePhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if (!lineFlangerSwitch.isChecked()) {
                    lineFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if (!lineDelaySwitch.isChecked()) {
                    lineDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });

        lineRingmodSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lineRingmodButton.setBackgroundResource(R.drawable.ringmodbuttonpressed);
                    if(port != null) {
                        byte outBuffer[] = {(byte) 0b11101101};
                        sendserial(outBuffer);
                    }
                } else {
                    if(lineRingmodBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !lineRingmodSwitch.isChecked())
                    {
                        lineRingmodButton.setBackgroundResource(R.drawable.ringmodbuttoninbetween);
                        if(port != null) {
                            byte outBuffer[] = {(byte) 0b11001101};
                            sendserial(outBuffer);
                        }
                    }
                    else
                    {
                        lineRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);

                    }
                }
            }
        });

        View lineDistortionView = view.findViewById(R.id.lineDistortionBottom);
        lineDistortionBehavior = BottomSheetBehavior.from(lineDistortionView);
        lineDistortionBehavior.setPeekHeight(0);
        lineDistortionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(!lineDistortionSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b11000001};
                    sendserial(outBuffer);
                }
                else if(lineDistortionSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b11100001};
                    sendserial(outBuffer);
                }



                if (lineDistortionBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if (!lineDistortionSwitch.isChecked()) {
                        lineDistortionButton.setBackgroundResource(R.drawable.analogdistbuttoninbetween);
                    }

                    lineDistortionBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else {
                    if(!lineDistortionSwitch.isChecked()) {
                        lineDistortionButton.setBackgroundResource(R.drawable.analogdistbutton);
                    }
                    lineDistortionBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!lineTremoloSwitch.isChecked())
                {
                    lineTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!lineAtbSwitch.isChecked())
                {
                    lineAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!lineAutowahSwitch.isChecked())
                {
                    lineAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!lineRingmodSwitch.isChecked())
                {
                    lineRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!lineNoisegateSwitch.isChecked())
                {
                    lineNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if (!lineReverbSwitch.isChecked()) {
                    lineReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }
                if (!lineChorusSwitch.isChecked()) {
                    lineChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if (!lineCrusherSwitch.isChecked()) {
                    lineCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if (!linePhaserSwitch.isChecked()) {
                    linePhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if (!lineFlangerSwitch.isChecked()) {
                    lineFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if (!lineDelaySwitch.isChecked()) {
                    lineDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });

        lineDistortionSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lineDistortionButton.setBackgroundResource(R.drawable.analogdistbuttonpressed);
                    if(port != null) {
                        byte outBuffer[] = {(byte) 0b11100001};
                        sendserial(outBuffer);
                    }
                } else {
                    if(lineDistortionBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !lineDistortionSwitch.isChecked())
                    {
                        lineDistortionButton.setBackgroundResource(R.drawable.analogdistbuttoninbetween);
                        if(port != null) {
                            byte outBuffer[] = {(byte) 0b11000001};
                            sendserial(outBuffer);
                        }
                    }
                    else
                    {
                        lineDistortionButton.setBackgroundResource(R.drawable.analogdistbutton);
                    }
                }
            }
        });


        View lineNoisegateView = view.findViewById(R.id.lineNoisegateBottom);
        lineNoisegateBehavior = BottomSheetBehavior.from(lineNoisegateView);
        lineNoisegateBehavior.setPeekHeight(0);
        lineNoisegateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(!lineNoisegateSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b11000100};
                    sendserial(outBuffer);
                }
                else if(lineNoisegateSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b11100100};
                    sendserial(outBuffer);
                }


                if (lineNoisegateBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if (!lineNoisegateSwitch.isChecked()) {
                        lineNoisegateButton.setBackgroundResource(R.drawable.noisegatebuttoninbetween);
                    }

                    lineNoisegateBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDistortionBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else {
                    if(!lineNoisegateSwitch.isChecked()) {
                        lineNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                    }
                    lineNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!lineTremoloSwitch.isChecked())
                {
                    lineTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!lineAtbSwitch.isChecked())
                {
                    lineAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!lineAutowahSwitch.isChecked())
                {
                    lineAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!lineRingmodSwitch.isChecked())
                {
                    lineRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!lineDistortionSwitch.isChecked())
                {
                    lineDistortionButton.setBackgroundResource(R.drawable.analogdistbutton);
                }
                if (!lineReverbSwitch.isChecked()) {
                    lineReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }
                if (!lineChorusSwitch.isChecked()) {
                    lineChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if (!lineCrusherSwitch.isChecked()) {
                    lineCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if (!linePhaserSwitch.isChecked()) {
                    linePhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if (!lineFlangerSwitch.isChecked()) {
                    lineFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if (!lineDelaySwitch.isChecked()) {
                    lineDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });

        lineNoisegateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lineNoisegateButton.setBackgroundResource(R.drawable.noisegatebuttonpressed);
                    if(port != null) {
                        byte outBuffer[] = {(byte) 0b11100100};
                        sendserial(outBuffer);
                    }

                } else {
                    if(lineNoisegateBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !lineNoisegateSwitch.isChecked())
                    {
                        lineNoisegateButton.setBackgroundResource(R.drawable.noisegatebuttoninbetween);
                        if(port != null) {
                            byte outBuffer[] = {(byte) 0b11000100};
                            sendserial(outBuffer);
                        }
                    }
                    else
                    {
                        lineNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                    }
                }
            }
        });

        View lineAutowahView = view.findViewById(R.id.lineAutowahBottom);
        lineAutowahBehavior = BottomSheetBehavior.from(lineAutowahView);
        lineAutowahBehavior.setPeekHeight(0);
        lineAutowahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(!lineAutowahSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b11001010};
                    sendserial(outBuffer);
                }
                else if(lineAutowahSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b11101010};
                    sendserial(outBuffer);
                }


                if (lineAutowahBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if (!lineAutowahSwitch.isChecked()) {
                        lineAutowahButton.setBackgroundResource(R.drawable.autowahbuttoninbetween);
                    }

                    lineAutowahBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDistortionBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else {
                    if(!lineAutowahSwitch.isChecked()) {
                        lineAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                    }
                    lineAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!lineTremoloSwitch.isChecked())
                {
                    lineTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!lineAtbSwitch.isChecked())
                {
                    lineAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!lineDistortionSwitch.isChecked())
                {
                    lineDistortionButton.setBackgroundResource(R.drawable.analogdistbutton);
                }
                if(!lineRingmodSwitch.isChecked())
                {
                    lineRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!lineNoisegateSwitch.isChecked())
                {
                    lineNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if (!lineReverbSwitch.isChecked()) {
                    lineReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }
                if (!lineChorusSwitch.isChecked()) {
                    lineChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if (!lineCrusherSwitch.isChecked()) {
                    lineCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if (!linePhaserSwitch.isChecked()) {
                    linePhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if (!lineFlangerSwitch.isChecked()) {
                    lineFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if (!lineDelaySwitch.isChecked()) {
                    lineDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });

        lineAutowahSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lineAutowahButton.setBackgroundResource(R.drawable.autowahbuttonpressed);
                    if(port != null) {
                        byte outBuffer[] = {(byte) 0b11101010};
                        sendserial(outBuffer);
                    }

                } else {
                    if(lineAutowahBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !lineAutowahSwitch.isChecked())
                    {
                        lineAutowahButton.setBackgroundResource(R.drawable.autowahbuttoninbetween);
                        if (port != null) {
                            byte outBuffer[] = {(byte) 0b11001010};
                            sendserial(outBuffer);
                        }
                    }
                    else
                    {
                        lineAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                    }
                }
            }
        });



        View lineReverbView = view.findViewById(R.id.lineReverbBottom);
        lineReverbBehavior = BottomSheetBehavior.from(lineReverbView);
        lineReverbBehavior.setPeekHeight(0);
        lineReverbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(!lineReverbSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b11001101};
                    sendserial(outBuffer);
                }
                else if(lineReverbSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b11101101};
                    sendserial(outBuffer);
                }



                if(lineReverbBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if(!lineReverbSwitch.isChecked())
                    {
                        lineReverbButton.setBackgroundResource(R.drawable.reverbbuttoninbetween);
                    }

                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDistortionBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    if(!lineReverbSwitch.isChecked()) {
                        lineReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                    }
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }


                if(!lineTremoloSwitch.isChecked())
                {
                    lineTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!lineAtbSwitch.isChecked())
                {
                    lineAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!lineAutowahSwitch.isChecked())
                {
                    lineAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!lineRingmodSwitch.isChecked())
                {
                    lineRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!lineNoisegateSwitch.isChecked())
                {
                    lineNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if (!lineDistortionSwitch.isChecked()) {
                    lineDistortionButton.setBackgroundResource(R.drawable.analogdistbutton);
                }
                if (!lineChorusSwitch.isChecked()) {
                    lineChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if (!lineCrusherSwitch.isChecked()) {
                    lineCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if (!linePhaserSwitch.isChecked()) {
                    linePhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if (!lineFlangerSwitch.isChecked()) {
                    lineFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if (!lineDelaySwitch.isChecked()) {
                    lineDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });

        lineReverbSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lineReverbButton.setBackgroundResource(R.drawable.reverbbuttonpressed);
                    if(port != null) {
                        byte outBuffer[] = {(byte) 0b11101101};
                        sendserial(outBuffer);
                    }
                } else {
                    if(lineReverbBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !lineReverbSwitch.isChecked())
                    {
                        lineReverbButton.setBackgroundResource(R.drawable.reverbbuttoninbetween);
                        if(port != null) {
                            byte outBuffer[] = {(byte) 0b11001101};
                            sendserial(outBuffer);
                        }
                    }
                    else
                    {
                        lineReverbButton.setBackgroundResource(R.drawable.reverbbutton);
//                        byte outBuffer[] = {(byte) 0b11001101};
//                        sendserial(outBuffer);
                    }
                }
            }
        });


        View lineChorusView = view.findViewById(R.id.lineChorusBottom);
        lineChorusBehavior = BottomSheetBehavior.from(lineChorusView);
        lineChorusBehavior.setPeekHeight(0);
        lineChorusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(!lineChorusSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b11000110};
                    sendserial(outBuffer);
                }
                else if(lineChorusSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b11100110};
                    sendserial(outBuffer);
                }


                if(lineChorusBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if(!lineChorusSwitch.isChecked())
                    {
                        lineChorusButton.setBackgroundResource(R.drawable.chorusbuttoninbetween);
                    }

                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDistortionBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    if(!lineChorusSwitch.isChecked()) {
                        lineChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                    }
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!lineTremoloSwitch.isChecked())
                {
                    lineTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!lineAtbSwitch.isChecked())
                {
                    lineAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!lineAutowahSwitch.isChecked())
                {
                    lineAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!lineRingmodSwitch.isChecked())
                {
                    lineRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!lineNoisegateSwitch.isChecked())
                {
                    lineNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if (!lineReverbSwitch.isChecked()) {
                    lineReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }
                if (!lineDistortionSwitch.isChecked()) {
                    lineDistortionButton.setBackgroundResource(R.drawable.analogdistbutton);
                }
                if (!lineCrusherSwitch.isChecked()) {
                    lineCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if (!linePhaserSwitch.isChecked()) {
                    linePhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if (!lineFlangerSwitch.isChecked()) {
                    lineFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if (!lineDelaySwitch.isChecked()) {
                    lineDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });

        lineChorusSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lineChorusButton.setBackgroundResource(R.drawable.chorusbuttonpressed);
                    if(port != null) {
                        byte outBuffer[] = {(byte) 0b11100110};
                        sendserial(outBuffer);
                    }
                } else {
                    if(lineChorusBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !lineChorusSwitch.isChecked())
                    {
                        lineChorusButton.setBackgroundResource(R.drawable.chorusbuttoninbetween);
                        if(port != null) {
                            byte outBuffer[] = {(byte) 0b11000110};
                            sendserial(outBuffer);
                        }
                    }
                    else
                    {
                        lineChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                    }
                }
            }
        });

        View lineCrusherView = view.findViewById(R.id.lineCrusherBottom);
        lineCrusherBehavior = BottomSheetBehavior.from(lineCrusherView);
        lineCrusherBehavior.setPeekHeight(0);
        lineCrusherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(!lineCrusherSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b11000101};
                    sendserial(outBuffer);
                }
                else if(lineCrusherSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b11100101};
                    sendserial(outBuffer);
                }


                if(lineCrusherBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if(!lineCrusherSwitch.isChecked())
                    {
                        lineCrusherButton.setBackgroundResource(R.drawable.bitcrusherbuttoninbetween);
                    }

                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDistortionBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    if(!lineCrusherSwitch.isChecked()) {
                        lineCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                    }
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!lineTremoloSwitch.isChecked())
                {
                    lineTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!lineAtbSwitch.isChecked())
                {
                    lineAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!lineAutowahSwitch.isChecked())
                {
                    lineAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!lineRingmodSwitch.isChecked())
                {
                    lineRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!lineNoisegateSwitch.isChecked())
                {
                    lineNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if (!lineReverbSwitch.isChecked()) {
                    lineReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }
                if (!lineChorusSwitch.isChecked()) {
                    lineChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if (!lineDistortionSwitch.isChecked()) {
                    lineDistortionButton.setBackgroundResource(R.drawable.analogdistbutton);
                }
                if (!linePhaserSwitch.isChecked()) {
                    linePhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if (!lineFlangerSwitch.isChecked()) {
                    lineFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if (!lineDelaySwitch.isChecked()) {
                    lineDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });


        lineCrusherSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lineCrusherButton.setBackgroundResource(R.drawable.bitcrusherbuttonpressed);
                    if(port != null) {
                        byte outBuffer[] = {(byte) 0b11100101};
                        sendserial(outBuffer);
                    }
                } else {
                    if(lineCrusherBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !lineCrusherSwitch.isChecked())
                    {
                        lineCrusherButton.setBackgroundResource(R.drawable.bitcrusherbuttoninbetween);
                        if(port != null) {
                            byte outBuffer[] = {(byte) 0b11000101};
                            sendserial(outBuffer);
                        }
                    }
                    else
                    {
                        lineCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                    }
                }
            }
        });

        View lineFlangerView = view.findViewById(R.id.lineFlangerBottom);
        lineFlangerBehavior = BottomSheetBehavior.from(lineFlangerView);
        lineFlangerBehavior.setPeekHeight(0);
        lineFlangerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(!lineFlangerSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b11000111};
                    sendserial(outBuffer);
                }
                else if(lineFlangerSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b11100111};
                    sendserial(outBuffer);
                }


                if(lineFlangerBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if(!lineFlangerSwitch.isChecked())
                    {
                        lineFlangerButton.setBackgroundResource(R.drawable.flangerbuttoninbetween);
                    }

                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDistortionBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    if(!lineFlangerSwitch.isChecked()) {
                    lineFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!lineTremoloSwitch.isChecked())
                {
                    lineTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!lineAtbSwitch.isChecked())
                {
                    lineAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!lineAutowahSwitch.isChecked())
                {
                    lineAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!lineRingmodSwitch.isChecked())
                {
                    lineRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!lineNoisegateSwitch.isChecked())
                {
                    lineNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if (!lineReverbSwitch.isChecked()) {
                    lineReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }
                if (!lineChorusSwitch.isChecked()) {
                    lineChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if (!lineCrusherSwitch.isChecked()) {
                    lineCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if (!linePhaserSwitch.isChecked()) {
                    linePhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if (!lineDistortionSwitch.isChecked()) {
                    lineDistortionButton.setBackgroundResource(R.drawable.analogdistbutton);
                }
                if (!lineDelaySwitch.isChecked()) {
                    lineDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });

        lineFlangerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lineFlangerButton.setBackgroundResource(R.drawable.flangerbuttonpressed);
                    if(port != null) {
                        byte outBuffer[] = {(byte) 0b11100111};
                        sendserial(outBuffer);
                    }
                } else {
                    if(lineFlangerBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !lineFlangerSwitch.isChecked())
                    {
                        lineFlangerButton.setBackgroundResource(R.drawable.flangerbuttoninbetween);
                        if(port != null) {
                            byte outBuffer[] = {(byte) 0b11000111};
                            sendserial(outBuffer);
                        }
                    }
                    else
                    {
                        lineFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                    }
                }
            }
        });

        View linePhaserView = view.findViewById(R.id.linePhaserBottom);
        linePhaserBehavior = BottomSheetBehavior.from(linePhaserView);
        linePhaserBehavior.setPeekHeight(0);
        linePhaserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(!linePhaserSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b11001000};
                    sendserial(outBuffer);
                }
                else if(linePhaserSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b11101000};
                    sendserial(outBuffer);
                }


                if(linePhaserBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if(!linePhaserSwitch.isChecked())
                    {
                        linePhaserButton.setBackgroundResource(R.drawable.phaserbuttoninbetween);
                    }

                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDistortionBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    if(!linePhaserSwitch.isChecked()) {
                        linePhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                    }
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!lineTremoloSwitch.isChecked())
                {
                    lineTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!lineAtbSwitch.isChecked())
                {
                    lineAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!lineAutowahSwitch.isChecked())
                {
                    lineAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!lineRingmodSwitch.isChecked())
                {
                    lineRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!lineNoisegateSwitch.isChecked())
                {
                    lineNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if (!lineReverbSwitch.isChecked()) {
                    lineReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }
                if (!lineChorusSwitch.isChecked()) {
                    lineChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if (!lineCrusherSwitch.isChecked()) {
                    lineCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if (!lineDistortionSwitch.isChecked()) {
                    lineDistortionButton.setBackgroundResource(R.drawable.analogdistbutton);
                }
                if (!lineFlangerSwitch.isChecked()) {
                    lineFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if (!lineDelaySwitch.isChecked()) {
                    lineDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });

        linePhaserSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    linePhaserButton.setBackgroundResource(R.drawable.phaserbuttonpressed);
                    if(port != null) {
                        byte outBuffer[] = {(byte) 0b11101000};
                        sendserial(outBuffer);
                    }
                } else {
                    if(linePhaserBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !linePhaserSwitch.isChecked())
                    {
                        linePhaserButton.setBackgroundResource(R.drawable.phaserbuttoninbetween);
                        if(port != null) {
                            byte outBuffer[] = {(byte) 0b11001000};
                            sendserial(outBuffer);
                        }
                    }
                    else
                    {
                        linePhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                    }
                }
            }
        });

        View lineDelayView = view.findViewById(R.id.lineDelayBottom);
        lineDelayBehavior = BottomSheetBehavior.from(lineDelayView);
        lineDelayBehavior.setPeekHeight(0);
        lineDelayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(!lineDelaySwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b11001100};
                    sendserial(outBuffer);
                }
                else if(lineDelaySwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b11101100};
                    sendserial(outBuffer);
                }


                if(lineDelayBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if(!lineDelaySwitch.isChecked())
                    {
                        lineDelayButton.setBackgroundResource(R.drawable.delaybuttoninbetween);
                    }

                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    lineAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineDistortionBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    linePhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    lineChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else {
                    if(!lineDelaySwitch.isChecked()) {
                        lineDelayButton.setBackgroundResource(R.drawable.delaybutton);
                    }
                    lineDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!lineTremoloSwitch.isChecked())
                {
                    lineTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!lineAtbSwitch.isChecked())
                {
                    lineAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!lineAutowahSwitch.isChecked())
                {
                    lineAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!lineRingmodSwitch.isChecked())
                {
                    lineRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!lineNoisegateSwitch.isChecked())
                {
                    lineNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if (!lineReverbSwitch.isChecked()) {
                    lineReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }
                if (!lineChorusSwitch.isChecked()) {
                    lineChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if (!lineCrusherSwitch.isChecked()) {
                    lineCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if (!lineDistortionSwitch.isChecked()) {
                    lineDistortionButton.setBackgroundResource(R.drawable.analogdistbutton);
                }
                if (!lineFlangerSwitch.isChecked()) {
                    lineFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if (!lineDistortionSwitch.isChecked()) {
                    lineDistortionButton.setBackgroundResource(R.drawable.analogdistbutton);
                }


            }
        });
        lineDelaySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lineDelayButton.setBackgroundResource(R.drawable.delaybuttonpressed);
                    if(port != null) {
                        byte outBuffer[] = {(byte) 0b11101100};
                        sendserial(outBuffer);
                    }
                } else {
                    if(lineDelayBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !lineDelaySwitch.isChecked())
                    {
                        lineDelayButton.setBackgroundResource(R.drawable.delaybuttoninbetween);
                        if(port != null) {
                            byte outBuffer[] = {(byte) 0b11001100};
                            sendserial(outBuffer);
                        }
                    }
                    else
                    {
                        lineDelayButton.setBackgroundResource(R.drawable.delaybutton);
                    }
                }
            }
        });
    }

    BottomSheetBehavior micReverbBehavior, micChorusBehavior, micCrusherBehavior,
            micFlangerBehavior, micPhaserBehavior, micDelayBehavior, micTremoloBehavior, micAutowahBehavior, micBassemulatorBehavior,
     micRingmodBehavior, micNoisegateBehavior, micAtbBehavior;
    public void bottomMicEffectsInit(){

        final ImageButton micDelayButton = (ImageButton) view.findViewById(R.id.mic_delay_button);
        final ImageButton micReverbButton = (ImageButton) view.findViewById(R.id.mic_reverb_button);
        final ImageButton micChorusButton = (ImageButton) view.findViewById(R.id.mic_chorus_button);
        final ImageButton micPhaserButton = (ImageButton) view.findViewById(R.id.mic_phaser_button);
        final ImageButton micFlangerButton = (ImageButton) view.findViewById(R.id.mic_flanger_button);
        final ImageButton micCrusherButton = (ImageButton) view.findViewById(R.id.mic_bitcrusher_button);
        final ImageButton micTremoloButton = (ImageButton) view.findViewById(R.id.mic_tremolo_button);
        final ImageButton micAutowahButton = (ImageButton) view.findViewById(R.id.mic_autowah_button);
        final ImageButton micBassemulatorButton = (ImageButton) view.findViewById(R.id.mic_digitalfuzz_button);
        final ImageButton micRingmodButton = (ImageButton) view.findViewById(R.id.mic_ringmod_button);
        final ImageButton micNoisegateButton = (ImageButton) view.findViewById(R.id.mic_noisegate_button);
        final ImageButton micAtbButton = (ImageButton) view.findViewById(R.id.mic_atb_button);

        final ToggleButton micReverbSwitch = (ToggleButton) view.findViewById(R.id.MicReverbSwitch);
        final ToggleButton micChorusSwitch = (ToggleButton) view.findViewById(R.id.MicChorusSwitch);
        final ToggleButton micCrusherSwitch = (ToggleButton) view.findViewById(R.id.MicCrusherSwitch);
        final ToggleButton micFlangerSwitch = (ToggleButton) view.findViewById(R.id.MicFlangerSwitch);
        final ToggleButton micPhaserSwitch = (ToggleButton) view.findViewById(R.id.MicPhaserSwitch);
        final ToggleButton micDelaySwitch = (ToggleButton) view.findViewById(R.id.MicDelaySwitch);
        final ToggleButton micNoisegateSwitch = (ToggleButton) view.findViewById(R.id.MicNoisegateSwitch);
        final ToggleButton micAtbSwitch = (ToggleButton) view.findViewById(R.id.MicAtbSwitch);
        final ToggleButton micRingmodSwitch = (ToggleButton) view.findViewById(R.id.MicRingmodSwitch);
        final ToggleButton micTremoloSwitch = (ToggleButton) view.findViewById(R.id.MicTremoloSwitch);
        final ToggleButton micAutowahSwitch = (ToggleButton) view.findViewById(R.id.MicAutowahSwitch);
        final ToggleButton micBassemulatorSwitch = (ToggleButton) view.findViewById(R.id.MicBassemulatorSwitch);





        final View micBassemulatorView = view.findViewById(R.id.micBassemulatorBottom);
        micBassemulatorBehavior = BottomSheetBehavior.from(micBassemulatorView);
        micBassemulatorBehavior.setPeekHeight(0);
        micBassemulatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(micBassemulatorBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    if(!micBassemulatorSwitch.isChecked())
                    {
                        micBassemulatorButton.setBackgroundResource(R.drawable.digitalfuzzbuttoninbetweenpng);
                    }

                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                }
                else {
                    if(!micBassemulatorSwitch.isChecked()) {
                        micBassemulatorButton.setBackgroundResource(R.drawable.digitalfuzzbutton);
                    }
                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });



        micBassemulatorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micBassemulatorButton.setBackgroundResource(R.drawable.digitalfuzzbuttonpressed);
                }
                else {
                    if(micBassemulatorBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micBassemulatorSwitch.isChecked())
                    {
                        micBassemulatorButton.setBackgroundResource(R.drawable.digitalfuzzbuttoninbetweenpng);
                    }
                    else
                    {
                        micBassemulatorButton.setBackgroundResource(R.drawable.digitalfuzzbutton);
                    }
                }
            }
        });


        final View micNoisegateView = view.findViewById(R.id.micNoisegateBottom);
        micNoisegateBehavior = BottomSheetBehavior.from(micNoisegateView);
        micNoisegateBehavior.setPeekHeight(0);
        micNoisegateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(!micNoisegateSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b01000100};
                    sendserial(outBuffer);
                }
                else if(micNoisegateSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b01100100};
                    sendserial(outBuffer);
                }


                if(micNoisegateBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    if(!micNoisegateSwitch.isChecked())
                    {
                        micNoisegateButton.setBackgroundResource(R.drawable.noisegatebuttoninbetween);
                    }

                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                }
                else {
                    if(!micNoisegateSwitch.isChecked()) {
                        micNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                    }
                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundResource(R.drawable.digitalfuzzbutton);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });



        micNoisegateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micNoisegateButton.setBackgroundResource(R.drawable.noisegatebuttonpressed);
                    if(port != null) {
                        byte outBuffer[] = {(byte) 0b01100100};
                        sendserial(outBuffer);
                    }
                }
                else {
                    if(micNoisegateBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micNoisegateSwitch.isChecked())
                    {
                        micNoisegateButton.setBackgroundResource(R.drawable.noisegatebuttoninbetween);
                        if(port != null) {
                            byte outBuffer[] = {(byte) 0b01000100};
                            sendserial(outBuffer);
                        }
                    }
                    else
                    {
                        micNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                    }
                }
            }
        });

        final View micAtbView = view.findViewById(R.id.micAtbBottom);
        micAtbBehavior = BottomSheetBehavior.from(micAtbView);
        micAtbBehavior.setPeekHeight(0);
        micAtbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(!micAtbSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b01000000};
                    sendserial(outBuffer);
                }
                else if(micAtbSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b01100000};
                    sendserial(outBuffer);
                }


                if(micAtbBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    if(!micAtbSwitch.isChecked())
                    {
                        micAtbButton.setBackgroundResource(R.drawable.analog_tb_buttoninbetween);
                    }

                    micAtbBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                }
                else {
                    if(!micAtbSwitch.isChecked()) {
                        micAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                    }
                    micAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }


                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundResource(R.drawable.digitalfuzzbutton);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });



        micAtbSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micAtbButton.setBackgroundResource(R.drawable.analog_tb_buttonpressed);
                    if(port != null) {
                        byte outBuffer[] = {(byte) 0b01100000};
                        sendserial(outBuffer);
                    }


                }
                else {
                    if(micAtbBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micAtbSwitch.isChecked())
                    {
                        micAtbButton.setBackgroundResource(R.drawable.analog_tb_buttoninbetween);
                        if(port != null) {
                            byte outBuffer[] = {(byte) 0b01000000};
                            sendserial(outBuffer);
                        }
                    }
                    else
                    {
                        micAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                    }
                }
            }
        });


        final View micRingmodView = view.findViewById(R.id.micRingmodBottom);
        micRingmodBehavior = BottomSheetBehavior.from(micRingmodView);
        micRingmodBehavior.setPeekHeight(0);
        micRingmodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(!micRingmodSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b01001001};
                    sendserial(outBuffer);
                }
                else if(micRingmodSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b01101001};
                    sendserial(outBuffer);
                }


                if(micRingmodBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    if(!micRingmodSwitch.isChecked())
                    {
                        micRingmodButton.setBackgroundResource(R.drawable.ringmodbuttoninbetween);
                    }

                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                }
                else {
                    if(!micRingmodSwitch.isChecked()) {
                        micRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                    }
                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundResource(R.drawable.digitalfuzzbutton);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });



        micRingmodSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micRingmodButton.setBackgroundResource(R.drawable.ringmodbuttonpressed);
                    if(port != null) {
                        byte outBuffer[] = {(byte) 0b01101001};
                        sendserial(outBuffer);
                    }
                }
                else {
                    if(micRingmodBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micRingmodSwitch.isChecked())
                    {
                        micRingmodButton.setBackgroundResource(R.drawable.ringmodbuttoninbetween);
                        if(port != null) {
                            byte outBuffer[] = {(byte) 0b01001001};
                            sendserial(outBuffer);
                        }
                    }
                    else
                    {
                        micRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                    }
                }
            }
        });


        final View micAutowahView = view.findViewById(R.id.micAutowahBottom);
        micAutowahBehavior = BottomSheetBehavior.from(micAutowahView);
        micAutowahBehavior.setPeekHeight(0);
        micAutowahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(!micAutowahSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b01001010};
                    sendserial(outBuffer);
                }
                else if(micAutowahSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b01101010};
                    sendserial(outBuffer);
                }


                if(micAutowahBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    if(!micAutowahSwitch.isChecked())
                    {
                        micAutowahButton.setBackgroundResource(R.drawable.autowahbuttoninbetween);
                    }

                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                }
                else {
                    if(!micAutowahSwitch.isChecked()) {
                        micAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                    }
                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundResource(R.drawable.digitalfuzzbutton);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });



        micAutowahSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micAutowahButton.setBackgroundResource(R.drawable.autowahbuttonpressed);
                    if(port != null) {
                        byte outBuffer[] = {(byte) 0b01101010};
                        sendserial(outBuffer);
                    }
                }
                else {
                    if(micAutowahBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micAutowahSwitch.isChecked())
                    {
                        micAutowahButton.setBackgroundResource(R.drawable.autowahbuttoninbetween);
                        if(port != null) {
                            byte outBuffer[] = {(byte) 0b01001010};
                            sendserial(outBuffer);
                        }
                    }
                    else
                    {
                        micAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                    }
                }
            }
        });


        final View micTremoloView = view.findViewById(R.id.micTremoloBottom);
        micTremoloBehavior = BottomSheetBehavior.from(micTremoloView);
        micTremoloBehavior.setPeekHeight(0);
        micTremoloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(!micTremoloSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b01001011};
                    sendserial(outBuffer);
                }
                else if(micTremoloSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b01101011};
                    sendserial(outBuffer);
                }


                if(micTremoloBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    if(!micTremoloSwitch.isChecked())
                    {
                        micTremoloButton.setBackgroundResource(R.drawable.tremolobuttoninbetween);
                    }

                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                }
                else {
                    if(!micTremoloSwitch.isChecked()) {
                        micTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                    }
                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundResource(R.drawable.digitalfuzzbutton);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });



        micTremoloSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micTremoloButton.setBackgroundResource(R.drawable.tremolobuttonpressed);
                    if(port != null) {
                        byte outBuffer[] = {(byte) 0b01101011};
                        sendserial(outBuffer);
                    }
                }
                else {
                    if(micTremoloBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micTremoloSwitch.isChecked())
                    {
                        micTremoloButton.setBackgroundResource(R.drawable.tremolobuttoninbetween);
                        if(port != null) {
                            byte outBuffer[] = {(byte) 0b01001011};
                            sendserial(outBuffer);
                        }
                    }
                    else
                    {
                        micTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                    }
                }
            }
        });


        final View micReverbView = view.findViewById(R.id.micReverbBottom);
        micReverbBehavior = BottomSheetBehavior.from(micReverbView);
        micReverbBehavior.setPeekHeight(0);
        micReverbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(!micReverbSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b01001101};
                    sendserial(outBuffer);
                }
                else if(micReverbSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b01101101};
                    sendserial(outBuffer);
                }


                if(micReverbBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    if(!micReverbSwitch.isChecked())
                    {
                        micReverbButton.setBackgroundResource(R.drawable.reverbbuttoninbetween);
                    }

                    micReverbBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                }
                else {
                    if(!micReverbSwitch.isChecked()) {
                        micReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                    }
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundResource(R.drawable.digitalfuzzbutton);
                }


                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });



        micReverbSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micReverbButton.setBackgroundResource(R.drawable.reverbbuttonpressed);
                    if(port != null) {
                        byte outBuffer[] = {(byte) 0b01101101};
                        sendserial(outBuffer);
                    }
                }
                else {
                    if(micReverbBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micReverbSwitch.isChecked())
                    {
                        micReverbButton.setBackgroundResource(R.drawable.reverbbuttoninbetween);
                        if(port != null) {
                            byte outBuffer[] = {(byte) 0b01001101};
                            sendserial(outBuffer);
                        }
                    }
                    else
                    {
                        micReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                    }
                }
            }
        });




        final View micChorusView = view.findViewById(R.id.micChorusBottom);
        micChorusBehavior = BottomSheetBehavior.from(micChorusView);
        micChorusBehavior.setPeekHeight(0);
        micChorusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {

                if(!micChorusSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b01000110};
                    sendserial(outBuffer);
                }
                else if(micChorusSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b01100110};
                    sendserial(outBuffer);
                }

                if(micChorusBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if(!micChorusSwitch.isChecked())
                    {
                        micChorusButton.setBackgroundResource(R.drawable.chorusbuttoninbetween);
                    }

                    micChorusBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                }
                else {
                    if(!micChorusSwitch.isChecked()) {
                        micChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                    }
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundResource(R.drawable.digitalfuzzbutton);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }

                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });

        micChorusSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micChorusButton.setBackgroundResource(R.drawable.chorusbuttonpressed);
                    if(port != null) {
                        byte outBuffer[] = {(byte) 0b01100110};
                        sendserial(outBuffer);
                    }
                }
                else {
                    if(micChorusBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micChorusSwitch.isChecked())
                    {
                        micChorusButton.setBackgroundResource(R.drawable.chorusbuttoninbetween);
                        if(port != null) {
                            byte outBuffer[] = {(byte) 0b01000110};
                            sendserial(outBuffer);
                        }
                    }
                    else
                    {
                        micChorusButton.setBackgroundResource(R.drawable.chorusbutton);
//                        byte outBuffer[] = {(byte) 0b01000110};
//                        sendserial(outBuffer);
                    }
                }
            }
        });

        final View micCrusherView = view.findViewById(R.id.micCrusherBottom);
        micCrusherBehavior = BottomSheetBehavior.from(micCrusherView);
        micCrusherBehavior.setPeekHeight(0);
        micCrusherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(!micCrusherSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b01000101};
                    sendserial(outBuffer);
                }
                else if(micCrusherSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b01100101};
                    sendserial(outBuffer);
                }

                if(micCrusherBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if(!micCrusherSwitch.isChecked())
                    {
                        micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbuttoninbetween);
                    }


                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                }
                else {
                    if(!micCrusherSwitch.isChecked()) {
                        micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                    }
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundResource(R.drawable.digitalfuzzbutton);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });

        micCrusherSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbuttonpressed);
                    if(port != null) {
                        byte outBuffer[] = {(byte) 0b01100101};
                        sendserial(outBuffer);
                    }
                }
                else {
                    if(micCrusherBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micCrusherSwitch.isChecked())
                    {
                        micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbuttoninbetween);
                        if(port != null) {
                            byte outBuffer[] = {(byte) 0b01000101};
                            sendserial(outBuffer);
                        }
                    }
                    else
                    {
                        micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                    }
                }
            }
        });

        final View micFlangerView = view.findViewById(R.id.micFlangerBottom);
        micFlangerBehavior = BottomSheetBehavior.from(micFlangerView);
        micFlangerBehavior.setPeekHeight(0);
        micFlangerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(!micFlangerSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b01000111};
                    sendserial(outBuffer);
                }
                else if(micFlangerSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b01100111};
                    sendserial(outBuffer);
                }

                if(micFlangerBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if(!micFlangerSwitch.isChecked())
                    {
                        micFlangerButton.setBackgroundResource(R.drawable.flangerbuttoninbetween);
                    }

                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                else {
                    if(!micFlangerSwitch.isChecked()) {
                        micFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                    }
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundResource(R.drawable.digitalfuzzbutton);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }
            }
        });

        micFlangerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micFlangerButton.setBackgroundResource(R.drawable.flangerbuttonpressed);
                    if(port != null) {
                        byte outBuffer[] = {(byte) 0b01100111};
                        sendserial(outBuffer);
                    }
                }
                else {
                    if(micFlangerBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micFlangerSwitch.isChecked())
                    {
                        micFlangerButton.setBackgroundResource(R.drawable.flangerbuttoninbetween);
                        if(port != null) {
                            byte outBuffer[] = {(byte) 0b01000111};
                            sendserial(outBuffer);
                        }
                    }
                    else
                    {
                        micFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                    }
                }
            }
        });

        final View micPhaserView = view.findViewById(R.id.micPhaserBottom);
        micPhaserBehavior = BottomSheetBehavior.from(micPhaserView);
        micPhaserBehavior.setPeekHeight(0);
        micPhaserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(!micPhaserSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b01001000};
                    sendserial(outBuffer);
                }
                else if(micPhaserSwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b01101000};
                    sendserial(outBuffer);
                }

                if(micPhaserBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if(!micPhaserSwitch.isChecked())
                    {
                        micPhaserButton.setBackgroundResource(R.drawable.phaserbuttoninbetween);
                    }

                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                }

                else {
                    if(!micPhaserSwitch.isChecked()) {
                        micPhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                    }
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundResource(R.drawable.digitalfuzzbutton);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if(!micDelaySwitch.isChecked())
                {
                    micDelayButton.setBackgroundResource(R.drawable.delaybutton);
                }

            }
        });

        micPhaserSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micPhaserButton.setBackgroundResource(R.drawable.phaserbuttonpressed);
                    if(port != null) {
                        byte outBuffer[] = {(byte) 0b01101000};
                        sendserial(outBuffer);
                    }
                }
                else {
                    if(micPhaserBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micPhaserSwitch.isChecked())
                    {
                        micPhaserButton.setBackgroundResource(R.drawable.phaserbuttoninbetween);
                        if(port != null) {
                            byte outBuffer[] = {(byte) 0b01001000};
                            sendserial(outBuffer);
                        }
                    }
                    else
                    {
                        micPhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                    }
                }
            }
        });

        final View micDelayView = view.findViewById(R.id.micDelayBottom);
        micDelayBehavior = BottomSheetBehavior.from(micDelayView);
        micDelayBehavior.setPeekHeight(0);
        micDelayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View echoView) {
                if(!micDelaySwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b01001100};
                    sendserial(outBuffer);
                }
                else if(micDelaySwitch.isChecked() && port != null)
                {
                    byte outBuffer[] = {(byte) 0b01101100};
                    sendserial(outBuffer);
                }


                if(micDelayBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {

                    if(!micDelaySwitch.isChecked())
                    {
                        micDelayButton.setBackgroundResource(R.drawable.delaybuttoninbetween);
                    }

                    micDelayBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    micTremoloBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAutowahBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micAtbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micRingmodBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micBassemulatorBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micNoisegateBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micPhaserBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micFlangerBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micCrusherBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micReverbBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    micChorusBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                }

                else {
                    if(!micDelaySwitch.isChecked()) {
                        micDelayButton.setBackgroundResource(R.drawable.delaybutton);
                    }
                    micDelayBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                if(!micAtbSwitch.isChecked())
                {
                    micAtbButton.setBackgroundResource(R.drawable.analog_tb_button);
                }
                if(!micTremoloSwitch.isChecked())
                {
                    micTremoloButton.setBackgroundResource(R.drawable.tremolobutton);
                }
                if(!micAutowahSwitch.isChecked())
                {
                    micAutowahButton.setBackgroundResource(R.drawable.autowahbutton);
                }
                if(!micRingmodSwitch.isChecked())
                {
                    micRingmodButton.setBackgroundResource(R.drawable.ringmodbutton);
                }
                if(!micBassemulatorSwitch.isChecked())
                {
                    micBassemulatorButton.setBackgroundResource(R.drawable.digitalfuzzbutton);
                }


                if(!micReverbSwitch.isChecked())
                {
                    micReverbButton.setBackgroundResource(R.drawable.reverbbutton);
                }

                if(!micChorusSwitch.isChecked())
                {
                    micChorusButton.setBackgroundResource(R.drawable.chorusbutton);
                }
                if(!micCrusherSwitch.isChecked())
                {
                    micCrusherButton.setBackgroundResource(R.drawable.bitcrusherbutton);
                }
                if(!micPhaserSwitch.isChecked())
                {
                    micPhaserButton.setBackgroundResource(R.drawable.phaserbutton);
                }
                if(!micFlangerSwitch.isChecked())
                {
                    micFlangerButton.setBackgroundResource(R.drawable.flangerbutton);
                }
                if(!micNoisegateSwitch.isChecked())
                {
                    micNoisegateButton.setBackgroundResource(R.drawable.noisegatebutton);
                }
            }
        });

        micDelaySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    micDelayButton.setBackgroundResource(R.drawable.delaybuttonpressed);
                    if(port != null) {
                        byte outBuffer[] = {(byte) 0b01101100};
                        sendserial(outBuffer);
                    }
                }
                else {
                    if(micDelayBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED && !micDelaySwitch.isChecked())
                    {
                        micDelayButton.setBackgroundResource(R.drawable.delaybuttoninbetween);
                        if(port != null) {
                            byte outBuffer[] = {(byte) 0b01001100};
                            sendserial(outBuffer);
                        }
                    }
                    else
                    {
                        micDelayButton.setBackgroundResource(R.drawable.delaybutton);
                    }
                }
            }
        });


    }
}
