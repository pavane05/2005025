package com.example.gametalk_2005025.entitiy.report;

import com.example.gametalk_2005025.entitiy.User;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class UserReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reporter_user_id")
    private User reporterUser;

    @ManyToOne
    @JoinColumn(name = "reported_user_id")
    private User reportedUser;

    @Column(nullable = false)
    private String content;

}
