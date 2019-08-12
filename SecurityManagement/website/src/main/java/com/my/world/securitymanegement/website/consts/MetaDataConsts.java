package com.my.world.securitymanegement.website.consts;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: securitymanagement
 * @description: 加载页面元数据常量
 * @author: xuechi
 * @create: 2019-06-25 09:25
 **/
public class MetaDataConsts {
    public final static String HEADER_MENU_PATH = "config/HeaderMenu.yml";
    public final static String USER_META_DATA_PATH = "config/security/userMetaData.yml";
    public final static String ENUM_META_DATA_PATH = "config/sysSetting/enumMetaData.yaml";
    public static Map<String, String> meteDataMap = new HashMap(){
        {
            put("user", USER_META_DATA_PATH);
            put("enum", ENUM_META_DATA_PATH);
        }
    };
}
