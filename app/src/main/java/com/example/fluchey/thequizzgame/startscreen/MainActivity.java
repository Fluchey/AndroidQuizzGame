package com.example.fluchey.thequizzgame.startscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fluchey.thequizzgame.R;
import com.example.fluchey.thequizzgame.gamescreen.view.GameActivity;

public class MainActivity extends AppCompatActivity {
    private ListView category;
    private ListView numberOfQuestions;
    private ListView difficulty;

    private ArrayAdapter<String> categoryAdapter;
    private ArrayAdapter<String> numberOfQuestionsAdapter;
    private ArrayAdapter<String> difficultyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        category = (ListView) findViewById(R.id.categoryListview);
        numberOfQuestions = (ListView) findViewById(R.id.numberOfQuestionsListView);
        difficulty = (ListView) findViewById(R.id.difficultyListView);

        difficultyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.difficulty));
        difficulty.setAdapter(difficultyAdapter);
    }

    public void startNewGame(View view) {
        Intent startNewGameIntent = new Intent(MainActivity.this, GameActivity.class);
        MainActivity.this.startActivity(startNewGameIntent);
    }
}
