package com.yibao.springbootshiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author liyi
 * @create 2021 -10 -18 -17:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class Role implements Serializable { // 角色表
    private Integer id;
    private String name;

    // 权限集合
    private List<Permission> permissions;
}
