<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isErrorPage="true"%>
<%response.setStatus(HttpServletResponse.SC_OK);%>
<%@ include file="../include/userHeader.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>天猫${keyword}搜索</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.0.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>

  </head>
  
  <body>
  
    <div style="position: relative; left: 0px;top: 0px">
    <%@ include file="../include/simpleSearchDiv.jsp" %>
    </div>
    <!-- 搜索产品页面 -->
    <%@ include file="../home/searchKeyProd.jsp" %>
    
    <div style="position: relative;">
		<%@ include file="../include/userFooter.jsp"%>
    </div>
    
    
  </body>
</html>
