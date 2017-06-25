package com.lierl.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 在需要建立索引的类上加上@Document注解，即表明这个实体需要进行索引
 * indexName索引库的名称，个人建议以项目的名称命名
 * type类型，个人建议以实体的名称命名
 * shards() default 5;//默认分区数
 * replicas() default 1;//每个分区默认的备份数
 * refreshInterval() default "1s";//刷新间隔
 * indexStoreType() default "fs";//索引文件存储类型
 *
 * 加上了@Document注解之后，默认情况下这个实体中所有的属性都会被建立索引、并且分词
 *
 * Created by lierl on 2017/6/25.
 */
@Document(indexName = "article_index",type="article",shards = 5,replicas = 1,indexStoreType = "fs",refreshInterval = "-1")
public class Article implements Serializable{

    private static final long serialVersionUID = 7756135478992558574L;

    @Id
    private Long id;

    private String title;//标题

    private String abstracts;//摘要

    private String content;//内容

    /**
     * type() default FieldType.Auto;#自动检测属性的类型
     * index() default FieldIndex.analyzed;#默认情况下分词
     * format() default DateFormat.none;
     * store() default false;#默认情况下不存储原文
     * searchAnalyzer() default "";#指定字段搜索时使用的分词器
     *  indexAnalyzer() default "";#指定字段建立索引时指定的分词器
     * ignoreFields() default {};#如果某个字段需要被忽略
     *
     *这些默认值指的是我们没有在我们没有在属性上添加@Filed注解的默认处理。一旦添加了@Filed注解，
     * 所有的默认值都不再生效。此外，如果添加了@Filed注解，那么type字段必须指定
     */
    @Field(format = DateFormat.date_time,index = FieldIndex.no,store = true,type = FieldType.Object)
    private Date postTime;//发表时间

    private Long clickCount;//点击率

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }
}
