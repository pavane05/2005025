package com.example.gametalk_2005025.service.impl;

import com.example.gametalk_2005025.dto.JwtAuthenticationResponse;
import com.example.gametalk_2005025.dto.RefreshTokenRequest;
import com.example.gametalk_2005025.dto.SignInRequest;
import com.example.gametalk_2005025.dto.SignUpRequest;
import com.example.gametalk_2005025.entitiy.Role;
import com.example.gametalk_2005025.entitiy.User;
import com.example.gametalk_2005025.repository.UserRepository;
import com.example.gametalk_2005025.service.AuthenticationService;
import com.example.gametalk_2005025.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public User signup(SignUpRequest signUpRequest) {
        User user = new User();

        user.setEmail(signUpRequest.getEmail());
        user.setName(signUpRequest.getName());
        user.setTel(signUpRequest.getTel());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        return userRepository.save(user);

    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest signInRequest) {

        // 인증용 객체 생성
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));

        var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid id or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());

            return jwtAuthenticationResponse;
        }
        return null;
    }


}
