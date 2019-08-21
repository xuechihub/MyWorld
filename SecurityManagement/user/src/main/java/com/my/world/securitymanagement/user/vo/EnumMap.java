package com.my.world.securitymanagement.user.vo;

/**
 * @program: MyWorld
 * @description: 枚举Map
 * @author: xue chi
 * @create: 2019-08-16 13:41
 **/
public class EnumMap {

    private String text;

    private Integer value;

    public EnumMap() {

    }

    public EnumMap(String text, Integer value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "EnumMap{" +
                "text='" + text + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
