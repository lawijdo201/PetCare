package com.example.petcare.service.Impl;

import com.example.petcare.data.dao.UserDAO;
import com.example.petcare.data.dto.UserDTO;
import com.example.petcare.entity.User;
import com.example.petcare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean join(UserDTO userDTO) {

        //1. 중복 아이디 및 이메일 체크
        if (userDAO.existsByidAndEmail(userDTO.getId(), userDTO.getEmail())) {
            return false;
        }
        //2. 회원가입
        else {
            User user = User.builder()
                    .id(userDTO.getId())
                    .pw(userDTO.getPw())
                    .email(userDTO.getEmail())
                    .build();
            userDAO.saveMember(user);
        }
        return true;
    }

    @Override
    public boolean login(UserDTO userDTO) {
        return false;
    }
}
