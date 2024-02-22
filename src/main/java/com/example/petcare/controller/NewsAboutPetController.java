package com.example.petcare.controller;

import org.springframework.ui.Model;
import com.example.petcare.service.News.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/news")
public class NewsAboutPetController {
    private final NewsService newsService;

    public NewsAboutPetController(NewsService newsService) {
        this.newsService = newsService;
    }

    //강아지 관련 뉴스
    @GetMapping("/dog")
    public String newsDog(Model model) {
        model.addAttribute("NewsDTO",newsService.newsDog());
        return "news";
    }

    //고양이 관련 뉴스
    @GetMapping("/cat")
    public String newsCat(Model model) {
        model.addAttribute("NewsDTO",newsService.newsCat());
        return "news";
    }

    //검색 뉴스
    @GetMapping("/search")
    public String newsAll(Model model, @RequestParam String newsName) {
        model.addAttribute("NewsDTO", newsService.newsSearch(newsName));
        return "news";
    }
}
