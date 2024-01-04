package com.example.petcare.data.dao.Impl;

import com.example.petcare.data.dao.UserDAO;
import com.example.petcare.entity.User;
import com.example.petcare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDAOImpl implements UserDAO {
    private final UserRepository userRepository;
    @Autowired
    public UserDAOImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveMember(User user) {
        userRepository.save(user);
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
    public boolean existsByidAndEmail(String id, String email) {
        if (userRepository.existsByidAndEmail(id, email)) {
            return false;
        }
        return true;
    }

    @Override
    public void updateUserPassword(String pw, String id) {

    }
}
