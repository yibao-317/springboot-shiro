package com.yibao.springbootshiro.util;

import org.apache.shiro.util.SimpleByteSource;

import java.io.Serializable;

/**
 * @author liyi
 * @create 2021 -10 -19 -10:26
 */
// 自定义salt实现，实现序列化接口
public class MyByteSource extends SimpleByteSource implements Serializable {
    // 根据情况，添加需要的构造器
    public MyByteSource(String string) {
        super(string);
    }

}
