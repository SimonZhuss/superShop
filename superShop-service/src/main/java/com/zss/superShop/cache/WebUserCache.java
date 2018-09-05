package com.zss.superShop.cache;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zss.superShop.api.entity.RspCode;
import com.zss.superShop.exception.SuperShopException;
import com.zss.superShop.manager.RedisManager;

@Component
public class WebUserCache {
	public static final String USER_TOKEN_KEY = "TOKEN";
	public static final Long USER_TOKEN_LIVER = 30*24*3600l;
	
	public static final String USER_RULE_KEY = "RULE";
	public static final Long USER_RULE_LIVER = 5*60*60l;
	
	public static final String USER_MENU_KEY = "MENU";
	public static final Long USER_MENU_LIVER = 5*60*60l;

	@Autowired
	private RedisManager redisUtil;
	
	public void setToken(String key,String value){
		boolean result  = redisUtil.set(USER_TOKEN_KEY,key, value, USER_TOKEN_LIVER);
		if(!result) throw new SuperShopException(RspCode.DATA_ERROR);
	}
	
	public String getToken(String key){
		return (String) redisUtil.get(USER_TOKEN_KEY+":"+key);
	}
	
	public void cleanUser(String key){
		redisUtil.remove(USER_TOKEN_KEY, key);
		redisUtil.remove(USER_RULE_KEY, key);
		redisUtil.remove(USER_MENU_KEY, key);
	}
}
