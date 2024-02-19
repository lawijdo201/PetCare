package com.example.petcare.service.PetCareService.Impl;

import com.example.petcare.data.dao.PetCareDAO;
import com.example.petcare.entity.PetCare;
import com.example.petcare.entity.UserCareService;
import com.example.petcare.entity.UserEntity;
import com.example.petcare.repository.UserRepository;
import com.example.petcare.service.PetCareService.PetCareService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetCareServiceImpl implements PetCareService {
    private final PetCareDAO petCareDAO;
    private final UserRepository userRepository;

    public PetCareServiceImpl(PetCareDAO petCareDAO, UserRepository userRepository) {
        this.petCareDAO = petCareDAO;
        this.userRepository = userRepository;
    }

    @Override
    public void saveRole(PetCare petCare) {
        petCareDAO.saveMember(petCare);
    }

    @Override
    public String findRole(String username) {
        String role = petCareDAO.findRole(username);
        return role;
    }

    @Override
    public boolean existByusername(String username){
        return petCareDAO.existByusername(username);
    }

    @Override
    public UserEntity getUserEntity(String username) {
        return userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get();
    }

    @Override
    public void saveInfo(UserCareService userCareService) {
        petCareDAO.savePetCare(userCareService);
    }

    @Override
    public boolean existByusernameFromUserCareService(String username){
        return petCareDAO.existByUsernameFromUserCareService(username);
    }

    @Override
    public List<UserCareService> getAllBoardToMain() {
        return petCareDAO.findUserCareService();
    }
}
