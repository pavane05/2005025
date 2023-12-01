package com.example.gametalk_2005025.service.impl;

import com.example.gametalk_2005025.dto.UserDto;
import com.example.gametalk_2005025.entitiy.User;
import com.example.gametalk_2005025.repository.UserRepository;
import com.example.gametalk_2005025.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
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

    // 유저 리스트 출력용
    @Override
    public List<UserDto> findAll() {
        List<User> userList = userRepository.findAll();
        //Controller로 dto로 변환해서 줘야 함
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList){
            userDtoList.add(UserDto.toUserDTO(user));

        }
        return userDtoList;
    }



    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


}
