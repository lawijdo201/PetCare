package com.example.petcare.data.dao.Impl;

import com.example.petcare.data.dao.PetCareDAO;

import com.example.petcare.entity.PetCare;
import com.example.petcare.entity.UserCareService;
import com.example.petcare.repository.PetCareRepository;
import com.example.petcare.repository.UserCareServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetCareDAOImpl implements PetCareDAO {
    private final PetCareRepository petCareRepository;
    private final UserCareServiceRepository userCareServiceRepository;

    public PetCareDAOImpl(PetCareRepository petCareRepository, UserCareServiceRepository userCareServiceRepository) {
        this.petCareRepository = petCareRepository;
        this.userCareServiceRepository = userCareServiceRepository;
    }

    @Override
    public void saveMember(PetCare petCare) {
        petCareRepository.save(petCare);
    }

    @Override
    public String findRole(String username) {
        return null;//petCareRepository.findRole(username);

    }

    @Override
    public boolean existByusername(String username) {
        //return petCareRepository.existsByUsername(username);
        return false;
    }

    @Override
    public void savePetCare(UserCareService userCareService) {
        userCareServiceRepository.save(userCareService);
    }

    @Override
    public boolean existByUsernameFromUserCareService(String username) {
        return false;//userCareServiceRepository.existsByUsername(username);
    }

    @Override
    public List<UserCareService> findUserCareService() {
        return userCareServiceRepository.findAll();
    }
}
