package com.example.petcare.service.FindPet;

import com.example.petcare.data.dto.Board.NearByBoardDTO;
import com.example.petcare.data.dto.PetInfo.PetDTO;
import com.example.petcare.entity.PetInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface FindPetService {
    void saveBoard(PetDTO petDTO, MultipartFile file);
    public Page<PetInfo> getBoardList(Pageable pageable);
    public PetInfo getBoard(Integer id);
    public NearByBoardDTO getNearByBoard(Integer id);

    public void deleteBoard(Integer id);

    public void updateBoard(PetInfo NewPetInfo, MultipartFile file);


}
