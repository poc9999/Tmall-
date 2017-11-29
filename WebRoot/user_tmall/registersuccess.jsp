<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isErrorPage="true"%>
<%response.setStatus(HttpServletResponse.SC_OK);%>
<%@ include file="../include/userHeader.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册用户信息成功</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
	
		div.registerSuccessDiv{
			margin: 10px 20px;
			background-color: #F3FDF6;
			border: 1px solid #DEF3E6;
			font-size: 16px;
			color: #3C3C3C;
			padding: 20px 130px;
		}
	</style>
  </head>
  <body>
   	
   	<div class="registerSuccessDiv">
   		<img src="./img/registerSuccess.png"/>
   		恭喜注册成功
   	</div>
   	<!-- 引入页脚 -->
    <%@ include file="../include/userFooter.jsp" %>
  </body>
</html>
