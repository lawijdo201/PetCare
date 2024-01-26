package com.example.petcare.service.Calorie;

import com.example.petcare.data.dto.Calorie.*;

public interface CalorieService {
    ResertCalorieCatDTO catFoodCalorie(CalorieCatDTO calorieCatDTO);
    ResertCalorieDogDTO dogFoodCalorie(CalorieDogDTO calorieDogDTO);

    float catRecommandCal(RecommandCalDTO recommandCalDTO);
    float dogRecommandCal(RecommandCalDTO recommandCalDogDTO);

}
