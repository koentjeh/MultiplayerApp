package com.example.gebruiker.tictactoe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Gebruiker on 2017-01-17.
 */

public class PlayerDBHandler extends SQLiteOpenHelper {

    private static final String TAG = "PersonDBHandler";

    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "players.db";
    private static final String DB_TABLE_NAME = "players";

    // Tabel en kolom namen ...
    private static final String COLOMN_ID = "_id";  // primary key, auto increment
    private static final String COLOMN_NAME = "name";
    private static final String COLOMN_SCORE = "score";

    // Default constructor
    public PlayerDBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Als de db niet bestaat wordt de db gemaakt. In de onCreate() de query
    // voor de aanmaak van de database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PLAYER_TABLE = "CREATE TABLE " + DB_TABLE_NAME +
                "(" +
                COLOMN_ID + " INTEGER PRIMARY KEY," +
                COLOMN_NAME + " TEXT," +
                COLOMN_SCORE + " INTEGER" +
                ")";
        db.execSQL(CREATE_PLAYER_TABLE);
    }

    // Bij verandering van de db wordt onUpgrade aangeroepen.
    // Wat zou je hier kunnen doen? Weggooien en opnieuw aanmaken?
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_NAME);
        onCreate(db);
    }

    // Check op dubbele namen
    public Boolean checkDuplicatePlayerName(String name) {

        Boolean error = false;
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + DB_TABLE_NAME + " WHERE " + COLOMN_NAME + "=" + "\"" + name + "\"", null);

        // Als spelernaam is gevonden
        if (cursor.moveToFirst()) {
            // Geef error melding (result)
            Log.i(TAG, "addPlayer: naam bestaat al");
            error = true;
        } else {
            // error blijft false
            Log.i(TAG, "addPlayer: naam bestaat nog niet");
        }
        cursor.close();

        return error;
    }

    // Speler toevoegen aan highscores
    public void addPlayer(PlayerModel player) {

        String result = "";
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + DB_TABLE_NAME + " WHERE " + COLOMN_NAME + "=" + "\"" + player.name + "\"", null);

        // Als spelernaam is gevonden
        if (cursor.moveToFirst()) {

            // Geef error melding (result)
            Log.i(TAG, "addPlayer: naam bestaat al");
            result = "De gekozen naam bestaat al.";

        } else {
            Log.i(TAG, "addPlayer: naam bestaat nog niet");

            // Voeg de speler toe
            ContentValues values = new ContentValues();
            values.put(COLOMN_NAME, player.name);
            values.put(COLOMN_SCORE, player.score);

            // Voer query uit
            SQLiteDatabase db = this.getWritableDatabase();
            db.insert(DB_TABLE_NAME, null, values);
            db.close();

            result = "";
        }
        cursor.close();

//        return result;
    }

    // Players.db database leegmaken
    public String resetHighscores() {
        Log.i(TAG, "resetHighscores: deleting table (" + DB_TABLE_NAME + ")");

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("TRUNCATE TABLE " + DB_TABLE_NAME);
        
        String result = "Alle data is verwijderd.";

        return result;
    }

    public ArrayList<PlayerModel> getHighscoreList() {
        Log.i(TAG, "getHighscorelist()");

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + DB_TABLE_NAME + " ORDER BY " + COLOMN_SCORE + " DESC LIMIT 10", null);

        ArrayList<PlayerModel> playerList = new ArrayList<>();
        PlayerModel p;
        int i = 1;

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {

            p = new PlayerModel();
            p.highscorePos = i;
            p.id = cursor.getString(cursor.getColumnIndex(COLOMN_ID));
            p.name = cursor.getString(cursor.getColumnIndex(COLOMN_NAME));
            p.score = cursor.getString(cursor.getColumnIndex(COLOMN_SCORE));
            playerList.add(p);

            i++;
            cursor.moveToNext();
        }
        cursor.close();

        // Als er geen speler in de database staat geef dummy data mee.
        if (playerList.size() == 0) {
            p = new PlayerModel();
            p.highscorePos = 0;
            p.name = "Nog geen data om te weergeven.";
            p.score = "";
            playerList.add(p);
        }

        return playerList;
    }
}