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
    private String protein;     //조단백질
    private String province;    //조지방
    private String fiber;       //조섬유
    private String ash;         //조회분
    private String calcium;     //칼슘
    private String phosphorus;  //인
    private String moisture;    //수분
    private String taurine;     //타우린
}
