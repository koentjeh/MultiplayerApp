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

                mode = "Singleplayer";
                gameButtonClicked(mode, v);

            }
        });

        final Button buttonMultiplayer = (Button) findViewById(R.id.buttonMultiplayer);
        buttonMultiplayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mode = "Multiplayer";
                gameButtonClicked(mode, v);

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
