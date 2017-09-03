package com.lierl.api.kafka;

import com.alibaba.fastjson.JSON;
import com.lierl.api.service.IAngularService;
import com.lierl.api.service.IOsChinaBlogService;
import com.lierl.api.spider.bean.AngularjsCn;
import com.lierl.api.spider.bean.OsChinaBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by lierl on 2017/9/2.
 */
@Component
public class Consumer {

    @Autowired
    private IOsChinaBlogService osChinaBlogService;

    @Autowired
    private IAngularService angularService;

    @KafkaListener(topics = {"oschina"})
    public void processMessage(String content) {
        OsChinaBlog oschinaBlog = JSON.parseObject(content, OsChinaBlog.class);
        oschinaBlog.setCreateTime(new Date());
        osChinaBlogService.insert(oschinaBlog);
    }

    @KafkaListener(topics = {"angularjs"})
    public void angularjs(String content) {
        AngularjsCn angularjsCn = JSON.parseObject(content, AngularjsCn.class);
        angularjsCn.setCreateTime(new Date());
        angularService.insert(angularjsCn);
    }
}
