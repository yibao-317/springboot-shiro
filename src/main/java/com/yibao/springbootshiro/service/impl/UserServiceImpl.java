package com.yibao.springbootshiro.service.impl;

import com.yibao.springbootshiro.dao.UserDao;
import com.yibao.springbootshiro.entity.Permission;
import com.yibao.springbootshiro.entity.User;
import com.yibao.springbootshiro.service.UserService;
import com.yibao.springbootshiro.util.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liyi
 * @create 2021 -10 -18 -14:17
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    // 方法：注册
    @Override
    public void register(User user) {
        /*
            1、密码 --->> MD5 + Salt + hash散列次数
         */
        // 1.1 生成随机盐
        String salt = SaltUtils.getSalt(4);
        // 1.2 将盐封装在对象中
        user.setSalt(salt);
        // 1.3 将密码进行加密
        Md5Hash passwordMD5 = new Md5Hash(user.getPassword(), salt, 1024);
        user.setPassword(passwordMD5.toHex());
        // 1.4 调用dao存储
        userDao.save(user);
    }

    /**
     * 方法：根据用户名，查询用户信息
     *
     * @param username
     */
    @Override
    public User findUserByName(String username) {
        return userDao.findUserByName(username);
    }

    /**
     * 方法：根据用户名，查询用户所有权限
     *
     * @param username
     * @return
     */
    @Override
    public User findRolesByUsername(String username) {
        return userDao.findRolesByUsername(username);
    }

    /**
     * 方法：根据 roleId查询权限
     *
     * @param roleId
     * @return
     */
    @Override
    public List<Permission> findPermissionByRoleId(int roleId) {
        return userDao.findPermissionByRoleId(roleId);
    }


}
