package com.yibao.springbootshiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author liyi
 * @create 2021 -10 -18 -17:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Permission implements Serializable { // 权限表
    private Integer id;
    private String name;
    private String url;
}
