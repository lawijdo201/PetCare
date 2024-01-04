package com.example.petcare.controller;

import com.example.petcare.data.dto.UserDTO;
import com.example.petcare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loginPage")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String loginPage(Model model) {
        return "login";
    }

    @RequestMapping("/login")
    public String login(UserDTO userDTO) {
        userService.login(userDTO);
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(UserDTO userDTO) {
        return "login";
    }

    @RequestMapping("/joinPage")
    public String joinPage(UserDTO userDTO) {
        return "join";
    }
    @RequestMapping("/join")
    public String join(UserDTO userDTO) {
        System.out.println("id:" + userDTO.getId());
        System.out.println("email:" + userDTO.getEmail());
        System.out.println("pw:" + userDTO.getPw());


        //1. 회원가입
        if (userService.join(userDTO)) {
            return "login";
        }

        return "joinFalse";
    }
}
