package com.example.petcare.data.dao.Impl;

import com.example.petcare.data.dao.BoardDAO;
import com.example.petcare.entity.Board;
import com.example.petcare.data.dto.NearByBoardDTO;
import com.example.petcare.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardDAOImpl implements BoardDAO {
    private BoardRepository boardRepository;
    @Autowired

    public BoardDAOImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void write(Board board) {
        boardRepository.save(board);
    }
    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

    @Override
    public Board getBoard(Integer id) {
        return boardRepository.findById(id).get();
    }

    @Override
    public NearByBoardDTO getNearByBoard(Integer id) {
        return boardRepository.selectIdWithBidAndAid(id);
    }

    @Override
    public void deleteBoard(Integer id) {
        boardRepository.deleteById(id);
    }

}
