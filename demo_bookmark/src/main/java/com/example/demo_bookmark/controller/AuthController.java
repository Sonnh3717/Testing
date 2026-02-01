package com.example.demo_bookmark.controller;

import com.example.demo_bookmark.dto.request.LoginRequest;
import com.example.demo_bookmark.dto.request.SignInRequest;
import com.example.demo_bookmark.dto.response.LoginResponse;
import com.example.demo_bookmark.dto.response.SignInResponse;
import com.example.demo_bookmark.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller = tầng nhận HTTP request và trả HTTP response.
 * Nó KHÔNG nên chứa nghiệp vụ phức tạp. Chỉ gọi service và trả kết quả.
 */
@RestController
// @RestController = @Controller + @ResponseBody
// => trả JSON trực tiếp thay vì trả về view (jsp/html)
@RequestMapping("/api/auth") // base path
@RequiredArgsConstructor
public class AuthController {

    // Inject service
    private final AuthService authService;

    /**
     * API login:
     * - URL: POST /api/auth/login
     * - Body: JSON {username, password}
     * - Response: JSON LoginResponse
     * Hàm này được gọi thông qua link - api: api/auth/login
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest req
            // @RequestBody: lấy JSON body map vào LoginRequest
            // @Valid: kích hoạt validate (@NotBlank...) trong LoginRequest
    ) {
        // Gọi service xử lý nghiệp vụ / trả luôn response tại đây
        LoginResponse res = authService.login(req); //LoginReq từ DTO - data object - gói data chuẩn object để
        //JSON - javaScripts OBJECT notation truyền đi

        // Trả 200 OK + body JSON
        return ResponseEntity.ok(res);
    }

    //ResponseEntity là gì?
    //ResponseEntity<T> là object đại diện cho TOÀN BỘ HTTP response
    @PostMapping("/signIn")
    public ResponseEntity<SignInResponse> createUser(@RequestBody SignInRequest rq){
        SignInResponse response = authService.signIn(rq);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}