package com.jeohyoo.portfolio.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class indexController {
    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "Home Page");
        return "index"; // src/main/resources/templates/index.html
    }
}
