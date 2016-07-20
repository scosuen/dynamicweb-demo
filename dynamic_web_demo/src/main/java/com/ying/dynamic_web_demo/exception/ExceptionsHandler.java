package com.ying.dynamic_web_demo.exception;

import java.io.IOException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ying.dynamic_web_demo.utils.CommonJSONs;

@ControllerAdvice
@ResponseBody
public class ExceptionsHandler {

	@ExceptionHandler(Exception.class)
	public String handleException(NativeWebRequest request, Exception e) {
		return CommonJSONs.getErrorJson(100, "Unknown exception. " + e.getMessage());
	}
	
	@ExceptionHandler(IOException.class)
	public String handleIOException(NativeWebRequest request, IOException e) {
		return CommonJSONs.getErrorJson(101, "Json file cannot be read. " + e.getMessage());
	}
	
	@ExceptionHandler(JsonParseException.class)
	public String processJsonParseException(NativeWebRequest request, JsonParseException e) {
		return CommonJSONs.getErrorJson(102, "Json parse exception. " + e.getMessage());
	}
	
	@ExceptionHandler(JsonMappingException.class)
	public String processJsonMappingException(NativeWebRequest request, JsonMappingException e) {
		return CommonJSONs.getErrorJson(103, "Json cannot be mapped to object. " + e.getMessage());
	}
	
	@ExceptionHandler(JsonProcessingException.class)
	public String processJsonProcessingException(NativeWebRequest request, JsonProcessingException e) {
		return CommonJSONs.getErrorJson(104, "Object cannot map to Json. " + e.getMessage());
	}
	
	@ExceptionHandler(NoDataFoundException.class)
	public String processJsonNoDataFoundException(NativeWebRequest request, NoDataFoundException e) {
		return e.getErrorJson();
	}
}
