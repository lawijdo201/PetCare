package com.example.petcare.service;


import com.example.petcare.data.dto.UserDTO;

public interface UserService {
    boolean join(UserDTO userDTO);
    boolean login(UserDTO userDTO);
}