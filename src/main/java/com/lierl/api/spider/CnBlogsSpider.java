package com.lierl.api.spider;

import com.lierl.api.spider.bean.CnBlogs;
import org.assertj.core.util.Lists;
import org.springframework.kafka.core.KafkaTemplate;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author lierl
 * @create 2017-08-29 14:14
 **/
public class CnBlogsSpider implements PageProcessor {

	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000)
			.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");

	public static final String URL_LIST = "https://www.cnblogs.com/sitehome/p/\\d{1,3}";

	public static int pageNum = 1;

	private String blogPageUrl;
	private String homeUrl;
	private int totalPageNum;

	private KafkaTemplate<String, String> template;

	private String author;

	public CnBlogsSpider(String author, KafkaTemplate<String, String> template){
		this.author = author;
		this.homeUrl = "http://www.cnblogs.com/"+author+"/default.html";
		this.blogPageUrl = homeUrl+"\\?page=\\d{1,4}";
		this.pageNum = 1;
		this.totalPageNum = 2;
		this.template = template;
	}

	@Override
	public void process(Page page) {
		if(page.getUrl().regex("http://www.cnblogs.com/mvc/blog/news.aspx\\?blogApp=\\w+").match()){
			String name = page.getHtml().css("div#profile_block").xpath("//a[1]/text()").get();
			String age = page.getHtml().css("div#profile_block").xpath("//a[2]/text()").get();
			String fans = page.getHtml().css("div#profile_block").xpath("//a[3]/text()").get();
			String follow = page.getHtml().css("div#profile_block").xpath("//a[4]/text()").get();
//			blogs.setName(name);
//			blogs.setAge(age);
//			blogs.setFans(fans);
//			blogs.setFollow(follow);
			page.addTargetRequest("http://www.cnblogs.com/"+author+"/default.html");
		}else{
			if(pageNum == 1){
				String blogs = page.getHtml().css("div#navigator").xpath("//div[@id='blog_stats']/text()").get().trim();
//				随笔- 248  文章- 24  评论- 25 
				String essay = blogs.split("-")[0].trim();
				String article = blogs.split("-")[1].trim();
				String allComments = blogs.split("-")[2].trim();

			}

			if(page.getUrl().regex("^"+homeUrl+"$").match()){
				List<String> urls = page.getHtml().css("div.forFlow").css("div.day").css("div.postTitle").xpath("//a/@href").all();
				page.addTargetRequests(urls);
				pageNum++;
				page.addTargetRequest(homeUrl+"?page=2");
			}else if(page.getUrl().regex(blogPageUrl).match() && pageNum <= totalPageNum){
				if(pageNum == 2){
					String total = page.getHtml().css("div#homepage_top_pager").xpath("//div[@class='pager']/text()").get();
					totalPageNum = Integer.valueOf(total.split(":")[0].replace("共","").replace("页","").trim());
				}
				List<String> urls = page.getHtml().css("div.forFlow").css("div.day").css("div.postTitle").xpath("//a/@href").all();
				page.addTargetRequests(urls);
				page.addTargetRequest(homeUrl+"?page="+ ++pageNum);
			}else{

			}
		}
	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new CnBlogsSpider("juandx",null)).addUrl("http://www.cnblogs.com/mvc/blog/news.aspx?blogApp=juandx").thread(3).run();

	}
}
