package com.example.petcare.controller;

import com.example.petcare.data.dto.User.UserDTO;
import com.example.petcare.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loginPage")
@Slf4j
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    @PostMapping("/logindo")
    public String login(UserDTO userDTO) {
        userService.login(userDTO);
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(UserDTO userDTO) {
        return "community";
    }

    @GetMapping("/join")
    public String joinPage() {
        log.info("왜안돼");
        return "join";
    }
    @PostMapping("/joindo")
    public String join(UserDTO userDTO) {
        log.info("id:" + userDTO.getUsername());
        log.info("email:" + userDTO.getEmail());
        log.info("pw:" + userDTO.getPw());

        //1. 회원가입
        if (userService.join(userDTO)) {
            return "login";
        }

        return "joinFalse";
    }
}
