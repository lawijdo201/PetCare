package com.example.petcare.data.dao;


import com.example.petcare.entity.UserEntity;

import java.util.Optional;

public interface UserDAO {

    void saveMember(UserEntity userEntity);

    UserEntity findMember(String username);

    void deleteMember(String id);

    void updateMember(String id, String pw, String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);


}
