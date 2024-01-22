package com.example.petcare.service;

import com.example.petcare.data.dto.Board.BoardDTO;
import com.example.petcare.entity.Board;
import com.example.petcare.data.dto.NearByBoardDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {
    void saveBoard(BoardDTO boardDTO);
    public List<Board> getBoardList();
    public Board getBoard(Integer id);
    public NearByBoardDTO getNearByBoard(Integer id);

    public void deleteBoard(Integer id);

    public void updateBoard(BoardDTO NewBoard);

}
