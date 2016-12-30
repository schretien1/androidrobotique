package com.example.kabtel.myapplicationcommande;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Commande extends AppCompatActivity {

    Button button;
    static boolean send = false;

    public static final int STOP = 99;
    public static String buffer = "0";
    int ancienBufferInt = 0;
    int boucle = 1;
    int bufferInt = 0;
    Client communication;
    BufferedReader in;
    PrintWriter out;

    int arriere;
    int avant;
    int control;
    int droite;
    int gauche;

    int manuel;
    int lumiere;
    int ligne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commande);


        button = (Button) findViewById(R.id.quitter);


        final ImageButton up = (ImageButton) findViewById(R.id.haut);
        up.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.i("Appui","BOUTTON APPUYER up");
                        avant = 1;
                        arriere = 0;
                        break;
                    case MotionEvent.ACTION_UP:
                        avant = 0;
                        Log.i("Appui","BOUTTON Relacher up");
                        break;
                }
                ChoixControl();
                Log.i("Appui","oiuytrertuWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
                return false;
            }
        });

        final ImageButton right = (ImageButton) findViewById(R.id.droite);
        right.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.i("Appui","BOUTTON APPUYER right");
                        droite = 1;
                        gauche = 0;
                        break;
                    case MotionEvent.ACTION_UP:
                        droite = 0;
                        Log.i("Appui","BOUTTON Relacher right");
                        break;
                }
                ChoixControl();
                return false;
            }
        });

        final ImageButton down = (ImageButton) findViewById(R.id.bas);
        down.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.i("Appui","BOUTTON APPUYER down");
                        arriere = 1;
                        avant = 0;
                        break;
                    case MotionEvent.ACTION_UP:
                        arriere =0;
                        Log.i("Appui","BOUTTON Relacher down");
                        break;
                }
                ChoixControl();
                return false;
            }
        });

        final ImageButton left = (ImageButton) findViewById(R.id.gauche);
        left.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        droite = 0;
                        gauche = 1;
                        Log.i("Appui","BOUTTON APPUYER left");
                        break;
                    case MotionEvent.ACTION_UP:
                        gauche =0;
                        Log.i("Appui","BOUTTON Relacher left");
                        break;
                }
                ChoixControl();
                return false;
            }
        });

        Button bmanuel = (Button) findViewById(R.id.manuel);
        bmanuel.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        manuel = 1;
                        Log.i("Appui","BOUTTON APPUYER Manuel");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.i("Appui","BOUTTON Relacher Manuel");
                        break;
                }
                ChoixControl();
                return false;
            }
        });

        bmanuel = (Button) findViewById(R.id.manuel);
        bmanuel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                manuel = 1;
                lumiere = 0;
                ligne = 0;
                ChoixControl();
            }
        });

/*
        final Button bligne = (Button) findViewById(R.id.ligne);
        bligne.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.i("Appui","BOUTTON APPUYER HAUT");
                avant = 1;
                arriere = 0;
                buffer = "4";
            }
        });
        */

        Button bligne = (Button) findViewById(R.id.ligne);
        bligne.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        manuel = 0;
                        lumiere = 0;
                        ligne = 1;
                        avant=0;
                        arriere=0;
                        gauche=0;
                        droite=0;
                        Log.i("Appui","BOUTTON APPUYER Ligne");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.i("Appui","BOUTTON Relacher Ligne");
                        break;
                }
                ChoixControl();
                return false;
            }
        });

        Button blumiere = (Button) findViewById(R.id.lumiere);
        blumiere.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        manuel = 0;
                        lumiere = 1;
                        ligne = 0;
                        avant=0;
                        arriere=0;
                        gauche=0;
                        droite=0;
                        Log.i("Appui","BOUTTON APPUYER Lumiere");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.i("Appui","BOUTTON Relacher Lumiere");
                        break;
                }
                ChoixControl();
                return false;
            }
        });

    }

    protected void onStart() {
        super.onStart();
        communication = new Client();
        communication.execute(new String[0]);
    }

    protected void onDestroy() {
        super.onDestroy();
        buffer = "99";
        try {
            Thread.sleep(1000);
            communication.closeSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void Quitter(View view) {
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public int ChoixControl() {
        if (this.manuel == 0 && this.ligne == 0 && this.lumiere == 0 && this.droite == 0 && this.gauche == 0 && this.avant == 0 && this.arriere == 0) {
            this.control = 0;
        } else if (this.manuel == 1 && this.ligne == 0 && this.lumiere == 0 && this.droite == 0 && this.gauche == 0 && this.avant == 0 && this.arriere == 0) {
            this.control = 1;
        } else if (this.manuel == 0 && this.ligne == 1 && this.lumiere == 0 && this.droite == 0 && this.gauche == 0 && this.avant == 0 && this.arriere == 0) {
            this.control = 2;
        } else if (this.manuel == 0 && this.ligne == 0 && this.lumiere == 1 && this.droite == 0 && this.gauche == 0 && this.avant == 0 && this.arriere == 0) {
            this.control = 3;
        } else if (this.manuel == 1 && this.ligne == 0 && this.lumiere == 0 && this.droite == 0 && this.gauche == 0 && this.avant == 1 && this.arriere == 0) {
            this.control = 4;
        } else if (this.manuel == 1 && this.ligne == 0 && this.lumiere == 0 && this.droite == 0 && this.gauche == 0 && this.avant == 0 && this.arriere == 1) {
            this.control = 5;
        } else if (this.manuel == 1 && this.ligne == 0 && this.lumiere == 0 && this.droite == 0 && this.gauche == 1 && this.avant == 0 && this.arriere == 0) {
            this.control = 6;
        } else if (this.manuel == 1 && this.ligne == 0 && this.lumiere == 0 && this.droite == 1 && this.gauche == 0 && this.avant == 0 && this.arriere == 0) {
            this.control = 7;
        } else if (this.manuel == 1 && this.ligne == 0 && this.lumiere == 0 && this.droite == 1 && this.gauche == 0 && this.avant == 1 && this.arriere == 0) {
            this.control = 8;
        } else if (this.manuel == 1 && this.ligne == 0 && this.lumiere == 0 && this.droite == 0 && this.gauche == 1 && this.avant == 1 && this.arriere == 0) {
            this.control = 9;
        } else if (this.manuel == 1 && this.ligne == 0 && this.lumiere == 0 && this.droite == 1 && this.gauche == 0 && this.avant == 0 && this.arriere == 1) {
            this.control = 12;
        } else if (this.manuel == 1 && this.ligne == 0 && this.lumiere == 0 && this.droite == 0 && this.gauche == 1 && this.avant == 0 && this.arriere == 1) {
            this.control = 13;
        }
        buffer = String.valueOf(control);
        return this.control;
    }



    private class Client extends AsyncTask<String, Void, String> {
        Socket socket;

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {

            try {
                Log.i("CONNEXION : ","Avant la tentative de connexion");
                socket = new Socket(InetAddress.getByName("172.20.10.4"), 2002);
                //socket = new Socket(InetAddress.getByName("80.215.92.197"), 2002);
                System.out.println("Demande de connexion");
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream());
                out.print(buffer);
                out.flush();
                System.out.println("buffer = " + buffer);
                while (boucle == 1) {
                    bufferInt = Integer.parseInt(buffer);
                    if (bufferInt != ancienBufferInt || send) {
                        out.print(buffer);
                        out.flush();
                        System.out.println("buffer bouvle = " + buffer);
                        ancienBufferInt = bufferInt;
                    }
                    if (bufferInt == STOP) {
                        boucle = 0;
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
                socket.close();
                boucle = 1;
                buffer = "1";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
