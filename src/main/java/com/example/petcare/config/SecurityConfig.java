package com.example.petcare.config;

import com.example.petcare.service.User.Impl.CustomOAuth2UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    public SecurityConfig(AuthenticationManagerBuilder authenticationManagerBuilder, CustomOAuth2UserService customOAuth2UserService) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.customOAuth2UserService = customOAuth2UserService;
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public RoleHierarchy roleHierarchy() {

        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();

        hierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER\n" +
                "ROLE_USER > ROLE_Anonymous");

        return hierarchy;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*경로별 인가*/
        http
                .authorizeHttpRequests(auth ->
                                auth.requestMatchers("/").permitAll()
                                        .requestMatchers("/login","/logindo","/join","/joindo").permitAll()
                                        .requestMatchers("/search/**").permitAll()
                                        .requestMatchers("/community/list","/community/view").permitAll()
                                        .requestMatchers("/calorie/**").permitAll()
                                        .requestMatchers("/findPet/list","/findPet/view").permitAll()
                                        .requestMatchers("/news/**").permitAll()
                                        .requestMatchers("/checkDuplicateUsername").permitAll()
                                        .requestMatchers("/checkDuplicateEmail").permitAll()
                                        .requestMatchers("/community/write","/community/writedo").hasAnyRole("USER")
                                        .requestMatchers("/findPet/write","/findPet/writedo").hasAnyRole("USER")
                                        .requestMatchers("/logout").hasAnyRole("USER")
                                        .requestMatchers("/main").hasAnyRole("USER")
                                        .requestMatchers("/calorie/recommand_calorie/cat").permitAll()
                                        .anyRequest().authenticated());


        /*httpBasic 인증*/

        http
                .httpBasic((auth) -> auth.disable());

        /*form로그인*/

        http
                .formLogin((formLogin)-> formLogin
                        .loginPage("/login")
                        .loginProcessingUrl("/logindo")
                        .usernameParameter("username")
                        .passwordParameter("pw")
                        .successHandler(
                                new AuthenticationSuccessHandler() {
                                    @Override
                                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                        System.out.println("authentication : " + authentication.getName());
                                        response.sendRedirect("/"); // 인증이 성공한 후에는 root로 이동
                                    }
                                }
                        )
                        .permitAll());
        http
                .logout((auth) -> auth.logoutUrl("/logout")
                        .logoutSuccessUrl("/"));
        /*oauth2*/
        http
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                        .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                                .userService(customOAuth2UserService)));

        /*다중 로그인*/

        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(1)                     //다중 로그인
                        .maxSessionsPreventsLogin(false));      //true는 새로운 로그인 차단, false는 기존 세션 삭제

        /*동일한 세션에 대해 id변경*/

        http
                .sessionManagement((auth) -> auth
                        .sessionFixation().changeSessionId());


        return http.build();
    }
}
