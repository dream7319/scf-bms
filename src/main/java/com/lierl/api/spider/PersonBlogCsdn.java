package com.lierl.api.spider;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lierl.api.spider.bean.CsdnBlog;
import com.lierl.api.spider.bean.OsChinaBlog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.kafka.core.KafkaTemplate;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;
import java.util.Map;

/**
 * @author lierl
 * @create 2017-09-04 11:41
 **/
public class PersonBlogCsdn implements PageProcessor{

	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000)
			.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");

	public int pageNum;

	public String blogPageUrl;

	public PersonBlogCsdn(){}

	private String homeUrl;

	private int totalPageNum;

	static List<OsChinaBlog> blogsList = Lists.newArrayList();

	private KafkaTemplate<String, String> template;

	private Map<String,String> counts;

	public PersonBlogCsdn(String homeUrl, KafkaTemplate<String, String> template, Map<String,String> counts){
		this.homeUrl = homeUrl;
		this.blogPageUrl = homeUrl+"/article/list/\\d{1,5}";
		this.pageNum = 1;
		this.totalPageNum = 0;
		this.template = template;
		this.counts = counts;
	}

	@Override
	public void process(Page page) {
		if(page.getUrl().regex("^"+homeUrl+"$").match()){
			List<String> uris = page.getHtml().xpath("//*div[@id='article_list']/div[@class='list_item article_item']/div[@class='article_title']/h1/span/a/@href").all();
			page.addTargetRequests(uris);

			String pageData = page.getHtml().xpath("//*div[@id='papelist']/span/text()").get();
			if(StringUtils.isNotBlank(pageData)){
				//88条 共6页
				totalPageNum = Integer.valueOf(pageData.substring(pageData.indexOf("共")+1).replace("页","").trim());
				String total = pageData.substring(0,pageData.indexOf("条")).trim();
				counts.put(homeUrl,total);
			}else {
				totalPageNum = 1;
			}
			this.pageNum++;
			page.addTargetRequest(homeUrl+"/article/list/2");
		}else if(page.getUrl().regex(blogPageUrl).match() && pageNum <= totalPageNum){
			List<String> uris = page.getHtml().xpath("//*div[@id='article_list']/div[@class='list_item article_item']/div[@class='article_title']/h1/span/a/@href").all();
			page.addTargetRequests(uris);
			this.pageNum++;
			page.addTargetRequest(homeUrl+"/article/list/"+ ++pageNum);
		}else{
			CsdnBlog csdn = new CsdnBlog();
			String title = page.getHtml().xpath("//*div[@id='article_details']/div[@class='article_title']/h1/span/a/text()").get();
			csdn.setTitle(title);
			String tags = Joiner.on(",").join(page.getHtml().xpath("//*div[@id='article_details']/div[@class='article_manage']/div[@class='article_l']/span/a/text()").all());
			csdn.setTags(tags);
			Selectable articleR = page.getHtml().xpath("//*div[@id='article_details']/div[@class='article_manage']/div[@class='article_r']");
			String date = articleR.xpath("//span[1]/text()").get();
			csdn.setDate(date);
			String read = articleR.xpath("//span[2]/text()").get().replace("人阅读","");
			csdn.setRead(read);
			String comments = articleR.xpath("//span[3]/text()").get().replace("(","").replace(")","").trim();
			csdn.setComments(comments);
			String content = page.getHtml().xpath("//*div[@id='article_details']/div[@id='article_content']/html()").get();
			csdn.setContent(content);
			String ding = page.getHtml().xpath("//*div[@id='article_details']/div[@id='digg']/dl[1]/dd/text()").get();
			csdn.setDing(ding);
			String cai = page.getHtml().xpath("//*div[@id='article_details']/div[@id='digg']/dl[2]/dd/text()").get();
			csdn.setCai(cai);
			String author = page.getHtml().xpath("//*div[@id='side']/div[@class='side']/div[@id='panel_Profile']/ul[2]/div[@id='blog_userface']/span/a/text()").get();
			csdn.setAuthor(author);
			String visitor = page.getHtml().xpath("//*div[@id='side']/div[@class='side']/div[@id='panel_Profile']/ul[2]/ul[1]/li[1]/span/text()").get();
			csdn.setVisitor(visitor);
			String point = page.getHtml().xpath("//*div[@id='side']/div[@class='side']/div[@id='panel_Profile']/ul[2]/ul[1]/li[2]/span/text()").get();
			csdn.setPoint(point);
			String ranking = page.getHtml().xpath("//*div[@id='side']/div[@class='side']/div[@id='panel_Profile']/ul[2]/ul[1]/li[4]/span/text()").get();
			csdn.setRanking(ranking);
			String original = page.getHtml().xpath("//*div[@id='side']/div[@class='side']/div[@id='panel_Profile']/ul[2]/ul[2]/li[1]/span/text()").get();
			csdn.setOriginal(original);
			String reprint = page.getHtml().xpath("//*div[@id='side']/div[@class='side']/div[@id='panel_Profile']/ul[2]/ul[2]/li[2]/span/text()").get();
			csdn.setReprint(reprint);
			String translation = page.getHtml().xpath("//*div[@id='side']/div[@class='side']/div[@id='panel_Profile']/ul[2]/ul[2]/li[3]/span/text()").get();
			csdn.setTranslation(translation);
			String allComments = page.getHtml().xpath("//*div[@id='side']/div[@class='side']/div[@id='panel_Profile']/ul[2]/ul[2]/li[4]/span/text()").get();
			csdn.setAllComments(allComments);
			template.send("csdn", JSON.toJSONString(csdn));
		}
	}


	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new PersonBlogCsdn("http://blog.csdn.net/willib",null, Maps.newHashMap())).addUrl("http://blog.csdn.net/willib").thread(5).run();
	}
}
