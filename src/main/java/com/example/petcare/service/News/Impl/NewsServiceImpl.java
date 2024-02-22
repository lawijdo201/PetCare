package com.example.petcare.service.News.Impl;

import com.example.petcare.data.dto.News.NewsDTO;
import com.example.petcare.service.News.NewsService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class NewsServiceImpl implements NewsService {

    @Value("${search.api.id}")
    String searchId;

    @Value("${search.api.key}")
    String searchKey;


    //강아지 DTO객체 받아오기
    @Override
    public List<NewsDTO> newsDog() {
        List<NewsDTO> newsDTO = new ArrayList<>();
        String result = conectNaverAPI("강아지");
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(result);
            JSONArray items = (JSONArray) jsonObject.get("items");
            for (Object o : items) {
                newsDTO.add(NewsDTO.builder().title(((JSONObject) o).get("title").toString())
                        .date(((JSONObject) o).get("pubDate").toString())
                        .content(((JSONObject) o).get("description").toString().substring(0,Math.min(60,((JSONObject) o).get("description").toString().length()))+"...")
                        .link(((JSONObject) o).get("originallink").toString())
                        .build());
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return newsDTO;
    }

    @Override
    public List<NewsDTO> newsCat() {
        List<NewsDTO> newsDTO = new ArrayList<>();
        String result = conectNaverAPI("고양이");
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(result);
            JSONArray items = (JSONArray) jsonObject.get("items");
            for (Object o : items) {
                newsDTO.add(NewsDTO.builder().title(((JSONObject) o).get("title").toString())
                        .date(((JSONObject) o).get("pubDate").toString())
                        .content(((JSONObject) o).get("description").toString())
                        .link(((JSONObject) o).get("originallink").toString())
                        .build());
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return newsDTO;
    }

    @Override
    public List<NewsDTO> newsSearch(String title) {
        List<NewsDTO> newsDTO = new ArrayList<>();
        String result = conectNaverAPI(title);
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(result);
            JSONArray items = (JSONArray) jsonObject.get("items");
            System.out.println(items);
            for (Object o : items) {
                newsDTO.add(NewsDTO.builder().title(((JSONObject) o).get("title").toString())
                        .date(((JSONObject) o).get("pubDate").toString())
                        .content(((JSONObject) o).get("description").toString())
                        .link(((JSONObject) o).get("originallink").toString())
                        .build());
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return newsDTO;
    }

    private String conectNaverAPI(String Data) {
            String encodedData = URLEncoder.encode(Data, StandardCharsets.UTF_8);
            String result = WebClient.builder()
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultHeader("X-Naver-Client-Id", searchId)
                    .defaultHeader("X-Naver-Client-Secret", searchKey)
                    .build()
                    .get()
                    .uri(uriBuilder -> {
                        UriComponents uri = UriComponentsBuilder.newInstance()
                                .scheme("https")
                                .host("openapi.naver.com")
                                .path("/v1/search/news.json")
                                .queryParam("query", encodedData)
                                .queryParam("display",40)
                                .build(true);
                        return uri.toUri();
                    })
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            return result;
    }
}
