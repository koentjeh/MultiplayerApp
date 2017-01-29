package com.example.gebruiker.tictactoe.model;

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

        Log.i(TAG, "onCreate: Tabel aangemaakt.");
    }

    // Bij verandering van de db wordt onUpgrade aangeroepen.
    // Wat zou je hier kunnen doen? Weggooien en opnieuw aanmaken?
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_NAME);
        onCreate(db);

        Log.i(TAG, "onCreate: Tabel upgrade uitgevoerd.");
    }

    // Check op dubbele namen
    // return true = naam gevonden
    // return false = naam beschikbaar
    public Boolean checkDuplicatePlayerName(String name) {

        Boolean playerExists = false;
        Cursor cursor = getReadableDatabase().rawQuery("SELECT name FROM " + DB_TABLE_NAME + " WHERE lower(" + COLOMN_NAME + ")=" + "\"lower(" + name + ")\"", null);

        if (cursor.getCount() == 0) {
            Log.i(TAG, "checkDuplicatePlayerName: naam bestaat nog niet");
        } else {
            playerExists = true;
            Log.i(TAG, "checkDuplicatePlayerName: naam bestaat al");
        }

        cursor.close();

        return playerExists;
    }

    // Speler toevoegen aan highscores
    public void addPlayer(Player player) {

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + DB_TABLE_NAME + " WHERE " + COLOMN_NAME + "=" + "\"" + player.getName() + "\"", null);

        // Check of spelernaam is gevonden
        if (cursor.moveToFirst()) {
            Log.i(TAG, "addPlayer: naam bestaat al");
        } else {
            Log.i(TAG, "addPlayer: naam bestaat nog niet");

            // Voeg de speler toe
            ContentValues values = new ContentValues();
            values.put(COLOMN_NAME, player.getName());
//            values.put(COLOMN_SCORE, player.getScore());

            // Voer query uit
            SQLiteDatabase db = this.getWritableDatabase();
            db.insert(DB_TABLE_NAME, null, values);
            db.close();
        }
        cursor.close();
    }

    // Players.db database leegmaken
    public String resetHighscores() {
        Log.i(TAG, "resetHighscores: deleting table (" + DB_TABLE_NAME + ")");

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + DB_TABLE_NAME);
        
        String result = "Alle data is verwijderd.";

        return result;
    }

    // Lijst met 10 spelers met beste score ophalen
    public ArrayList<Player> getHighscoreList() {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + DB_TABLE_NAME + " ORDER BY " + COLOMN_SCORE + " DESC LIMIT 10", null);

        ArrayList<Player> playerList = new ArrayList<>();
        Player p;
        // Begin bij positie 1
        int i = 1;

        cursor.moveToFirst();

        Log.i(TAG, "getHighscoreList: ");

        // Als er een of meerdere spelers zijn gevonden.
        if (playerList.size() > 0) {

            while (!cursor.isAfterLast()) {

                // Speler object aan lijst toevoegen
                p = new Player();
//                p.highscorePos = i;
//                p.setId(cursor.getInt(cursor.getColumnIndex(COLOMN_ID)));
                p.setName(cursor.getString(cursor.getColumnIndex(COLOMN_NAME)));
//                p.setScore(cursor.getInt(cursor.getColumnIndex(COLOMN_SCORE)));
                playerList.add(p);

                Log.i(TAG, "getHighscorelist: Player at position " + i + "added to list.");

                i++;
                cursor.moveToNext();
            }

        } else {
            p = new Player();
//            p.highscorePos = 0;
            p.setName("Nog geen data om te weergeven.");
//            p.setScore(0);
            playerList.add(p);

            Log.i(TAG, "getHighscorelist: Dummydata added");
        }
        cursor.close();

        // Return de lijst met 10 beste spelers
        return playerList;
    }

    // Speler zoeken met naam
    // true = staat al in db
    // false = staat nog niet in db
    public Boolean checkIfDuplicateName(String name) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DB_TABLE_NAME + " WHERE " + COLOMN_NAME + "=" + "\"" + name + "\"", null);
        boolean result = false;

        // Als spelernaam is gevonden
        if (cursor.getCount() > 0) {
            result = true;
        }

        cursor.close();

        Log.i(TAG, "getPlayerByName(): Naam gevonden = " + result);

        return result;
    }
}