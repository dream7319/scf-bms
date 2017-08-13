package com.lierl.api.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletResponse;

public class Utils {

	public static final String SECRET = "abcoop";

	private static final String HMAC_SHA256 = "HmacSHA256";


	public static String hashHmac(String data, String secret) {
		try {
			Mac sha256_HMAC = Mac.getInstance(HMAC_SHA256);
			SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), HMAC_SHA256);
			sha256_HMAC.init(secret_key);
			return Base64.encodeBase64String(sha256_HMAC.doFinal(data.getBytes()));
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean write(HttpServletResponse response, int status) {
		response.setStatus(status);
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		return false;
	}
}
