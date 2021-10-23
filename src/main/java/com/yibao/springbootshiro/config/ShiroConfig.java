package com.yibao.springbootshiro.config;

import com.yibao.springbootshiro.cache.RedisCacheManager;
import com.yibao.springbootshiro.realm.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * shiro配置类
 *
 * @author liyi
 * @create 2021 -10 -18 -10:11
 */
@Configuration
public class ShiroConfig {
    // 1、创建shiroFilter  --- 负责拦截所有请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 1.1 给filter设置安全管理器 (会去找自动装配好的Bean)
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        /*
            1.2 设置资源（公共资源、受限资源）
                anon: 无需认证可访问
                authc: 登录认证可访问
                user: 拥有“记住我”可访问,必须存在用户
                perms: 拥有某个资源权限
                roles: 拥有某个角色权限
         */
        HashMap<String, String> map = new HashMap<>();
//        map.put("/index.jsp", "authc");  // 访问此资源的时候，需要认证登录
        map.put("/user/login", "anon"); // 放开登录，拦截所有，需要写在拦截前面
        map.put("/user/register", "anon"); // 放开注册
        map.put("/register.jsp", "anon"); // 放开注册界面
        map.put("/**", "authc"); // 拦截所有
        // 1.2.1 设置权限
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        // 1.2.2 设置默认URL ---- 框架默认会去找 login.jsp  ---- 可以自行配置
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");

        return shiroFilterFactoryBean;
    }

    // 2、创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        // 2.1 给安全管理器配置 Realm
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    // 3、创建自定义Realm
    @Bean(name = "realm")
    public Realm getRealm() {
        CustomRealm customRealm = new CustomRealm();
        // 使用 MD5后，需要修改凭证校验匹配器，不然会使用默认的
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("MD5");  // 设置 MD5 算法
        matcher.setHashIterations(1024);  // 设置 hash次数
        customRealm.setCredentialsMatcher(matcher);

        // 开启缓存 EhCache
//        customRealm.setCacheManager(new EhCacheManager());

        customRealm.setCacheManager(new RedisCacheManager());
        customRealm.setCachingEnabled(true); // 开启全局缓存
        customRealm.setAuthenticationCachingEnabled(true); // 开启认证缓存
        customRealm.setAuthenticationCacheName("authenticationCache"); // 给认证缓存取名字
        customRealm.setAuthorizationCachingEnabled(true); // 开启授权缓存
        customRealm.setAuthorizationCacheName("authorizationCache"); // 给授权缓存取名字

        return customRealm;
    }

}
