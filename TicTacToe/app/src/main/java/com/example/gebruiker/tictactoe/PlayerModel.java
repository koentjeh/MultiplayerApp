package com.example.gebruiker.tictactoe;

/**
 * Created by Gebruiker on 2017-01-17.
 */

public class PlayerModel {

    private final String name;
    private final String figure;

    public PlayerModel(String name, String figure) {
        this.name = name;
        this.figure = figure;
    }

    public String getName() {
        return name;
    }

    public String getFigure() {
        return figure;
    }
}
