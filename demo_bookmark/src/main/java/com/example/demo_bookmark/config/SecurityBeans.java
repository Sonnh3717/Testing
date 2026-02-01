package com.example.demo_bookmark.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Khai báo bean PasswordEncoder để Spring inject vào AuthService.
 */
@Configuration
public class SecurityBeans {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()); // test API cho dễ
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().permitAll()
        );
        http.formLogin(f -> f.disable());
        http.httpBasic(b -> b.disable()); // nếu muốn tắt luôn basic auth
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCryptPasswordEncoder: thuật toán hash password phổ biến
        return new BCryptPasswordEncoder();
    }
}