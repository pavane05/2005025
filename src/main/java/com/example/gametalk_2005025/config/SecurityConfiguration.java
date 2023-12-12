package com.example.gametalk_2005025.config;

import com.example.gametalk_2005025.entitiy.Role;
import com.example.gametalk_2005025.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Slf4j
@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final PasswordEncoderConfig passwordEncoderConfig;
    private final UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/admin").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("/user").hasAnyAuthority(Role.USER.name())
                        .anyRequest().permitAll())
//                .authenticationProvider(authenticationProvider()).addFilterBefore(
//                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(login ->
                        login
                                .loginPage("/auth/login") // 로그인 페이지 지정
                                .defaultSuccessUrl("/", true) // 로그인 성공 시 리다이렉트될 URL
                                .usernameParameter("email")
                                .failureUrl("/auth/login/error")
                                .permitAll())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))// 로그아웃 엔드포인트를 지정
                        .logoutSuccessUrl("/") // 로그아웃 성공 시 리다이렉트될 URL
                        .invalidateHttpSession(true)
                );

        log.debug("Security filter chain configured.");

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService.userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoderConfig.encoder());

        log.debug("Authentication provider configured.");


        return authenticationProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {

        log.debug("Authentication manager configured.");

        return config.getAuthenticationManager();
    }

}
