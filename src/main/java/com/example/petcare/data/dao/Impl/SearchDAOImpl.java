package com.example.petcare.data.dao.Impl;

import com.example.petcare.data.dao.SearchDAO;
import com.example.petcare.entity.Board;
import com.example.petcare.entity.PetInfo;
import com.example.petcare.repository.BoardRepository;
import com.example.petcare.repository.FindPetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class SearchDAOImpl implements SearchDAO {
    private final BoardRepository boardRepository;
    private final FindPetRepository findPetRepository;

    public SearchDAOImpl(BoardRepository boardRepository, FindPetRepository findPetRepository) {
        this.boardRepository = boardRepository;
        this.findPetRepository = findPetRepository;
    }

    @Override
    public Page<Board> SearchBoardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Override
    public Page<PetInfo> SearchPetList(Pageable pageable) {
        return findPetRepository.findAll(pageable);
    }
}
