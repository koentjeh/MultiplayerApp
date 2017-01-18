package com.example.gebruiker.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Knop die naar de prestartgame pagina gaat
        Button buttonStartGame = (Button) findViewById(R.id.buttonStartGame);
        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.i(TAG, "onClick: redirect -> prestartgame activity");
                Intent intent = new Intent(MainActivity.this, PregameActivity.class);
                startActivity(intent);
            }
        });

        // Knop die naar de highscores pagina gaat
        Button buttonHighscores = (Button) findViewById(R.id.buttonHighscores);
        buttonHighscores.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.i(TAG, "onClick: redirect -> highscores activity");
                Intent intent = new Intent(getBaseContext(), HighscoreActivity.class);
                startActivity(intent);
            }
        });

        // Knop die naar de over ons pagina gaat
        Button buttonAboutUs = (Button) findViewById(R.id.buttonAboutUs);
        buttonAboutUs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.i(TAG, "onClick: redirect -> aboutus activity");
                Intent intent = new Intent(MainActivity.this, AboutusActivity.class);
                startActivity(intent);
            }
        });

        // Knop om applicatie af te sluiten
        Button buttonExitApplication = (Button) findViewById(R.id.closeApplication);
        buttonExitApplication.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Sluit de app af
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
    }
}
