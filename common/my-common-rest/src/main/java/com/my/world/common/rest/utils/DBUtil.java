package com.my.world.common.rest.utils;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: MyWorld
 * @description: 数据库工具类
 * @author: xue chi
 * @create: 2019-08-19 16:55
 **/
public class DBUtil {

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 获取对应PO字段的数据库表字段名，以检验数据库字段的合法性，mybatis mapper接口使用
     *
     * @param clazz  数据库表对应PO
     * @param column PO的字段名
     * @param <T>    PO类
     * @return 对应PO的数据库表的字段名，格式aaa_bbb,不存在返回“”
     */
    public static <T> String getColumn(Class<T> clazz, String column) {
        try {
            Field field = clazz.getDeclaredField(column);
            if (field != null) {
                String name = field.getName();
                return humpToLine(name);
            }
        } catch (NoSuchFieldException e) {
        }
        return "";
    }

    /**
     * 驼峰转下划线，如: aB  == >  a_b
     *
     * @param str 要转换的驼峰字符转aB
     * @return 转换后的下滑线字符转a_b
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

//    public static void main(String[] args) throws NoSuchFieldException {
//        String msg = DBUtil.getColumn(ParameterInvalidItem.class, "fieldName");
//        System.out.println(msg);
//    }
}