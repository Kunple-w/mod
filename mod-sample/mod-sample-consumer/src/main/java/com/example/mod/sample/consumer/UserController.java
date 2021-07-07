package com.example.mod.sample.consumer;

import com.example.mod.sample.consumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangyongxu
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/sayHi")
    public ResponseEntity<String> sayHi() {
        String s = userService.sayHi("jack", "hello");
        return ResponseEntity.ok(s);
    }
}
