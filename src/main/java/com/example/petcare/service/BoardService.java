package com.example.petcare.service;

import com.example.petcare.entity.Board;
import com.example.petcare.data.dto.NearByBoardDTO;

import java.util.List;

public interface BoardService {
    void saveBoard(Board board);
    public List<Board> getBoardList();
    public Board getBoard(Integer id);
    public NearByBoardDTO getNearByBoard(Integer id);

    public void deleteBoard(Integer id);

    public void updateBoard(Board NewBoard);

}
