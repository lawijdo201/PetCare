package com.example.petcare.repository;

import com.example.petcare.entity.Board;
import com.example.petcare.data.dto.NearByBoardDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Integer> {
   @Query(value = "SELECT new com.example.petcare.data.dto.NearByBoardDTO(b.id, COALESCE((SELECT MAX(b2.id) FROM Board b2 WHERE b2.id < b.id),0), COALESCE((SELECT MIN(b3.id) FROM Board b3 WHERE b3.id > b.id),9999999)) FROM Board b WHERE b.id = :pk")
   NearByBoardDTO selectIdWithBidAndAid(@Param("pk") Integer pk);
}