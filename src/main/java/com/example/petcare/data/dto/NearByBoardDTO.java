package com.example.petcare.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NearByBoardDTO {
    private Integer id;
    private int bid;
    private int aid;
}
