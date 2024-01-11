package com.example.petcare.service;

import com.example.petcare.data.dto.NearByBoardDTO;
import com.example.petcare.entity.PetInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FindPetService {
    void saveBoard(PetInfo petInfo, MultipartFile file);
    public List<PetInfo> getBoardList();
    public PetInfo getBoard(Integer id);
    public NearByBoardDTO getNearByBoard(Integer id);

    public void deleteBoard(Integer id);

    public void updateBoard(PetInfo NewPetInfo, MultipartFile file);


}
