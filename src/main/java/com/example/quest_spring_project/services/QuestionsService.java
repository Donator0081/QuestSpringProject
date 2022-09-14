package com.example.quest_spring_project.services;

import com.example.quest_spring_project.entities.Question;

import java.util.HashMap;

public interface QuestionsService {

    HashMap<Integer, Question> getQuestions();

    Question getQuestionById(int id);
}
