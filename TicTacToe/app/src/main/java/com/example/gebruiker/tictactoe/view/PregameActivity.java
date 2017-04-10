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

public class PregameActivity extends AppCompatActivity {

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
        figure = "O";

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
}