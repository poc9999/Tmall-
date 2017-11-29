<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isErrorPage="true"%>
<%response.setStatus(HttpServletResponse.SC_OK);%>
<%@ include file="../include/userHeader.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>支付成功页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
  	<link href="${pageContext.request.contextPath}/css/paysuccess.css" rel="stylesheet"/>
  </head>
  
  <body>
  <div style="position: relative; top: 0px;left: 0px">
  <%@ include file="../include/simpleSearchDiv.jsp" %>
  </div>
    <div class="allPayOkOrder">
        <div class="UserInfoDiv">
        <div class="alert alert-success"><span class="glyphicon glyphicon-ok-sign">你已经成功付款</span></div>
        <div class="addressInfo">
            <ul class="allUserInfo">
            	<li>订单编号:${order.orderCode}</li>
                <li>收货地址:${order.address}</li>
                <li>收货人姓名:${order.receiver}</li>
                <li>收货人联系电话:${order.mobile}</li>
                <li>实付款:<span class="payedPrice">¥<fmt:formatNumber value="${totalPrice}" pattern="#0.00"/></span> </li>
                <li>预计送达时间:<fmt:formatDate value="${arrDate}"/></li>
            </ul>
            <div class="checkLinkBuy">
                你可以查看<a href="#">买到的宝贝</a>  <a href="#">交易详情</a>
            </div>
        </div>
        </div>
        <!--空的div-->
        <div style="height: 30px; border-top:1px dotted #d4d4d4;margin: 0px 30px"></div>

        <div class="alert alert-danger">
            <span class="glyphicon glyphicon-info-sign"></span><b>安全提醒:</b>下单后，<b style="color: red">用QQ给您发送链接办理退款的都是骗子！</b><p style="color: black;display: inline-block">天猫不存在系统升级，订单异常等问题，谨防假冒客服电话诈骗!</p>
        </div>
        </div>
        
        <%@ include file="../include/userFooter.jsp" %>
  </body>
</html>
