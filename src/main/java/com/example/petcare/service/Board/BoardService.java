package com.example.petcare.service.Board;

import com.example.petcare.data.dto.Board.BoardDTO;
import com.example.petcare.entity.Board;
import com.example.petcare.data.dto.Board.NearByBoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    void saveBoard(BoardDTO boardDTO);
    public Page<Board> getBoardList(Pageable pageable);
    public Board getBoard(Integer id);
    public NearByBoardDTO getNearByBoard(Integer id);

    public void deleteBoard(Integer id);

    public void updateBoard(BoardDTO NewBoard);

}
