package com.example.petcare.data.dao.Impl;

import com.example.petcare.data.dao.FindPetDAO;
import com.example.petcare.data.dto.Board.NearByBoardDTO;
import com.example.petcare.entity.PetInfo;
import com.example.petcare.repository.FindPetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FindPetDAOImpl implements FindPetDAO {
    private final FindPetRepository findPetRepository;

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
    public Page<PetInfo> getBoardList(Pageable pageable) {
        return findPetRepository.findAll(pageable);
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
