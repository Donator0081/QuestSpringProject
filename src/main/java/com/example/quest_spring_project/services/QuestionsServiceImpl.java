package com.example.quest_spring_project.services;

import com.example.quest_spring_project.entities.Question;
import com.example.quest_spring_project.repository.QuestionsRepo;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class QuestionsServiceImpl implements QuestionsService {

    private final QuestionsRepo repo;

    public QuestionsServiceImpl(QuestionsRepo repo) {
        this.repo = repo;
    }

    @Override
    public HashMap<Integer, Question> getQuestions() {
        return repo.getQuestions();
    }

    @Override
    public Question getQuestionById(int id) {
        return repo.getQuestionById(id);
    }
}
