package com.bue.test.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bue.test.msg.CommonResp;
import com.bue.test.util.BusinessException;

import lombok.extern.log4j.Log4j2;

@RestControllerAdvice
@Log4j2
public class ExceptionAdvise {

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = {Exception.class})
	public @ResponseBody Object handleException(Exception e) {
		log.error(e);
		return CommonResp.builder().httpStatus(""+HttpStatus.INTERNAL_SERVER_ERROR).errDesc(e.getClass()+":"+e.getMessage()).build();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = {BusinessException.class})
	public @ResponseBody Object handleException(BusinessException e) {
		log.error(e.getMessage());
		return CommonResp.builder().httpStatus(""+HttpStatus.BAD_REQUEST).errDesc(e.getErrorCode()+":"+e.getErrorDesc()).build();
	}
}
