package com.lierl.util;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpUtil {
	
	private static OkHttpClient client;

	private static OkHttpClient getClient() {
		if (client == null)
			client = new OkHttpClient();
		return client;
	}
	/**
	 * 
	 *	RequestBody body = new FormBody.Builder().add("description", "a").build();
	 * @param url
	 * @param body
	 * @throws IOException
	 */
	public static String post(String url,RequestBody body) throws IOException{
		String result =getClient().newCall(new Request.Builder().url(url).post(body).build()).execute().body().string();
		return result;
	}
	
	public static String get(String url) throws IOException{
		String result= getClient().newCall(new Request.Builder().url(url).get().build()).execute().body().string();
		return result;
	}
	
	
}
