package com.example.gebruiker.tictactoe.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gebruiker.tictactoe.R;
import com.example.gebruiker.tictactoe.model.PlayerDBHandler;

/**
 * Created by Gebruiker on 2017-01-17.
 */

public class PregameActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PregameActivity";

    private String name;
    private String figure;

    private Button buttonXPregame;
    private Button buttonOPregame;
    private Button buttonRandomPregame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregame);

        // Input voor spelernaam
        final EditText inputPlayerName = (EditText) findViewById(R.id.playerName);
        // Texveld voor error meldingen
        final TextView errorMsg = (TextView) findViewById(R.id.errorMsg);

        // Standaard beginspeler
        figure = "R";

        // Knoppen om beginspeler te kiezen
        buttonXPregame = (Button) findViewById(R.id.xPregame);
        buttonOPregame = (Button) findViewById(R.id.oPregame);
        buttonRandomPregame = (Button) findViewById(R.id.randomPregame);

        buttonXPregame.setOnClickListener(this);
        buttonOPregame.setOnClickListener(this);
        buttonRandomPregame.setOnClickListener(this);

        Button buttonStartGame = (Button) findViewById(R.id.startGame);
        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            // Naam van speler ophalen uit input
            name = inputPlayerName.getText().toString();

            // Speler database
            PlayerDBHandler pdb = new PlayerDBHandler(getApplicationContext());

            // Minimale lengte van naam is 4
            if ( name.length() < 4 ) {

                errorMsg.setText("Naam moet uit minimaal 4 karakters bestaan.");
                Log.i(TAG, "name length: min 4");

            // Controleert op dubbele naam
            } else if ( pdb.checkDuplicatePlayerName(name) ) {

                errorMsg.setText("Deze naam bestaat al.");
                Log.i(TAG, "duplicate name: true");

            // Als er geen errors zijn gevonden start het spel
            } else {

                // Nieuw scherm openen en data meegeven
                Intent intent = new Intent(getBaseContext(), GameActivity.class);
                intent.putExtra("playername", name);
                intent.putExtra("startFigure", figure);
                startActivity(intent);
            }
            }
        });
    }

    @Override
    public void onClick(View view) {
        
        // Als knop is ingedrukt zet actief anders inactief
        if (view.getId() == R.id.xPregame) {
            Log.i(TAG, "onClick: set starting player: X");
            // Kruisje begint
            figure = "X";
            // knop actief
            buttonXPregame.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.accent));
        } else {
            // knop inactief
            buttonXPregame.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.primary_light));
        }

        // Als knop is ingedrukt zet actief anders inactief
        if (view.getId() == R.id.oPregame) {
            Log.i(TAG, "onClick: set starting player: O");
            // Rondje begint
            figure = "O";
            // knop actief
            buttonOPregame.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.accent));
        } else {
            // knop inactief
            buttonOPregame.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.primary_light));
        }

        // Als knop is ingedrukt zet actief anders inactief
        if (view.getId() == R.id.randomPregame) {
            Log.i(TAG, "onClick: set starting player: R");
            // Kruisje of rondje begint (Willekeurig)
            figure = "R";
            // knop actief
            buttonRandomPregame.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.accent));
        } else {
            // knop inactief
            buttonRandomPregame.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.primary_light));
        }
    }
}