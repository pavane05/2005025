package com.example.gametalk_2005025.service.impl;

import com.example.gametalk_2005025.entitiy.User;
import com.example.gametalk_2005025.entitiy.report.UserReport;
import com.example.gametalk_2005025.repository.UserReportRepository;
import com.example.gametalk_2005025.service.ReportService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    private final UserReportRepository userReportRepository;


    @Override
    public UserReport reportUser(Optional<User> reporterUser, Optional<User> reportedUser, String content) {
        UserReport userReport = new UserReport();
        userReport.setReporterUser(reporterUser);
        userReport.setReportedUser(reportedUser);
        userReport.setContent(content);

        return userReportRepository.save(userReport);

    }

    // 기타 필요한 메소드 추가
}