package com.example.petcare.service.Search;

import com.example.petcare.entity.Board;
import com.example.petcare.entity.PetInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchService {
    Page<Board> SearchBoard(Pageable pageable);
    Page<PetInfo> SearchPetBoard(Pageable pageable);
}
