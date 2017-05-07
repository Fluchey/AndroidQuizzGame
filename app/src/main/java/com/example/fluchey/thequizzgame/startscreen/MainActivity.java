package com.example.fluchey.thequizzgame.startscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fluchey.thequizzgame.R;
import com.example.fluchey.thequizzgame.gamescreen.view.GameActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startNewGame(View view) {
        Intent startNewGameIntent = new Intent(MainActivity.this, GameActivity.class);
        MainActivity.this.startActivity(startNewGameIntent);
    }
}
