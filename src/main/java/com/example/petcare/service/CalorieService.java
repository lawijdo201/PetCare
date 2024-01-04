package com.example.petcare.service;

import com.example.petcare.data.dto.Calorie.CalorieCatDTO;
import com.example.petcare.data.dto.Calorie.CalorieDogDTO;
import com.example.petcare.data.dto.Calorie.resultCalorieDTO;
public interface CalorieService {
    resultCalorieDTO catFoodCalorie(CalorieCatDTO calorieCatDTO);

     resultCalorieDTO dogFoodCalorie(CalorieDogDTO calorieDogDTO);
}
