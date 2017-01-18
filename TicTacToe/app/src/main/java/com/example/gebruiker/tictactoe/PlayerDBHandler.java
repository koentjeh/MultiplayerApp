package com.example.gebruiker.tictactoe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Gebruiker on 2017-01-17.
 */

public class PlayerDBHandler extends SQLiteOpenHelper {

    private static final String TAG = "PersonDBHandler";

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "players.db";
    private static final String DB_TABLE_NAME = "players";

    // Tabel en kolom namen ...
    private static final String COLOMN_ID = "_id";  // primary key, auto increment
    private static final String COLOMN_NAME = "name";
    private static final String COLOMN_SCORE = "score";

    // Default constructor
    public PlayerDBHandler(Context context, String name,
                           SQLiteDatabase.CursorFactory factory,
                           int version) {
        super(context, DB_NAME, factory, DB_VERSION);
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

    // Speler toevoegen
    public void addPlayer(PlayerModel player)
    {
        ContentValues values = new ContentValues();
        values.put(COLOMN_NAME, player.getName());
        values.put(COLOMN_SCORE, player.getScore());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DB_TABLE_NAME, null, values);
        db.close();
    }

    public void getPersonByName(String name) {

        String query_a = "SELECT * FROM " + DB_TABLE_NAME + " WHERE " +
                COLOMN_NAME + "=" + "\"" + name + "\"";

        String query_b = "SELECT * FROM " + DB_TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query_b, null);

        cursor.moveToFirst();
        while(cursor.moveToNext()) {
            Log.i(TAG, "--------------------------------------------");
            Log.i(TAG, cursor.getString(cursor.getColumnIndex(COLOMN_ID)));
            Log.i(TAG, cursor.getString(cursor.getColumnIndex(COLOMN_NAME)));
            Log.i(TAG, cursor.getString(cursor.getColumnIndex(COLOMN_SCORE)));
            Log.i(TAG, "--------------------------------------------");
        }

        db.close();
    }
}