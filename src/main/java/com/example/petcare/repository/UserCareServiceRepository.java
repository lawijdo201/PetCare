package com.example.petcare.repository;

import com.example.petcare.entity.UserCareService;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCareServiceRepository extends JpaRepository<UserCareService, Integer> {

    @EntityGraph(attributePaths = {"day"})
    @Override
    Optional<UserCareService> findById(Integer id);

    List<UserCareService> findByAddressContainingOrDetailAddressContaining(String keyword1, String keyword2);
}
