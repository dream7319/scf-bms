package com.lierl.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lierl.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * Created by lierl on 2017/6/25.
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article>{

    @Select("select * from article")
    @Results({
            @Result(column = "id",property = "id",jdbcType = JdbcType.INTEGER),
            @Result(column = "title",property = "title",jdbcType = JdbcType.VARCHAR),
            @Result(column = "abstracts",property = "abstracts",jdbcType = JdbcType.VARCHAR),
            @Result(column = "content",property = "content",jdbcType = JdbcType.VARCHAR),
            @Result(column = "post_time",property = "postTime",jdbcType = JdbcType.DATE),
            @Result(column = "click_count",property = "clickCount",jdbcType = JdbcType.INTEGER)
    })
    public List<Article> getAllArticles();
}
