package com.example.gebruiker.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonStartGame = (Button) findViewById(R.id.buttonStartGame);
        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { startButtonClicked(mode, v); }
        });

        final Button aboutUs = (Button) findViewById(R.id.buttonAboutUs);
        aboutUs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                setContentView(R.layout.activity_aboutus);

            }
        });

        final Button exitApplication = (Button) findViewById(R.id.closeApplication);
        exitApplication.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Sluit de app af
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });

    }

    private void startButtonClicked(String mode, View v) {

        Intent intent = new Intent(MainActivity.this, PregameActivity.class);
        startActivity(intent);

    }

    @Override
    public void onClick(View view) {

    }
}
