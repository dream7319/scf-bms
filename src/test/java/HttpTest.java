import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

/**
 * @author lierl
 * @create 2017-08-08 13:23
 **/
public class HttpTest {
	public static OkHttpClient client;

	private static OkHttpClient getClient() {
		if(client == null){
			final okhttp3.OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
			client = httpBuilder
					.connectTimeout(10, TimeUnit.SECONDS)
					.writeTimeout(15, TimeUnit.SECONDS)
					.build(); //设置超时
		}

		return client;
	}

	public static void main(String[] args) throws Exception{
		String content = "{\"channelId\":\"1\",\"method\":\"loanApplyResultNotify\",\"params\":\"{\\\"commissions\\\":0.0,\\\"loanAmount\\\":1.0,\\\"loanId\\\":\\\"20170808092950000028\\\",\\\"loanTerm\\\":3,\\\"orders\\\":[{\\\"loanAmount\\\":2333.86,\\\"sourceOrderId\\\":\\\"SF170721015717\\\"}],\\\"paymentOption\\\":1,\\\"result\\\":1}\",\"sign\":\"ZrdV+id8JjK3NU6fOiKcGjB04+GE4mROoltoMGXmr1ohA/6XymBEb1pBFD8CNHtQ8q+oBfnHZEy4XNV+0+3shK4yLhyMiD2CPY7yFni1YqUkqViBAZDeLhLahOoqtY/feiLSZ0W6zIl/Ai4ASmleiwC1r5RX1fmRI/5lLctAZNA=\",\"signType\":\"RSA2\",\"ver\":\"1.0\"}";

		RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
				URLEncoder.encode(content, "UTF-8"));
		Request request = new Request.Builder()
				.post(body)
				.url("http://openapi.ickey.cn/loan/loan/loan-result")
				.build();
		OkHttpClient client = getClient();
		Response response = client.newCall(request).execute();
		if (response.isSuccessful()) {
			String result = response.body().string();//响应
			System.out.println(result);
		}
	}
}
