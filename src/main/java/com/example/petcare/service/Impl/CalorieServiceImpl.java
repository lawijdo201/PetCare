package com.example.petcare.service.Impl;

import com.example.petcare.data.dto.Calorie.CalorieCatDTO;
import com.example.petcare.data.dto.Calorie.CalorieDogDTO;
import com.example.petcare.data.dto.Calorie.resultCalorieDTO;
import com.example.petcare.service.CalorieService;
import org.springframework.stereotype.Service;

@Service
public class CalorieServiceImpl implements CalorieService {
    @Override
    public resultCalorieDTO catFoodCalorie(CalorieCatDTO calorieCatDTO) {
        return null;
    }

    @Override
    public resultCalorieDTO dogFoodCalorie(CalorieDogDTO calorieDogDTO) {
        return null;
    }
}
