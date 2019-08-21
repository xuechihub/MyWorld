package com.my.world.securitymanagement.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: MyWorld
 * @description: 搜索条件
 * @author: xue chi
 * @create: 2019-08-19 15:14
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchCondition implements Serializable {
    private static final long serialVersionUID = 6463765126267175309L;
    private String field;

    private Boolean isFuzzy;

    private String like;
}
