package com.cts.searchprofile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.cts.searchprofile.data.Profile;

@Configuration
@EnableRedisRepositories(basePackages = {"com.cts.searchprofile.data.repository"})
public class RedisConfig {
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
	    return new JedisConnectionFactory();
	}

	@Bean
	public RedisTemplate<String, Profile> redisTemplate() {
	    RedisTemplate<String, Profile> template = new RedisTemplate<>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    template.setHashKeySerializer(new StringRedisSerializer());
	    template.setKeySerializer(new StringRedisSerializer());
	    template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
	    template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
	    return template;
	}

}
