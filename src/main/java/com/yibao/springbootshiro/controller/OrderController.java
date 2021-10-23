package com.yibao.springbootshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liyi
 * @create 2021 -10 -18 -16:55
 */
@Controller
@RequestMapping("order")
public class OrderController { // 测试使用

    /**
     * 方法：伪代码，测试order权限
     */
    @RequestMapping("getOrder")
    public String orderPermission() {
        // 1、获取主体对象
        Subject subject = SecurityUtils.getSubject();
        // 2、判断角色
        if (subject.hasRole("admin")) {
            System.out.println("能够进行订单操作");
        } else {
            System.out.println("无权操作订单！");
        }
        return "redirect:/index.jsp";
    }

    /**
     * 方法：伪代码，用于测试注解的权限
     * @return
     */
    @RequestMapping("getOrder2")
//    @RequiresRoles(value = {"admin","user"})  // 必须同时具备admin、user角色才能访问该方法
    @RequiresPermissions("user:find:*")  // 符合该权限字符串的能访问该方法
    public String orderPermission2(){
        System.out.println("进入该方法 --- 注解权限");
        return "redirect:/index.jsp";
    }

}
