package com.example.petcare.data.dao.Impl;

import com.example.petcare.data.dao.UserDAO;
import com.example.petcare.entity.UserEntity;
import com.example.petcare.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDAOImpl implements UserDAO {
    private final UserRepository userRepository;

    public UserDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void saveMember(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity findMember(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        return userEntity;
    }

    @Override
    public void deleteMember(String id) {

    }

    @Override
    public void updateMember(String id, String pw, String email) {

    }

    @Override
    public boolean existsByUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            return false;
        }
        return true;
    }
    @Override
    public boolean existsByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            return false;
        }
        return true;
    }

}
