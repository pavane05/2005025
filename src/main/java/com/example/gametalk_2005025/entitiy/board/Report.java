package com.example.gametalk_2005025.entitiy.board;

import com.example.gametalk_2005025.entitiy.User;
import jakarta.persistence.*;

@Entity(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Comment comment;

}