package com.my.world.securitymanegement.website.controller;

import com.my.world.common.annotations.CommonRequestBody;
import com.my.world.common.utils.YmlUtils;
import com.my.world.securitymanegement.website.consts.MetaDataConsts;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @program: securitymanagement
 * @description: 元数据控制器
 * @author: xue chi
 * @create: 2019-06-25 10:59
 **/
@RestController
@RequestMapping("/rest/metaData")
@CommonRequestBody
public class MetaDataController {

    @RequestMapping("/headerMenu")
    public Object index() {
        Object menu = YmlUtils.convertToMap(MetaDataConsts.HEADER_MENU_PATH);
        return menu;
    }

    @RequestMapping("/table/{id}")
    public Object metaData(@PathVariable("id") String id, @RequestParam("isEdit") boolean isEdit) throws JSONException {
        HashMap menu = null;
        if ("user".equals(id) || "enum".equals(id)) {
            menu = YmlUtils.convertToMap(MetaDataConsts.meteDataMap.get(id));
            HashMap metaData = (HashMap) menu.get("metaData");
            ArrayList<HashMap> header = (ArrayList<HashMap>) metaData.get("header");
            for (HashMap item : header) {
                Iterator iterator = item.keySet().iterator();
                while (iterator.hasNext()) {
                    Object k = iterator.next();
                    if ("isEdit".equals(item.get(k))) {
                        item.put(k, !isEdit);
                    }
                }
            }
            return menu;
        }
        return null;
    }
}
