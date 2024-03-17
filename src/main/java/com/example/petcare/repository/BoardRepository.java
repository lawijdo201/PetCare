package com.example.petcare.repository;

import com.example.petcare.entity.Board;
import com.example.petcare.data.dto.Board.NearByBoardDTO;
import com.example.petcare.entity.PetInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
   Page<Board> findByTitleContainingOrContentContaining(String keyword1, String keyword2, Pageable pageable);

   @Query("SELECT board FROM Board board JOIN board.userEntity userEntity WHERE userEntity.username LIKE %:username%")
   Page<Board> findByUsernameLikeKeyword(@Param("username") String username, Pageable pageable);
}
