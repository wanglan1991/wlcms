<%--
  Created by IntelliJ IDEA.
  User: sxjun
  Date: 15-9-18
  Time: 下午3:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>
<link  type="text/css" rel="stylesheet" href="${ctxAssets}/thinker-md/thinker-md.vendor.css" />
<h4>
  该权限管理系统以SpringMvc+MiniJdbc+Shiro+MySQL+MQ+Redis+Flappy+Maven为架构，实现了用户-角色-权限三者结合的功能权限颗粒化控制：</h4>
<pre><code class="hljs livecodeserver">按钮根据权限限制
  菜单根据权限显示
  所有相关<span class="hljs-built_in"><span class="hljs-built_in">URL</span></span>根据权限拦截
</code></pre>
<span id="OSC_h4_2"></span><h4>数据权限暂时以用户为中心查询：</h4>
<pre><code class="hljs">查询部门只能查询本部门以及子级部门
  查询用户只能查询本级没有管理权限的用户以及所有子级用户
</code></pre>
<span id="OSC_h4_3"></span><h4 id="-shiro-redis-map-ehcache-shiro-xml-">
  会话管理使用Shiro的框架，结合Redis缓存，便于缓存控制以及实现分布式部署。如果想要实现自带的Map缓存或者使用Ehcache缓存都可以直接修改<code>shiro.xml</code>文件即可</h4>

<span id="OSC_h4_12"></span><h4>未完待续。。。</h4>

</body>
</html>
