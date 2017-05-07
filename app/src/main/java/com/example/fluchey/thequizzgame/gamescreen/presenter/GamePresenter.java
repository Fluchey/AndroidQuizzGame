package com.example.fluchey.thequizzgame.gamescreen.presenter;

import com.example.fluchey.thequizzgame.gamescreen.model.GameModel;
import com.example.fluchey.thequizzgame.gamescreen.model.GameModelInter;
import com.example.fluchey.thequizzgame.gamescreen.view.GameActivityInter;
import com.example.fluchey.thequizzgame.other.Question;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by fluchey on 2017-04-29.
 */

public class GamePresenter implements GamePresenterInter, GameModelInter.OnRequestFinishedListener {
    private GameActivityInter gameActivity;
    private GameModelInter gameModel;

    public GamePresenter(GameActivityInter gameActivity) {
        this.gameActivity = gameActivity;
        this.gameModel = new GameModel(this);
    }

    @Override
    public void checkCorrectAnswer(int idInArray) {
        String chosenAnswer = gameModel.getCurrentQuestion().getAllAnswers().get(idInArray);
        String correctAnswer = gameModel.getCurrentQuestion().getCorrectAnswer();

        if(chosenAnswer.equals(correctAnswer)){
            gameActivity.showCorrectAnswer(idInArray);

            /**
             * Increase the number of correctly answered questions by 1
             */
            gameModel.setCorrectAnsweredQuestions(gameModel.getCorrectAnsweredQuestions() + 1);
            gameActivity.setCorrectAnswersInt(gameModel.getCorrectAnsweredQuestions());
        }else {
            gameActivity.showFalseAnswer(idInArray, gameModel.getCurrentQuestion().getAllAnswers().indexOf(correctAnswer));
        }

        gameModel.setNumberOfTotalAnsweredQuestions(gameModel.getNumberOfTotalAnsweredQuestions() + 1);

        if(gameModel.getTotalQuestions() == gameModel.getNumberOfTotalAnsweredQuestions()){
            gameActivity.setTextOnNewQuestionButton("See score");
        }
    }

    @Override
    public void getNewQuestion() {
        if(gameModel.getTotalQuestions() == gameModel.getNumberOfTotalAnsweredQuestions()){
            gameActivity.startScoreActivity();
        }

        gameModel.updateCurrentQuestion();

        gameActivity.showCategoryText(gameModel.getCurrentQuestion().getCategory());
        gameActivity.showQuestionText(gameModel.getCurrentQuestion().getQuestion());
        gameActivity.showQuestionAlternatives(gameModel.getCurrentQuestion().getAllAnswers());
    }

    @Override
    public void getNewGame(String questionAmount, String category, String difficulty) {
        gameModel.getNewGame(questionAmount, category, difficulty);
    }

    @Override
    public void showFirstQuestion() {
        getNewQuestion();
    }
}
