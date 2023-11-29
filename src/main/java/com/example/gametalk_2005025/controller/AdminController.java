package com.example.gametalk_2005025.controller;

import com.example.gametalk_2005025.dto.SignUpRequest;
import com.example.gametalk_2005025.entitiy.User;
import com.example.gametalk_2005025.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    @GetMapping
    public ResponseEntity<String> sayHello() {


        return ResponseEntity.ok("Hi Admin");
    }

}
