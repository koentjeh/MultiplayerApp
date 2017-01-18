package com.example.gebruiker.tictactoe;

/**
 * Created by Gebruiker on 2017-01-17.
 */

public class PlayerModel {

    public String id;
    public int highscorePos;
    public String name;
    public String score;

    public PlayerModel() {

    }

    public String getId() { return  id; }

    public String getName() {
        return name;
    }

    public String getScore() { return score; }
}
