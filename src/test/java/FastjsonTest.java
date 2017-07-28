import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

import java.util.List;
import java.util.Map;

/**
 * @author lierl
 * @create 2017-07-21 14:08
 **/
public class FastjsonTest {
	public static void main(String[] args) {
		String aa = "{\"a\":\"aa\",\"b\":\"bb\",\"c\":[{\"c\":\"c\"},{\"d\":\"d\"}]}";
		Map<String,Object> map = JSON.parseObject(aa, Map.class, Feature.OrderedField);
		List<Map<String,Object>> data = (List<Map<String,Object>>)map.get("c");
		System.out.println(data.get(0).get("c"));
	}
}


