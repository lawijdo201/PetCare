package com.example.petcare.service;

import com.example.petcare.data.dto.NewsDTO;
import org.json.simple.JSONObject;

import java.util.List;

public interface NewsService {
    List<NewsDTO> newsDog();
    List<NewsDTO> newsCat();

    List<NewsDTO> newsSearch(String title);
}
