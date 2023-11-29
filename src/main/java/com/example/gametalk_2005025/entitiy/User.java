package com.example.gametalk_2005025.entitiy;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "members")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer member_id;

    @NotEmpty(message = "이메일을 입력해주세요.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식을 확인해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    @Size(min = 4, message = "비밀번호는 최소 4자 이상이어야 합니다.")
    private String password;

    @NotEmpty(message = "이름을 입력해주세요.")
    @Size(min = 2, message = "이름은 최소 2자 이상이어야 합니다.")
    private String name;

    @NotEmpty(message = "전화번호를 입력해주세요.")
    private String tel;

    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
