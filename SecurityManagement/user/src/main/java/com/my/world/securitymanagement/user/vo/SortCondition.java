package com.my.world.securitymanagement.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: MyWorld
 * @description: 排序条件
 * @author: xue chi
 * @create: 2019-08-19 15:13
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SortCondition implements Serializable {

    private static final long serialVersionUID = 4001258959220501345L;

    private String field;

    private Boolean isASC;

}
