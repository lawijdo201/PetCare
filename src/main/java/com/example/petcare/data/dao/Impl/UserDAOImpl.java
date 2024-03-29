package com.example.petcare.data.dao.Impl;

import com.example.petcare.data.dao.UserDAO;
import com.example.petcare.entity.UserEntity;
import com.example.petcare.repository.UserRepository;
import org.springframework.stereotype.Service;

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
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username).get();
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
