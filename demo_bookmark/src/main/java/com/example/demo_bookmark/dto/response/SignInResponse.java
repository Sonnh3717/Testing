package com.example.demo_bookmark.dto.response;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInResponse {
    @NotBlank // validate: không được null / không được rỗng / không được chỉ có khoảng trắng
    private String username;

    @NotBlank
    private String full_name;

    private String status;
}