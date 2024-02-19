package com.example.petcare.service.Board.Impl;

import com.example.petcare.data.dao.BoardDAO;
import com.example.petcare.data.dto.Board.BoardDTO;
import com.example.petcare.entity.Board;
import com.example.petcare.data.dto.Board.NearByBoardDTO;
import com.example.petcare.entity.UserEntity;
import com.example.petcare.repository.UserRepository;
import com.example.petcare.service.Board.BoardService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardDAO boardDAO;
    private final UserRepository userRepository;

    public BoardServiceImpl(BoardDAO boardDAO, UserRepository userRepository) {
        this.boardDAO = boardDAO;
        this.userRepository = userRepository;
    }

    //글 저장
    public void saveBoard(BoardDTO boardDTO, String usesrname) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(usesrname);
        Board board = Board.builder()
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .userEntity(userEntity.get())
                .build();
        boardDAO.write(board);
    }

    //글 목록 불러오기
    public Page<Board> getBoardList(Pageable pageable) {
        return boardDAO.getBoardList(pageable);
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
    public void updateBoard(BoardDTO boardDTO, Integer id){

        //Entity를 영속상태로 가져옴
        Board OldBoard = boardDAO.getBoard(id);

        //Dirty Checking
        OldBoard.setTitle(boardDTO.getTitle());
        OldBoard.setContent(boardDTO.getContent());
    }
}
