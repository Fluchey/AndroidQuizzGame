package com.example.fluchey.thequizzgame.gamescreen.model;

import android.os.AsyncTask;
import android.util.Log;

import com.example.fluchey.thequizzgame.gamescreen.presenter.GamePresenter;
import com.example.fluchey.thequizzgame.other.Connector;
import com.example.fluchey.thequizzgame.other.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by fluchey on 2017-04-29.
 */

public class GameModel implements GameModelInter {
    private OnRequestFinishedListener onRequestFinishedListener;
    private HashMap<Question, Boolean> questions;
    private Question currentQuestion;

    private int totalQuestions;
    private int numberOfTotalAnsweredQuestions;
    private int correctAnsweredQuestions;

    public GameModel(final OnRequestFinishedListener onRequestFinishedListener) {
        this.onRequestFinishedListener = onRequestFinishedListener;
        questions = new HashMap<>();
    }

    @Override
    public void getNewGame(String questionAmount, String category, String difficulty) {
        ApiRequest apiRequest = (GameModel.ApiRequest) new GameModel.ApiRequest(this).execute(questionAmount, category, difficulty);
    }

    @Override
    public void populateNewQuestions(String jsonFromApi) {
        this.questions.clear();

        try {
            JSONObject jsonObject = new JSONObject(jsonFromApi);
            JSONArray jsonArrayQuestions = jsonObject.getJSONArray("results");

            for (int i = 0; i < jsonArrayQuestions.length(); i++) {
                JSONObject question = jsonArrayQuestions.getJSONObject(i);
                String category = URLDecoder.decode(question.getString("category"));
                String stringQuestion = URLDecoder.decode(question.getString("question"));
                String correctAnswer = URLDecoder.decode(question.getString("correct_answer"));
                JSONArray incorrectAnswers = question.getJSONArray("incorrect_answers");
                ArrayList<String> incorrectAnswersArr = new ArrayList<>();
                for (int j = 0; j < incorrectAnswers.length(); j++) {
                    incorrectAnswersArr.add(URLDecoder.decode((String) incorrectAnswers.get(j)));
                }

                Question thisQuestion = new Question(category, stringQuestion, correctAnswer, incorrectAnswersArr);
                thisQuestion.setAllAnswers(thisQuestion.getIncorrectAnswers());
                thisQuestion.getAllAnswers().add(thisQuestion.getCorrectAnswer());

                Collections.shuffle(thisQuestion.getAllAnswers());
                questions.put(thisQuestion, false);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        totalQuestions = questions.size();
    }

    @Override
    public void updateCurrentQuestion() {
        ArrayList<Question> notAnsweredQuestions = new ArrayList<>();

        /* Get all non answered questions */
        for (Question q: questions.keySet()){
            if(!questions.get(q)){
                notAnsweredQuestions.add(q);
            }
        }

        if (notAnsweredQuestions.size() == 0){
            return;
        }

        Random random = new Random();
        int randomInt = random.nextInt(notAnsweredQuestions.size());
        Question newQuestion = notAnsweredQuestions.get(randomInt);

        this.currentQuestion = newQuestion;

        /* Update the question to answered */
        this.questions.put(newQuestion, true);
    }

    @Override
    public Question getCurrentQuestion() {
        return this.currentQuestion;
    }

    @Override
    public int getTotalQuestions() {
        return totalQuestions;
    }

    @Override
    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    @Override
    public int getNumberOfTotalAnsweredQuestions() {
        return numberOfTotalAnsweredQuestions;
    }

    @Override
    public void setNumberOfTotalAnsweredQuestions(int numberOfTotalAnsweredQuestions) {
        this.numberOfTotalAnsweredQuestions = numberOfTotalAnsweredQuestions;
    }

    @Override
    public int getCorrectAnsweredQuestions() {
        return correctAnsweredQuestions;
    }

    @Override
    public void setCorrectAnsweredQuestions(int correctAnsweredQuestions) {
        this.correctAnsweredQuestions = correctAnsweredQuestions;
    }

    private class ApiRequest extends AsyncTask<String, GamePresenter, Void>{
        private GameModel gameModel;
        String result = "";

        public ApiRequest(GameModel gameModel) {
            this.gameModel = gameModel;
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                result = Connector.connect(params[0], params[1], params[2]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            gameModel.populateNewQuestions(result);
            gameModel.onRequestFinishedListener.showFirstQuestion();
        }
    }
}
