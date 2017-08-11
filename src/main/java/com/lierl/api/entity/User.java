package com.lierl.api.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lierl.api.excel.FieldColumnMapping;
import lombok.Data;

import java.util.Date;

/**
 * Created by lierl on 2017/6/25.
 */
@TableName("scf_user")
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
    @TableField("user_status")
    private Boolean userStatus;//用户状态 0:禁用，1:可用
    @TableField("user_type")
    private String userType;//用户类型 0:普通用户,1:管理员,2:超级管理员
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="CET")
    private Date birthday;//出生日期
    @TableField("create_time")
    private Date createTime;//创建日期
    @TableField("update_time")
    private Date updateTime;//更新日期
    @TableField("delete_flag")
    @TableLogic
    private Boolean deleteFlag;//删除标识：0：删除,1：未删除

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Boolean userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
