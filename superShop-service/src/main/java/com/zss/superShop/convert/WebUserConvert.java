package com.zss.superShop.convert;

import com.zss.superShop.api.entity.HeaderQuery;
import com.zss.superShop.api.entity.WebUserAuth;
import com.zss.superShop.utils.AESUtil;

/**
 * web用户封装
 * @author zhushanshan
 * @2018年7月17日 下午3:58:00
 */
public class WebUserConvert {
	
	//封装token的值
	public static String convertTokenValue(HeaderQuery hq,Integer userId,String userName){
		StringBuffer sb = new StringBuffer(userId);
		sb.append(userId).append("-");
		sb.append(hq.getPlatform()).append("-");
		sb.append(hq.getTimestamp()).append("-");
		sb.append(userName);
		return AESUtil.encrypt(sb.toString());
	}
	
	//token转换成TokenDTO
	public static WebUserAuth convertTokenDTO(String token){
		WebUserAuth  r = new WebUserAuth();
		String t = AESUtil.decrypt(token);
		String[] ts = t.split("-");
		r.setUserId(Integer.valueOf(ts[0]));
		r.setLoginName(ts[3]);
		return r;
	}
}
