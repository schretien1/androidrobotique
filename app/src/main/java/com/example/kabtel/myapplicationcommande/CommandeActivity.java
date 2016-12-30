/*package com.example.kabtel.myapplicationcommande;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class CommandeActivity extends Activity {
    public static final int STOP = 99;
    public static String buffer;
    int f5a;
    int ancienBufferInt;
    int boucle;
    int bufferInt;
    Client communication;
    BufferedReader in;
    PrintWriter out;
    //TelecommandeView telecommandeView;

    private class Client extends AsyncTask<String, Void, String> {
        Socket socket;

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {
            try {
                this.socket = new Socket(InetAddress.getByName("172.20.10.4"), 2002);
                System.out.println("Demande de connexion");
                CommandeActivity.this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                CommandeActivity.this.out = new PrintWriter(this.socket.getOutputStream());
                CommandeActivity.this.out.print(CommandeActivity.buffer);
                CommandeActivity.this.out.flush();
                while (CommandeActivity.this.boucle == 1) {
                    CommandeActivity.this.bufferInt = Integer.parseInt(CommandeActivity.buffer);
                    if (CommandeActivity.this.bufferInt != CommandeActivity.this.ancienBufferInt || Commande.send) {
                        CommandeActivity.this.out.print(CommandeActivity.buffer);
                        CommandeActivity.this.out.flush();
                        System.out.println("buffer = " + CommandeActivity.buffer);
                        CommandeActivity.this.ancienBufferInt = CommandeActivity.this.bufferInt;
                    }
                    if (CommandeActivity.this.bufferInt == CommandeActivity.STOP) {
                        CommandeActivity.this.boucle = 0;
                    }
                }
                System.out.println("fermeture");
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return "ok";
        }

        public void closeSocket() {
            try {
                this.socket.close();
                CommandeActivity.this.boucle = 1;
                CommandeActivity.buffer = "1";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public CommandeActivity() {
        this.communication = null;
        this.in = null;
        this.out = null;
        this.boucle = 1;
        this.bufferInt = 0;
        this.ancienBufferInt = 0;
        this.f5a = 0;
    }

    static {
        buffer = "0";
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.telecommandeView = new TelecommandeView(this);
        setContentView(this.telecommandeView);
        this.telecommandeView.requestFocus();
    }

    protected void onStart() {
        super.onStart();
        this.communication = new Client();
        this.communication.execute(new String[0]);
    }

    protected void onDestroy() {
        super.onDestroy();
        buffer = "99";
        try {
            Thread.sleep(1000);
            this.communication.closeSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/