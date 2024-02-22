package com.example.petcare.data.dto.Search;

import com.example.petcare.entity.PetInfo;
import com.example.petcare.entity.UserCareService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import com.example.petcare.entity.Board;
import lombok.Data;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchDTO {
    private List<Board> boardList;
    private List<PetInfo> petInfoList;
    private List<UserCareService> userCareService;
}
