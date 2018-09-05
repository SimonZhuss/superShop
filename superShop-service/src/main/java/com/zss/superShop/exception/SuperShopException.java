package com.zss.superShop.exception;

import com.zss.superShop.api.entity.RspCode;

/**
 * 慧石web统一业务异常
 * @author zhushanshan
 * @2018年7月16日 下午6:43:03
 */
public class SuperShopException extends RuntimeException{
	private static final long serialVersionUID = 4404150705068861840L;
	
	private String code;
    private String msg;

    public SuperShopException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public SuperShopException(String message) {
        super(message);
        this.msg = message;
    }

    public SuperShopException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public SuperShopException(RspCode c) {
    	 this.code = c.getCode();
         this.msg = c.getMessage();
    }
    
    public SuperShopException(Throwable cause) {
        super(cause);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
