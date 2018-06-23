package com.example.api.util;

/**
 * @author haijun
 * @class ${classname}
 * @date 2018/6/19, 12:04
 */
public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
