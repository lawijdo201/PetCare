package com.example.petcare.controller;

import com.example.petcare.data.dto.Search.SearchDTO;
import com.example.petcare.service.Search.SearchService;
import com.example.petcare.entity.Board;
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

    @GetMapping("/search")
    public String Search(@RequestParam("keyword") String keyword, Model model) {
        SearchDTO searchDTO = searchService.SearchBoard(keyword);
        log.info(searchDTO.getBoardList().toString());
        model.addAttribute("board", searchDTO.getBoardList());
        model.addAttribute("petinfo", searchDTO.getPetInfoList());
        return "SearchAll";
    }
}
