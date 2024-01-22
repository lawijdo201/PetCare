/*
package com.example.petcare.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    public SecurityConfig(AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        */
/*csrf disable*//*

        http
                .csrf((auth)->auth.disable());
        */
/*경로별 인가*//*

        http
                .authorizeHttpRequests(auth ->
                                auth.requestMatchers("/").permitAll()
                                        .requestMatchers("/loginPage/login","loginPage/logindo","/loginPage/join","/loginPage/joindo").permitAll()
                                        .requestMatchers("/community/list","/community/view").permitAll()
                                        .requestMatchers("/calorie","/calorie/feed","/calorie/feed/dog","/calorie/feed/cat").permitAll()
                                        .requestMatchers("/findPet/list","/findPet/view").permitAll()
                                        .requestMatchers("/news/*").permitAll()
                                        .requestMatchers("/loginPage/logout").hasAnyRole("USER","ADMIN")
                                        .anyRequest().authenticated());
        */
/*httpBasic 인증*//*

        http
                .httpBasic((auth) -> auth.disable());

        */
/*form로그인*//*

        http
                .formLogin((formLogin)->{
                    formLogin
                            .loginPage("/loginPage/login")
                            .loginProcessingUrl("/logindo")
                            .permitAll();
                });
        return http.build();
    }
}*/
