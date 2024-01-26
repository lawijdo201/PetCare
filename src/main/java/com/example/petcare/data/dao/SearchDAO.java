package com.example.petcare.data.dao;

import com.example.petcare.entity.Board;
import com.example.petcare.entity.PetInfo;


import java.util.List;

public interface SearchDAO {
    List<Board> SearchBoardList(String keyword);
    List<PetInfo> SearchPetList(String keyword);
}
