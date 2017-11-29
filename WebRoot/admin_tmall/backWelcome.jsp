<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../include/adminNavigator.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- 引入bootstrap框架 -->
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
  </head>
  <body>
  <br/>
  <br/>
  <br/>
  <br/>
  <div class="jumbotron">
    	<div style="display:block;" align="center">
    		<h1><span class="label label-success">欢迎${admin.name}登录tmall后台管理系统</span></h1>
    	</div>
   </div>
    <div>
    <!-- 页尾 -->
    <%@include file="../include/adminFooter.jsp" %>
    </div>
  </body>
</html>
