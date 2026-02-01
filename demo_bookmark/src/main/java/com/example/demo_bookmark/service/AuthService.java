package com.example.demo_bookmark.service;

import com.example.demo_bookmark.dto.request.LoginRequest;
import com.example.demo_bookmark.dto.request.SignInRequest;
import com.example.demo_bookmark.dto.response.LoginResponse;
import com.example.demo_bookmark.dto.response.SignInResponse;
import com.example.demo_bookmark.model.UserEntity;
import com.example.demo_bookmark.reposistory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service = tầng nghiệp vụ (business logic).
 * Controller gọi Service, Service gọi Repository.
 */
@Service // Đánh dấu bean service để Spring quản lý (DI)
@RequiredArgsConstructor // Lombok: tạo constructor chứa các field final -> auto DI
public class AuthService {

    // Repository để truy vấn DB
    private final UserRepository userRepository;

    // PasswordEncoder để so sánh password nhập vào với password đã hash trong DB
    private final PasswordEncoder passwordEncoder;

    /**
     * login() xử lý nghiệp vụ đăng nhập:
     * 1) tìm user theo username
     * 2) kiểm tra password
     * 3) tạo token demo
     * 4) trả về response
     */
    public LoginResponse login(LoginRequest req) {

        // 1) Lấy user từ DB theo username
        // userRepository.findByUsername(...) trả Optional<User>
        // - Nếu có user -> Optional chứa user
        // - Nếu không -> Optional empty
        UserEntity user = userRepository.findByUsername(req.getUsername())
                // orElseThrow: nếu Optional rỗng thì ném exception
                // => giúp xử lý luôn case "không có user"
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        // 2) Kiểm tra password
        // passwordEncoder.matches(rawPassword, hashedPasswordInDb)
        // - rawPassword: password người dùng nhập
        // - hashedPasswordInDb: hash lưu trong DB
        // BCrypt sẽ hash rawPassword rồi so sánh đúng/sai
        boolean ok = passwordEncoder.matches(req.getPassword(), user.getPasswordHash());

        // Nếu sai, ném lỗi
        if (!ok) {
            throw new RuntimeException("Invalid username or password");
        }

        // 3) Tạo token demo (thực tế nên dùng JWT)
        // UUID.randomUUID(): tạo chuỗi ngẫu nhiên để demo token
        String fakeToken = "demo-" + UUID.randomUUID();

        // 4) Map dữ liệu user -> LoginResponse DTO
        return LoginResponse.builder()
                .token(fakeToken)
                .userId(user.getId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .build();
    }

    public SignInResponse signIn(SignInRequest rq) {

        //  Check username đã tồn tại chưa
        boolean exists = userRepository.existsByUsername(rq.getUsername());
        if (exists) {
            return SignInResponse.builder()
                    .status("FAIL")
                    .build();
        }

        //  Tạo entity User mới
        UserEntity user = UserEntity.builder()
                .username(rq.getUsername())
                .passwordHash(passwordEncoder.encode(rq.getPassword())) // HASH password
                .fullName(rq.getFull_name())
                .build();

        //Không cần viết hàm mở kết nối và nó được Hibernate thực hiện tự động.
        //  Lưu user vào DB bằng cách gọi tầng Repository để giao tiếp với DB
        UserEntity savedUser = userRepository.save(user);

        //Trả response
        return SignInResponse.builder()
                .username(savedUser.getUsername())
                .full_name(savedUser.getFullName())
                .status("SUCCESS")
                .build();
    }

}