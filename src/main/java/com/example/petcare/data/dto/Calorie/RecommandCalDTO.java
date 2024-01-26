package com.example.petcare.data.dto.Calorie;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecommandCalDTO {
    private float weight;
    private float stats;
}
