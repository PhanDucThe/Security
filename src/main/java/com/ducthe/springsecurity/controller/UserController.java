package com.ducthe.springsecurity.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping(value = "/")
    public void createUser(@RequestParam(required = false) String userName,
                           @RequestParam(required = false) String passWord) {

         //B1:  Tạo ra một đối tượng BCryptPasswordEncoder để mã hoá mật khẩu dạng Bcrypt.
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10); // 10 ở đây chính là độ phức tạp của thuật toán.

        // Mật khẩu ban đầu.
        System.out.println(passWord);

        // B2: Mã hoá mật khẩu.
        String hashedPassword = encoder.encode(passWord);

        // B3: Hiển thị ra để so sách.
        System.out.println(hashedPassword);
    }
}
