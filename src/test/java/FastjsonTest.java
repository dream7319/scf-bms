/**
 * Created by Administrator on 2017/7/21.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Maps;

import java.util.Date;
import java.util.Map;

/**
 * @author lierl
 * @create 2017-07-21 14:08
 **/
public class FastjsonTest {
	public static void main(String[] args) {
		Map<String,Object> datas = Maps.newHashMap();
		datas.put("bb","aa");
		datas.put("aa",new Date());
		datas.put("cc",Gender.FAMALE.getValue());

		String mm = JSON.toJSONStringWithDateFormat(datas,"yyyy-MM-dd", SerializerFeature.PrettyFormat);

		System.out.println(mm);
	}
}

enum Gender{
	MALE(1),FAMALE(2);

	private int value;

	private Gender(int value){
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
