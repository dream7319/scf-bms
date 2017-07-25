/**
 * Created by Administrator on 2017/7/18.
 */

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.util.Date;

/**
 * @author lierl
 * @create 2017-07-18 15:30
 **/
public class JodaTimeTest {

	@Test
	public void test1(){
		DateTimeFormatter pattern = DateTimeFormat.forPattern("yyyy-MM-dd");

		System.out.println(pattern.print(DateTime.now()));

		DateTime dt = DateTime.now();

		System.out.println("--"+new DateTime().plusMonths(1).minusDays(1).toDate());

		System.out.println(dt.toString("yyyy年MM月dd日"));

		System.out.println(DateFormatUtils.format(new Date(),"yyyy-MM-dd"));

		System.out.println(StringUtils.uncapitalize("UserServiceImpl"));


	}
}
