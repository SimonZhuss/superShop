package com.zss.superShop.manager;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zss.superShop.api.entity.HeaderQuery;
import com.zss.superShop.api.entity.RspCode;
import com.zss.superShop.api.entity.WebUserAuth;
import com.zss.superShop.cache.WebUserCache;
import com.zss.superShop.constants.WebConstants;
import com.zss.superShop.convert.WebUserConvert;
import com.zss.superShop.exception.SuperShopException;
import com.zss.superShop.utils.FastJsonUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class WebUserManager {

	@Autowired
	private WebUserCache webUserCache;
	
	/**
	 * 获取请求体
	 * @return
	 */
	public HttpServletRequest getRequest(){
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	public HeaderQuery convertHeader(){
		HttpServletRequest request=getRequest();
        String platform=request.getHeader(WebConstants.HEADER_PLATFORM);
        String timestamp=request.getHeader(WebConstants.HEADER_TIMESTAMP);
        String token=request.getHeader(WebConstants.HEADER_TOKEN);
        return new HeaderQuery(platform,timestamp,token);
	}
	
	/**
	 * 验证用户
	 * @param headerParam
	 * @return
	 */
	public WebUserAuth authToken(){
		HeaderQuery header = convertHeader();
		if(header== null || StringUtils.isBlank(header.getToken())) throw new SuperShopException(RspCode.USER_INVALID); 
		WebUserAuth tokenDTO = WebUserConvert.convertTokenDTO(header.getToken());
		if(tokenDTO == null
				|| tokenDTO.getUserId() == null
				|| StringUtils.isBlank(tokenDTO.getLoginName())) throw new SuperShopException(RspCode.USER_INVALID); 
		String r = webUserCache.getToken(tokenDTO.getUserId().toString());
		if(!header.getToken().equals(r))throw new SuperShopException(RspCode.USER_INVALID); 
		log.info(">>>>>>auth user tokenDTO:{}",FastJsonUtil.convertObjToStr(tokenDTO));
		return tokenDTO;
	}
}
