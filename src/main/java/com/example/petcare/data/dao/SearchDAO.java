package com.example.petcare.data.dao;

import com.example.petcare.entity.Board;
import com.example.petcare.entity.PetInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface SearchDAO {
    Page<Board> SearchBoardList(Pageable pageable);
    Page<PetInfo> SearchPetList(Pageable pageable);
}
