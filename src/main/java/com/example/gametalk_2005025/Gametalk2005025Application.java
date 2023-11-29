package com.example.gametalk_2005025;

import com.example.gametalk_2005025.entitiy.Role;
import com.example.gametalk_2005025.entitiy.User;
import com.example.gametalk_2005025.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Gametalk2005025Application { // implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Gametalk2005025Application.class, args);
    }

    // 관리자 추가용
//    public void run(String... args) {
//        User user = new User();
//            user.setEmail("admin1@gmail.com");
//            user.setName("admin1");
//            user.setTel("010-1234-5678");
//            user.setRole(Role.ADMIN);
//            user.setPassword(new BCryptPasswordEncoder().encode("admin")); // 비밀번호 암호화 저장
//            userRepository.save(user);
//
//    }


     // 단독 관리자 생성
//    public void run(String... args) {
//        User adminAccount = userRepository.findByRole(Role.ADMIN);
//        if(null == adminAccount) {
//            User user = new User();
//            user.setEmail("admin1@gmail.com");
//            user.setName("admin1");
//            user.setTel("010-1234-5678");
//            user.setRole(Role.ADMIN);
//            user.setPassword(new BCryptPasswordEncoder().encode("admin")); // 비밀번호 암호화 저장
//            userRepository.save(user);
//        }
//    }

}


