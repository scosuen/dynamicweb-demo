package com.ying.dynamic_web_demo.exception;

import com.ying.dynamic_web_demo.utils.CommonJSONs;

public class NoDataFoundException extends Exception {

	public NoDataFoundException() {
		super();
	}

	public NoDataFoundException(String message) {
		super(message);
	}
	
	public String getErrorJson () {
		return CommonJSONs.getErrorJson(200, "No data found. " + getMessage());
	}
}
