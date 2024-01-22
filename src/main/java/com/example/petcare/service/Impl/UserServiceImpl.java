package com.example.petcare.service.Impl;

import com.example.petcare.data.dao.UserDAO;
import com.example.petcare.data.dto.User.UserDTO;
import com.example.petcare.entity.UserEntity;
import com.example.petcare.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
/*    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserDAO userDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDAO = userDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }*/

    @Override
    public boolean join(UserDTO userDTO) {

        //1. 중복 아이디 및 이메일 체크
        if (!userDAO.existsByUsernameAndEmail(userDTO.getUsername(), userDTO.getEmail())) {
            log.info("{} 아이디에 대해 중복된 아이디가 존재합니다.",userDTO.getUsername());
            return false;
        }

        //2. 회원가입
        UserEntity userEntity = UserEntity.builder()
                .username(userDTO.getUsername())
                //.pw(bCryptPasswordEncoder.encode(userDTO.getPw()))
                .email(userDTO.getEmail())
                .role("ROLE_USER")
                .build();

        userDAO.saveMember(userEntity);
        log.info("{} 아이디에 대해 회원가입이 완료되었습니다.", userDTO.getUsername());
    return true;
    }

    @Override
    public boolean login(UserDTO userDTO) {
        return false;
    }
}
