package com.example.petcare.service.Search;

import com.example.petcare.data.dto.Search.SearchDTO;
import com.example.petcare.entity.Board;
import com.example.petcare.entity.PetInfo;
import com.example.petcare.entity.UserCareService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchService {
    Page<UserCareService> SearchCareAddress(String keyword, Pageable pageable);
    //main 작성자 검색
    Page<UserCareService> SearchCareUsername(String keyword, Pageable pageable);


    Page<PetInfo> SearchPetCareContent(String keyword, Pageable pageable);

    Page<PetInfo> SearchPetCareUsername(String keyword, Pageable pageable);

    //팻 찾기 제목 + 본문 검색
    Page<Board> SearchBoardContent(String keyword, Pageable pageable);

    //팻 찾기 사용자 검색
    Page<Board> SearchBoardUsername(String keyword, Pageable pageable);
}
