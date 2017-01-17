package com.example.gebruiker.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Gebruiker on 2017-01-17.
 */

public class PregameActivity extends AppCompatActivity implements View.OnClickListener {

    private String playerName;
    private String startFigure;

    private Button buttonXPregame;
    private Button buttonOPregame;
    private Button buttonRandomPregame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregame);

        // Input voor spelernaam
        final EditText inputPlayerName = (EditText) findViewById(R.id.playerName);

        // Standaard beginspeler
        startFigure = "R";

        // Knoppen om beginspeler te kiezen
        buttonXPregame = (Button) findViewById(R.id.xPregame);
        buttonOPregame = (Button) findViewById(R.id.oPregame);
        buttonRandomPregame = (Button) findViewById(R.id.randomPregame);

        buttonXPregame.setOnClickListener(this);
        buttonOPregame.setOnClickListener(this);
        buttonRandomPregame.setOnClickListener(this);

        final Button buttonStartGame = (Button) findViewById(R.id.startGame);

        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Controle op errors
                boolean errors = false;

                // Naam van speler ophalen uit input
                playerName = inputPlayerName.getText().toString();

                // Minimaal lengte naam = 4 anders error melding
                if (playerName.length() < 4) {
                    TextView errorMsg = (TextView) findViewById(R.id.errorMsg);
                    errorMsg.setText("Naam moet uit minimaal 4 karakters bestaan.");
                    errors = true;
                }

                // Als er geen errors zijn gevonden start het spel
                if (errors == false) {
                    Intent intent = new Intent(v.getContext(), GameActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {

        // Als knop is ingedrukt zet actief anders inactief
        if (view.getId() == R.id.xPregame) {

            // Kruisje begint
            startFigure = "X";
            // knop actief
            buttonXPregame.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.accent));

        } else {
            // knop inactief
            buttonXPregame.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.primary_light));
        }

        // Als knop is ingedrukt zet actief anders inactief
        if (view.getId() == R.id.oPregame) {

            // Rondje begint
            startFigure = "O";
            // knop actief
            buttonOPregame.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.accent));

        } else {
            // knop inactief
            buttonOPregame.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.primary_light));
        }

        // Als knop is ingedrukt zet actief anders inactief
        if (view.getId() == R.id.randomPregame) {

            // Kruisje of rondje begint (Willekeurig)
            startFigure = "R";
            // knop actief
            buttonRandomPregame.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.accent));

        } else {
            // knop inactief
            buttonRandomPregame.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.primary_light));
        }
    }
}