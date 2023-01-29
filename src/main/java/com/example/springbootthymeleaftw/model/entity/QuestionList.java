package com.example.springbootthymeleaftw.model.entity;

import java.util.List;

public class QuestionList {
    private List<QuestionEntity> questions;

    public QuestionList() {
    }

    public QuestionList(List<QuestionEntity> questions) {
        this.questions = questions;
    }

    public List<QuestionEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionEntity> questions) {
        this.questions = questions;
    }
}
