package com.example.petcare.service.Search;

import com.example.petcare.data.dto.Search.SearchDTO;
import com.example.petcare.entity.Board;
import com.example.petcare.entity.PetInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchService {
    SearchDTO SearchBoard(String keyword);
    SearchDTO SearchCareAddress(String keyword);

    //main 작성자 검색
    SearchDTO SearchCareUsername(String keyword);
}
