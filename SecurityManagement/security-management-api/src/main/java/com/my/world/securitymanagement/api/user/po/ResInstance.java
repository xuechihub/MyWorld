package com.my.world.securitymanagement.api.user.po;

public class ResInstance {
    private String id;

    private String parentId;

    private String name;

    private String resTypeId;

    private Integer configPermissions;

    private Integer defaultPermissions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResTypeId() {
        return resTypeId;
    }

    public void setResTypeId(String resTypeId) {
        this.resTypeId = resTypeId;
    }

    public Integer getConfigPermissions() {
        return configPermissions;
    }

    public void setConfigPermissions(Integer configPermissions) {
        this.configPermissions = configPermissions;
    }

    public Integer getDefaultPermissions() {
        return defaultPermissions;
    }

    public void setDefaultPermissions(Integer defaultPermissions) {
        this.defaultPermissions = defaultPermissions;
    }
}