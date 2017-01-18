package com.example.gebruiker.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.color.holo_blue_light;

/**
 * Created by wbjar on 16-1-2017.
 */

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private Button a1, a2, a3, b1, b2, b3, c1, c2, c3;
    private Button buttonNextGame;
    private Button[] buttonArray;
    private Boolean turn = true; // If true, turn = X. If false, turn = O.
    private int turnCount = 0;
    private TextView textViewScore;
//    private int score = 0;


    PlayerModel player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Get Intent.
        Intent intent = getIntent();

        // Maak nieuwe speler aan
        player = new PlayerModel();
        player.name = intent.getStringExtra("playerName");
        player.score = 0;

        TextView textViewPlayerName = (TextView) findViewById(R.id.textViewPlayerName);
        textViewPlayerName.setText(player.name);

        buttonNextGame = (Button) findViewById(R.id.buttonNextGame);
        textViewScore = (TextView) findViewById(R.id.textViewScore);

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
        if(view.getId() == R.id.buttonNextGame) {
            clearPlayField();
        } else {
            Button button = (Button) view;
            buttonClicked(button);
//            Log.d("Score:", player.score);
        }
    }

    private void clearPlayField() {
        for(Button button : buttonArray) {
            button.setText("");
//            Log.d("color", ""+defaultButtonColor);
            button.setBackgroundColor(getResources().getColor(holo_blue_light));
            button.setClickable(true);
            turnCount = 0;
        }
    }

    public void buttonClicked(Button b) {
        if(turn) { // X's turn.
            b.setText("X");
        } else { // O's turn.
            b.setText("O");
        }

        b.setClickable(false);
        turn = !turn;
        turnCount++;
        checkWinner();

    }

    private void checkWinner() {
        boolean winner = false;
        String color = "#000000";

        // Check horizontal.
        if (a1.getText() == a2.getText() && a2.getText() == a3.getText() && !a1.isClickable()) {
            a1.setBackgroundColor(Color.parseColor(color));
            a2.setBackgroundColor(Color.parseColor(color));
            a3.setBackgroundColor(Color.parseColor(color));
            winner = true;
        } else if (b1.getText() == b2.getText() && b2.getText() == b3.getText() && !b1.isClickable()) {
            b1.setBackgroundColor(Color.parseColor(color));
            b2.setBackgroundColor(Color.parseColor(color));
            b3.setBackgroundColor(Color.parseColor(color));
            winner = true;
        } else if (c1.getText() == c2.getText() && c2.getText() == c3.getText() && !c1.isClickable()) {
            c1.setBackgroundColor(Color.parseColor(color));
            c2.setBackgroundColor(Color.parseColor(color));
            c3.setBackgroundColor(Color.parseColor(color));
            winner = true;
        }

        // Check vertical
        else if (a1.getText() == b1.getText() && b1.getText() == c1.getText() && !a1.isClickable()) {
            a1.setBackgroundColor(Color.parseColor(color));
            b1.setBackgroundColor(Color.parseColor(color));
            c1.setBackgroundColor(Color.parseColor(color));
            winner = true;
        } else if (a2.getText() == b2.getText() && b2.getText() == c2.getText() && !b2.isClickable()) {
            a2.setBackgroundColor(Color.parseColor(color));
            b2.setBackgroundColor(Color.parseColor(color));
            c2.setBackgroundColor(Color.parseColor(color));
            winner = true;
        } else if (a3.getText() == b3.getText() && b3.getText() == c3.getText() && !c3.isClickable()) {
            a3.setBackgroundColor(Color.parseColor(color));
            b3.setBackgroundColor(Color.parseColor(color));
            c3.setBackgroundColor(Color.parseColor(color));
            winner = true;
        }

        // Check diagonal.
        else if (a1.getText() == b2.getText() && b2.getText() == c3.getText() && !a1.isClickable()) {
            a1.setBackgroundColor(Color.parseColor(color));
            b2.setBackgroundColor(Color.parseColor(color));
            c3.setBackgroundColor(Color.parseColor(color));
            winner = true;
        } else if (a3.getText() == b2.getText() && b2.getText() == c1.getText() && !b2.isClickable()) {
            a3.setBackgroundColor(Color.parseColor(color));
            b2.setBackgroundColor(Color.parseColor(color));
            c1.setBackgroundColor(Color.parseColor(color));
            winner = true;
        }

        if (winner) {
            if (!turn) {
                player.score++;
                setScore(player.score);
                notification("X heeft gewonnnen!");
            } else {
                notification("O heeft gewonnnen!");
            }
            afterGameEnd();

        } else if (turnCount == 9) {
            notification("Het is gelijkspel!");
            afterGameEnd();
        }

    }

    private void notification(String s) {
        Context context = getApplicationContext();
        CharSequence text = s;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void afterGameEnd() {
        buttonNextGame.setVisibility(View.VISIBLE);
        buttonNextGame.setOnClickListener(this);

        for (Button button : buttonArray) {
            button.setClickable(false);
        }
    }

    public void setScore(int score) {
        player.score = score;
        textViewScore.setText("Score: " + player.score);
    }
}
