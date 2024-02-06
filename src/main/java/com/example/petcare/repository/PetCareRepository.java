package com.example.petcare.repository;

import com.example.petcare.entity.PetCare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PetCareRepository extends JpaRepository<PetCare, String> {
    @Query(value = "SELECT p.role FROM PetCare p, WHERE p.")
    String findRole(@Param("username") String username);

    boolean existsByUsername(String username);
}
