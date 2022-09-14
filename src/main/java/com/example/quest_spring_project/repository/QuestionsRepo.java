package com.example.quest_spring_project.repository;

import com.example.quest_spring_project.entities.Question;

import java.util.HashMap;

public interface QuestionsRepo {

    HashMap<Integer, Question> getQuestions();

    Question getQuestionById(int id);
}
