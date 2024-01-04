package com.example.petcare.data.dao;

import com.example.petcare.entity.Board;
import com.example.petcare.data.dto.NearByBoardDTO;

import java.util.List;

public interface BoardDAO {
    void write(Board board);
    public List<Board> getBoardList();
    public Board getBoard(Integer id);

    public NearByBoardDTO getNearByBoard(Integer id);
    void deleteBoard(Integer id);

}
