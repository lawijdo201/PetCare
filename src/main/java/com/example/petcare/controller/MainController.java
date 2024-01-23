package com.example.petcare.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
public class MainController {
    @GetMapping("/")
    public String main(Model model) {
        return "index";
    }

    @RequestMapping("/main")
    public String test(Model model){
        return "index";
    }
}