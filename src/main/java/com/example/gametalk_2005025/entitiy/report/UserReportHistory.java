package com.example.gametalk_2005025.entitiy.report;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class UserReportHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reporterEmail;

    @Column(nullable = false)
    private String reportedUserEmail;

    @Column(nullable = false)
    private String content;

    public UserReportHistory(final String reporterEmail, final String reportedUserEmail, final String content) {
        this.reporterEmail = reporterEmail;
        this.reportedUserEmail = reportedUserEmail;
        this.content = content;
    }
}
