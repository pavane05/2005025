package com.example.gametalk_2005025.service;

import com.example.gametalk_2005025.dto.SignUpRequest;
import com.example.gametalk_2005025.entitiy.User;

public interface AuthenticationService {

    User signup(SignUpRequest signUpRequest);
}
