package com.example.gametalk_2005025.service;

import com.example.gametalk_2005025.entitiy.User;
import com.example.gametalk_2005025.entitiy.report.UserReport;

import java.util.Optional;

public interface ReportService {

    UserReport reportUser(Optional<User> reporterUser, Optional<User> reportedUser, String content);
}
