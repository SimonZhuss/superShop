package com.zss.superShop.api.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoReq {

	@ApiModelProperty(value = "用户名")
	private String name;
	
	@ApiModelProperty(value = "密码")
	private String login;
}
