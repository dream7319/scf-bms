package com.lierl.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by lierl on 2017/6/25.
 */
//@TableName("user")
@Data
public class User {
    private Integer id;
    private String username;//用户名
    private String password;
    private Integer age;//年龄
    private String name;//用户别名
    private String sex;// MALE,FEMALE
	private String email;//邮箱
    private String telphone;//电话
    private String photo;//头像
    private Boolean userStatus;//用户状态 0:禁用，1:可用
    private Boolean userType;//用户类型 0:普通用户,1:管理员,2:超级管理员
    private Date birthday;//出生日期
    private Date createTime;//创建日期
    private Date updateTime;//更新日期
    private Boolean deleteFlag;//删除标识：0：删除,1：未删除
}
