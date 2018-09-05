package com.zss.superShop.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@ConfigurationProperties("redis")
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	private Logger logger = LoggerFactory.getLogger(RedisConfig.class);
	private String host = "120.26.130.187";
	private int port = 6379;
	private String password = "jsy2017++";
	private Integer database=3;

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Bean
	public CacheManager cacheManager(RedisTemplate redisTemplate) {
		RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
		//默认过期时间1小时
		redisCacheManager.setDefaultExpiration(60 * 60);
		return redisCacheManager;
	}

	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setDatabase(database);
		factory.setHostName(host);
		factory.setPort(port);
		factory.setPassword(password);
		return factory;
	}

	@Bean
	public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate template = new RedisTemplate();
		template.setConnectionFactory(redisConnectionFactory);
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		StringRedisSerializer keyKeySerializer = new StringRedisSerializer();
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.setKeySerializer(keyKeySerializer);
		template.afterPropertiesSet();
		return template;
	}

	@Override
	@Bean
	public CacheErrorHandler errorHandler() {
		return new CacheErrorHandler() {
			@Override
			public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
				logger.error("handleCacheGetError,key:{}", key, exception);
			}

			@Override
			public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
				logger.error("handleCachePutError,key:{}", key, exception);

			}

			@Override
			public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
				logger.error("handleCacheEvictError,key:{}", key, exception);
			}

			@Override
			public void handleCacheClearError(RuntimeException exception, Cache cache) {
				logger.error("handleCacheClearError,key:{}", exception);
				throw exception;
			}
		};
	}
}

