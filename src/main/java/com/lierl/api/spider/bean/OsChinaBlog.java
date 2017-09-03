package com.lierl.api.spider.bean;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.util.Date;

/**
 * @author lierl
 * @create 2017-08-31 10:57
 **/
@TableName("scf_oschina_blog")
public class OsChinaBlog {
	private int id;
	String title;
	String author;
	@TableField("read_num")
	int readNum;
	@TableField("vote_num")
	int voteNum;
	@TableField("publish_time")
	String publishTime;
	@TableField("collector_num")
	int collectorNum;
	int words;
	String tag;
	String address;
	int fans;
	@TableField("blog_num")
	int blogNum;
	@TableField("blog_words")
	int blogWords;
	String url;
	@TableField("create_time")
	Date createTime;
	String content;
	int comment;
	@TableField("blog_url")
	private String blogUrl;

	public String getBlogUrl() {
		return blogUrl;
	}

	public void setBlogUrl(String blogUrl) {
		this.blogUrl = blogUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getComment() {
		return comment;
	}

	public void setComment(int comment) {
		this.comment = comment;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getReadNum() {
		return readNum;
	}

	public void setReadNum(int readNum) {
		this.readNum = readNum;
	}

	public int getVoteNum() {
		return voteNum;
	}

	public void setVoteNum(int voteNum) {
		this.voteNum = voteNum;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public int getCollectorNum() {
		return collectorNum;
	}

	public void setCollectorNum(int collectorNum) {
		this.collectorNum = collectorNum;
	}

	public int getWords() {
		return words;
	}

	public void setWords(int words) {
		this.words = words;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getFans() {
		return fans;
	}

	public void setFans(int fans) {
		this.fans = fans;
	}

	public int getBlogNum() {
		return blogNum;
	}

	public void setBlogNum(int blogNum) {
		this.blogNum = blogNum;
	}

	public int getBlogWords() {
		return blogWords;
	}

	public void setBlogWords(int blogWords) {
		this.blogWords = blogWords;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Blog{" +
			   "title='" + title + '\'' +
			   ", author='" + author + '\'' +
			   ", readNum=" + readNum +
			   ", voteNum=" + voteNum +
			   ", publishTime='" + publishTime + '\'' +
			   ", collectorNum=" + collectorNum +
			   ", words=" + words +
			   ", tag='" + tag + '\'' +
			   ", address='" + address + '\'' +
			   ", fans=" + fans +
			   ", blogNum=" + blogNum +
			   ", blogWords=" + blogWords +
			   ", url='" + url + '\'' +
			   ", comment=" + comment +
			   '}';
	}
}
