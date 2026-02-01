package com.example.demo_bookmark.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {
    @NotBlank // validate: không được null / không được rỗng / không được chỉ có khoảng trắng
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String full_name;
}
