package com.zss.superShop.api.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "base响应对象")
public class BaseResponse  implements Serializable {
	private String resCode = RspCode.SUCCESS.getCode();

	private String msg = RspCode.SUCCESS.getMessage();

	@ApiModelProperty(value = "结果编码", required = true)
	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	@ApiModelProperty(value = "结果描述", required = true)
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}