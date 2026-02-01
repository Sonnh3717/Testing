package com.example.demo_bookmark.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="role_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne()
    @JoinColumn(name = "UserId") //tự tạo collumn tên userId cho bảng này.
    private UserEntity user; //Đây là gì? - T tự gọi là "biến quan hệ" - biên biểu thị quan hệ
    //Trong JPA chỉ có một bên kiểm soát quan hệ (là bên nhận FK) được gọi là
    //owning side (bên N), còn bên One thì là InverseSide
    //Do bên one không trực tiếp quản lí quan hệ, nên muốn query nhanh thì
    //Lấy biến quan hệ để "mappedBy"

    @ManyToOne()
    @JoinColumn(name = "RoleId") //tương tự - tạo RoleId
    private RoleEntity role;




}
