package com.imooc.pojo.vo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class UsersVO {
    /**
     * 主键id 用户id
     */
    @Id
    private String id;

    /**
     * 用户名 用户名
     */
    private String username;

    /**
     * 密码 密码
     */
    private String password;

    /**
     * 昵称 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String face;

    /**
     * 性别 性别 1:男  0:女  2:保密
     */
    private Integer sex;

    /*
     * 用户会话 uniqueToken
     * */

    private String uniqueToken;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getUniqueToken() {
        return uniqueToken;
    }

    public void setUniqueToken(String uniqueToken) {
        this.uniqueToken = uniqueToken;
    }
}