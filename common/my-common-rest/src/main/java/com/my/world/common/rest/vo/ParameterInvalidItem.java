package com.my.world.common.rest.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: MyWorld
 * @description: 参数无效项
 * @author: xue chi
 * @create: 2019-07-18 15:59
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParameterInvalidItem {

    /**
     * 无效字段的名称
     */
    private String fieldName;

    /**
     * 错误信息
     */
    private String message;
}
