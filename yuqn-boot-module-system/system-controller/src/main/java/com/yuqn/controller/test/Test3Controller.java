package com.yuqn.controller.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test3")
public class Test3Controller {

    @GetMapping("/getmesforxml")
    public String getmes(){
        return "ok";
    }
}
