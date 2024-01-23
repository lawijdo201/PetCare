package com.example.petcare.service.News;

import com.example.petcare.data.dto.News.NewsDTO;

import java.util.List;

public interface NewsService {
    List<NewsDTO> newsDog();
    List<NewsDTO> newsCat();

    List<NewsDTO> newsSearch(String title);
}
