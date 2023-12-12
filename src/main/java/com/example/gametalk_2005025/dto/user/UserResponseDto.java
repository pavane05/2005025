package com.example.gametalk_2005025.dto.user;

import com.example.gametalk_2005025.entitiy.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String Name;
    private String tel;
    private String password;


    public static UserResponseDto of(User user) {
        return new UserResponseDto(user.getName(), user.getTel(), user.getPassword());
    }
}