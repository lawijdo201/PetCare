package com.example.petcare.service.Search.Impl;

import com.example.petcare.data.dao.SearchDAO;
import com.example.petcare.data.dto.Search.SearchDTO;
import com.example.petcare.entity.Board;
import com.example.petcare.entity.PetInfo;
import com.example.petcare.service.Search.SearchService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {
    private final SearchDAO searchDAO;

    public SearchServiceImpl(SearchDAO searchDAO) {
        this.searchDAO = searchDAO;
    }

    @Override
    public Page<SearchDTO> SearchBoard(Pageable pageable) {
        searchDAO.SearchBoardList(pageable);
        searchDAO.SearchPetList(pageable);
        return null;
    }

}
