package com.lierl.controller;

import com.lierl.entity.Article;
import com.lierl.repository.ArticleRepository;
import com.lierl.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lierl on 2017/6/25.
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/lists")
    public List<Article> getAllArticle(){
        return articleService.getAllArticles();
    }

    @GetMapping("/add")
    public Article addArticle(){
        Article article = articleService.selectById(1);

        articleRepository.save(article);

        return article;
    }
}
