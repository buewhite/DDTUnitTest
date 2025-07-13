package com.bue.test.util;

import lombok.Data;

@Data
public class BusinessException extends Exception{

	private static final long serialVersionUID = 1L;
	String errorCode;
	String errorDesc;
	
	public BusinessException(String errorCode, String errorDesc) {
		super("errorCode:"+errorCode+", errorDesc:"+errorDesc);
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}
	
}
