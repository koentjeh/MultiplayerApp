package com.example.gebruiker.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Gebruiker on 2017-01-17.
 */

public class HighscoreActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ListView mHighscoreListView;
    private HighscoreAdapter mHighscoreAdapter;

    private ArrayList<PlayerModel> highscoreList = new ArrayList<>();

    private PlayerDBHandler pdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        pdb = new PlayerDBHandler(getApplicationContext());
        highscoreList = pdb.getHighscoreList();

        Log.i(TAG, "onCreate: " + highscoreList );

        PlayerModel p = new PlayerModel();
        p.name = "Koen";

        pdb.addPlayer(p);

//        Log.i(TAG, "onCreate: "+ pdb.addPlayer(p));


        mHighscoreListView = (ListView) findViewById(R.id.highscoreListItem);
        mHighscoreAdapter = new HighscoreAdapter(this, getLayoutInflater(), highscoreList);
        mHighscoreListView.setAdapter(mHighscoreAdapter);
        mHighscoreAdapter.notifyDataSetChanged();
    }
}
