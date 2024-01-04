package com.example.petcare.controller;

import com.example.petcare.data.dto.Calorie.CalorieCatDTO;
import com.example.petcare.data.dto.Calorie.CalorieDogDTO;
import com.example.petcare.service.CalorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calorie")
public class CalorieController {
    private CalorieService calorieService;

    @Autowired
    public CalorieController(CalorieService calorieService) {
        this.calorieService = calorieService;
    }

    @RequestMapping("/feed")
    public String foodCalorie(CalorieDogDTO calorieDogDTO, CalorieCatDTO calorieCatDTO, Model model) {
        return "foodCalorie";
    }
}
