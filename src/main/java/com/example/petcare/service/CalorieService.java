package com.example.petcare.service;

import com.example.petcare.data.dto.Calorie.CalorieCatDTO;
import com.example.petcare.data.dto.Calorie.CalorieDogDTO;
import com.example.petcare.data.dto.Calorie.ResertCalorieCatDTO;
import com.example.petcare.data.dto.Calorie.ResertCalorieDogDTO;

public interface CalorieService {
    ResertCalorieCatDTO catFoodCalorie(CalorieCatDTO calorieCatDTO);
    ResertCalorieDogDTO dogFoodCalorie(CalorieDogDTO calorieDogDTO);
}
