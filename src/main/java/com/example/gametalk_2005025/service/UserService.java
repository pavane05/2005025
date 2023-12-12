package com.example.gametalk_2005025.service;

import com.example.gametalk_2005025.dto.user.UserDto;
import com.example.gametalk_2005025.dto.user.UserResponseDto;
import com.example.gametalk_2005025.dto.user.UserUpdateDto;
import com.example.gametalk_2005025.entitiy.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface UserService {

    UserDetailsService userDetailsService();

    List<UserDto> findAll();

    User myInfo(String loginId);

//    Boolean delete(String loginId, String nowPassword);


    // 로그인 정보 조회
    UserResponseDto getMyInfo();

    // 정보 수정
    void updateMyInfo(UserUpdateDto dto);

    // 회원 삭제
    void deleteById(Integer id);
}
