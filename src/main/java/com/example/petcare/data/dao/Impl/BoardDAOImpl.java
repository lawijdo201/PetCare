package com.example.petcare.data.dao.Impl;

import com.example.petcare.data.dao.BoardDAO;
import com.example.petcare.entity.Board;
import com.example.petcare.data.dto.Board.NearByBoardDTO;
import com.example.petcare.mapper.BoardMapper;
import com.example.petcare.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardDAOImpl implements BoardDAO {
    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    public BoardDAOImpl(BoardRepository boardRepository, BoardMapper boardMapper) {
        this.boardRepository = boardRepository;
        this.boardMapper = boardMapper;
    }

    public void write(Board board) {
        boardRepository.save(board);
    }
    public Page<Board> getBoardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Override
    public Board getBoard(Integer id) {
        return boardRepository.findById(id).get();
    }

    @Override
    public NearByBoardDTO getNearByBoard(Integer id) {
        return boardMapper.selectIdWithBidAndAid(id);
        //return boardRepository.selectIdWithBidAndAid(id);
    }

    @Override
    public void deleteBoard(Integer id) {
        boardRepository.deleteById(id);
    }

}
