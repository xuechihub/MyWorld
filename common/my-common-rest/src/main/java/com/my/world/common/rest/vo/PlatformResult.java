package com.my.world.common.rest.vo;

import com.my.world.common.rest.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: MyWorld
 * @description: rest接口通用返回结果
 * @author: xue chi
 * @create: 2019-07-10 15:11
 **/
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlatformResult implements Result {

    private static final long serialVersionUID = 874200365941306385L;

    private Integer code;

    private String msg;

    private Object data;

    private static PlatformResult success() {
        PlatformResult result = new PlatformResult();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static PlatformResult success(Object data) {
        PlatformResult result = new PlatformResult();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static PlatformResult failure(ResultCode code) {
        PlatformResult result = new PlatformResult();
        result.setResultCode(code);
        return result;
    }

    public static PlatformResult failure(ResultCode code, Object data) {
        PlatformResult result = new PlatformResult();
        result.setResultCode(code);
        result.setData(data);
        return result;
    }

    private void setResultCode(ResultCode code) {
        this.code = code.code();
        this.msg = code.message();
    }
}
