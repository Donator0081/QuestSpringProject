package com.example.quest_spring_project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswersDTO {
    private int id;
    private String text;
    private boolean isCorrect;
    private int questionsId;


    @Override
    public String toString() {
        return "AnswersDTO{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", isCorrect=" + isCorrect +
                ", questionsId=" + questionsId +
                '}';
    }
}
