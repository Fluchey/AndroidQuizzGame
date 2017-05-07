package com.example.fluchey.thequizzgame.gamescreen.model;

import com.example.fluchey.thequizzgame.other.Question;

import org.json.JSONException;

/**
 * Created by fluchey on 2017-04-29.
 */

public interface GameModelInter {
    interface OnRequestFinishedListener{
        void showFirstQuestion();
    }

    void getNewGame(String questionAmount, String category, String difficulty);

    void populateNewQuestions(String jsonFromApi) throws JSONException;

    void updateCurrentQuestion();

    Question getCurrentQuestion();

    int getTotalQuestions();

    void setTotalQuestions(int totalQuestions);

    int getNumberOfTotalAnsweredQuestions();

    void setNumberOfTotalAnsweredQuestions(int numberOfTotalAnsweredQuestions);

    int getCorrectAnsweredQuestions();

    void setCorrectAnsweredQuestions(int correctAnsweredQuestions);

}
