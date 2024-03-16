package com.example.shivamBhardwaj.simoneducation6392.Models;

import java.io.Serializable;

public class QuizModel implements Serializable {
    private String quizSubject, quizLanguage, quizNumber,quizId;
    private String Question1, q1Option1, q1Option2, q1Option3, q1Option4, q1correctAnswer;
    private String Question2, q2Option1, q2Option2, q2Option3, q2Option4, q2correctAnswer;
    private String Question3, q3Option1, q3Option2, q3Option3, q3Option4, q3correctAnswer;
    private String Question4, q4Option1, q4Option2, q4Option3, q4Option4, q4correctAnswer;
    private String Question5, q5Option, q5Option2, q5Option3, q5Option4, q5correctAnswer;
    private long quizAddedAt;

    public QuizModel() {
    }

    public String getQuizSubject() {
        return quizSubject;
    }

    public void setQuizSubject(String quizSubject) {
        this.quizSubject = quizSubject;
    }

    public String getQuizLanguage() {
        return quizLanguage;
    }

    public void setQuizLanguage(String quizLanguage) {
        this.quizLanguage = quizLanguage;
    }

    public String getQuizNumber() {
        return quizNumber;
    }

    public void setQuizNumber(String quizNumber) {
        this.quizNumber = quizNumber;
    }

    public String getQuestion1() {
        return Question1;
    }

    public void setQuestion1(String question1) {
        Question1 = question1;
    }

    public String getQ1Option1() {
        return q1Option1;
    }

    public void setQ1Option1(String q1Option1) {
        this.q1Option1 = q1Option1;
    }

    public String getQ1Option2() {
        return q1Option2;
    }

    public void setQ1Option2(String q1Option2) {
        this.q1Option2 = q1Option2;
    }

    public String getQ1Option3() {
        return q1Option3;
    }

    public void setQ1Option3(String q1Option3) {
        this.q1Option3 = q1Option3;
    }

    public String getQ1Option4() {
        return q1Option4;
    }

    public void setQ1Option4(String q1Option4) {
        this.q1Option4 = q1Option4;
    }

    public String getQ1correctAnswer() {
        return q1correctAnswer;
    }

    public void setQ1correctAnswer(String q1correctAnswer) {
        this.q1correctAnswer = q1correctAnswer;
    }

    public String getQuestion2() {
        return Question2;
    }

    public void setQuestion2(String question2) {
        Question2 = question2;
    }

    public String getQ2Option1() {
        return q2Option1;
    }

    public void setQ2Option1(String q2Option1) {
        this.q2Option1 = q2Option1;
    }

    public String getQ2Option2() {
        return q2Option2;
    }

    public void setQ2Option2(String q2Option2) {
        this.q2Option2 = q2Option2;
    }

    public String getQ2Option3() {
        return q2Option3;
    }

    public void setQ2Option3(String q2Option3) {
        this.q2Option3 = q2Option3;
    }

    public String getQ2Option4() {
        return q2Option4;
    }

    public void setQ2Option4(String q2Option4) {
        this.q2Option4 = q2Option4;
    }

    public String getQ2correctAnswer() {
        return q2correctAnswer;
    }

    public void setQ2correctAnswer(String q2correctAnswer) {
        this.q2correctAnswer = q2correctAnswer;
    }

    public String getQuestion3() {
        return Question3;
    }

    public void setQuestion3(String question3) {
        Question3 = question3;
    }

    public String getQ3Option1() {
        return q3Option1;
    }

    public void setQ3Option1(String q3Option1) {
        this.q3Option1 = q3Option1;
    }

    public String getQ3Option2() {
        return q3Option2;
    }

    public void setQ3Option2(String q3Option2) {
        this.q3Option2 = q3Option2;
    }

    public String getQ3Option3() {
        return q3Option3;
    }

    public void setQ3Option3(String q3Option3) {
        this.q3Option3 = q3Option3;
    }

    public String getQ3Option4() {
        return q3Option4;
    }

    public void setQ3Option4(String q3Option4) {
        this.q3Option4 = q3Option4;
    }

    public String getQ3correctAnswer() {
        return q3correctAnswer;
    }

    public void setQ3correctAnswer(String q3correctAnswer) {
        this.q3correctAnswer = q3correctAnswer;
    }

    public String getQuestion4() {
        return Question4;
    }

    public void setQuestion4(String question4) {
        Question4 = question4;
    }

    public String getQ4Option1() {
        return q4Option1;
    }

    public void setQ4Option1(String q4Option1) {
        this.q4Option1 = q4Option1;
    }

    public String getQ4Option2() {
        return q4Option2;
    }

    public void setQ4Option2(String q4Option2) {
        this.q4Option2 = q4Option2;
    }

    public String getQ4Option3() {
        return q4Option3;
    }

    public void setQ4Option3(String q4Option3) {
        this.q4Option3 = q4Option3;
    }

    public String getQ4Option4() {
        return q4Option4;
    }

    public void setQ4Option4(String q4Option4) {
        this.q4Option4 = q4Option4;
    }

    public String getQ4correctAnswer() {
        return q4correctAnswer;
    }

    public void setQ4correctAnswer(String q4correctAnswer) {
        this.q4correctAnswer = q4correctAnswer;
    }

    public String getQuestion5() {
        return Question5;
    }

    public void setQuestion5(String question5) {
        Question5 = question5;
    }

    public String getQ5Option() {
        return q5Option;
    }

    public void setQ5Option(String q5Option) {
        this.q5Option = q5Option;
    }

    public String getQ5Option2() {
        return q5Option2;
    }

    public void setQ5Option2(String q5Option2) {
        this.q5Option2 = q5Option2;
    }

    public String getQ5Option3() {
        return q5Option3;
    }

    public void setQ5Option3(String q5Option3) {
        this.q5Option3 = q5Option3;
    }

    public String getQ5Option4() {
        return q5Option4;
    }

    public void setQ5Option4(String q5Option4) {
        this.q5Option4 = q5Option4;
    }

    public String getQ5correctAnswer() {
        return q5correctAnswer;
    }

    public void setQ5correctAnswer(String q5correctAnswer) {
        this.q5correctAnswer = q5correctAnswer;
    }

    public long getQuizAddedAt() {
        return quizAddedAt;
    }

    public void setQuizAddedAt(long quizAddedAt) {
        this.quizAddedAt = quizAddedAt;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }
}
