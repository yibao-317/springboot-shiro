package com.yibao.springbootshiro.dao;

import com.yibao.springbootshiro.entity.Permission;
import com.yibao.springbootshiro.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liyi
 * @create 2021 -10 -18 -14:10
 */
@Mapper
@Repository
public interface UserDao {
    // 方法：注册保存新用户
    void save(User user);

    // 方法：根据用户名查询用户信息
    User findUserByName(String username);

    // 方法：根据用户名查询该用户的 所有权限
    User findRolesByUsername(String username);

    // 方法：根据roleId查询权限
    List<Permission> findPermissionByRoleId(int roleId);

}
