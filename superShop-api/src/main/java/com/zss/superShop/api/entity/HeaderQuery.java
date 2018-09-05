package com.zss.superShop.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * 公共入参-header中
 * @author zhushanshan
 * @2018年7月13日 下午5:09:34
 */
@Data
@ToString
@AllArgsConstructor
public class HeaderQuery {

	/**
	 * 	类型 1 web
 	 */
	private String platform;
	/**
	 * 时间戳
	 */
	private String timestamp; 
	
	//用户唯一标识
	private String token;
}
