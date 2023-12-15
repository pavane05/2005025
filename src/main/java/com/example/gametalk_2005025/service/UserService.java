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

//    Boolean delete(String loginId, String nowPassword);


    User myInfo(String email);

    // 로그인 정보 조회
    UserResponseDto getMyInfo();

    // 정보 수정
    void updateMyInfo(UserUpdateDto dto);

    // 비밀번호 초기화 기능을 제공하는 메서드
    void resetPassword(String email);


    // 회원 삭제
    void deleteById(Integer id);

    // 유저 검색
    User findByEmail(String userEmail);

    List<User> searchUsersByName(String name);
}
