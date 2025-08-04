package com.xxx.springboot15redis.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author luruoyang
 */
@Configuration
public class RedisConfig {

  @Bean
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(factory);

    // key使用String序列化
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setHashKeySerializer(new StringRedisSerializer());

    // value使用FastJSON序列化
    // 使用支持字符串的FastJSON序列化器
    // FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
    // redisTemplate.setValueSerializer(fastJsonRedisSerializer);
    // redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);

    // value使用JDK序列化（可以正确处理各种对象包括字符串）
    redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
    redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());

    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }
}
