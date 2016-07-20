package com.ying.dynamic_web_demo.utils;

public class CommonJSONs {

	/**
	 * {"success": true} 
	 * {"success": false, "error": { "code": 1, "message": "message."} }
	 * 
	 * @param errorCode
	 * @param errorMsg
	 * @return
	 */
	public static String getErrorJson(int errorCode, String errorMsg) {
		StringBuffer json = new StringBuffer("{\"success\": ");

		if (errorCode > 0)
			json.append("false");
		else
			json.append("true");

		if (errorCode > 0) {
			json.append(", \"error\": { \"code\": ").append(errorCode).append(", ");
			json.append("\"message\": \"").append(errorMsg).append("\"} ");
		}

		json.append("}");
		return json.toString();
	}
	
	public static String getSuccResponseJSON() {
		return getErrorJson(0, null);
	}
}
