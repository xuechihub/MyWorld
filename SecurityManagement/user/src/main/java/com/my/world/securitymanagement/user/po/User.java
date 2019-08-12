package com.my.world.securitymanagement.user.po;

import com.my.world.common.validator.NewPOValidatorGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class User {
    private String id;

    private String organizationId;

    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp= "^[\u4e00-\u9fa5_a-zA-Z0-9]{2,32}$",message = "长度为2-32位，只能输入中文，英文字母和数字及特殊字符'_'")
    private String userName;

    @NotBlank(groups= NewPOValidatorGroup.class, message = "密码不能为空")
    @Pattern(groups= NewPOValidatorGroup.class,regexp="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*_+~(){},./|:\\?\\[\\]''+-]).{6,20}$", message = "长度为6-20位，必须包含数字、小写字母、大写字母、特殊字符")
    private String password;

    private Integer userType;

    private Date lastVisit;

    private String lastIp;

    private String createUser;

    private Date createTime;

    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}