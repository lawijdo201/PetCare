package com.example.petcare.data.dao.Impl;

import com.example.petcare.data.dao.SearchDAO;
import com.example.petcare.entity.Board;
import com.example.petcare.entity.PetInfo;
import com.example.petcare.entity.UserCareService;
import com.example.petcare.repository.BoardRepository;
import com.example.petcare.repository.FindPetRepository;
import com.example.petcare.repository.UserCareServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchDAOImpl implements SearchDAO {
    private final BoardRepository boardRepository;
    private final FindPetRepository findPetRepository;
    private final UserCareServiceRepository userCareServiceRepository;

    public SearchDAOImpl(BoardRepository boardRepository, FindPetRepository findPetRepository, UserCareServiceRepository userCareServiceRepository) {
        this.boardRepository = boardRepository;
        this.findPetRepository = findPetRepository;
        this.userCareServiceRepository = userCareServiceRepository;
    }

    @Override
    public List<Board> SearchBoardList(String keyword) {
        return boardRepository.findByTitleContaining(keyword);
    }

    @Override
    public List<PetInfo> SearchPetList(String keyword) {
        return findPetRepository.findByTitleContaining(keyword);
    }

    @Override
    public List<UserCareService> SearchUserCareService(String keyword) {
        return null;
    }

}
