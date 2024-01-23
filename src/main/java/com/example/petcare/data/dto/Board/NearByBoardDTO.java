package com.example.petcare.data.dto.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//인덱스로 구현
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NearByBoardDTO {
    private int id;
    private int bid;
    private int aid;
}
