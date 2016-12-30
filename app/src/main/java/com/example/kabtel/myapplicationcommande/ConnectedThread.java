package com.example.kabtel.myapplicationcommande;

import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Handler;

/**
 * Created by kabtel on 08/06/16.
 */
public class ConnectedThread {
    public ConnectedThread(Handler handler, BluetoothSocket socket) {
        Handler mmHandler = handler;
        BluetoothSocket mmSocket = socket;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;
        try {
            tmpIn = socket.getInputStream();
            tmpOut = socket.getOutputStream();
        }
        catch (IOException e) { }
        InputStream mmInStream = tmpIn;
        OutputStream mmOutStream = tmpOut;
    }
}
