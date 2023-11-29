package com.example.gametalk_2005025.service.impl;

import com.example.gametalk_2005025.dto.UserDTO;
import com.example.gametalk_2005025.entitiy.User;
import com.example.gametalk_2005025.repository.UserRepository;
import com.example.gametalk_2005025.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username).orElseThrow(()
                        -> new UsernameNotFoundException("User not found"));
            }
        };

    }


}
