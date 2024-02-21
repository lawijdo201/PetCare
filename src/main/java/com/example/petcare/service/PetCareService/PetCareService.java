package com.example.petcare.service.PetCareService;

import com.example.petcare.data.dto.PetCare.GiveCareDTO;
import com.example.petcare.entity.PetCare;
import com.example.petcare.entity.UserCareService;
import com.example.petcare.entity.UserEntity;

import java.util.List;

public interface PetCareService {
    void saveRole(PetCare petCare);

    void saveBoard();

    List<UserCareService> getBoardList();

    GiveCareDTO getBoard(Integer id);

    String findRole(String username);

    boolean existByusername(String username);

    UserEntity getUserEntity(String username);

    void saveInfo(UserCareService userCareService);

    boolean existByusernameFromUserCareService(String username);

}
