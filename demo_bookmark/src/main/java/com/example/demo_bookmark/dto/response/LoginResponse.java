package com.example.demo_bookmark.dto.response;

import lombok.*; //all import

/**
 * DTO dùng để trả dữ liệu về cho client sau khi login.
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class LoginResponse {
    private String token;
    private Long userId;
    private String username;
    private String fullName;
}