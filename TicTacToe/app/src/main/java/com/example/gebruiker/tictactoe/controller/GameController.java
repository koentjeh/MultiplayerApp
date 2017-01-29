package com.example.gebruiker.tictactoe.controller;

import android.util.Log;
import android.widget.Button;

import com.example.gebruiker.tictactoe.R;
import com.example.gebruiker.tictactoe.model.Board;
import com.example.gebruiker.tictactoe.model.Computer;
import com.example.gebruiker.tictactoe.model.Player;
import com.example.gebruiker.tictactoe.model.PlayerDBHandler;
import com.example.gebruiker.tictactoe.model.Score;

import java.util.Random;

/**
 * Created by Gebruiker on 2017-01-19.
 */

public class GameController {

    private static final String TAG = "GameController";

    private Player player;
    private Computer computer;

    private String turn;
    private int turnCount;

    private Score score;
    private Board board;

    public GameController() {
        board = new Board();
        computer = new Computer();
    }

    public void createPlayer(String name) {
        player = new Player();
        player.setName(name);

        score = new Score();
    }

    public String getPlayerName() { return player.getName(); }


    /**
     * Sets off a startplayer
     * Makes a move if startplayer is O (Computer)
     *
     * R = random
     */
    public void startTurn(String turn) {

        if (turn.equals("R")) {
            String rp = "XO";
            turn = String.valueOf(rp.charAt(new Random().nextInt(rp.length())));
        }

        Log.i(TAG, "Beginspeler: " + turn);

        if (turn.equals("O")) {
            computerTurn();
        }

        this.turn = turn;
    }

    /**
     * Gives turn to other player
     */
    public void changeTurn() {

        if (turn.equals("X")) {
            turn = "O";
        } else {
            turn = "X";
        }
    }


    /**
     * ??
     */
    public void setTile(Button clickedButton) {
        // Fill selected tile with player (X/O) and disable button
        clickedButton.setText(turn);
        clickedButton.setClickable(false);

        // Increase turns
        turnCount++;

        // Get position of clicked button in Board
        int[] pos = getPos(clickedButton);

        // Fill tile in Board
        board.setTile(turn, pos[0], pos[1]);
    }

    /**
     * Get the position on Board of clicked button
     */
    public int[] getPos(Button clickedButton) {

        // Return value
        int[] pos = new int[2];
        // Clicked row
        int x = 0;
        // Clicked column
        int y = 0;

        switch (clickedButton.getId()) {
            case R.id.buttonA1:
                x = 0;
                y = 0;
                break;
            case R.id.buttonA2:
                x = 1;
                y = 0;
                break;
            case R.id.buttonA3:
                x = 2;
                y = 0;
                break;
            case R.id.buttonB1:
                x = 0;
                y = 1;
                break;
            case R.id.buttonB2:
                x = 1;
                y = 1;
                break;
            case R.id.buttonB3:
                x = 2;
                y = 1;
                break;
            case R.id.buttonC1:
                x = 0;
                y = 2;
                break;
            case R.id.buttonC2:
                x = 1;
                y = 2;
                break;
            case R.id.buttonC3:
                x = 2;
                y = 2;
                break;
            default:
                Log.i("BOARD:", "Tile ID not found");
                break;
        }

        pos[0] = x;
        pos[1] = y;

        return pos;
    }

    /**
     * Check if last move is a winning move
     *
     * if Won:
     */
    public void checkWinner(int[] pos) {

        // Check for winner
        Boolean isWinner = board.checkForWinningMove(turn, pos[0], pos[1]);

        // Player won
        if (isWinner && turn.equals("X")) {

            endGame("won");

        // Computer won
        } else if (isWinner && turn.equals("O")) {

            endGame("lost");

        // It's a tie
        } else if (turnCount > = 9) {

            endGame("tie");

        } else {
            changeTurn();
        }
    }

    private void endGame(String result) {

        if (result.equals("won")) {

            score.updateScore(1);

        } else if (result.equals("tie")) {

            score.updateScore(1);

        } else if (result.equals("lost")) {

            // Voeg speler toe aan highscores
            PlayerDBHandler pdb = new PlayerDBHandler(getApplicationContext());
            pdb.addPlayer(player);
        }
    }
}
