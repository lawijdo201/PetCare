package com.example.petcare.service.User;


import com.example.petcare.data.dto.User.UserDTO;

public interface UserService {
    boolean join(UserDTO userDTO);

    boolean existID(String id);

    boolean existEmail(String email);
}