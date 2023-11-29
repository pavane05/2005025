package com.example.gametalk_2005025.repository;

import com.example.gametalk_2005025.entitiy.Role;
import com.example.gametalk_2005025.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);



    // 중복 방지를 위한 존재 여부 체크
    boolean existsByEmail(String email);
    boolean existsByName(String name);

    // 관리자 존재 여부 체크용. Gametalk2005025Application에서 실행 시 관리자 존재 여부 체크 후 생성 할려고 할 경우 이용. 그 외에는 아직 사용 없음.
    User findByRole(Role role);
}
