package com.yibao.springbootshiro.realm;

import com.yibao.springbootshiro.entity.Permission;
import com.yibao.springbootshiro.entity.Role;
import com.yibao.springbootshiro.entity.User;
import com.yibao.springbootshiro.service.UserService;
import com.yibao.springbootshiro.util.ApplicationContextUtils;
import com.yibao.springbootshiro.util.MyByteSource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 自定义 Realm
 *
 * @author liyi
 * @create 2021 -10 -18 -10:28
 */
public class CustomRealm extends AuthorizingRealm {
    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 1、获取主身份信息
        String principal = (String) principals.getPrimaryPrincipal();
        // 2、根据身份信息，查询角色、权限、资源
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 2.1  通过工厂获取service的bean对象
        UserService userService = (UserService) ApplicationContextUtils.getBean("userService");
        // 2.2 调用service，获取所有权限
        User user = userService.findRolesByUsername(principal);
        // 2.3 添加 角色权限
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            // 该用户 角色相关权限
            for (Role role : user.getRoles()) {
                simpleAuthorizationInfo.addRole(role.getName());
                // 2.4 添加 权限信息  --- 权限字符串
                List<Permission> permissions = userService.findPermissionByRoleId(role.getId());
                if (!CollectionUtils.isEmpty(permissions)) {
                    for (Permission permission : permissions) {
                        simpleAuthorizationInfo.addStringPermission(permission.getName());
                    }
                }
            }
        }


        return simpleAuthorizationInfo;
    }


    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1、获取身份信息
        String principal = (String) token.getPrincipal();
        /*
            2、查询数据库，获取身份信息（用户名、密码...）
         */
        // 2.1 通过工厂获取service的bean对象【默认策略：名字是类名小写 userServiceImpl,可以通过在@Service中命名,替换默认的名字】
        UserService userService = (UserService) ApplicationContextUtils.getBean("userService");
        // 2.2 获取对象，进行认证
        User user = userService.findUserByName(principal);
        if (!ObjectUtils.isEmpty(user)) {
            return new SimpleAuthenticationInfo(
                    user.getUsername(), user.getPassword(), new MyByteSource(user.getSalt()), this.getName());
        }

        return null;
    }
}
