package com.example.gebruiker.tictactoe.model;

import android.util.Log;
import android.widget.Button;

import com.example.gebruiker.tictactoe.R;

/**
 * Created by Gebruiker on 2017-01-19.
 */

public class Board {

    private String board[][];
    private static final int BOARD_SIZE = 3;

    public Board() {

        board = new String[BOARD_SIZE][BOARD_SIZE];

        // Voor elk rij
        for (int x = 0; x < BOARD_SIZE; x++) {

            // Voor elke kolom in de rij
            for (int y = 0; y < BOARD_SIZE; y++) {

                // Defineer een lege tegel
                board[x][y] = "";
            }
        }
    }

    public void setTile(String turn, int x, int y) {
        board[x][y] = turn;
    }

    public boolean checkForWinningMove(String turn, int x, int y) {

        boolean winner = false;
        int checkColumns = 0;
        int checkRows = 0;
        int checkDiagonalUpDown = 0;
        int checkDiagonalDownUp = 0;

        int e = BOARD_SIZE - 1;

        for (int i = 0; i < BOARD_SIZE; i++) {

//            System.out.println(i);

            System.out.println(board[y][i].equals(turn));

            // Controleer rij
            if (board[i][x].equals(turn)) {
                checkRows++;
            }

            // Controleer kolom
            if (board[y][i].equals(turn)) {
                checkColumns++;
            }

            // Controleer diagonaal van linksboven naar rechtsonder
            if (board[i][i].equals(turn)) {
                checkDiagonalUpDown++;
            }

            // Controleer diagonaal van linksonder naar rechtsboven
            if(board[e][i].equals(turn)) {
                checkDiagonalDownUp++;
            }
            e--;
        }

//        System.out.println(checkRows);

        // Als het aantal getelde kolommen, rijen of diagonale velden gelijk is aan de grootte van het speelveld, dan is er een winnaar
        if (checkColumns == BOARD_SIZE || checkRows == BOARD_SIZE || checkDiagonalUpDown == BOARD_SIZE || checkDiagonalDownUp == BOARD_SIZE) {
            winner = true;
        }

        return winner;
    }
}
