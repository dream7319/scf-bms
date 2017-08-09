import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.Feature;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lierl
 * @create 2017-08-08 15:47
 **/
public class JSONArrayTest {
	public static void main(String[] args) {
		String aa ="{\"aa\":[\"aa\",\"bb\",\"cc\"],\"mm\":\"mm\"}";

		Map<String,Object> map = JSON.parseObject(aa, HashMap.class, Feature.OrderedField);

		JSONArray bb = (JSONArray)map.get("aa");

		for (Object o : bb) {
			System.out.println(o);
		}

		System.out.println("-"+JSON.toJSONString(null));

//		System.out.println(bb.getClass());
	}
}
