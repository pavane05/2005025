package com.example.gametalk_2005025.service;

import com.example.gametalk_2005025.dto.UserDto;
import com.example.gametalk_2005025.entitiy.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    UserDetailsService userDetailsService();

    List<UserDto> findAll();


}
