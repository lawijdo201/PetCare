package com.example.petcare.repository;

import com.example.petcare.data.dto.Board.NearByBoardDTO;
import com.example.petcare.entity.UserCareService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserCareServiceRepository extends JpaRepository<UserCareService, Integer> {

    @EntityGraph(attributePaths = {"day"})
    @Override
    Optional<UserCareService> findById(Integer id);

    @Query(value = "SELECT new com.example.petcare.data.dto.Board.NearByBoardDTO(b.id, COALESCE((SELECT MAX(b2.id) FROM UserCareService b2 WHERE b2.id < b.id),0), COALESCE((SELECT MIN(b3.id) FROM UserCareService b3 WHERE b3.id > b.id),9999999)) FROM UserCareService b WHERE b.id = :pk")
    NearByBoardDTO selectIdWithBidAndAid(@Param("pk") Integer pk);

    Page<UserCareService> findByAddressContainingOrDetailAddressContaining(String keyword1, String keyword2, Pageable pageable);
}
