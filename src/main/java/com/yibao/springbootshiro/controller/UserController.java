package com.yibao.springbootshiro.controller;

import com.yibao.springbootshiro.entity.User;
import com.yibao.springbootshiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liyi
 * @create 2021 -10 -18 -10:59
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 身份认证：利用shiro
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("login")
    public String login(String username, String password) {
        // 获取主体对象
        Subject subject = SecurityUtils.getSubject();
        try {
            // 登录
            subject.login(new UsernamePasswordToken(username, password));
            // 成功 --- 进入 index界面
            return "redirect:/index.jsp";
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        // 失败 --- 返回login界面
        return "redirect:/login.jsp";
    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping("logout")
    public String logout() {
        // 获取主体对象
        Subject subject = SecurityUtils.getSubject();
        // 退出登录，重定向登录界面
        subject.logout();
        return "redirect:/login.jsp";
    }

    /**
     * 方法：注册
     *
     * @param user
     * @return
     */
    @RequestMapping("register")
    public String register(User user) {
        try {
            userService.register(user);
            // 注册成功  --->> 登录界面
            return "redirect:/login.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            // 注册失败  --->> 注册界面
            return "redirect:/register.jsp";
        }
    }


}
