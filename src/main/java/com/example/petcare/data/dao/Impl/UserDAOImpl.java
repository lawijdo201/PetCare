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
    public boolean findMember(String name) {
        return false;
    }

    @Override
    public void deleteMember(String id) {

    }

    @Override
    public void updateMember(String id, String pw, String email) {

    }

    @Override
    public boolean existsByUsernameAndEmail(String username, String email) {
        if (userRepository.existsByUsernameAndEmail(username, email)) {
            return false;
        }
        return true;
    }

    @Override
    public void updateUserPassword(String pw, String id) {

    }
}
