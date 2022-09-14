package com.example.quest_spring_project.controllers;

import com.example.quest_spring_project.entities.Question;
import com.example.quest_spring_project.services.QuestionsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/quest")
public class RestQuestionsController {

    private final QuestionsService service;

    public RestQuestionsController(QuestionsService service) {
        this.service = service;
    }

    @GetMapping("/start")
    public String showQuestStart() {
        return "quest-start";
    }

    @GetMapping("/start-quiz")
    public String showQuestion(Model model) {
        int starterId = 1;
        Question question = service.getQuestionById(starterId);
        model.addAttribute("question", question);
        model.addAttribute("nextQuestionNumber", starterId + 1);
        return "question-page";
    }

    @GetMapping("{id}")
    public String showQuestionById(@PathVariable int id, @RequestParam("chosenAnswer") String answer, Model model) {
        Question currentQuestion = service.getQuestionById(id);
        Question previousQuestion = service.getQuestionById(id - 1);
        model.addAttribute("question", currentQuestion);
        model.addAttribute("nextQuestionNumber", id + 1);
        if (id == service.getQuestions().size() + 1) {
            return "redirect:/quest/happy-end";
        }
        if (previousQuestion.getCorrectAnswer().getText().equals(answer)) {
            return "question-page";
        } else {
            return "redirect:/quest/fail";
        }
    }

    @GetMapping("/fail")
    public String showFailPage() {
        return "fail-page";
    }

    @GetMapping("/happy-end")
    public String showHappyEndingPage() {
        return "happy-ending-page";
    }
}
