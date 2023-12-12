package com.example.gametalk_2005025.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {


    // 현재 로그인 되어있는 유저 정보의 ID 가져오기
    public static String getLoginUserId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("로그인 유저 정보가 없습니다.");
        }

        String LoginId = authentication.getName();
        return LoginId;
    }
}