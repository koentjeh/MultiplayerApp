package com.example.gebruiker.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by wbjar on 16-1-2017.
 */

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        String mode = intent.getStringExtra("EXTRA_MESSAGE");
        TextView textViewMode = (TextView) findViewById(R.id.mode);
        textViewMode.setText(mode);

    }
}
