package com.example.petcare.data.dao;

import com.example.petcare.data.dto.Board.NearByBoardDTO;
import com.example.petcare.entity.PetInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FindPetDAO {
    void write(PetInfo petInfo);
    Page<PetInfo> getBoardList(Pageable pageable);
    PetInfo getBoard(Integer id);

    NearByBoardDTO getNearByBoard(Integer id);
    void deleteBoard(Integer id);

}
