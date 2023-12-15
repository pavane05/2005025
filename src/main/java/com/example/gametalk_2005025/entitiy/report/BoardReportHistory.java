package com.example.gametalk_2005025.entitiy.report;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class BoardReportHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reporterEmail;

    @Column(nullable = false)
    private String reportedBoardEmail;

    @Column(nullable = false)
    private String content;

    public BoardReportHistory(String reporterEmail, String reportedBoardEmail, String content) {
        this.reporterEmail = reporterEmail;
        this.reportedBoardEmail = reportedBoardEmail;
        this.content = content;
    }
}