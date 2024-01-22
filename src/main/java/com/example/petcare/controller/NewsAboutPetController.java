package com.example.petcare.controller;

import org.springframework.ui.Model;
import com.example.petcare.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/news")
public class NewsAboutPetController {
    private final NewsService newsService;

    public NewsAboutPetController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/dog")
    public String newsDog(Model model) {
        model.addAttribute("NewsDTO",newsService.newsDog());
        return "news";
    }

    @GetMapping("/cat")
    public String newsCat(Model model) {
        model.addAttribute("NewsDTO",newsService.newsCat());
        return "news";
    }

    @GetMapping("/search")
    public String newsAll(Model model, @RequestParam String newsName) {
        model.addAttribute("NewsDTO", newsService.newsSearch(newsName));
        return "news";
    }
}
