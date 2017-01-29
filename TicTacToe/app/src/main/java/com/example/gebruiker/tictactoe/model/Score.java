package com.example.gebruiker.tictactoe.model;

/**
 * Created by Gebruiker on 2017-01-27.
 */

public class Score {
    private int score = 0;

    public void updateScore(int value) { score += value; }
    public int getScore() { return score; }
}
