package com.example.gametalk_2005025.dto;

import lombok.Data;

@Data
public class RefreshTokenRequest {

    private String token;

}


// http://localhost:8080/api/v1/auth/refresh