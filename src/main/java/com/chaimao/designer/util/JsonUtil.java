package com.chaimao.designer.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonUtil {

	public static ObjectMapper objectMapper;
	/**
	 * 
	* @Title: getJsonString 
	* @Description: 获取json值，若为空则返回空
	* @param @param json
	* @param @param key
	* @param @return 
	* @return String    
	* @throws
	 */
	public static String getJsonString(JSONObject json, String key) {
		String result = "";
		try {
			return result = json.getString(key);
		} catch (Exception e) {
			return "";
		}
		
	}
	
	/**
	 * 把JavaBean转换为json字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String toJSon(Object object) {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
