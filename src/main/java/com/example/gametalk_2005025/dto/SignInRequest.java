package com.example.gametalk_2005025.dto;

import lombok.Data;

@Data
public class SignInRequest {

    private String email;
    private String password;
}


// http://localhost:8080/api/v1/auth/signin