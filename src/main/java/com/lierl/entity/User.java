package com.lierl.entity;

/**
 * Created by lierl on 2017/6/25.
 */
public class User {
    private Integer id;
    private String username;
    private Integer age;
    private String message;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User() {
    }

    public User(String username, Integer age, String message) {
        this.username = username;
        this.age = age;
        this.message = message;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", message='" + message + '\'' +
                '}';
    }
}
