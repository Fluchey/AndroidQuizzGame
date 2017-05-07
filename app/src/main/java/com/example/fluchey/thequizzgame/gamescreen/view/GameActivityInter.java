package com.example.fluchey.thequizzgame.gamescreen.view;

import com.example.fluchey.thequizzgame.other.Question;

import java.util.ArrayList;

/**
 * Created by fluchey on 2017-04-29.
 */

public interface GameActivityInter {

    /**
     * Shows the question to the user
     * @param question
     */
    void showQuestionText(String text);

    void showQuestionAlternatives(ArrayList<String> alternatives);

    void showCategoryText(String category);

    /**
     * Show the correct answer in the arraylist of possible answer
     * @param correctId The ID of the correct Answer
     */
    void showCorrectAnswer(int correctId);

    void showFalseAnswer(int wrongAnswer, int correctAnswer);

    void setCorrectAnswersInt(int number);

    void setTotalQuestionsInt(int number);

    void startScoreActivity();

    void setTextOnNewQuestionButton(String text);
}
