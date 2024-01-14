package com.example.petcare.data.dto.Calorie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalorieCatDTO {
    private String state;       //성장기 or 성묘
    private float protein;     //조단백질
    private float province;    //조지방
    private float fiber;       //조섬유
    private float ash;         //조회분
    private float calcium;     //칼슘
    private float phosphorus;  //인
    private float moisture;    //수분
    private float taurine;     //타우린
}
