package com.yuqn.controller;

import com.yuqn.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys")
public class UserController {
    @GetMapping("/getMessage")
    public String toMessage(){
        User user = new User("1","新信息");
        return user.getMessage();
    }
}
