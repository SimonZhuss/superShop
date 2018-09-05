package com.zss.superShop.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zss.superShop.api.entity.DataResponse;
import com.zss.superShop.api.entity.RspCode;

/**
 * Created by qhong on 2018/5/28 15:51
 **/
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


	/**
	 * 程序异常
	 */
	@ExceptionHandler(SuperShopException.class)
	@ResponseBody
	public DataResponse handleHuishiWebException(SuperShopException e) throws SuperShopException {
		DataResponse r = new DataResponse();
		r.setResCode(e.getCode());
		r.setMsg(e.getMsg());
		log.info("HuishiWebException:{}",e.getMsg());
		return r;
	}

	/**
	 * 验证异常
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public DataResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e)
			throws MethodArgumentNotValidException {
		DataResponse r = new DataResponse();
		BindingResult bindingResult = e.getBindingResult();
		String errorMesssage = "Invalid Request:\n";

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			errorMesssage += fieldError.getDefaultMessage() + "\n";
		}
		r.setResCode(RspCode.PARAM_INVALID.getCode());
		r.setMsg(errorMesssage);
		log.info("MethodArgumentNotValidException:{}",errorMesssage);
		return r;
	}

	/**
	 * 全局异常
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public DataResponse handleException(Exception e) throws Exception {
		DataResponse r = new DataResponse();
		r.setResCode(RspCode.CODE_ERROR.getCode());
		r.setMsg(RspCode.CODE_ERROR.getMessage());
	/*	if(e.getCause() instanceof MySQLIntegrityConstraintViolationException){
			r.setResCode(RspCode.DATA_REPEAT.getCode());
			r.setMsg(RspCode.DATA_REPEAT.getMessage());
		}*/
		log.error(e.getMessage(), e);
		return r;
	}
}
