package com.example.gametalk_2005025.repository;


import com.example.gametalk_2005025.entitiy.report.UserReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserReportRepository extends JpaRepository<UserReport, Long> {
    // 추가적인 메소드가 필요하다면 여기에 작성 가능
}
