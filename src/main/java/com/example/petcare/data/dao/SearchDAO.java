package com.example.petcare.data.dao;

import com.example.petcare.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchDAO {
    Page<Board> getBoardList(Pageable pageable);
}
