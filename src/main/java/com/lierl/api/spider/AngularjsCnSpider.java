package com.lierl.api.spider;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.lierl.api.spider.bean.AngularjsCn;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.kafka.core.KafkaTemplate;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

import java.util.List;

/**
 * @author lierl
 * @create 2017-08-31 9:31
 **/
public class AngularjsCnSpider implements PageProcessor{

	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000)
			.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");

	private static final String LIST_URL = "http://www\\.angularjs\\.cn/api/article/latest.*";
	public static int pageNum = 1;

	private KafkaTemplate<String, String> template;

	public AngularjsCnSpider() {
	}

	public AngularjsCnSpider(KafkaTemplate<String, String> template) {
		this.template = template;
	}

	@Override
	public void process(Page page) {

		if (page.getUrl().regex(LIST_URL).match() && pageNum <=109) {
			List<String> ids = new JsonPathSelector("$.data[*]._id").selectList(page.getRawText());
			if (CollectionUtils.isNotEmpty(ids)) {
				for (String id : ids) {
					page.addTargetRequest("http://www.angularjs.cn/api/article/" + id);
				}
			}
			pageNum++;
			page.addTargetRequest("http://www.angularjs.cn/api/article/latest?p="+ pageNum +"&s=20");
		} else {
			String title = new JsonPathSelector("$.data.title").select(page.getRawText());
			String content =  new JsonPathSelector("$.data.content").select(page.getRawText());
			String visitors = new JsonPathSelector("$.data.visitors").select(page.getRawText());
			String comments = new JsonPathSelector("$.data.comments").select(page.getRawText());
			String tags = Joiner.on(",").join(new JsonPathSelector("$.data.tagsList[*].tag").selectList(page.getRawText()));
			String author = new JsonPathSelector("$.data.author.name").select(page.getRawText());
			String score = new JsonPathSelector("$.data.author.score").select(page.getRawText());
			String url = new JsonPathSelector("$.data.author.avatar").select(page.getRawText());
			String publishDate = new JsonPathSelector("$.data.date").select(page.getRawText());
			String updateDate = new JsonPathSelector("$.data.updateTime").select(page.getRawText());
			String hots = new JsonPathSelector("$.data.hots").select(page.getRawText());

			AngularjsCn angular = new AngularjsCn();
			angular.setContent(content);
			angular.setTitle(title);
			angular.setVisitors(visitors);
			angular.setComments(comments);
			angular.setTags(tags);
			angular.setAuthor(author);
			angular.setScore(score);
			angular.setUrl(url);
			angular.setPublishDate(publishDate);
			angular.setUpdateDate(updateDate);
			angular.setHots(hots);
			template.send("angularjs", JSON.toJSONString(angular));
		}
	}


	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new AngularjsCnSpider()).addUrl("http://www.angularjs.cn/api/article/latest?p=1&s=20").thread(10).run();
	}
}
