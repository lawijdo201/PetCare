package com.example.petcare.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Arrays;

@Aspect
@Component
public class UsernameAspect {

    /*Controller마다 세션으로부터 사용자 이름 받아와서 Model에 집어넣기*/
    @Before("execution(* com.example.petcare.controller.*.*(..))")
    public void userInfoSet(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();                          //메게변수 배열 저장
        Arrays.stream(args)                                           //메게변수 stream변환
                .filter(arg -> arg instanceof Model)                  //메게변수 중 Model 필터링
                .findFirst()
                .ifPresent(modelArg -> {
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    Model model = (Model) modelArg;
                    String id = authentication.getName();
                    if (!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser") && !authentication.getAuthorities().isEmpty()) {
                        model.addAttribute("info", id);
                    }
                });
    }
}
