package com.yibao.springbootshiro.service;

import com.yibao.springbootshiro.entity.Permission;
import com.yibao.springbootshiro.entity.User;

import java.util.List;

/**
 * @author liyi
 * @create 2021 -10 -18 -14:16
 */
public interface UserService {
    // 方法：注册
    void register(User user);

    // 方法：根据用户名查询用户信息
    User findUserByName(String username);

    // 方法：根绝用户名查询角色集合
    User findRolesByUsername(String username);

    // 方法：根据roleId查询权限
    List<Permission> findPermissionByRoleId(int roleId);
}
