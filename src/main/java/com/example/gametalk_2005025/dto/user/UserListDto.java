package com.example.gametalk_2005025.dto.user;


import com.example.gametalk_2005025.entitiy.User;
import lombok.Data;


@Data
public class UserListDto {
    private Long id;
    private String email;
    private String name;
    private String tel;

    public static UserListDto toUserDTO(User user) {
        UserListDto userDto = new UserListDto();
        userDto.id = user.getId();
        userDto.email = user.getEmail();
        userDto.name = user.getName();
        userDto.tel = user.getTel();
        return userDto;
    }
}