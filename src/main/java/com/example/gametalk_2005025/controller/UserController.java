package com.example.gametalk_2005025.controller;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hi User");
    }

    // 로그인 후 토큰 넘겨주고 회원정보 받아오기
    @GetMapping("/profile")
    public String getMemberInfo(Authentication authentication) {
        String profile = authentication.getPrincipal().toString();

        return profile;
    }


}
