<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>首页界面</title>
</head>
<body>

<h2>首页界面 index.jsp [受限资源]</h2>

<ul>
    <shiro:hasAnyRoles name="admin,user,visitor">
        <li><a href="">用户管理</a>
            <ul>
                <shiro:hasPermission name="user:add:*">
                    <li><a href="">添加</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="user:delete:*">
                    <li><a href="">删除</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="visitor:find:*">
                    <li><a href="">查询</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="user:update:*">
                    <li><a href="">修改</a></li>
                </shiro:hasPermission>
            </ul>
        </li>
    </shiro:hasAnyRoles>
    <shiro:hasRole name="admin">
        <li><a href="">订单管理</a></li>
        <li><a href="">物流管理</a></li>
        <li><a href="">后台管理</a></li>
    </shiro:hasRole>
</ul>

<%-- 退出 --%>
<a href="${pageContext.request.contextPath}/user/logout">退出登录</a>


</body>
</html>