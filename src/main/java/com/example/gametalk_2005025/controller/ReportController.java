package com.example.gametalk_2005025.controller;


import com.example.gametalk_2005025.dto.report.UserReportRequest;
import com.example.gametalk_2005025.entitiy.User;
import com.example.gametalk_2005025.repository.UserRepository;
import com.example.gametalk_2005025.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reports/users")
public class ReportController {

    private final UserRepository userRepository;
    private final ReportService reportService;

    @PostMapping
    public ResponseEntity<String> reportUser(@RequestBody UserReportRequest userReportRequest) {
        // 사용자 신고 처리 로직
        Optional<User> reporterUser = userRepository.findByEmail(userReportRequest.getReporterUserEmail());
        Optional<User> reportedUser = userRepository.findByEmail(userReportRequest.getReportedUserEmail());
        reportService.reportUser(reporterUser, reportedUser, userReportRequest.getContent());

        return ResponseEntity.ok("User reported successfully.");
    }
}