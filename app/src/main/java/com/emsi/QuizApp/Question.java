package com.emsi.QuizApp;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private ArrayList<String> answers;
    private String content;
    private String correctAnswer;
    private int difficulty;
    private String imageUrl;


    public Question(ArrayList<String> answers, String content, String correctAnswer, int difficulty, String imageUrl) {
        this.answers = answers;
        this.content = content;
        this.correctAnswer = correctAnswer;
        this.difficulty = difficulty;
        this.imageUrl = imageUrl;
    }


    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
