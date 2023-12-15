package com.example.gametalk_2005025.repository;

import com.example.gametalk_2005025.entitiy.report.BoardReportHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardReportRepository extends JpaRepository<BoardReportHistory, Long> {

    boolean existsByReporterEmailAndReportedBoardEmail(String reporterEmail, String reportedBoardEmail);

    List<BoardReportHistory> findByReportedBoardEmail(String reportedBoardEmail);

    void deleteAllByReportedBoardEmail(String Email);
}