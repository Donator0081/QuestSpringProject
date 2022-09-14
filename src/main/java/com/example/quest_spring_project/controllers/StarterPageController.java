package com.example.quest_spring_project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StarterPageController {


    @GetMapping("/")
    public String showStarterPage(){
        return "starter-page";
    }
}
