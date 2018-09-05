package com.zss.superShop.constants;

public interface WebConstants {

	public static final String HUISHI = "huishi";
	
	public static final String HEADER_TOKEN = "token";
	
	public static final String HEADER_PLATFORM = "platform";
	
	public static final String HEADER_TIMESTAMP = "timestamp";
	
	public static final String USER_LOGIN_SUCCESS_MSG = "security1";
	
	public static final String DEFAULT_ORDER_ID = "-1";
	
	public static final String YES = "1";
	
	public static final String NO = "0";
	
	public static final int VALID = 1;
	
	public static final int INVALID = 0;
	
	//系统操作类型
	public enum OPT_TYPE{
		
		SUBMIT("提交"),CANCEL("取消"),REJECT("补资料");
		
		public String desc;
		
		OPT_TYPE(String desc){
			this.desc = desc;
		}
	}
	
	//系统分隔符
	public enum DELIMITER{
		
		BAR("-");
		
		public String code;
		
		DELIMITER(String code){
			this.code = code;
		}
	}
}
