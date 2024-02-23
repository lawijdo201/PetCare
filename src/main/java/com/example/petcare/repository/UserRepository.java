package com.example.petcare.repository;

import com.example.petcare.entity.UserCareService;
import com.example.petcare.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);
    @Query("SELECT care FROM UserCareService care " +
            "JOIN care.userEntity user " +
            "WHERE user.username LIKE %:keyword%")
    Page<UserCareService> findByUsernameInUserCareService(@Param("keyword") String keyword, Pageable pageable);
}
