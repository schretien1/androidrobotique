package com.example.kabtel.myapplicationcommande;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.MyButton);
    }

    public void invokeFunction(View view) {
        Intent intent1 = new Intent(MainActivity.this, Commande.class);
        startActivity(intent1);
    }

}
