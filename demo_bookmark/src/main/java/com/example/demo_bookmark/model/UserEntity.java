package com.example.demo_bookmark.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity = "Model" trong MVC (ở tầng dữ liệu).
 * Nó ánh xạ 1 bảng trong DB (ở đây là bảng "users").
 */
@Entity // Báo cho JPA/Hibernate biết đây là 1 entity cần quản lý (mapping DB)
@Table(name = "users") // Tên bảng trong DB - tên table mà mình muốn ánh xạ tới.
@Getter
@Setter // Lombok: tự sinh getter/setter để khỏi viết tay
@NoArgsConstructor // Lombok: tạo constructor rỗng
@AllArgsConstructor // Lombok: tạo constructor đủ field
@Builder // Lombok: cho phép tạo object theo kiểu builder (User.builder()...)
public class UserEntity {

    @Id // xác định Khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment cho id;
    // IDENTITY: DB (MySQL) tự tăng id (AUTO_INCREMENT)
    private Long Id;

    @Column(nullable = false, unique = true, length = 50) //Ánh xạ tới column
    // nullable=false: không được null
    // unique=true: username không được trùng
    //- kiểu dữ liệu cũng phải khớp
    private String username;

    @Column(nullable = false, length = 100)
    // Demo: lưu password đã hash (ví dụ BCrypt). Không lưu plain text.
    private String passwordHash;

    @Column(nullable = false, length = 100)
    private String fullName;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserRoleEntity> UserRoleEntity = new ArrayList<>();

}