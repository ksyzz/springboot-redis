package com.example.shiro.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author fengqian
 * @since <pre>2018/07/04</pre>
 */
@Configuration
@EnableCaching
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private int maxWaitMillis;


    @Bean
    public RedisTemplate initRedisTemplate(@Qualifier("connectionFactory") JedisConnectionFactory jedisConnectionFactory) {

        RedisSerializer str = new StringRedisSerializer();
        RedisSerializer json = new GenericJackson2JsonRedisSerializer();
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setKeySerializer(str);
        redisTemplate.setValueSerializer(json);
        redisTemplate.setHashKeySerializer(str);
        redisTemplate.setHashValueSerializer(json);
        return redisTemplate;
    }

    @Bean(name = "connectionFactory")
    public JedisConnectionFactory initConnect(){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
        connectionFactory.afterPropertiesSet();
        return connectionFactory;
    }

    @Bean
    public CacheManager init(@Qualifier("connectionFactory") JedisConnectionFactory jedisConnectionFactory){
        Set<String> caches = new HashSet<>();
        caches.add("user");
        RedisCacheManager cacheManager = RedisCacheManager.builder(jedisConnectionFactory)
                .initialCacheNames(caches)
                .build();
        cacheManager.initializeCaches();
        return cacheManager;
    }


}
