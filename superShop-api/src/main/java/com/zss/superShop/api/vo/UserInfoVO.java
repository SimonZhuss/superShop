package com.zss.superShop.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * 征信信息返回对象
 * @author zhushanshan
 * @2018年6月20日 下午8:33:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVO implements Serializable {

    private static final long serialVersionUID = -6582521392950573499L;

	@ApiModelProperty(value = "用户名")
	private String name;
	
	@ApiModelProperty(value = "密码")
	private String pwd;
}
