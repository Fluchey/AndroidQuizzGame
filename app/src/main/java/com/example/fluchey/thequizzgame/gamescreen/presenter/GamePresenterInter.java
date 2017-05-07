package com.example.fluchey.thequizzgame.gamescreen.presenter;

/**
 * Created by fluchey on 2017-04-29.
 */

public interface GamePresenterInter {
    void checkCorrectAnswer(int idInArray);

    void getNewQuestion();

    void getNewGame(String questionAmount, String category, String difficulty);
}
