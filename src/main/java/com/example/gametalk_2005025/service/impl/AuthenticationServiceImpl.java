package com.example.gametalk_2005025.service.impl;

import com.example.gametalk_2005025.dto.*;
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


    public User signup(SignUpRequest signUpRequest) {                                                               // 회원 가입을 처리. 이미 존재하는 이메일인 경우 예외를 던짐. 새로운 사용자를 생성하고 저장한 후 반환.

        // 중복 가입 방지
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("이미 가입된 이메일입니다.");
        } else {    // 가입
            User user = new User();

            user.setEmail(signUpRequest.getEmail());
            user.setName(signUpRequest.getName());
            user.setTel(signUpRequest.getTel());
            user.setRole(Role.USER);
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

            return userRepository.save(user);
        }
    }

    /*

        // DTO로 기능 이동 이전 코드
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

    // 미입력 방지(기능 이동)
        if (signUpRequest.getEmail().isEmpty()) {
            throw new RuntimeException("이메일을 입력해주세요.");
        } else if (signUpRequest.getPassword().isEmpty()) {
            throw new RuntimeException("비밀번호를 입력해주세요.");
        } else if (signUpRequest.getPassword().length()<3) {
            throw new RuntimeException("비밀번호가 너무 짧습니다.");
        } else if (signUpRequest.getName().isEmpty()) {
            throw new RuntimeException("이름을 입력해주세요.");
        } else if (signUpRequest.getName().length()<2) {
            throw new RuntimeException("이름이 너무 짧습니다.")
        } else if (signUpRequest.getTel().isEmpty()) {
            throw new RuntimeException("전화번호를 입력해주세요.");
        }

    */


    @Override
    public JwtAuthenticationResponse signin(SignInRequest signInRequest) {        // 로그인을 처리. 입력된 이메일이 존재하지 않거나 비밀번호가 일치하지 않으면 예외를 던짐. 사용자를 인증하고, 액세스 토큰과 리프레시 토큰을 생성하여 반환.

        // 존재 여부 체크
        if (!userRepository.existsByEmail(signInRequest.getEmail())) {
            throw new RuntimeException("존재하지 않는 이메일입니다.");
        } else {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),
                    signInRequest.getPassword()));

            var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(()
                    -> new IllegalArgumentException("Invalid id or password"));
            var jwt = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshToken);

            return jwtAuthenticationResponse;
        }
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {    // 주어진 리프레시 토큰을 사용하여 새로운 액세스 토큰을 생성. 클라이언트가 제공한 리프레시 토큰이 유효하면 새로운 액세스 토큰을 생성하고 응답.
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
