package com.yuqn.controller;


import com.yuqn.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainController {
    @GetMapping("/getMessage")
    public String toMessage(){
        User user = new User("1","新信息");
        return user.getMessage();
    }
}
