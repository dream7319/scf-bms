package com.lierl.api.spider.bean;

/**
 * @author lierl
 * @create 2017-09-08 17:12
 **/
public class CnBlogs {
	private Integer id;
	private String name;
	private String fans;
	private String follow;
	private String age;
	private String essay;
	private String article;
	private String allComments;


	public String getEssay() {
		return essay;
	}

	public void setEssay(String essay) {
		this.essay = essay;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getAllComments() {
		return allComments;
	}

	public void setAllComments(String allComments) {
		this.allComments = allComments;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFans() {
		return fans;
	}

	public void setFans(String fans) {
		this.fans = fans;
	}

	public String getFollow() {
		return follow;
	}

	public void setFollow(String follow) {
		this.follow = follow;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
