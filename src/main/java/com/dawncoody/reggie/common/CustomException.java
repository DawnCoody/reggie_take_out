package com.dawncoody.reggie.common;

/**
 * @description: 自定义异常
 * @author: dawncoody
 * @create: 2023-03-23 18:29
 */
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
