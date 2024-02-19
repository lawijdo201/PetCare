package com.example.petcare.controller;

import com.example.petcare.data.dto.PetCare.RoleDTO;
import com.example.petcare.entity.PetCare;
import com.example.petcare.repository.UserRepository;
import com.example.petcare.service.PetCareService.PetCareService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/petcare")
@Slf4j
public class PetCareController {
    private final PetCareService petCareService;
    private final UserRepository userRepository;

    public PetCareController(PetCareService petCareService, UserRepository userRepository) {
        this.petCareService = petCareService;
        this.userRepository = userRepository;
    }

    @GetMapping("/choose")
    public String chooseRole() {
        if (!petCareService.existByusername(SecurityContextHolder.getContext().getAuthentication().getName())) {
            log.info("사용자가 역활기억하기 설정 안했을시");
            return "PetCareRole";
        }
        return "/";
    }
    @PostMapping("/choose")
    public String chooseRoleDo(RoleDTO roleDTO) {
        if (roleDTO.getRemember() != null) {
            log.info("내 설정 기억하기 채크시");
            roleDTO.getRole();
            System.out.println("checked");

            PetCare petCare = PetCare.builder()
                    .userEntity(userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get())
                    .role(roleDTO.getRole())
                    .build();
            petCareService.saveRole(petCare);

        }
        return "/";
    }
}
