package com.lierl.api.init;

/**
 * Created by lierl on 2017/9/2.
 */

import com.google.common.collect.Lists;
import com.lierl.api.spider.PersonBlogOschina;
import com.lierl.api.spider.downeloader.CustomerDownloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * 初始化
 */
//@Component
public class InitRunner implements CommandLineRunner {

    @Autowired
    private KafkaTemplate<String, String> template;

    List<String> errorUrls = Lists.newArrayList();

    @Override
    public void run(String... s) throws Exception {
//        Spider.create(new OsChinaSpider(template)).addUrl("https://www.oschina.net/blog/action/ajax/get_more_recommend_blog?classification=0&p=14").thread(2).run();
//        Spider.create(new AngularjsCnSpider(template)).addUrl("http://www.angularjs.cn/api/article/latest?p=1&s=20").thread(10).run();
        List<String> urls = Files.readAllLines(Paths.get("E:\\urls.txt"));
        long start = System.currentTimeMillis();
        for (int i=0;i<urls.size();i++) {
            System.out.println("i="+i);
            String url = urls.get(i);
            Spider.create(new PersonBlogOschina(url,template)).setDownloader(new CustomerDownloader(errorUrls)).addUrl(url).thread(10).run();
        }
        long end = System.currentTimeMillis();

        System.out.println("花费时间为："+((end - start)/1000/60));

        Files.write(Paths.get("E:\\error.txt"),errorUrls, StandardOpenOption.APPEND);
    }
}
