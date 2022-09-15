package com.example.quest_spring_project.repository;

import com.example.quest_spring_project.entities.Question;
import com.example.quest_spring_project.utils.SourceLoader;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class QuestionsRepoImpl implements QuestionsRepo {


    private HashMap<Integer, Question> questions;
    private final SourceLoader sourceLoader;

    public QuestionsRepoImpl(SourceLoader sourceLoader) {
        this.sourceLoader = sourceLoader;
        fillQuestions();
    }

    @Override
    public HashMap<Integer, Question> getQuestions() {
        return questions;
    }

    @Override
    public Question getQuestionById(int id) {
        return questions.get(id);
    }

    private void fillQuestions() {
        questions = new HashMap<>();
        for (int i = 1; i < sourceLoader.getQuantityOfQuestions() + 1; i++) {
            sourceLoader.setVariables(i);
            if (!sourceLoader.hasError()) {
                questions.put(i, sourceLoader.build());
            }
        }
    }

}
