package com.example.petcare.data.dao.Impl;

import com.example.petcare.data.dao.PetCareDAO;

import com.example.petcare.data.dto.Board.NearByBoardDTO;
import com.example.petcare.entity.PetCare;
import com.example.petcare.entity.UserCareService;
import com.example.petcare.repository.PetCareRepository;
import com.example.petcare.repository.UserCareServiceRepository;
import com.example.petcare.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<UserCareService> getBoardList(Pageable pageable) {
        return userCareServiceRepository.findAll(pageable);
    }

    @Override
    public UserCareService getBoard(Integer id) {
        return userCareServiceRepository.findById(id).get();
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
    public void deleteById(Integer id) {
        userCareServiceRepository.deleteById(id);
    }

    @Override
    public NearByBoardDTO getNearByBoard(Integer id) {
        return userCareServiceRepository.selectIdWithBidAndAid(id);
    }
}
