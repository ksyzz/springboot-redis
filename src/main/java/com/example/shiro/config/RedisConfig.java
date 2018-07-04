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


    /**
     * 如果只使用注解则不需要配置该bean
     * @param jedisConnectionFactory
     * @return
     */
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
        // redis2.x以后，cacheManager 不再需要RedisTemplate来创建了
        RedisCacheManager cacheManager = RedisCacheManager.builder(jedisConnectionFactory)
                .build();
        cacheManager.initializeCaches();
        return cacheManager;
    }
}
