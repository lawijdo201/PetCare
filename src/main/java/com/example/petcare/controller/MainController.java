package com.example.petcare.controller;
import com.example.petcare.data.dto.AlertMessage.AlertDTO;
import com.example.petcare.data.dto.PetCare.GiveCareDTO;
import com.example.petcare.data.dto.PetCare.RoleDTO;
import com.example.petcare.entity.PetCare;
import com.example.petcare.entity.UserCareService;
import com.example.petcare.entity.UserEntity;
import com.example.petcare.service.PetCareService.PetCareService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@Slf4j
public class MainController {
    private final PetCareService petCareService;

    public MainController(PetCareService petCareService) {
        this.petCareService = petCareService;
    }

    @GetMapping("/")
    public String main(Model model) {
        List<UserCareService> MainBoardList = petCareService.getAllBoardToMain();
        System.out.println(MainBoardList);
        model.addAttribute("GiveCare", MainBoardList);
        return "index";
    }

    @GetMapping("/main")
    public String test(Model model) {
        return "index";
    }

    /*@GetMapping("/choose")
    public String chooseRole(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //세션의 사용자 이름이 역활 지정이 안되있으면 역활 지정 페이지로 이동
        if (!petCareService.existByusername(username)) {
            return "PetCareRole";
        }

        //역활 지정이 되있다면
        else if (petCareService.findRole(username).equals("petowner")) {
            //역활이 돌봄을 필요로 하는거라면

            return "CareReceive";
            //아니면 역활이 돌봐주는 거라면
        } else {
            //사용자 설정에서 이미 아이 돌봄 추가 정보 입력을 하였다면
            if (petCareService.existByusernameFromUserCareService(username)) {
                AlertDTO alertDTO = new AlertDTO("기존 설정된 돌봄세팅이 3시간동안 업로드 됩니다.","upload");
                model.addAttribute("Message", alertDTO);
                return "Alert";

                //사용자 설정에서 아이 돌봄 추가 정보 입력을 안했다면
            } else {
                return "CareGive";
            }
        }
    }


    @PostMapping("/choose")
    public String chooseRoleDo(RoleDTO roleDTO, Model model) {
        //내 설정 기억하기 채크시 db에 저장
        if (roleDTO.getRemember() != null) {
            roleDTO.getRole();
            System.out.println("checked");

            PetCare petCare = PetCare.builder()
                    //.username(SecurityContextHolder.getContext().getAuthentication().getName())
                    .role(roleDTO.getRole())
                    .build();
            petCareService.saveRole(petCare);
        }

        //역활이 돌봄을 필요로 하는거라면
        else if (roleDTO.getRole().equals("petowner")) {
            log.info(roleDTO.getRole());
            return "CareReceive";
            //역활이 돌봐주는거라면
        } else {
            log.info(roleDTO.getRole());
            return "CareGive";
        }
        return "redirect:/";
    }

    @PostMapping("/giveCare")
    public String giveCare(Model model, @Valid GiveCareDTO giveCareDTO, BindingResult bindingResult) {
        //1. 유효값 애러일 경우
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : errors) {
                model.addAttribute(error.getField(), error.getDefaultMessage());
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return "CareGive";
        }
        log.info(giveCareDTO.getWalk());        //null or True
        log.info(giveCareDTO.getIdcard());

        //2. db에 저장
        UserCareService userCareService = UserCareService.builder()
                //.username(SecurityContextHolder.getContext().getAuthentication().getName())
                .day(giveCareDTO.getDay())
                .walk(giveCareDTO.getWalk())
                .idcard(giveCareDTO.getIdcard())
                .homecam(giveCareDTO.getHomecam())
                .meeting(giveCareDTO.getMeeting())
                .care(giveCareDTO.getCare())
                .content(giveCareDTO.getContent())
                .price_perHour(giveCareDTO.getPrice_perHour())
                .price_walk1(giveCareDTO.getPrice_walk1())
                .price_walk2(giveCareDTO.getPrice_walk2())
                .price_walk3(giveCareDTO.getPrice_walk3())
                .price_walk4(giveCareDTO.getPrice_walk4())
                .postcode(giveCareDTO.getPostcode())
                .address(giveCareDTO.getAddress())
                .detailAddress(giveCareDTO.getDetailAddress())
                .build();

        petCareService.saveInfo(userCareService);
        return "redirect:/";
    }

    @GetMapping("/upload")
    public String upload(Model model) {
        *//*글 업로드*//*



        return "UserInfo";
    }*/
}