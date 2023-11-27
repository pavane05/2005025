package com.example.gametalk_2005025.repository;

import com.example.gametalk_2005025.entitiy.Role;
import com.example.gametalk_2005025.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    User findByRole(Role role);
}
