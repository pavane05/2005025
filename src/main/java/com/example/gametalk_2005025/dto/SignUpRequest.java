package com.example.gametalk_2005025.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String email;
    private String name;
    private String password;
    private String tel;
}

// http://localhost:8080/api/v1/auth/singup
// Json postman user 삽입 양식
// {
//    "email": "user@gmail.com",
//    "name": "user",
//    "password": "user",
//    "tel": "010-1234-5678"
//}