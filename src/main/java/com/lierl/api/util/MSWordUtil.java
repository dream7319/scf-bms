package com.lierl.api.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;


public class MSWordUtil {

	/**
	 * 模板替换
	 * 
	 * @param templatePath
	 * @param dst
	 * @param params
	 * @throws Exception
	 */
	public static void replace(String templatePath, String dst, Map<String, String> params) throws Exception {

		InputStream is = new FileInputStream(templatePath);

		try {

			HWPFDocument doc = new HWPFDocument(is);
			Range range = doc.getRange();
			if (!params.isEmpty()) {
				for (String key : params.keySet()) {
					String value = params.get(key);
					range.replaceText(key, value);
				}
			}

			OutputStream os = new FileOutputStream(dst);
			// 把doc输出到输出流中
			doc.write(os);
			closeStream(os);
		} finally {
			closeStream(is);

		}
	}

	/**
	 * 关闭输入流
	 * 
	 * @param is
	 */
	private static void closeStream(InputStream is) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭输出流
	 * 
	 * @param os
	 */
	private static void closeStream(OutputStream os) {
		if (os != null) {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	

}
