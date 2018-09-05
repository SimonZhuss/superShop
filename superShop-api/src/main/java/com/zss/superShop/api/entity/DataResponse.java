package com.zss.superShop.api.entity;

import lombok.ToString;

/**
 * Created by yijy on 2017/11/22.
 */
@ToString
public class DataResponse extends BaseResponse{

	private static final long serialVersionUID = -2479705076778238646L;
	private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    public DataResponse(){
	}
    
    public DataResponse(Object data,String code,String msg){
		this.data = data;
		this.setResCode(code);
		this.setMsg(msg);
	}

	public static DataResponse process(Object data, RspCode code){
		return new DataResponse(data,code.getCode(),code.getMessage());
	}
}
