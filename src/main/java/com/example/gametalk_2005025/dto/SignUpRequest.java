package com.example.gametalk_2005025.dto;

import lombok.Data;

@Data
public class SignUpRequest {

    private String user_id;
    private String name;
    private String email;
    private String password;
    private String tel;
}
