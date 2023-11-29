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
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;


    public User signup(SignUpRequest signUpRequest) {

        // 미입력 방지
//        if (signUpRequest.getEmail().isEmpty()) {
//            throw new RuntimeException("이메일을 입력해주세요.");
//        } else if (signUpRequest.getPassword().isEmpty()) {
//            throw new RuntimeException("비밀번호를 입력해주세요.");
//        } else if (signUpRequest.getPassword().length()<3) {
//            throw new RuntimeException("비밀번호가 너무 짧습니다.");
//        } else if (signUpRequest.getName().isEmpty()) {
//            throw new RuntimeException("이름을 입력해주세요.");
//        } else if (signUpRequest.getName().length()<2) {
//            throw new RuntimeException("이름이 너무 짧습니다.")
//        } else if (signUpRequest.getTel().isEmpty()) {
//            throw new RuntimeException("전화번호를 입력해주세요.");
//        }

        // 중복 가입 방지
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("이미 가입된 이메일입니다.");
        } else {

            User user = new User();

            user.setEmail(signUpRequest.getEmail());
            user.setName(signUpRequest.getName());
            user.setTel(signUpRequest.getTel());
            user.setRole(Role.USER);
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

            return userRepository.save(user);
        }
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest signInRequest) {

        // 미입력 방지
//        if (signInRequest.getEmail().isEmpty()) {
//            throw new RuntimeException("이메일을 입력해주세요.");
//        } else if (signInRequest.getPassword().isEmpty()) {
//            throw new RuntimeException("비밀번호를 입력해주세요.");
//        }

        // 존재 여부 체크
        if (!userRepository.existsByEmail(signInRequest.getEmail())) {
            throw new RuntimeException("존재하지 않는 이메일입니다.");
        } else {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));

            var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid id or password"));
            var jwt = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshToken);

            return jwtAuthenticationResponse;
        }
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());

            return jwtAuthenticationResponse;
        }
        return null;
    }


}
