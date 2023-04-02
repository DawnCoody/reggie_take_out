package com.dawncoody.reggie;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

/**
 * @description:
 * @author: dawncoody
 * @create: 2023-03-23 21:11
 */
public class TestStream {
    @Test
    public void test10(){
        Stream.of(1,2,3,4,5)
                .map(t -> t+=1)//Function<T,R>接口抽象方法 R apply(T t)
                .forEach(System.out::println);
    }
}
