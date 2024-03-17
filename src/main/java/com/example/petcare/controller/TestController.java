package com.example.petcare.controller;

import com.example.petcare.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    TestService testService;
    @GetMapping("/test/{id}")
    public Board test1(@PathVariable Integer id) {
        return testService.test1(id);

    }
}
