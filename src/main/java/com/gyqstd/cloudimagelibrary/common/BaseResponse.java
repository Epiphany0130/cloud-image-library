package com.gyqstd.cloudimagelibrary.common;

import com.gyqstd.cloudimagelibrary.exception.ErrorCode;
import lombok.Data;

import java.io.Serializable;

/**
 * 全局响应封装类
 * @author GuYuqi
 * @version 1.0
 */
@Data
public class BaseResponse<T> implements Serializable {

    private int code;

    private T data;

    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code, data, ""); // 直接调用另一个构造方法
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }

}
