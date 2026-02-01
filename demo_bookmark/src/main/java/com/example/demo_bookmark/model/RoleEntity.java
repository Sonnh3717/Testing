package com.example.demo_bookmark.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long role_id;

    @Column(name="role_name", nullable = false)
    private String role_name;
    //Set not null, unique cho attribute
    @Column(name="code", nullable = false, unique = true)
    private String code;

    /*CHỈ DÙNG KHI KHÔNG BIẾT @ManytoMany */
//    //Đây đéo phải một field hay attribute
//    //Đây cơ bản là một câu truy vấn trá hình
//    //JPA HIỂU LÀ: "Quan hệ này được quản lý bởi field "role" bên entity Book"
//    //Nên nhớ, field chứ không phải attribute, vì cơ bản phải có annotation
//    //liên quan đến Column hoặc JoinColumn mới được coi là field;
//    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
//    private List<UserRoleEntity> UserRoleEntity = new ArrayList<>(); //Lấy từ query
//
//    //Hibernate lấy List thế nào?
//    /* Hibernate rất khôn, n thấy mappedBy và List, nó tự động query
//    “Hãy lấy tất cả các dòng RoleUser
//    mà UserRole.role_id = Role.id hiện tại”
//    Tức nó mapping - Join theo roleId --> TỨC LIST NÀY BIỂU THỊ THEO ID CỦA USERROLE
//    NÓ SẼ LÀ LIST CÓ USERROLE CÓ ROLEID TƯƠNG ỨNG.
//    * */

    /*Dùng khi biết @ManytoMany*/


}
