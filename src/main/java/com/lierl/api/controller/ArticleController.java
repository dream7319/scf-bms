package com.lierl.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lierl on 2017/6/25.
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    /*@Autowired
    private IArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/lists")
    public List<Article> getAllArticle(){
        return articleService.getAllArticles();
    }

    @GetMapping("/add")
    public Article addArticle(){
//        Article article = articleService.selectById(1);
        Article article = new Article();
        article.setTitle("测试");
        article.setContent("我们建立一个网站或应用程序，并要添加搜索功能，但是想要完成搜索工作的创建是非常困难的。我们希望搜索解决方案要运行速度快，我们希望能有一个零配置和一个完全免费的搜索模式，我们希望能够简单地使用JSON通过HTTP来索引数据，我们希望我们的搜索服务器始终可用，我们希望能够从一台开始并扩展到数百台，我们要实时搜索，我们要简单的多租户，我们希望建立一个云的解决方案。因此我们利用Elasticsearch来解决所有这些问题以及可能出现的更多其它问题");
        article.setAbstracts("添加搜索功能，但是想要完成搜索工作的创建是非");
        article.setClickCount(200L);
        article.setPostTime(new Date());
        articleRepository.save(article);

        return article;
    }

    @GetMapping("/{id}")
    public Article selectOne(@PathVariable Long id){
//        articleRepository.search()
        return articleRepository.findOne(id);
    }

    @GetMapping("/builder")
    public void queryBuilder(){
        QueryBuilder queryBuilder = QueryBuilders.queryStringQuery("elasticsearch");
        articleRepository.search(queryBuilder);
    }

    @GetMapping("/search")
    public void searchQuery(){
//        SearchQuery query = queryFactory.createQuery(queryName, params, factoryServices, entityClass);
        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(termQuery("message", "elasticsearch"))
                .build();


        Page<Article> search = articleRepository.search(query);
    }

    public void hightlight(){
        QueryBuilder queryBuilder = QueryBuilders.queryStringQuery("??");
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withHighlightFields(new HighlightBuilder.Field("title").preTags("<em>").postTags("</em>").fragmentSize(250))
                .withHighlightFields(new HighlightBuilder.Field("desc").preTags("<em>").postTags("</em>").fragmentSize(250))
                .withPageable(new PageRequest(0, 20))
                .build();

        Page<Article> search = articleRepository.search(searchQuery);
    }*/

}
