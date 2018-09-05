package com.zss.superShop.api;

import com.zss.superShop.api.entity.DataResponse;
import com.zss.superShop.api.req.UserInfoReq;
import com.zss.superShop.api.vo.UserInfoVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/user/")
@Api(value ="用户管理",tags={"userManager"})
public interface UserFacade {

	@ApiOperation(value = "登录")
	@ApiResponse(code = 200, message = "请求已完成", response = DataResponse.class)
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	DataResponse login(@RequestBody UserInfoReq queryReq);
	
	@ApiOperation(value = "查询用户")
	@ApiResponses({
		@ApiResponse(code = 200, message = "请求已完成", response = UserInfoVO.class)
	})
	@RequestMapping(path = "/queryUser", method = RequestMethod.POST)
	DataResponse queryUser(@RequestBody UserInfoReq queryReq);
}