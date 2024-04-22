package com.yuqn.controller.test1;

import com.yuqn.service.test1.Test1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test1")
public class Test1Controller {

    private Test1Service test1Service;

    @Autowired
    public void setTest1Service(Test1Service test1Service) {
        this.test1Service = test1Service;
    }

    @GetMapping("/getmessage")
    public String getmes(){
        return test1Service.message();
    }
}
