package com.my.world.securitymanagement.api.user.po;

import com.my.world.securitymanagement.api.user.vo.EnumMap;

import java.util.List;

public class Enum {
    private String code;

    private String name;

    private List<EnumMap> items;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EnumMap> getItems() {
        return items;
    }

    public void setItems(List<EnumMap> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Enum{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", items=" + items +
                '}';
    }
}

