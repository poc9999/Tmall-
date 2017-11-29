<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ include file="../include/adminNavigator.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>所有订单页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.0.0.js"></script>

	<script type="text/javascript">
		$(function(){
		
			$(".delivery").click(function(){
				var $oid=$(this).attr("oid");
				var url="${pageContext.request.contextPath}/user_tmall/submitorder.action";
				param={type:"delivery",oid:$oid};
				//param={type:"delete",id:$id,pid:$pid};
				$.ajax({
					url:url,
					data:param,
					type:"post",
					success:function(returnDate){
					//alert('999');
						window.location.reload();
					}
				});
			});
		});
	</script>
  </head>
  
  <body>
       <div class="workingArea">
        <h1 class="label label-info">订单管理</h1>
        <br/>
        <br/>
         <div class="DataTableDiv">
            <table class="table table-bordered table-hover table-striped table-condensed">
                <!--表头-->
                <thead>
                <tr class="warning">
                <th>ID</th>
                <th>订单状态</th>
                <th>订单金额</th>
                <th>商品数量</th>
                <th>买家昵称</th>
                <th>创建订单时间</th>
                <th>支付订单时间</th>
                <th>订单发货时间</th>
                <th>确认收货时间</th>
                <th>订单详情</th>
                <th>操作</th>
                </tr>
                </thead>
                <!--表体-->
                <tbody>
                <c:forEach items="${page.data}" var="orderitem">
                	<tr>
                	 <td>${orderitem.id}</td>
                	 <td>${orderitem.order.statusDesc}</td>
                	 <td>¥<fmt:formatNumber value="${orderitem.product.promotePrice*orderitem.number}" pattern="#0.00"/></td>
                	 <td>${orderitem.number}</td>
                	 <td>${orderitem.order.userMessage}</td>
                	 <td><fmt:formatDate value="${orderitem.order.createDate}" type="both" dateStyle="medium" timeStyle="medium"/></td>
                	 <td><fmt:formatDate value="${orderitem.order.payDate}" type="both" dateStyle="medium" timeStyle="medium"/></td>
                	 <td><fmt:formatDate value="${orderitem.order.deliveryDate}" type="both" dateStyle="medium" timeStyle="medium"/></td>
                	 <td><fmt:formatDate value="${orderitem.order.confirmDate}" type="both" dateStyle="medium" timeStyle="medium"/></td>
                	 <td><input type="button" class="btn btn-success btn-default btn-xs" id="lookorder" value="查看订单"/></td>
                	 
                	 <c:if test="${orderitem.order.status=='1'}">
                		<td><p style="font-size: 14px;">待付款</p></td>
                	</c:if>
                	 <c:if test="${orderitem.order.status=='2'}">
                	 	<td><input type="button" class="btn btn-danger btn-default btn-xs delivery" oid="${orderitem.order.id}" value="立即发货"/></td>
                	</c:if>
                	<c:if test="${orderitem.order.status=='3'}">
                	 	<td><p style="font-size: 14px;">待确认</p></td>
                	</c:if>
                	<c:if test="${orderitem.order.status=='4'}">
                	 	<td><p style="font-size: 14px;">待评价</p></td>
                	</c:if>
                	<c:if test="${orderitem.order.status=='5'}">
                	 	<td><p style="font-size: 14px;">已完成</p></td>
                	</c:if>
                	<c:if test="${orderitem.order.status=='6'}">
                	 	<td><p style="font-size: 14px;">已删除</p></td>
                	</c:if>
                	</tr>
               	</c:forEach>
                </tbody>
            </table>
         </div>
     </div>
   	
   <!-- 分页Div -->
   <div class="pageDiv">
   		<%@include file="../include/OrderPage.jsp" %>
   </div>
   <hr/>
    <div style="position: relative;left: 0px;top: 0px">
    	<%@include file="../include/adminFooter.jsp" %>
    </div>
    
  </body>
</html>
