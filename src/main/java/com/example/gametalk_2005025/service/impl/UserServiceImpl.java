package com.example.gametalk_2005025.service.impl;

import com.example.gametalk_2005025.dto.user.UserDto;
import com.example.gametalk_2005025.dto.user.UserResponseDto;
import com.example.gametalk_2005025.dto.user.UserUpdateDto;
import com.example.gametalk_2005025.entitiy.User;
import com.example.gametalk_2005025.repository.UserRepository;
import com.example.gametalk_2005025.service.EmailService;
import com.example.gametalk_2005025.service.UserService;
import com.example.gametalk_2005025.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final EmailService emailService;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username).orElseThrow(()
                        -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    // 유저 리스트 출력용
    @Override
    public List<UserDto> findAll() {
        List<User> userList = userRepository.findAll();
        //Controller로 dto로 변환해서 줘야 함
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            userDtoList.add(UserDto.toUserDTO(user));

        }
        return userDtoList;
    }

    @Override
    public User myInfo(String email) {
        return userRepository.findByEmail(email).get();
    }

    // 로그인 정보 조회
    @Override
    public UserResponseDto getMyInfo() {
        return userRepository.findByEmail(SecurityUtil.getLoginUserId())
                .map(UserResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }


    // 정보 수정
    @Override
    public void updateMyInfo(UserUpdateDto dto) {
        User user = userRepository
                .findByEmail(SecurityUtil.getLoginUserId())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));

        user.updateUser(dto, encoder);
        userRepository.save(user);
    }


    // 임시 비밀번호 발급
    @Override
    public void resetPassword(String email) {
        try {
            Optional<User> userOptional = userRepository.findByEmail(email);

            userOptional.ifPresent(user -> {
                // 사용자가 존재할 경우에만 아래 코드 실행
                String newPassword = generateRandomPassword();
                user.setPassword(encoder.encode(newPassword));
                userRepository.save(user);

                String subject = "비밀번호 재설정";
                String body = "새로운 비밀번호: " + newPassword;
                emailService.sendPasswordResetEmail(email, subject, body);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 임시 비밀번호 생성
    private String generateRandomPassword() {
        // 비밀번호로 사용할 문자열 범위
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // SecureRandom을 사용하여 랜덤 문자열 생성
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(8); // 8은 생성할 비밀번호의 길이

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(chars.length());
            password.append(chars.charAt(index));
        }

        return password.toString();
    }

    // 회원 삭제
    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(Long.valueOf(id));
    }


//    @Override
//    public Boolean delete(String loginId, String nowPassword) {
//        User loginUser = userRepository.findByLoginId(loginId).get();
//
//        if (encoder.matches(nowPassword, loginUser.getPassword())) {
//            List<Like> likes = likeRepository.findAllByUserLoginId(loginId);
//            for (Like like : likes) {
//                like.getBoard().likeChange(like.getBoard().getLikeCnt() - 1);
//            }
//
//            List<Comment> comments = commentRepository.findAllByUserLoginId(loginId);
//            for (Comment comment : comments) {
//                comment.getBoard().commentChange(comment.getBoard().getCommentCnt() - 1);
//            }
//
//            userRepository.delete(loginUser);
//            return true;
//        } else {
//            return false;
//        }
//    }


}
