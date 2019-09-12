package com.my.world.common.rest.utils;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @program: MyWorld
 * @description: yml读取map工具类
 * @author: xue chi
 * @create: 2019-06-21 09:23
 **/
public class YmlUtils {
    /**
     * Yaml转json
     *
     * @param yamlString
     * @return
     */

    @SuppressWarnings("unchecked")
    public static JSONObject convertToJson(String yamlString) {
        Yaml yaml = new Yaml();
        Map<String, Object> map = (Map<String, Object>) yaml.load(yamlString);
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject;
    }

    /**
     * Yaml转Map
     *
     * @param path
     * @return
     */
    public static <T> T convertToMap(String path) {
        Resource resource = new ClassPathResource(path);
        Yaml yaml = new Yaml();
        T map = null;
        try {
            InputStream inputStream = resource.getInputStream();
            map = yaml.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * Yaml转Object
     *
     * @param yamlString
     * @return
     */
    public static Object convertToObject(String yamlString) {
        Yaml yaml = new Yaml();
        Object obj = yaml.load(yamlString);
        return obj;
    }


    /**
     * Yaml转List
     *
     * @param yamlString
     * @return
     */

    @SuppressWarnings("unchecked")
    public static List<String> toList(String yamlString) {
        Yaml yaml = new Yaml();
        List<String> list = (List<String>) yaml.load(yamlString);
        return list;
    }
}
