package com.yuqn.controller.test;

import com.yuqn.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test3")
@Tag(name = "test3接口")
public class Test3Controller {

    @GetMapping("/getmesforxml")
    @Operation(summary = "获取message")
    public String getmes(){
        return Result.ok("成功").toString();
    }
}
