package com.example.gametalk_2005025.dto.user;


import com.example.gametalk_2005025.entitiy.User;
import lombok.Data;


@Data
public class UserDto {
    private Long user_id;
    private String email;
    private String nowPassword;
    private String newPassword;
    private String newPasswordCheck;
    private String name;
    private String tel;

    public static UserDto toUserDTO(User user) {
        UserDto userDto = new UserDto();
        userDto.user_id = user.getId();
        userDto.email = user.getEmail();
        userDto.nowPassword = user.getPassword();
        userDto.name = user.getName();
        userDto.tel = user.getTel();
        return userDto;
    }
}