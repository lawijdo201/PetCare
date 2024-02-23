package com.example.petcare.service.Search.Impl;

import com.example.petcare.data.dto.Search.SearchDTO;
import com.example.petcare.entity.Board;
import com.example.petcare.entity.PetInfo;
import com.example.petcare.entity.UserCareService;
import com.example.petcare.repository.BoardRepository;
import com.example.petcare.repository.FindPetRepository;
import com.example.petcare.repository.UserCareServiceRepository;
import com.example.petcare.repository.UserRepository;
import com.example.petcare.service.Search.SearchService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;


@Service
@Slf4j
public class SearchServiceImpl implements SearchService {
    private final UserCareServiceRepository userCareServiceRepository;
    private final FindPetRepository findPetRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public SearchServiceImpl(UserCareServiceRepository userCareServiceRepository, FindPetRepository findPetRepository, UserRepository userRepository, BoardRepository boardRepository) {
        this.userCareServiceRepository = userCareServiceRepository;
        this.findPetRepository = findPetRepository;
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
    }



    //main 주소 검색
    @Override
    public Page<UserCareService> SearchCareAddress(String keyword, Pageable pageable) {
        return userCareServiceRepository.findByAddressContainingOrDetailAddressContaining(keyword, keyword, pageable);
    }

    //main 작성자 검색
    @Override
    public Page<UserCareService> SearchCareUsername(String keyword, Pageable pageable) {
        return userRepository.findByUsernameInUserCareService(keyword, pageable);
    }

    //팻 찾기 제목 + 본문 검색
    @Override
    public Page<PetInfo> SearchPetCareContent(String keyword, Pageable pageable) {
        return findPetRepository.findByTitleContainingOrContentContaining(keyword, keyword, pageable);
    }

    //팻 찾기 사용자 검색
    @Override
    public Page<PetInfo> SearchPetCareUsername(String keyword, Pageable pageable) {
        return findPetRepository.findByUsernameLikeKeyword(keyword, pageable);
    }

    //커뮤니티 찾기 제목 + 본문 검색
    @Override
    public Page<Board> SearchBoardContent(String keyword, Pageable pageable) {
        return boardRepository.findByTitleContainingOrContentContaining(keyword, keyword,pageable);
    }

    //커뮤니티 찾기 사용자 검색
    @Override
    public Page<Board> SearchBoardUsername(String keyword, Pageable pageable) {
        return boardRepository.findByUsernameLikeKeyword(keyword, pageable);
    }

}
