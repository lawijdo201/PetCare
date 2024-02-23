package com.example.petcare.service.PetCareService;

import com.example.petcare.data.dto.Board.NearByBoardDTO;
import com.example.petcare.data.dto.PetCare.GiveCareDTO;
import com.example.petcare.entity.PetCare;
import com.example.petcare.entity.UserCareService;
import com.example.petcare.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PetCareService {
    void saveRole(PetCare petCare);

    void saveBoard();

    Page<UserCareService> getBoardList(Pageable pageable);

    GiveCareDTO getBoard(Integer id);

    String findRole(String username);

    void updateUserCareServiceTime();

    boolean existByusername(String username);

    UserEntity getUserEntity(String username);

    void saveInfo(UserCareService userCareService);

    boolean existByusernameFromUserCareService(String username);

    void removeUserCareService(String username);

    NearByBoardDTO getNearByBoard(Integer id);

}
