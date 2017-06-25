package com.lierl.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lierl.entity.Article;
import com.lierl.mapper.ArticleMapper;
import com.lierl.service.IArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lierl on 2017/6/25.
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements IArticleService{
    @Override
    public List<Article> getAllArticles() {
        return baseMapper.getAllArticles();
    }
}
