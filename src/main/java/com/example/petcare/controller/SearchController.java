package com.example.petcare.controller;

import com.example.petcare.data.dto.Search.SearchDTO;
import com.example.petcare.service.Search.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    //검색 URL
    @GetMapping("/search")
    public String Search(@RequestParam("keyword") String keyword, Model model) {
        SearchDTO searchDTO = searchService.SearchBoard(keyword);
        log.info(searchDTO.getBoardList().toString());
        model.addAttribute("board", searchDTO.getBoardList());
        model.addAttribute("petinfo", searchDTO.getPetInfoList());
        model.addAttribute("care", searchDTO.getUserCareService());
        return "SearchAll";
    }

    //메인페이지 검색
    @GetMapping("/search/care")
    public String SearchByCare(String keyword, String search, Model model) {
        //주소 검색
        if (search.equals("address")) {
            SearchDTO searchDTO = searchService.SearchCareAddress(keyword);
            model.addAttribute("GiveCare", searchDTO.getUserCareService());
            return "index";
            //작성자 검색
        } else if (search.equals("username")) {
            SearchDTO searchDTO = searchService.SearchCareUsername(keyword);
            model.addAttribute("GiveCare", searchDTO.getUserCareService());
            return "index";
            //전체 검색
        } else {
            return "redirect:/";
        }
    }

    //동물 찾기 검색
    //견종, 제목 + 본문, 작성자
}
