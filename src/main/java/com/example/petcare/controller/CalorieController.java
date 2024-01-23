package com.example.petcare.controller;

import com.example.petcare.data.dto.Calorie.CalorieCatDTO;
import com.example.petcare.data.dto.Calorie.CalorieDogDTO;
import com.example.petcare.service.CalorieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calorie")
public class CalorieController {
    private final CalorieService calorieService;

    public CalorieController(CalorieService calorieService) {
        this.calorieService = calorieService;
    }

    @GetMapping("/feed")
    public String choicePet(Model model) {
        return "foodCalorieChoice";
    }

    @PostMapping("/feed/dog")
    public String foodDogCalorie(CalorieDogDTO calorieDogDTO, Model model) {
        model.addAttribute("AAFCO", calorieService.dogFoodCalorie(calorieDogDTO));
        return "foodCalorieDog";
    }

    @PostMapping("/feed/cat")
    public String foodCatCalorie(CalorieCatDTO calorieCatDTO, Model model) {
        model.addAttribute("AAFCO", calorieService.catFoodCalorie(calorieCatDTO));
        return "foodCalorieCat";
    }
}
