package com.yuqn.controller.test;

import com.yuqn.entity.test.YuqnTest;
import com.yuqn.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private TestService testService;

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/getmessage")
    public String getmes(){
        String mes = testService.getMessage();
        return mes;
    }

    @GetMapping("/getmesforxml")
    public List<YuqnTest> getmesforxml(){
        return testService.getAllMessage();
    }
}