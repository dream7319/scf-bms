package com.lierl.repository;

import com.lierl.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 泛型的参数分别是实体类型和主键类型
 *
 * Created by lierl on 2017/6/25.
 */
public interface ArticleRepository extends ElasticsearchRepository<Article,Long>{

}
