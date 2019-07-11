package com.my.security.login.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: securitymanagement
 * @description: rest接口通用返回结果
 * @author: xuechi
 * @create: 2019-07-10 15:11
 **/
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlatformResult implements Serializable {

    private static final long serialVersionUID = 874200365941306385L;

    private Integer code;

    private String msg;

    private Object data;


}
