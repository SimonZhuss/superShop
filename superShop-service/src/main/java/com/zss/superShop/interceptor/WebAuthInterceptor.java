package com.zss.superShop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zss.superShop.annotation.LoginAuth;
import com.zss.superShop.api.entity.RspCode;
import com.zss.superShop.constants.WebConstants;
import com.zss.superShop.exception.SuperShopException;
import com.zss.superShop.utils.AESUtil;


/**
 * huishi-web统一验证处理
 * @author zhushanshan
 * @2018年7月16日 下午1:36:31
 */
@Component
public class WebAuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		//验证header中的platform
		String platform = request.getHeader(WebConstants.HEADER_PLATFORM);
		if(StringUtils.isBlank(platform)){
			throw new SuperShopException(RspCode.PARAM_INVALID.getCode(),RspCode.PARAM_INVALID.getMessage()+WebConstants.HEADER_PLATFORM);
		}
		
		//验证header中的timestamp
		String timestamp = request.getHeader(WebConstants.HEADER_TIMESTAMP);
		if(StringUtils.isBlank(timestamp)){
			throw new SuperShopException(RspCode.PARAM_INVALID.getCode(),RspCode.PARAM_INVALID.getMessage()+WebConstants.HEADER_TIMESTAMP);
		}
		
		//验证token
		LoginAuth loginAuth;
		if(handler instanceof HandlerMethod) {
			loginAuth = ((HandlerMethod) handler).getMethodAnnotation(LoginAuth.class);
		}else{
			return true;
		}
		//如果方法上有LoginIgnore注解且值为true,则表示必须要用户登录
		if(loginAuth != null && loginAuth.value()){
			String token = request.getHeader(WebConstants.HEADER_TOKEN);
			if(StringUtils.isBlank(token)){
				throw new SuperShopException(RspCode.USER_INVALID);
			}
			//解密token
			String t = AESUtil.decrypt(token);
			String[] ts = t.split("-");
			if(ts.length != 4) throw new SuperShopException(RspCode.USER_INVALID);
			if(!ts[1].equals(platform) || StringUtils.isBlank(ts[2]))throw new SuperShopException(RspCode.USER_INVALID);
		}

		return true;
	}
}
