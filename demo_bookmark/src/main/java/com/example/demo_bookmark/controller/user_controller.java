package com.example.demo_bookmark.controller;


import com.example.demo_bookmark.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * Anotation: api/user/...
 * @Controller - file html + @respondbod y
 * @restcontroller - string
 * @RequestMapping - đăng kí đường dẫn
 */
@RestController
@RequestMapping("/user") // Mọi URL trong controller này sẽ đều bắt đầu bằng /user
public class user_controller {

    @GetMapping("/detail")
    public String getAllUser(){
        return "api/user/getAllUser";
    }

    @GetMapping("/add-User") //method = get, url = api/user/addUser
    //Gọi như 1 request -> api/user/addUser?username=x & id=y
    public String addUser(@RequestParam("username") String username, @RequestParam int id){
        return "api/user/addUser" + " "+  username + " " + id;
    }

    //PathVariable - biến sẽ được coi là 1 đường dẫn
    // -> /getDetailUser/x ; không có id = x;
    @GetMapping("/getDetailUser/{id}")
    public String getUserDetail_1(@PathVariable("id") int id){
        return "hello " + id;
    }

    @GetMapping("/get-Detail-User/{id}/{username}")
    public String getUserDetail_2(@PathVariable("id") int id, @PathVariable() String username){
        return "hello " + id + " " + username;
        //khi đặt tên biến trùng tên đường dẫn nó sẽ tự hiểu mình đang dùng cái gì
    }

    @PostMapping("/update") //truyen tham so dang json
    public String updateUser(@RequestBody List<User> user){
        //Để ý List phải có định dạng: [ {}, {} ] khi truyền trong JSON
        for (User data : user){
            System.out.println("Sekou: " + data.getUsername());
        }
        return "";
    }

    /*Dùng PathVariable khi cấu trúc link cần biểu thị sự phân cấp trong resources
    * Dùng RequestParam cho lọc, tìm kiếm, phân trang, sắp xếp, hoặc các flag tùy chọn
    * Dùng RequestBody khi dữ liệu truyền nhạy cảm hoặc quá phức tạp để biểu thị trên url
    * --> Khi này sẽ dùng post*/



}