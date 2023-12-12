package com.example.gametalk_2005025.dto;


import com.example.gametalk_2005025.entitiy.User;
import lombok.Data;


@Data
public class UserDto {
    private Long user_id;
    private String email;
    private String password;
    private String name;
    private String tel;

    public static UserDto toUserDTO(User user) {
        UserDto userDto = new UserDto();
        userDto.user_id = user.getUser_id();
        userDto.email = user.getEmail();
        userDto.password = user.getPassword();
        userDto.name = user.getName();
        userDto.tel = user.getTel();
        return userDto;
    }
}