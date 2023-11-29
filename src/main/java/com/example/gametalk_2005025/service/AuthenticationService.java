package com.example.gametalk_2005025.service;

import com.example.gametalk_2005025.dto.JwtAuthenticationResponse;
import com.example.gametalk_2005025.dto.RefreshTokenRequest;
import com.example.gametalk_2005025.dto.SignInRequest;
import com.example.gametalk_2005025.dto.SignUpRequest;
import com.example.gametalk_2005025.entitiy.User;

public interface AuthenticationService {

    User signup(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signin(SignInRequest signInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
