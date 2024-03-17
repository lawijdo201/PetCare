package com.example.petcare.controller;

import com.example.petcare.entity.Board;
import com.example.petcare.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    BoardMapper boardMapper;

    public Board test1(Integer id) {
        return boardMapper.selectById(id);

    }
}
