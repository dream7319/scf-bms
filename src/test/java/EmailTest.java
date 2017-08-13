import com.lierl.api.util.SendMailUtils;

/**
 * Created by lierl on 2017/8/13.
 */
public class EmailTest {
    public static void main(String[] args) throws Exception {

        SendMailUtils.sendMail("643055803@qq.com",
                "Java Mail 测试邮件3",
                "<a>html 元素</a>：<b>邮件内容</b>");
    }
}
