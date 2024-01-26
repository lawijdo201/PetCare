package com.example.petcare.data.dto.Search;

import com.example.petcare.entity.PetInfo;
import lombok.Builder;
import org.springframework.data.domain.Page;
import com.example.petcare.entity.Board;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchDTO {
    private List<Board> boardList;
    private List<PetInfo> petInfoList;

    public SearchDTO(List<Board> boardList, List<PetInfo> petInfoList) {
        this.boardList = boardList;
        this.petInfoList = petInfoList;
    }
}
