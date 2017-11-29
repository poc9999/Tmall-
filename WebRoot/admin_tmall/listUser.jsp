<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ include file="../include/adminNavigator.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>天猫后台管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- 引入各种css包和js包 -->
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.0.0.js"></script>
  </head>
  
  <body>
    
    <div class="workingArea">
        <h1 class="label label-info">用户管理</h1>
        <br/>
        <br/>
         <div class="DataTableDiv">
            <table class="table table-bordered table-hover table-striped table-condensed">
                <!--表头-->
                <thead>
                <tr class="warning">
                <th>ID</th>
                <th>用户名称</th>
                </tr>
                </thead>
                <!--表体-->
                <tbody>
                <c:forEach items="${page.data}" var="user">
               <tr>
               		<td>${user.id}</td>
               		<td>${user.name}</td>
               </tr>
                </c:forEach>
                </tbody>
            </table>
         </div>
     </div>
     <div class="pageDiv">
   		<%@include file="../include/UserPage.jsp" %>
     </div>
     <hr/>
    <%@include file="../include/adminFooter.jsp" %>
  </body>
</html>
