package com.example.petcare.data.dao;

import com.example.petcare.data.dto.NearByBoardDTO;
import com.example.petcare.entity.PetInfo;

import java.util.List;

public interface FindPetDAO {
    void write(PetInfo petInfo);
    public List<PetInfo> getBoardList();
    public PetInfo getBoard(Integer id);

    public NearByBoardDTO getNearByBoard(Integer id);
    void deleteBoard(Integer id);
}
