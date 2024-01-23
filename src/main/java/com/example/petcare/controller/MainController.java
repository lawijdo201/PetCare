package com.example.petcare.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
public class MainController {
    @GetMapping("/")
    public String main(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        if (authentication != null && !authentication.getAuthorities().isEmpty()) {
            System.out.println("??");
            model.addAttribute("info", id);
        }
        return "index";
    }

    @RequestMapping("/main")
    public String test(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        //String auth = authentication.getAuthorities().iterator().next().getAuthority();
        if (authentication != null && !authentication.getAuthorities().isEmpty()) {
            System.out.println("??");
            model.addAttribute("info", id);
            //model.addAttribute("info", auth);
        }
        return "index";
    }
}