package com.example.petcare.data.dao;

import com.example.petcare.entity.PetCare;
import com.example.petcare.entity.UserCareService;
import com.example.petcare.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface PetCareDAO {
    List<UserCareService> getBoardList();

    UserCareService getBoard(Integer id);

    void saveMember(PetCare petCare);

    String findRole(String username);

    boolean existByusername(String username);

    void savePetCare(UserCareService userCareService);

    boolean existByUsernameFromUserCareService(String username);

}
