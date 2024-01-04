package com.example.petcare.data.dto.Calorie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class resultCalorieDTO {
    private String province;   //조지방 함유량
    private String protein;   //조지방 함유량
    private String vi;   //인, 칼슘 함유량
}
