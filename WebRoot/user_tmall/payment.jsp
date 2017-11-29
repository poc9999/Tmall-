<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isErrorPage="true"%>
<%response.setStatus(HttpServletResponse.SC_OK);%>
<%@ include file="../include/userHeader.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>商品付款页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/payment.css"/>
  </head>
  
  <body>
    <div class="allPayment">
    <form action="${pageContext.request.contextPath}/user_tmall/submitorder.action?type=paySuccess" method="post">
    	<!-- 发送过来的订单和价格信息  设置一个隐藏域保存信息-->
    	<input type="hidden" name="orderInfo" value="${orderInfo}"/>
    	<input type="hidden" name="totalPrice" value="${totalPrice}"/>
    	<input type="hidden" name="pid" value="${pid}"/>
        <div>
            <span class="ScanPayment">扫一扫付款(元)</span>
            <span class="PaymentPrice">¥<fmt:formatNumber value="${totalPrice}" pattern="#0.00"/></span>
        </div>
        <div>
            <img src="${pageContext.request.contextPath}/img/payment.png"/>
        </div>
        <div>
            <button type="submit" class="btn btn-primary">确认支付</button>
        </div>
        </form>
    </div>
<%@ include file="../include/userFooter.jsp"%>
  </body>
</html>
