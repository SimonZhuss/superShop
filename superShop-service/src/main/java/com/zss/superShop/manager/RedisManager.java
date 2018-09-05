package com.zss.superShop.manager;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * Created by qhong on 2018/6/22 16:26
 **/
@Component
public class RedisManager {

	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * 删除对应的value
	 *
	 * @param key
	 */
	public void remove(final String prefix, final String key) {
		if (exists(prefix, key)) {
			String fullKey = prefix + ":" + key;
			redisTemplate.delete(fullKey);
		}
	}

	/**
	 * 判断缓存中是否有对应的value
	 *
	 * @param key
	 * @return
	 */
	public boolean exists(final String prefix, final String key) {
		String fullKey = prefix + ":" + key;
		return redisTemplate.hasKey(fullKey);
	}

	/**
	 * 读取缓存
	 *
	 * @param key
	 * @return
	 */
	public Object get(final String prefix, final String key) {
		Object result = null;
		String fullKey = prefix + ":" + key;
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		result = operations.get(fullKey);
		return result;
	}
	/**
	 * 写入缓存
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 写入缓存
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String prefix, final String key, Object value) {
		boolean result = false;
		String fullKey = prefix + ":" + key;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(fullKey, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 写入缓存
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String prefix, final String key, Object value, Long expireTime) {
		boolean result = false;
		String fullKey = prefix + ":" + key;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(fullKey, value);
			redisTemplate.expire(fullKey, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 读取缓存
	 *
	 * @param key
	 * @return
	 */
	public Object get(final String key) {
		Object result = null;
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		result = operations.get(key);
		return result;
	}
}
