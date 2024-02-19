package com.example.petcare.repository;

import com.example.petcare.entity.UserCareService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCareServiceRepository extends JpaRepository<UserCareService, Integer> {
/*    boolean existsByUsername(String username);*/
}
