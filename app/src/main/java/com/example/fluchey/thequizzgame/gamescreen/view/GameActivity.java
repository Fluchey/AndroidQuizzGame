package com.example.fluchey.thequizzgame.gamescreen.view;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fluchey.thequizzgame.R;
import com.example.fluchey.thequizzgame.gamescreen.presenter.GamePresenter;
import com.example.fluchey.thequizzgame.gamescreen.presenter.GamePresenterInter;
import com.example.fluchey.thequizzgame.scorescreen.view.ScoreActivity;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity implements GameActivityInter, AdapterView.OnItemClickListener {
    private GamePresenterInter gamePresenter;

    private TextView categoryText;
    private TextView questionText;
    private TextView currentQuestionInt;
    private TextView correctAnswersInt;
    private TextView totalQuestionsInt;

    private ListView listView;

    private Button okButton;
    private Button newQuestionButton;

    private int chosenAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        this.gamePresenter = new GamePresenter(this);

        categoryText = (TextView) (findViewById(R.id.tvCategoryAlt));
        questionText = (TextView) (findViewById(R.id.questionText));
        currentQuestionInt = (TextView) (findViewById(R.id.currentQuestionNumber));
        correctAnswersInt = (TextView) (findViewById(R.id.correctAnswersInt));
        totalQuestionsInt = (TextView) (findViewById(R.id.totalNumberOfQuestionsInt));

        listView = (ListView) (findViewById(R.id.questionsListView));
        listView.setOnItemClickListener(this);

        okButton = (Button) findViewById(R.id.okButton);
        newQuestionButton = (Button) findViewById(R.id.newQuestionButton);

        gamePresenter.getNewGame("10", "", "easy");

        currentQuestionInt.setText("1");
        correctAnswersInt.setText("0");
        totalQuestionsInt.setText("10");
    }


    @Override
    public void showQuestionText(String text) {
        questionText.setText(text);
    }

    @Override
    public void showQuestionAlternatives(ArrayList<String> alternatives) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alternatives);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public void showCategoryText(String category) {
        categoryText.setText(category);
    }

    @Override
    public void showCorrectAnswer(int correctId) {
        listView.getChildAt(correctId).setBackgroundColor(Color.GREEN);
        listView.setEnabled(false);
    }

    @Override
    public void showFalseAnswer(int wrongAnswer, int correctAnswer) {
        listView.getChildAt(wrongAnswer).setBackgroundColor(Color.RED);
        listView.getChildAt(correctAnswer).setBackgroundColor(Color.GREEN);
        listView.setEnabled(false);
    }

    @Override
    public void setCorrectAnswersInt(int number) {
        correctAnswersInt.setText(String.valueOf(number));
    }

    @Override
    public void setTotalQuestionsInt(int number) {
        totalQuestionsInt.setText(number);
    }

    @Override
    public void startScoreActivity() {
        Intent showScoreIntent = new Intent(GameActivity.this, ScoreActivity.class);
        showScoreIntent.putExtra("correctQuestions", correctAnswersInt.getText().toString());
        showScoreIntent.putExtra("totaltQuestions", totalQuestionsInt.getText().toString());
        GameActivity.this.startActivity(showScoreIntent);
    }

    @Override
    public void setTextOnNewQuestionButton(String text) {
        newQuestionButton.setText(text);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        chosenAnswer = (int) id;
    }

    public void showNewQuestion(View view) {
        okButton.setEnabled(true);
        gamePresenter.getNewQuestion();
        listView.setEnabled(true);

        int current = Integer.parseInt(currentQuestionInt.getText().toString());
        current += 1;
        currentQuestionInt.setText(String.valueOf(current));
    }

    public void checkAnswer(View view) {
        okButton.setEnabled(false);
        gamePresenter.checkCorrectAnswer(chosenAnswer);
    }
}
