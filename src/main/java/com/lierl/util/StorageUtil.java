package com.lierl.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

/**
 * 本地访问存储到工具
 * @author hanyouhong
 *
 */
public final class StorageUtil {

    public static final String ZERO = "0";

    public static final String[] UNITS = new String[]{"b", "kb", "Mb", "Gb", "Tb"};

    public static final int Kib = 1024;

    public static final long NORMAL_FILE_SIZE = 2 * Kib * Kib;

    static final String DECIMAL_FORMAT_PATTERN = "#,##0.#";

    static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat(DECIMAL_FORMAT_PATTERN);

    public static String url;

    public static OkHttpClient client;
    
    public static String getUrl(){
    	if(url==null || "".equals(url.trim())){
//    		url =  PropertiesUtil.load("server.properties","fs.server");
    	}
    	return url;
    }
    
    private StorageUtil() {
    }

    public static boolean isLarge(long size) {
        return size > NORMAL_FILE_SIZE;
    }

    public static long getSize(Path path) {
        long size = 0;

        try {
            size = Files.size(path);
        } catch (Exception e) {
        }
        return size;
    }

    public static String toString(long size) {
        if (size < 1) return ZERO;

        final int digitGroups = (int) (Math.log10(size) / Math.log10(Kib));
        final String amount = DECIMAL_FORMAT.format(size / Math.pow(Kib, digitGroups));
        final String unit = UNITS[digitGroups];

        return String.format("%s %s", amount, unit);
    }
    
    private static OkHttpClient getClient() {
    	if(client == null){
    		final OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
    		client = httpBuilder
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build(); //设置超时
    	}
    	  
    	return client;                 
    }
    
    public static void putfileToServer(byte[] fileByte,String filename) throws Exception{
    	Gson gson = new GsonBuilder().serializeNulls().create();

    	RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), fileByte);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM) 
                .addFormDataPart("file", filename, fileBody)
                .build();

        String server_url = getUrl();

        
        Request request = new Request.Builder()
                .url(server_url)
                .addHeader("Cookie", "111")
                 .addHeader("Connection", "close")
                .post(requestBody)
                .build();
        OkHttpClient client = getClient();
      
    	Response response = client.newCall(request).execute();
		String res = response.body().string();
    } 

    public static void putfileToServer(File file) throws Exception{
    	Gson gson = new GsonBuilder().serializeNulls().create();

    	RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM) 
                .addFormDataPart("file", file.getName(), fileBody)
                .build();

        
        String server_url = getUrl();

        
        Request request = new Request.Builder()
                .url(server_url)
                .addHeader("Cookie", "1")
                 .addHeader("Connection", "close")
                .post(requestBody)
                .build();
        OkHttpClient client = getClient();
      
    	Response response = client.newCall(request).execute();
		String res = response.body().string();
    }

    /**
     * 从服务其放回文件对象
     * @param url
     * @return
     */
    public static boolean getFileFromServer(String url,File dst) {
        Request request = new Request.Builder()
        		.url(url)
        		.addHeader("Cookie", "111")
        		.build();
        OkHttpClient client = getClient();
    	Response response;
    	boolean result = false;
		try {
			response = client.newCall(request).execute();
			if(response.isSuccessful()) {
	    		  InputStream is = null;
	              byte[] buf = new byte[2048];
	              int len = 0;
	              FileOutputStream fos = null;
	              // 储存下载文件的目录
	              try {
	                  is = response.body().byteStream();
//	                  long total = response.body().contentLength();
//	                  File file = new File(dst), getNameFromUrl(url));
	                  fos = new FileOutputStream(dst);
//	                  long sum = 0;
	                  while ((len = is.read(buf)) != -1) {
	                      fos.write(buf, 0, len);
//	                      sum += len;
	                  }
	                  fos.flush();
	                  result = true;
	                  // 下载完成
	              } catch (Exception e) {
	            	  
	              } finally {
	                  try {
	                      if (is != null)
	                          is.close();
	                  } catch (IOException e) {
	                  }
	                  try {
	                      if (fos != null)
	                          fos.close();
	                  } catch (IOException e) {
	                  }
	              }
	    	} else {
	    		String t= response.body().string();
	    	}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
    	return result;
    }
    
    /*public static void main(String[] args) {
    	File f = new File("E:\\aa.xlsx");
    	System.out.println(f.getName());
    	try {
    		StorageResult ret = putfileToServer(f,null,"admin01");
    		System.out.println(ret.toString());
    		if(ret.ok==1) {
    			String url = ret.data.url;
    			File dst = new File("E:\\repayment\\cc.xlsx");
    			if(getFileFromServer(url,dst,null,"admin01")) {
    				System.out.println("succ");
    			}
    			
    		}
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	}
    }*/
}
