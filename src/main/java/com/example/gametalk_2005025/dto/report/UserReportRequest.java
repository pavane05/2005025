package com.example.gametalk_2005025.dto.report;

import lombok.Data;

@Data
public class UserReportRequest {

    private String reporterUserEmail;
    private String reportedUserEmail;
    private String content;

    public UserReportRequest() {
        // 기본 생성자
    }

    public UserReportRequest(String reporterUserEmail, String reportedUserEmail, String content) {
        this.reporterUserEmail = reporterUserEmail;
        this.reportedUserEmail = reportedUserEmail;
        this.content = content;
    }

    // Getter, Setter, toString 등 필요한 메소드 추가
}

