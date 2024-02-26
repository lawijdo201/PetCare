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
    //어느 페이지마다 로그인시 SecurityContextHolder에서 사용자 아이디를 가져와서 model에 추가하는 코드가 반복되어
    //스프링aop를 활용해보았습니다.
    /*Controller마다 세션으로부터 사용자 이름 받아와서 Model에 집어넣기*/
    @Before("execution(* com.example.petcare.controller.*.*(..))")
    public void userInfoSet(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs(); // 메게변수 배열 저장
        for (Object arg : args) {           // 메게변수를 반복문으로 순회
            if (arg instanceof Model) {     // Model 타입인지 확인
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                Model model = (Model) arg;
                String id = authentication.getName();
                if (!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser") && !authentication.getAuthorities().isEmpty()) {
                    model.addAttribute("info", id);
                }
                break; // Model을 찾았으므로 반복문 종료
            }
        }
    }
}
