package com.example.petcare.controller;

import com.example.petcare.data.dto.Calorie.CalorieCatDTO;
import com.example.petcare.data.dto.Calorie.CalorieDogDTO;
import com.example.petcare.data.dto.Calorie.RecommandCalDTO;
import com.example.petcare.service.Calorie.CalorieService;
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

    //사료 URL
    @GetMapping("/feed")
    public String choicePet(Model model) {
        return "foodCalorieChoice";
    }

    //강아지 사료
    @PostMapping("/feed/dog")
    public String foodDogCalorie(CalorieDogDTO calorieDogDTO, Model model) {
        model.addAttribute("AAFCO", calorieService.dogFoodCalorie(calorieDogDTO));
        return "foodCalorieDog";
    }

    //고양이 사료
    @PostMapping("/feed/cat")
    public String foodCatCalorie(CalorieCatDTO calorieCatDTO, Model model) {
        model.addAttribute("AAFCO", calorieService.catFoodCalorie(calorieCatDTO));
        return "foodCalorieCat";
    }

    //칼로리 추천 URL
    @GetMapping("/recommand_calorie")
    public String recomandCalorie(Model model) {
        return "RecommendCalorie";
    }

    //고양이 칼로리 추천
    @GetMapping("/recommand_calorie/cat")
    public String recommandCat(RecommandCalDTO recommandCalDTO, Model model) {
        model.addAttribute("calorie", calorieService.catRecommandCal(recommandCalDTO));
        System.out.println(calorieService.catRecommandCal(recommandCalDTO));
        return "RecommandBMR";
    }

    //강아지 칼로리 추천
    @GetMapping("/recommand_calorie/dog")
    public String recommandDog(RecommandCalDTO recommandCalDogDTO, Model model) {
        model.addAttribute("calorie", calorieService.dogRecommandCal(recommandCalDogDTO));
        return "RecommandBMR";
    }
}
