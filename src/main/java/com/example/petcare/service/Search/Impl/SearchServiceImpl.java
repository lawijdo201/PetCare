package com.example.petcare.service.Search.Impl;

import com.example.petcare.data.dao.SearchDAO;
import com.example.petcare.data.dto.Search.SearchDTO;
import com.example.petcare.entity.Board;
import com.example.petcare.entity.PetInfo;
import com.example.petcare.service.Search.SearchService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;


@Service
@Slf4j
public class SearchServiceImpl implements SearchService {
    private final SearchDAO searchDAO;

    public SearchServiceImpl(SearchDAO searchDAO) {
        this.searchDAO = searchDAO;
    }

    @Override
    public SearchDTO SearchBoard(String keyword) {
        List<Board> boardList = searchDAO.SearchBoardList(keyword);
        List<PetInfo> petInfoList = searchDAO.SearchPetList(keyword);

        Collections.reverse(boardList);
        Collections.reverse(petInfoList);

        boardList.subList(0, Math.min(10, boardList.size()));
        petInfoList.subList(0, Math.min(10, petInfoList.size()));

        for (Board boards : boardList) {
            boards.setTitle(boards.getTitle().replace(keyword, "<span style=\"color: black; font-weight: bold;\">" + keyword + "</span>"));
        }
        for (PetInfo petInfo : petInfoList) {
            petInfo.getTitle().replace(keyword, "<span style=\"color: black; font-weight: bold;\">" + keyword + "</span>");
        }

        return new SearchDTO(boardList, petInfoList);
    }
}
