package com.my.world.securitymanagement.user.po;

import java.util.Date;

public class ResExtPropValue {
    private String id;

    private String resTypeId;

    private String resExtPropId;

    private String value;

    private String createUser;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResTypeId() {
        return resTypeId;
    }

    public void setResTypeId(String resTypeId) {
        this.resTypeId = resTypeId;
    }

    public String getResExtPropId() {
        return resExtPropId;
    }

    public void setResExtPropId(String resExtPropId) {
        this.resExtPropId = resExtPropId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
}