package com.example.gametalk_2005025.controller;

import com.example.gametalk_2005025.dto.JwtAuthenticationResponse;
import com.example.gametalk_2005025.dto.RefreshTokenRequest;
import com.example.gametalk_2005025.dto.SignInRequest;
import com.example.gametalk_2005025.dto.SignUpRequest;
import com.example.gametalk_2005025.entitiy.User;
import com.example.gametalk_2005025.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signin(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

}
