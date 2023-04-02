package com.dawncoody.reggie.common;

/**
 * @description: 基于TreadLocal封装工具类，用户保存和获取当前登录用户id
 * @author: dawncoody
 * @create: 2023-03-23 15:10
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }
}
