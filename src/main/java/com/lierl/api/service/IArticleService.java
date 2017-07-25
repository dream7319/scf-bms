package com.lierl.api.service;


import com.baomidou.mybatisplus.service.IService;
import com.lierl.api.entity.Article;

import java.util.List;

/**
 * Created by lierl on 2017/6/25.
 */
public interface IArticleService extends IService<Article> {
    public List<Article> getAllArticles();
}
