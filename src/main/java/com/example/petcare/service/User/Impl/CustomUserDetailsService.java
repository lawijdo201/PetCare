package com.example.petcare.service.User.Impl;

import com.example.petcare.data.dto.User.CustomUserDetails;
import com.example.petcare.entity.UserEntity;
import com.example.petcare.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         UserEntity userEntity = userRepository.findByUsername(username)
                 .orElseThrow(() -> new UsernameNotFoundException(username + "사용자의 이름이 존재하지 않습니다."));
         return new CustomUserDetails(userEntity);
    }
}
