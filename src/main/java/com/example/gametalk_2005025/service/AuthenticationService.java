package com.example.gametalk_2005025.service;

import com.example.gametalk_2005025.dto.*;
import com.example.gametalk_2005025.entitiy.User;

public interface AuthenticationService {

    User signup(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signin(SignInRequest signInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
