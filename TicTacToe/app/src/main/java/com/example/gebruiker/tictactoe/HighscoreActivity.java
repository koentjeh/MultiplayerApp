package com.example.gebruiker.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Gebruiker on 2017-01-17.
 */

public class HighscoreActivity extends AppCompatActivity {

    private ListView mHighscoreListView;
    private HighscoreAdapter mHighscoreAdapter;

    private ArrayList<PlayerModel> highscoreList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        // Dummy data
        PlayerModel item = new PlayerModel();
        item.name = "Koen";
        item.score = "10";
        highscoreList.add(item);

        item = new PlayerModel();
        item.name = "Jari";
        item.score = "20";
        highscoreList.add(item);

        item = new PlayerModel();
        item.name = "Cliff";
        item.score = "3";
        highscoreList.add(item);

        item = new PlayerModel();
        item.name = "Daniel";
        item.score = "99";
        highscoreList.add(item);

        PlayerDBHandler pdb = new PlayerDBHandler(getApplicationContext(), null, null, 1);
        pdb.getPersonByName("Koen");



        mHighscoreListView = (ListView) findViewById(R.id.highscoreListItem);
        mHighscoreAdapter = new HighscoreAdapter(this, getLayoutInflater(), highscoreList);
        mHighscoreListView.setAdapter(mHighscoreAdapter);
        mHighscoreAdapter.notifyDataSetChanged();
    }
}
