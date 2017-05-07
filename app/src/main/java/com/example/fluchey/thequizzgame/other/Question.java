package com.example.fluchey.thequizzgame.other;

import java.util.ArrayList;

/**
 * Created by fluchey on 2017-04-29.
 */

public class Question {
    private String category;
    private String question;
    private String correctAnswer;
    private ArrayList<String> incorrectAnswers;
    private ArrayList<String> allAnswers;
    private boolean shown;

    public Question(String category, String question, String correctAnswer, ArrayList<String> incorrectAnswers) {
        this.category = category;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public ArrayList<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(ArrayList<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public boolean isShown() {
        return shown;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }

    public ArrayList<String> getAllAnswers() {
        return allAnswers;
    }

    public void setAllAnswers(ArrayList<String> allAnswers) {
        this.allAnswers = allAnswers;
    }
}
