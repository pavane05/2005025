package com.example.gametalk_2005025.dto;

import com.example.gametalk_2005025.entitiy.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String tel;

    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(Long.valueOf(user.getMember_id()));
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setName(user.getName());
        userDTO.setName(user.getName());

        return userDTO;
    }

}
