package com.example.petcare.service.Calorie.Impl;

import com.example.petcare.data.dto.Calorie.*;
import com.example.petcare.service.Calorie.CalorieService;
import org.springframework.stereotype.Service;

@Service
public class CalorieServiceImpl implements CalorieService {
    @Override
    public ResertCalorieCatDTO catFoodCalorie(CalorieCatDTO calorieCatDTO) {
        float Moisture = (100 - calorieCatDTO.getMoisture()) / 100;

        //DM계산
        ResertCalorieCatDTO resertCalorieCatDTO = ResertCalorieCatDTO.builder()
                .protein(calorieCatDTO.getProtein() * Moisture)             //조단백질
                .province(calorieCatDTO.getProvince() * Moisture)           //조지방
                .calcium(calorieCatDTO.getCalcium() * Moisture)             //칼슘
                .phosphorus(calorieCatDTO.getPhosphorus() * Moisture)       //인
                .carbohydrate(Float.parseFloat(String.format("%.2f",100-calorieCatDTO.getProtein() * Moisture-calorieCatDTO.getProvince() * Moisture-calorieCatDTO.getMoisture()-calorieCatDTO.getAsh() * Moisture)))
                .build();

        //영양분 충족여부
        //성장기
        if (calorieCatDTO.getState().equals("kid")) {
            //조단백질
            if (calorieCatDTO.getProtein() * Moisture < 0.35) {
                resertCalorieCatDTO.setProteinCHECK("미충족");
            } else {
                resertCalorieCatDTO.setProteinCHECK("충족");
            }

            //조지방
            if (calorieCatDTO.getProvince() * Moisture < 0.09) {
                resertCalorieCatDTO.setProvinceCHECK("미충족");
            } else {
                resertCalorieCatDTO.setProvinceCHECK("충족");
            }

            //칼슘
            if (calorieCatDTO.getCalcium() * Moisture < 0.01) {
                resertCalorieCatDTO.setCalciumCHECK("미충족");
            } else {
                resertCalorieCatDTO.setCalciumCHECK("충족");
            }

            //인
            if (calorieCatDTO.getPhosphorus() * Moisture < 0.008) {
                resertCalorieCatDTO.setPhosphorusCHECK("미충족");
            } else {
                resertCalorieCatDTO.setPhosphorusCHECK("충족");
            }

            //타우린
            if (calorieCatDTO.getTaurine() * Moisture < 0.0015) {
                resertCalorieCatDTO.setTaurineCHECK("미충족");
            } else {
                resertCalorieCatDTO.setTaurineCHECK("충족");
            }
            //성숙기
        } else {
            //조단백질
            if (calorieCatDTO.getProtein() * Moisture < 0.26) {
                resertCalorieCatDTO.setProteinCHECK("미충족");
            } else {
                resertCalorieCatDTO.setProteinCHECK("충족");
            }

            //조지방
            if (calorieCatDTO.getProvince() * Moisture < 0.09) {
                resertCalorieCatDTO.setProvinceCHECK("미충족");
            } else {
                resertCalorieCatDTO.setProvinceCHECK("충족");
            }

            //칼슘
            if (calorieCatDTO.getCalcium() * Moisture < 0.006) {
                resertCalorieCatDTO.setCalciumCHECK("미충족");
            } else {
                resertCalorieCatDTO.setCalciumCHECK("충족");
            }

            //인
            if (calorieCatDTO.getPhosphorus() * Moisture < 0.005) {
                resertCalorieCatDTO.setPhosphorusCHECK("미충족");
            } else {
                resertCalorieCatDTO.setPhosphorusCHECK("충족");
            }

            //타우린
            if (calorieCatDTO.getTaurine() * Moisture < 0.0015) {
                resertCalorieCatDTO.setTaurineCHECK("미충족");
            } else {
                resertCalorieCatDTO.setTaurineCHECK("충족");
            }
        }

        return resertCalorieCatDTO;
    }

