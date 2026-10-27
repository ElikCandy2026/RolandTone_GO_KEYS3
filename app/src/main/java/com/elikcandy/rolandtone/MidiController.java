package com.elikcandy.rolandtone;

import android.content.Context;
import android.media.midi.*;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

public class MidiController {
    private MidiManager midiManager;
    private MidiInputPort midiInputPort;

    public MidiController(Context context) {
        midiManager = (MidiManager) context.getSystemService(Context.MIDI_SERVICE);
    }

    public void openFirstUsbMidiDevice() {
        if (midiManager == null) return;
        MidiDeviceInfo[] devices = midiManager.getDevices();
        if (devices == null) return;
        for (MidiDeviceInfo device : devices) {
            if (device.getType() != MidiDeviceInfo.TYPE_BLUETOOTH) {
                // Используем новый интерфейс OnDeviceOpenedListener вместо старого DeviceCallback
                midiManager.openDevice(device, new MidiManager.OnDeviceOpenedListener() {
                    @Override
                    public void onDeviceOpened(MidiDevice openedDevice) {
                        if (openedDevice != null) {
                            midiInputPort = openedDevice.openInputPort(0);
                        }
                    }
                }, new Handler(Looper.getMainLooper()));
                break;
            }
        }
    }

    public void sendToneCommand(int msb, int lsb, int pc) {
        if (midiInputPort == null) return;
        byte[] buffer = new byte[36];
        for (int i = 0; i < 4; i++) {
            int base = i * 9;
            byte status = (byte) (0xB0 + i);
            buffer[base] = status;
            buffer[base + 1] = 0x00;
            buffer[base + 2] = (byte) msb;
            buffer[base + 3] = status;
            buffer[base + 4] = 0x00;
            buffer[base + 5] = (byte) lsb;
            buffer[base + 6] = status;
            buffer[base + 7] = 0x00;
            buffer[base + 8] = (byte) pc;
        }
        try {
            midiInputPort.send(buffer, 0, buffer.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
