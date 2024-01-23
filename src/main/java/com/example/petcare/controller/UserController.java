package com.example.petcare.controller;

import com.example.petcare.data.dto.User.UserDTO;
import com.example.petcare.service.User.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/";
    }

    @GetMapping("/join")
    public String joinPage() {
        return "joinPage";
    }
    @PostMapping("/joindo")
    public String join(UserDTO userDTO) {
        log.info("username:" + userDTO.getUsername());
        log.info("email:" + userDTO.getEmail());
        log.info("pw:" + userDTO.getPw());

        //1. 회원가입
        if (userService.join(userDTO)) {
            return "login";
        }

        return "joinFalse";
    }
}
