package com.example.petcare.repository;

import com.example.petcare.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    boolean existsByUsernameAndEmail(String Username, String email);

    Optional<UserEntity> findByUsername(String username);
}
