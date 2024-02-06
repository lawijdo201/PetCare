package com.example.petcare.service.PetCareService.Impl;

import com.example.petcare.data.dao.PetCareDAO;
import com.example.petcare.data.dao.UserDAO;
import com.example.petcare.entity.PetCare;
import com.example.petcare.entity.UserCareService;
import com.example.petcare.service.PetCareService.PetCareService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetCareServiceImpl implements PetCareService {
    private final PetCareDAO petCareDAO;
    private final UserDAO userDAO;

    public PetCareServiceImpl(PetCareDAO petCareDAO, UserDAO userDAO) {
        this.petCareDAO = petCareDAO;
        this.userDAO = userDAO;
    }

    @Override
    public void saveRole(PetCare petCare) {
        petCareDAO.saveMember(petCare);
    }

    @Override
    public String findRole(String username) {
        //String role = petCareDAO.findRole(username);
        //return role;
        return "petowner";
    }

    @Override
    public boolean existByusername(String username){
        return userDAO.existsByUsername(username);
    }

    @Override
    public void saveInfo(UserCareService userCareService) {
        petCareDAO.savePetCare(userCareService);
    }

    @Override
    public boolean existByusernameFromUserCareService(String username){
        return false;//petCareDAO.existByUsernameFromUserCareService(username);
    }

    @Override
    public List<UserCareService> getAllBoardToMain() {
        return petCareDAO.findUserCareService();
    }
}
