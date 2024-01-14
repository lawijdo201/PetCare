package com.example.petcare.data.dto.Calorie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResertCalorieCatDTO {
    private float protein;     //조단백질 *
    private float province;    //조지방    *
    private float calcium;     //칼슘 *
    private float phosphorus;  //인  *
    private float carbohydrate; //탄수화물
    private String proteinCHECK;     //조단백질 충족여부  충족 true 미충족 false
    private String provinceCHECK;    //조지방 충족여부
    private String calciumCHECK;     //칼슘 충족여부
    private String phosphorusCHECK;  //인 충족여부
    private String taurineCHECK;     //타우린 충족여부
}
