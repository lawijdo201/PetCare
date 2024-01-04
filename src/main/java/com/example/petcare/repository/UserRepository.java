package com.example.petcare.repository;
import com.example.petcare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String>{
    boolean existsByidAndEmail(String id, String email);
}
