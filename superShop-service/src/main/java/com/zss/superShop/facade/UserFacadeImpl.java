package com.zss.superShop.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zss.superShop.annotation.LoginAuth;
import com.zss.superShop.api.UserFacade;
import com.zss.superShop.api.entity.DataResponse;
import com.zss.superShop.api.entity.RspCode;
import com.zss.superShop.api.entity.WebUserAuth;
import com.zss.superShop.api.req.UserInfoReq;
import com.zss.superShop.api.vo.UserInfoVO;
import com.zss.superShop.manager.WebUserManager;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserFacadeImpl implements UserFacade{
	
	@Autowired
	WebUserManager webUserManager;

	@Override
	public DataResponse login(@RequestBody @Validated UserInfoReq queryReq) {
    	if(queryReq.getName().equals("aa"))return DataResponse.process(null, RspCode.SUCCESS);
    	return DataResponse.process(null, RspCode.OPT_FAILED);
	}

	@Override
	@LoginAuth
	public DataResponse queryUser(@RequestBody @Validated UserInfoReq queryReq) {
		WebUserAuth user = webUserManager.authToken();
    	log.info(">>>>>>用户登录|| param :{}",user.getLoginName(),queryReq);
    	return DataResponse.process(new UserInfoVO("zhuss","1111111"), RspCode.SUCCESS);
	}

}
