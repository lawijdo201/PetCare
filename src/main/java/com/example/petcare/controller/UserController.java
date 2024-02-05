package com.example.petcare.controller;

import com.example.petcare.data.dto.User.UserDTO;
import com.example.petcare.service.User.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/info")
    public String info(){
        return "UserInfo";
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
    public String join(@Valid UserDTO userDTO, BindingResult bindingResult, Model model) {
        //1. 유효성 검사에 실패한 경우
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : errors) {
                model.addAttribute(error.getField(), error.getDefaultMessage());
                System.out.println("Field: " + error.getField());
                System.out.println("Message: " + error.getDefaultMessage());
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            System.out.println("redirect");
            return "joinPage";
        }

        //2. 회원가입
       if (userService.join(userDTO)) {
            return "login";
        }

        return "redirect:/";
    }

    @PostMapping("/checkDuplicateUsername")
    @ResponseBody
    public boolean duplicatiedUsername(@RequestParam("checkData") String value) {
        return userService.existID(value);
    }

    @PostMapping("/checkDuplicateEmail")
    @ResponseBody
    public boolean duplicatiedEmail(@RequestParam("checkData") String value) {
        return userService.existEmail(value);
    }
}
