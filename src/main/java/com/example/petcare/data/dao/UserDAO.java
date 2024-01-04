package com.example.petcare.data.dao;


import com.example.petcare.entity.User;

public interface UserDAO {
    public void saveMember(User user);

    boolean findMember(String id);

    void deleteMember(String id);

    void updateMember(String id, String pw, String email);

    boolean existsByidAndEmail(String id, String email);

    void updateUserPassword(String pw, String id);
}
