package com.example.gametalk_2005025.service.impl;

import com.example.gametalk_2005025.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    public String generateToken(UserDetails userDetails) {      // 사용자 정보를 받아와서 액세스 토큰을 생성. 토큰은 사용자의 이름을 주제로 하며, 발급 시간과 만료 시간이 설정
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // 하루
                .signWith(getSiginKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {      // 추가 클레임과 사용자 정보를 받아와서 리프레시 토큰을 생성. 리프레시 토큰은 사용자의 이름을 주제로 하며, 발급 시간과 만료 시간이 설정.
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 604800000)) // 7일
                .signWith(getSiginKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUserName(String token) {       // 주어진 토큰에서 사용자 이름을 추출.
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {     // 주어진 토큰에서 클레임을 추출.
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private Key getSiginKey() {     // 로그인에 사용되는 키를 생성.
        byte[] key = Decoders.BASE64.decode("25432A462D4A614E645267556A586E3272357538782F413F4428472B4B625065");
        return Keys.hmacShaKeyFor(key);
    }

    private Claims extractAllClaims(String token) {     // 주어진 토큰에서 모든 클레임을 추출
        return Jwts.parserBuilder().setSigningKey(getSiginKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {        // 주어진 토큰이 유효하면서 사용자와 일치하는지 확인.
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {      // 주어진 토큰이 만료되었는지 확인.
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}
