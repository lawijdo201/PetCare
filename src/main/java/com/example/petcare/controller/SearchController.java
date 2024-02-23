package com.example.petcare.controller;

import com.example.petcare.data.dto.Search.SearchDTO;
import com.example.petcare.entity.Board;
import com.example.petcare.entity.PetInfo;
import com.example.petcare.entity.UserCareService;
import com.example.petcare.service.Search.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }


    //메인페이지 검색
    @GetMapping("/search/care")
    public String SearchByCare(String keyword, String search,
                               @PageableDefault(page = 0, size = 10, sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable,
                               Model model) {
        Page<UserCareService> list;
        //주소 검색
        if (search.equals("address")) {
            list = searchService.SearchCareAddress(keyword, pageable);
            //작성자 검색
        } else if (search.equals("username")) {
            list = searchService.SearchCareUsername(keyword ,pageable);
            //전체 검색
        } else {
            return "redirect:/";
        }
        int CurrentPage = pageable.getPageNumber();

        List<Integer> num = new ArrayList<>();
        Map<String, Object> page = new HashMap<>();
        page.put("num", num);
        for (int i = CurrentPage-CurrentPage%10; i < Math.min(CurrentPage-CurrentPage%10+10,list.getTotalPages()); i++) {
            num.add(i);
        }
        page.put("CurrentPage", CurrentPage);

        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());
        model.addAttribute("list", list);
        model.addAttribute("page", page);

        return "index";
    }

    //동물 찾기 검색
    //제목 + 본문, 작성자
    @GetMapping("/search/petCare")
    public String SearchByPetCare(String keyword, String search,
                                  @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                  Model model) {
        Page<PetInfo> list;
        //제목 + 본문 검색
        if (search.equals("content")) {
            list = searchService.SearchPetCareContent(keyword, pageable);
            //작성자 검색
        } else if (search.equals("username")) {
            list = searchService.SearchPetCareUsername(keyword, pageable);
            //전체 검색
        } else {
            return "redirect:/findPet/list";
        }
        int CurrentPage = pageable.getPageNumber();

        List<Integer> num = new ArrayList<>();
        Map<String, Object> page = new HashMap<>();
        page.put("num", num);
        for (int i = CurrentPage-CurrentPage%10; i < Math.min(CurrentPage-CurrentPage%10+10,list.getTotalPages()); i++) {
            num.add(i);
        }
        page.put("CurrentPage", CurrentPage);

        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        return "FindPetMain";
    }


    //커뮤니티 검색
    //제목 + 본문, 작성자
    @GetMapping("/search/board")
    public String SearchByBoard(String keyword, String search,
                                @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                Model model) {

        Page<Board> list;
        //제목 + 본문 검색
        if (search.equals("content")) {
            list = searchService.SearchBoardContent(keyword, pageable);
            //작성자 검색
        } else if (search.equals("username")) {
            list = searchService.SearchBoardUsername(keyword, pageable);
            //전체 검색
        } else {
            return "redirect:/community/list";
        }
        int CurrentPage = pageable.getPageNumber();

        List<Integer> num = new ArrayList<>();
        Map<String, Object> page = new HashMap<>();
        page.put("num", num);
        for (int i = CurrentPage-CurrentPage%10; i < Math.min(CurrentPage-CurrentPage%10+10,list.getTotalPages()); i++) {
            num.add(i);
        }
        page.put("CurrentPage", CurrentPage);

        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        return "community";
    }
}
