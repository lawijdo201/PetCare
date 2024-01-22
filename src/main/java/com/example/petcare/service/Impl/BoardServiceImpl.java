package com.example.petcare.service.Impl;

import com.example.petcare.data.dao.BoardDAO;
import com.example.petcare.data.dto.Board.BoardDTO;
import com.example.petcare.entity.Board;
import com.example.petcare.data.dto.NearByBoardDTO;
import com.example.petcare.service.BoardService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardServiceImpl implements BoardService {
    private BoardDAO boardDAO;

    public BoardServiceImpl(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    //글 저장
    public void saveBoard(BoardDTO boardDTO) {
        Board board = Board.builder()
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .build();
        boardDAO.write(board);
    }

    //글 목록 불러오기
    public List<Board> getBoardList() {
        return boardDAO.getBoardList();
    }

    //글 내용 불러오기
    public Board getBoard(Integer id){
        return boardDAO.getBoard(id);
    }

    //이전글과 다음글 PK 불러오기
    public NearByBoardDTO getNearByBoard(Integer id){return boardDAO.getNearByBoard(id);}

    //글 삭제
    public void deleteBoard(Integer id) {
        boardDAO.deleteBoard(id);
    }

    //글 업데이트
    @Transactional
    public void updateBoard(BoardDTO boardDTO){

        //DTO -> Entity
        Board NewBoard = Board.builder()
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .build();

        //Entity를 영속상태로 가져옴
        Board OldBoard = boardDAO.getBoard(NewBoard.getId());
        System.out.println("기존의 Board");
        System.out.println(OldBoard.getId());
        System.out.println(OldBoard.getTitle());
        System.out.println(OldBoard.getContent());

        System.out.println("새로운 Board");
        System.out.println(NewBoard.getId());
        System.out.println(NewBoard.getTitle());
        System.out.println(NewBoard.getContent());

        //Dirty Checking
        OldBoard.setTitle(NewBoard.getTitle());
        OldBoard.setContent(NewBoard.getContent());
    }
}
