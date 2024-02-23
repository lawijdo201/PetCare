package com.example.petcare.data.dao;

import com.example.petcare.data.dto.Board.NearByBoardDTO;
import com.example.petcare.entity.PetCare;
import com.example.petcare.entity.UserCareService;
import com.example.petcare.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PetCareDAO {
    Page<UserCareService> getBoardList(Pageable pageable);

    UserCareService getBoard(Integer id);

    void saveMember(PetCare petCare);

    String findRole(String username);

    boolean existByusername(String username);

    void savePetCare(UserCareService userCareService);

    boolean existByUsernameFromUserCareService(String username);

    void deleteById(Integer id);

    NearByBoardDTO getNearByBoard(Integer id);
}
