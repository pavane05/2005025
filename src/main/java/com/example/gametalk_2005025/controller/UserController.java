package com.example.gametalk_2005025.controller;

import com.example.gametalk_2005025.dto.user.UserDto;
import com.example.gametalk_2005025.dto.user.UserResponseDto;
import com.example.gametalk_2005025.dto.user.UserUpdateDto;
import com.example.gametalk_2005025.entitiy.User;
import com.example.gametalk_2005025.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    // 로그인 후 토큰 넘겨주고 회원정보 받아오기
    @GetMapping("/profile")
    public String getUserInfo(Authentication authentication) {
        String profile = authentication.getPrincipal().toString();

        return profile;
    }

    // 전체 유저 리스트
    @GetMapping("/userlist")
    public String findAll(Model model) {

        List<UserDto> userList = userService.findAll();
        String list = userList.toString();

        model.addAttribute("title", "회원목록조회");
        model.addAttribute("userList", userList);
        return list;
    }

    // 유저 검색
    @GetMapping("/{userEmail}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/search")
    public List<User> searchUsersByName(@RequestParam String name) {
        return userService.searchUsersByName(name);
    }


    // 회원 정보 수정
    @PutMapping("/update")
    public ResponseEntity<UserResponseDto> updateMyInfo(@RequestBody UserUpdateDto dto) {
        userService.updateMyInfo(dto);
        return ResponseEntity.ok(userService.getMyInfo());
    }


    // 임시 비밀번호
    @PostMapping("/password")
    public String resetPassword(@RequestParam String email) {
        userService.resetPassword(email);

        log.info("Password reset instructions sent to email: {}", email);

        return "이메일을 확인하여 비밀번호 재설정 안내를 확인하세요.";
    }

    // 회원 삭제
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id) {
        userService.deleteById(id);

        return "삭제 완료";
    }




//    @PostMapping("/delete")
//    public String userDelete(@ModelAttribute UserDto dto, Authentication auth, Model model) {
//        Boolean deleteSuccess = userService.delete(auth.getName(), dto.getNowPassword());
//        if (deleteSuccess) {
//            model.addAttribute("message", "탈퇴 되었습니다.");
//            model.addAttribute("nextUrl", "/users/logout");
//            return "printMessage";
//        } else {
//            model.addAttribute("message", "현재 비밀번호가 틀려 탈퇴에 실패하였습니다.");
//            model.addAttribute("nextUrl", "/users/delete");
//            return "printMessage";
//        }
//    }

    // 유저 목록 출력
//    @GetMapping("/member")
//    public String findAll(Model model) {
//        List<UserDTO> userDTOList = UserService.findAll();
//        model.addAttribute("UserList", userDTOList);
//
//        return "list";
//    }


    // 토큰에 저장된 정보 가져오기
//    @GetMapping("/profile")
//    public String getUser(@AuthenticationPrincipal UserDetails userDetails) {
//        return "User Details: " + userDetails.getUsername() + userDetails.getPassword();
//    }


}
