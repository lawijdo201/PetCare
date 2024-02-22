package com.example.petcare.service.Search.Impl;

import com.example.petcare.data.dao.SearchDAO;
import com.example.petcare.data.dto.Search.SearchDTO;
import com.example.petcare.entity.Board;
import com.example.petcare.entity.PetInfo;
import com.example.petcare.entity.UserCareService;
import com.example.petcare.repository.UserCareServiceRepository;
import com.example.petcare.repository.UserRepository;
import com.example.petcare.service.Search.SearchService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;


@Service
@Slf4j
public class SearchServiceImpl implements SearchService {
    private final UserCareServiceRepository userCareServiceRepository;
    private final UserRepository userRepository;
    private final SearchDAO searchDAO;

    public SearchServiceImpl(UserCareServiceRepository userCareServiceRepository, UserRepository userRepository, SearchDAO searchDAO) {
        this.userCareServiceRepository = userCareServiceRepository;
        this.userRepository = userRepository;
        this.searchDAO = searchDAO;
    }

    @Override
    public SearchDTO SearchBoard(String keyword) {
        List<Board> boardList = searchDAO.SearchBoardList(keyword);
        List<PetInfo> petInfoList = searchDAO.SearchPetList(keyword);
        List<UserCareService> userCareServicesList = searchDAO.SearchUserCareService(keyword);

        Collections.reverse(boardList);
        Collections.reverse(petInfoList);
        Collections.reverse(userCareServicesList);

        boardList.subList(0, Math.min(10, boardList.size()));
        petInfoList.subList(0, Math.min(10, petInfoList.size()));
        userCareServicesList.subList(0, Math.min(10, userCareServicesList.size()));

        return new SearchDTO(boardList, petInfoList, userCareServicesList);
    }



    //main 주소 검색
    @Override
    public SearchDTO SearchCareAddress(String keyword) {
        List<UserCareService> userCareServicesList = userCareServiceRepository.findByAddressContainingOrDetailAddressContaining(keyword, keyword);
        Collections.reverse(userCareServicesList);
        userCareServicesList.subList(0, Math.min(10, userCareServicesList.size()));
        return SearchDTO.builder().userCareService(userCareServicesList).build();
    }

    //main 작성자 검색
    @Override
    public SearchDTO SearchCareUsername(String keyword) {
        List<UserCareService> userCareServicesList = userRepository.findByUsernameInUserCareService(keyword);
        Collections.reverse(userCareServicesList);
        userCareServicesList.subList(0, Math.min(10, userCareServicesList.size()));
        return SearchDTO.builder().userCareService(userCareServicesList).build();
    }

}
