package com.yibao.springbootshiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 实体类：User
 *
 * @author liyi
 * @create 2021 -10 -18 -13:57
 */
@Data
@Accessors(chain = true) // lombok的注解 ： 会在 setter方法调用后，返回当前对象
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String salt;
    // 角色集合
    private List<Role> roles;
}
