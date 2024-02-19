package com.example.petcare.data.dao;


import com.example.petcare.entity.UserEntity;

public interface UserDAO {
    void saveMember(UserEntity userEntity);

    boolean findMember(String id);

    void deleteMember(String id);

    void updateMember(String id, String pw, String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    void updateUserPassword(String pw, String id);

}
