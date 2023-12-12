package com.example.gametalk_2005025.service;

import com.example.gametalk_2005025.entitiy.EmailMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;

public interface EmailService {
    // 비밀번호 초기화 이메일을 전송하는 메서드
    void sendPasswordResetEmail(String to, String subject, String body);
}
