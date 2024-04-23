package com.yuqn.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author: yuqn
 * @Date: 2024/4/23 22:11
 * @description:
 * redis配置
 * @version: 1.0
 */
@Configuration
public class RedisConfig {
    @Bean
    @ConditionalOnSingleCandidate(RedisConnectionFactory.class)
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        // 设置key和value的序列化方式
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        // 设置key的序列化器为StringRedisSerializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 设置hash key的序列化器为StringRedisSerializer
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // 设置value的序列化器为JdkSerializationRedisSerializer
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        // 设置hash value的序列化器为JdkSerializationRedisSerializer
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        // 初始化RedisTemplate
        redisTemplate.afterPropertiesSet();
        // 返回配置好的RedisTemplate
        return redisTemplate;
    }
}
