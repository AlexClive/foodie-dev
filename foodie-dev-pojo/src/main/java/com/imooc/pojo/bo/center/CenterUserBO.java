package com.imooc.pojo.bo.center;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Date;

public class CenterUserBO {
    @ApiModelProperty(value = "用户名", name = "username", example = "json", required = true)
    private String username;
    @ApiModelProperty(value = "密码", name = "password", example = "123456", required = true)
    private String password;
    @ApiModelProperty(value = "确认密码", name = "ConfirmPassword", example = "123456", required = true)
    private String ConfirmPassword;
    @NotBlank(message = "用户昵称不能为空")
    @Length(max = 12, message = "用户昵称不能超过12位")
    @ApiModelProperty(value = "用户昵称", name = "nickname", example = "杰森", required = true)
    private String nickname;
    @Length(max = 12, message = "真实姓名不能超过12位")
    @ApiModelProperty(value = "真实姓名", name = "realname", example = "杰森", required = true)
    private String realname;
    @Pattern(regexp = "^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\\d{8})$",message = "手机号格式不正确")
    @ApiModelProperty(value = "手机号", name = "mobile", example = "13999999999", required = true)
    private String mobile;
    @Email
    @ApiModelProperty(value = "邮箱", name = "email", example = "zxr@zhangxuerong.com", required = true)
    private String email;
    @Min(value = 0,message = "性别选择不正确")
    @Max(value = 2,message = "性别选择不正确")
    @ApiModelProperty(value = "性别", name = "sex", example = "0: 女 1：男 2：保密", required = true)
    private Integer sex;
    @ApiModelProperty(value = "生日", name = "birthday", example = "1990-01-01", required = true)
    private Date birthday;

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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