    @Override
    public ResertCalorieDogDTO dogFoodCalorie(CalorieDogDTO calorieDogDTO) {
        float Moisture = (100 - calorieDogDTO.getMoisture()) / 100;

        //DM계산
        ResertCalorieDogDTO resertCalorieDogDTO = ResertCalorieDogDTO.builder()
                .protein(calorieDogDTO.getProtein() * Moisture)             //조단백질
                .province(calorieDogDTO.getProvince() * Moisture)           //조지방
                .calcium(calorieDogDTO.getCalcium() * Moisture)             //칼슘
                .phosphorus(calorieDogDTO.getPhosphorus() * Moisture)       //인
                .carbohydrate(Float.parseFloat(String.format("%.2f",100-calorieDogDTO.getProtein() * Moisture-calorieDogDTO.getProvince() * Moisture-calorieDogDTO.getMoisture()-calorieDogDTO.getAsh() * Moisture)))
                .build();

        //영양분 충족여부
        //성장기
        if (calorieDogDTO.getState().equals("kid")) {
            //조단백질
            if (calorieDogDTO.getProtein() * Moisture < 0.225) {
                resertCalorieDogDTO.setProteinCHECK("미충족");
            } else {
                resertCalorieDogDTO.setProteinCHECK("충족");
            }

            //조지방
            if (calorieDogDTO.getProvince() * Moisture < 0.085) {
                resertCalorieDogDTO.setProvinceCHECK("미충족");
            } else {
                resertCalorieDogDTO.setProvinceCHECK("충족");
            }

            //칼슘
            if (calorieDogDTO.getCalcium() * Moisture < 0.012) {
                resertCalorieDogDTO.setCalciumCHECK("미충족");
            } else {
                resertCalorieDogDTO.setCalciumCHECK("충족");
            }

            //인
            if (calorieDogDTO.getPhosphorus() * Moisture < 0.01) {
                resertCalorieDogDTO.setPhosphorusCHECK("미충족");
            } else {
                resertCalorieDogDTO.setPhosphorusCHECK("충족");
            }

            //성숙기
        } else {
            //조단백질
            if (calorieDogDTO.getProtein() * Moisture < 0.18) {
                resertCalorieDogDTO.setProteinCHECK("미충족");
            } else {
                resertCalorieDogDTO.setProteinCHECK("충족");
            }

            //조지방
            if (calorieDogDTO.getProvince() * Moisture < 0.055) {
                resertCalorieDogDTO.setProvinceCHECK("미충족");
            } else {
                resertCalorieDogDTO.setProvinceCHECK("충족");
            }

            //칼슘
            if (calorieDogDTO.getCalcium() * Moisture < 0.005) {
                resertCalorieDogDTO.setCalciumCHECK("미충족");
            } else {
                resertCalorieDogDTO.setCalciumCHECK("충족");
            }

            //인
            if (calorieDogDTO.getPhosphorus() * Moisture < 0.004) {
                resertCalorieDogDTO.setPhosphorusCHECK("미충족");
            } else {
                resertCalorieDogDTO.setPhosphorusCHECK("충족");
            }
        }

        return resertCalorieDogDTO;
    }

    //고양이 칼로리 계산
    @Override
    public float catRecommandCal(RecommandCalDTO recommandCalDTO) {
        float RER = 30 * recommandCalDTO.getWeight() + 70;
        return recommandCalDTO.getStats()*RER;
    }


    //강아지 칼로리 계산
    @Override
    public float dogRecommandCal(RecommandCalDTO recommandCalDTO) {
        float RER;
        if (recommandCalDTO.getWeight() < 2 && recommandCalDTO.getWeight() >= 45) {
            RER = 30 * recommandCalDTO.getWeight() + 70;
        } else {
            RER = recommandCalDTO.getWeight() * 70 * 0.75f;
        }


        return recommandCalDTO.getStats()*RER;
    }
}
