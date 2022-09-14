package com.example.quest_spring_project.repository;

import com.example.quest_spring_project.entities.Question;
import com.example.quest_spring_project.utils.SourceLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class QuestionsRepoImpl implements QuestionsRepo {


    private HashMap<Integer, Question> questions;
    private final SourceLoader sourceLoader;

    public QuestionsRepoImpl(@Value("${amountOfQuestions}") String amountOfQuestions, SourceLoader sourceLoader) {
        this.sourceLoader = sourceLoader;
        fillQuestions(amountOfQuestions);
    }

    @Override
    public HashMap<Integer, Question> getQuestions() {
        return questions;
    }

    @Override
    public Question getQuestionById(int id) {
        return questions.get(id);
    }

    private void fillQuestions(String amountOfQuestions) {
        questions = new HashMap<>();
        int questionsSize = Integer.parseInt(amountOfQuestions);
        for (int i = 1; i < questionsSize + 1; i++) {
            sourceLoader.read(i);
            if (!sourceLoader.hasError()) {
                questions.put(i, sourceLoader.build());
            }
        }
    }

}
