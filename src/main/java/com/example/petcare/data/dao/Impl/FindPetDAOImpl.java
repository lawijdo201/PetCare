package com.example.petcare.data.dao.Impl;

import com.example.petcare.data.dao.FindPetDAO;
import com.example.petcare.data.dto.NearByBoardDTO;
import com.example.petcare.entity.PetInfo;
import com.example.petcare.repository.FindPetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindPetDAOImpl implements FindPetDAO {
    private final FindPetRepository findPetRepository;

    @Autowired
    public FindPetDAOImpl(FindPetRepository findPetRepository) {
        this.findPetRepository = findPetRepository;
    }

    @Override
    public void write(PetInfo petInfo) {
        System.out.println(petInfo.getTitle());
        System.out.println(petInfo.getContent());
        findPetRepository.save(petInfo);
    }

    @Override
    public List<PetInfo> getBoardList() {
        return findPetRepository.findAll();
    }

    @Override
    public PetInfo getBoard(Integer id) {
        return findPetRepository.findById(id).get();
    }

    @Override
    public NearByBoardDTO getNearByBoard(Integer id) {
        return findPetRepository.selectIdWithBidAndAid(id);
    }

    @Override
    public void deleteBoard(Integer id) {
        findPetRepository.deleteById(id);
    }
}
