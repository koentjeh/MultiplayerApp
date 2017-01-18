package com.example.gebruiker.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Gebruiker on 2017-01-17.
 */

public class HighscoreActivity extends AppCompatActivity {

    private static final String TAG = "HighscoreActivity";

    private ListView mHighscoreListView;
    private HighscoreAdapter mHighscoreAdapter;

    private ArrayList<PlayerModel> highscoreList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        PlayerDBHandler pdb = new PlayerDBHandler(getApplicationContext());
        highscoreList = pdb.getHighscoreList();

        Log.i(TAG, "highscoreList: " + highscoreList );

        mHighscoreListView = (ListView) findViewById(R.id.highscoreListItem);
        mHighscoreAdapter = new HighscoreAdapter(this, getLayoutInflater(), highscoreList);
        mHighscoreListView.setAdapter(mHighscoreAdapter);
        mHighscoreAdapter.notifyDataSetChanged();

        Button buttonResetHighscores = (Button) findViewById(R.id.resetHighscores);
        buttonResetHighscores.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                PlayerDBHandler pdb = new PlayerDBHandler(getApplicationContext());
                pdb.resetHighscores();

            }
        });
    }
}
