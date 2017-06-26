package com.lierl.util;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

public class GsonUtil {

	public static Gson getGson() {
		return new Gson();
	}
	
	public static JSONObject getJsonObject(String json) {
		
		return new JSONObject();
		
	}

	public static String getJsonString(Object o) {
		return getGson().toJson(o);
		
	}
	
	public static  <T> T  getObject(String json, Class<T> o) {
		return 		getGson().fromJson(json, o);
	}
}
