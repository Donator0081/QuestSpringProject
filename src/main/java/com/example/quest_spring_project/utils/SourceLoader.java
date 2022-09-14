package com.example.quest_spring_project.utils;

import com.example.quest_spring_project.dto.AnswersDTO;
import com.example.quest_spring_project.dto.QuestionsDTO;
import com.example.quest_spring_project.entities.Answer;
import com.example.quest_spring_project.entities.Question;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SourceLoader {

    Logger logger = LoggerFactory.getLogger(getClass().getName());
    private String source;
    private Exception error;

    private int id;
    private  List<Answer> answers;

    private Answer correctAnswer;


    public void read(int id) {
        answers = new ArrayList<>();
        this.id = id;
        try {
            ObjectMapper mapper = new ObjectMapper();
            QuestionsDTO[] questionsDTOS =
                    mapper.readValue(new File("src/main/resources/static/json/questions.json"), QuestionsDTO[].class);
            AnswersDTO[] answersDTOS =
                    mapper.readValue(new File("src/main/resources/static/json/answers.json"), AnswersDTO[].class);
            setSource(id, questionsDTOS);
            setAnswers(id, answersDTOS);
            setCorrectAnswer();
        } catch (IOException e) {
            error = e;
        }
    }

    private void setCorrectAnswer() {
        answers.forEach(item -> {
            if (item.isCorrect()) {
                correctAnswer = item;
            }
        });
    }

    private void setAnswers(int id, AnswersDTO[] answersDTOS) {
        for (AnswersDTO item : answersDTOS) {
            if (item.getQuestionsId() == id) {
                answers.add(new Answer(item.getId(), item.getText(), item.isCorrect(),id));
            }
        }
    }

    private void setSource(int id, QuestionsDTO[] questionsDTOS) {
        for (QuestionsDTO item : questionsDTOS) {
            if (item.getId() == id) {
                source = item.getText();
            }
        }
    }

    public boolean hasError() {
        return error != null;
    }

    public Question build() {
        return Question.builder()
                .text(source)
                .answers(answers)
                .id(id)
                .correctAnswer(correctAnswer)
                .build();
    }
}
