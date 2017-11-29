<%@ page language="java"  isELIgnored="false" import="java.util.*" pageEncoding="utf-8"%>

<!-- 最上面的导航栏 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body>
    <div class="navitagorDiv">
        <!--导航栏-->
     <nav class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation">
        <img style="margin-left: 10px;margin-right: 0px; "class="pull-left"
             src="${pageContext.request.contextPath}/img/tmallbuy.png" height="45px"/>
             
         <a class="navbar-brand" href="#nowhere">天猫后台</a>
         <a class="navbar-brand" href="${pageContext.request.contextPath}/admin_tmall/categorylist.action?crud=select">分类管理</a>
         <a class="navbar-brand" href="${pageContext.request.contextPath}/admin_tmall/userlist.action?crud=select">用户管理</a>
         <a class="navbar-brand" href="${pageContext.request.contextPath}/admin_tmall/allorder.action">订单管理</a>
         <a class="navbar-brand" href="${pageContext.request.contextPath}/admin_tmall/adminLogin.jsp">退出登录</a>
     </nav>
     </div>
  </body>
</html>
