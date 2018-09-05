package com.zss.superShop.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebUserAuth {

	private Integer userId;
	
	private String loginName;
}
