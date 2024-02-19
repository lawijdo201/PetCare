package com.example.petcare.data.dao.Impl;

import com.example.petcare.data.dao.PetCareDAO;

import com.example.petcare.entity.PetCare;
import com.example.petcare.entity.UserCareService;
import com.example.petcare.repository.PetCareRepository;
import com.example.petcare.repository.UserCareServiceRepository;
import com.example.petcare.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetCareDAOImpl implements PetCareDAO {
    private final PetCareRepository petCareRepository;
    private final UserCareServiceRepository userCareServiceRepository;
    private final UserRepository userRepository;

    public PetCareDAOImpl(PetCareRepository petCareRepository, UserCareServiceRepository userCareServiceRepository, UserRepository userRepository) {
        this.petCareRepository = petCareRepository;
        this.userCareServiceRepository = userCareServiceRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveMember(PetCare petCare) {
        petCareRepository.save(petCare);
    }

    @Override
    public String findRole(String username) {
        return petCareRepository.findRole(username);
    }

    @Override
    public boolean existByusername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public void savePetCare(UserCareService userCareService) {
        userCareServiceRepository.save(userCareService);
    }

    @Override
    public boolean existByUsernameFromUserCareService(String username) {
        if (userRepository.findByUsername(username).get().getUserCareService() == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<UserCareService> findUserCareService() {
        return userCareServiceRepository.findAll();
    }
}
