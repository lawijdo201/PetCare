package com.example.petcare.repository;

import com.example.petcare.data.dto.Board.NearByBoardDTO;
import com.example.petcare.entity.PetInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface FindPetRepository extends JpaRepository<PetInfo, Integer> {
    @Query(value = "SELECT new com.example.petcare.data.dto.Board.NearByBoardDTO(b.id, COALESCE((SELECT MAX(b2.id) FROM PetInfo b2 WHERE b2.id < b.id),0), COALESCE((SELECT MIN(b3.id) FROM PetInfo b3 WHERE b3.id > b.id),9999999)) FROM PetInfo b WHERE b.id = :pk")
    NearByBoardDTO selectIdWithBidAndAid(@Param("pk") Integer pk);
    List<PetInfo> findByTitleContaining(String keyword);

}
