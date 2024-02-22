package com.example.petcare.service.User.Impl;

import com.example.petcare.data.dao.UserDAO;
import com.example.petcare.data.dto.User.CustomUserDetails;
import com.example.petcare.data.dto.User.Join_Provider;
import com.example.petcare.data.dto.User.UserDTO;
import com.example.petcare.entity.UserEntity;
import com.example.petcare.service.User.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserDAO userDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDAO = userDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    //아이디 중복 체크
    @Override
    public boolean existID(String id) {
        return userDAO.existsByUsername(id);
    }

    //이메일 체크
    @Override
    public boolean existEmail(String email) {
        System.out.println(email);
        //1. 이메일 형식 확인
        UserDTO userDTO = UserDTO.builder()
                .email(email).build();
        System.out.println(userDTO.getEmail());

        //2. 이메일이 존재하는지 확인
        return userDAO.existsByEmail(email);
    }

    @Override
    @Transactional
    public void modify(String username) {
        UserEntity userEntity = userDAO.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        userEntity.setUsername(username);
        UserDetails userDetails = new CustomUserDetails(userEntity);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public void join(UserDTO userDTO) {

        //회원가입
        UserEntity userEntity = UserEntity.builder()
                .username(userDTO.getUsername())
                .pw(bCryptPasswordEncoder.encode(userDTO.getPw()))
                .email(userDTO.getEmail())
                .role("ROLE_USER")
                .joinProvider(Join_Provider.Basic)
                .build();
        userDAO.saveMember(userEntity);
        log.info("{} 아이디에 대해 회원가입이 완료되었습니다.", userDTO.getUsername());
    }


}
