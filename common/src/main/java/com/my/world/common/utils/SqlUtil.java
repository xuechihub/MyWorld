package com.my.world.common.utils;

import com.my.world.common.vo.ParameterInvalidItem;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: MyWorld
 * @description: sql工具类
 * @author: xue chi
 * @create: 2019-08-19 16:55
 **/
public class SqlUtil {

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

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

    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static void main(String[] args) throws NoSuchFieldException {
        String msg = SqlUtil.getColumn(ParameterInvalidItem.class, "fieldName");
        System.out.println(msg);
    }
}