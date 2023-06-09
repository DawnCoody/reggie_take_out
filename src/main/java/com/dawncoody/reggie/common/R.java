package com.dawncoody.reggie.common;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:通用返回结果类，服务端响应的数据最终会封装成此对象
 * @author: dawncoody
 * @create: 2023-03-22 22:59
 */
@Data
public class R<T> implements Serializable {
    static final long serialVersionUID = 6140666027150479522L;
    /**
     * 编码：
     * 1 表示成功 其他数值便是失败
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    /**
     * 动态数据
     */
    private Map map = new HashMap();

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
}
