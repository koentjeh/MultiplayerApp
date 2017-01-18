package com.example.gebruiker.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by wbjar on 16-1-2017.
 */

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private Button a1, a2, a3, b1, b2, b3, c1, c2, c3;
    private Button[] buttonArray;
    private Boolean turn = true; // If true, turn = X. If false, turn = O.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Get Intent.
        Intent intent = getIntent();
        String playerName = intent.getStringExtra("playerName");
        TextView textViewPlayerName = (TextView) findViewById(R.id.textViewPlayerName);
        textViewPlayerName.setText(playerName);

        // Link buttons to buttons in layout.
        a1 = (Button) findViewById(R.id.buttonA1);
        a2 = (Button) findViewById(R.id.buttonA2);
        a3 = (Button) findViewById(R.id.buttonA3);
        b1 = (Button) findViewById(R.id.buttonB1);
        b2 = (Button) findViewById(R.id.buttonB2);
        b3 = (Button) findViewById(R.id.buttonB3);
        c1 = (Button) findViewById(R.id.buttonC1);
        c2 = (Button) findViewById(R.id.buttonC2);
        c3 = (Button) findViewById(R.id.buttonC3);

        // Insert all buttons into the array.
        buttonArray = new Button[]{a1, a2, a3, b1, b2, b3, c1, c2, c3};

        // Set the click listener for each button.
        for(Button button : buttonArray) {
            button.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        ButtonClicked(button);
    }

    public void ButtonClicked(Button b) {
        if(turn) { // X's turn.
            b.setText("X");
        } else { // O's turn.
            b.setText("O");
        }

        turn = !turn;
    }
}
