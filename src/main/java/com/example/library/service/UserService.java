package com.example.library.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.library.dto.SignupRequest;
import com.example.library.model.Role;
import com.example.library.model.User;
import com.example.library.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupRequest request){

        User user = User.builder()
                       .username(request.getUsername())
                       .password(passwordEncoder.encode(request.getPassword()))
                       .role(Role.USER)
                       .build();

        userRepository.save(user);
    }
    
}
