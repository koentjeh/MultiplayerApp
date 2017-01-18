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

        final Button buttonSingleplayer = (Button) findViewById(R.id.buttonSinglePlayer);
        buttonSingleplayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                mode = "Singleplayer";
//                gameButtonClicked(mode, v);

                Intent intent = new Intent(v.getContext(), PregameActivity.class);
                startActivity(intent);

            }
        });

        final Button buttonMultiplayer = (Button) findViewById(R.id.buttonMultiplayer);
        buttonMultiplayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mode = "Multiplayer";
                gameButtonClicked(mode, v);

            }
        });

        final Button buttonHighscores = (Button) findViewById(R.id.buttonHighscores);
        buttonHighscores.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), HighscoreActivity.class);
                startActivity(intent);

            }
        });

        final Button buttonAboutUs = (Button) findViewById(R.id.buttonAboutUs);
        buttonAboutUs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                setContentView(R.layout.activity_aboutus);

            }
        });

        final Button buttonExitApplication = (Button) findViewById(R.id.closeApplication);
        buttonExitApplication.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Sluit de app af
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });

    }

    private void gameButtonClicked(String mode, View v) {

        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra("EXTRA_MESSAGE", mode);
        startActivity(intent);

    }

    @Override
    public void onClick(View view) {

    }
}
