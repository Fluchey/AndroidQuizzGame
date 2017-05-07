package com.example.fluchey.thequizzgame.scorescreen.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.fluchey.thequizzgame.R;
import com.example.fluchey.thequizzgame.gamescreen.view.GameActivity;

import org.w3c.dom.Text;

public class ScoreActivity extends AppCompatActivity implements ScoreView {
    private TextView correctQuestions;
    private TextView totalQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        correctQuestions = (TextView) findViewById(R.id.tvScoreCorrectAmount);
        totalQuestions = (TextView) findViewById(R.id.tvScoreTotalQuestions);

        Bundle bundle = getIntent().getExtras();
        correctQuestions.setText(bundle.getString("correctQuestions"));
        totalQuestions.setText(bundle.getString("totaltQuestions"));
    }

    @Override
    public void setCongratulationText() {

    }

    @Override
    public void setTotalNumberOfQuestions() {

    }

    @Override
    public void setCorrectQuestions() {

    }

    public void startNewGame(View view) {
        Intent startNewGameIntent = new Intent(ScoreActivity.this, GameActivity.class);
        ScoreActivity.this.startActivity(startNewGameIntent);
    }
}
