package com.example.petcare.data.dao;

import com.example.petcare.entity.Board;
import com.example.petcare.data.dto.Board.NearByBoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardDAO {
    void write(Board board);
    Page<Board> getBoardList(Pageable pageable);
    Board getBoard(Integer id);

    NearByBoardDTO getNearByBoard(Integer id);
    void deleteBoard(Integer id);

}
