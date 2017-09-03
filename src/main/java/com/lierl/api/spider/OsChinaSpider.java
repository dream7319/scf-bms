package com.lierl.api.spider;

import com.google.common.collect.Lists;
import org.springframework.kafka.core.KafkaTemplate;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * @author lierl
 * @create 2017-08-29 16:48
 **/
public class OsChinaSpider implements PageProcessor{

	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000)
			.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");

	public static final String URL_LIST = "https://www.oschina.net/blog/action/ajax/get_more_recommend_blog\\?classification=0&p=\\d{1,3}";

	public static int pageNum = 272;

	private KafkaTemplate<String, String> template;

	public OsChinaSpider() {
	}

	public OsChinaSpider(KafkaTemplate<String, String> template) {
		this.template = template;
	}

	static List<String> urls = Lists.newArrayList();

	@Override
	public void process(Page page) {
		if (page.getUrl().regex("^https://www\\.oschina\\.net/blog$").match()) {
			try {
				List<String> urls = page.getHtml().xpath("//*[@id=\"topsOfRecommend\"]//div[@class='box item']/div[@class='box-fl']/a/@href").all();
				page.addTargetRequests(urls);
				pageNum++;
				page.addTargetRequest("https://www.oschina.net/blog/action/ajax/get_more_recommend_blog?classification=0&p=273");
			} catch (Exception e) {
				e.printStackTrace();
			}//417
		} else if(page.getUrl().regex(URL_LIST).match() && pageNum <=417 ){
			try {
				List<String> urls = page.getHtml().xpath("//*div[@class='box item']/div[@class='box-fl']/a/@href").all();
				page.addTargetRequests(urls);
				page.addTargetRequest("https://www.oschina.net/blog/action/ajax/get_more_recommend_blog?classification=0&p=" + ++pageNum);
				System.out.println("CurrPage:" + pageNum + "#######################################");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else {

//			PersonBlogOschina os = new PersonBlogOschina(homeUrl,template);
//			Spider.create(os).addUrl(homeUrl).thread(5).run();
		}

	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) throws IOException {
		Spider.create(new OsChinaSpider()).addUrl("https://www.oschina.net/blog/action/ajax/get_more_recommend_blog?classification=0&p=272").thread(2).run();

		Files.write(Paths.get("E:\\urls.txt"), urls,StandardOpenOption.APPEND);
		System.out.println("搜集总数："+urls.size());
		System.out.println("页数："+pageNum);
	}
}
