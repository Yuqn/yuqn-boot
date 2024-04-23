package com.yuqn.controller.redis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yuqn
 * @Date: 2024/4/23 22:33
 * @description:
 * redis demo
 * @version: 1.0
 */
@RestController
@RequestMapping("/demo")
@Tag(name = "demo模块")
public class RedisDemo {

    private RedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/set")
    @Operation(summary = "保存redis")
    public String setmes(){
        try {
            redisTemplate.opsForValue().set("one","yuqn");
            redisTemplate.opsForValue().set("two","24");
        }catch (Exception e){
            System.out.println("保存失败");
            return "保存失败";
        }
        return "保存成功";
    }
    @GetMapping("/get")
    @Operation(summary = "获取redis")
    public String getmes(){
        try {
            String mes = redisTemplate.opsForValue().get("one").toString();
            String age = redisTemplate.opsForValue().get("two").toString();
            System.out.println(mes);
            System.out.println(age);
        }catch (Exception e){
            System.out.println("保存失败");
            return "获取失败";
        }
        return "获取成功";
    }
}
