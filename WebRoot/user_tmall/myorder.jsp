<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isErrorPage="true"%>
<%response.setStatus(HttpServletResponse.SC_OK);%>
<%@ include file="../include/userHeader.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>所有订单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myorder.css"/>
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
  		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.0.0.js"></script>
  	<style type="text/css">
  	
  		div.noMatch {
		font-size: 20px;
		width: 200px;
		margin: 100px auto;
		color: #888;
	}
  	</style>
  	<script type="text/javascript">
	$(function(){
		//立即支付按钮
  		$("#payment").click(function(){
  			var $oiid = $(this).attr("oiid");
  			window.location.href="${pageContext.request.contextPath}/user_tmall/submitorder.action?type=myorderSubmit&oiid="+$oiid;
  		});
  		//催卖家发货按钮
  		$("#deliver").click(function(){
  			var $oiid = $(this).attr("oiid");
  			var url="${pageContext.request.contextPath}/user_tmall/submitorder.action";
  			params={type:"deliverGoods",oiid:$oiid};
  			$.ajax({
  				url:url,
  				data:params,
  				type:"post",
  				success:function(returnData){
  					alert(returnData);
  					window.location.reload();
  				}
  			});
  		});
  		//立即确认按钮
  		$("#confirm").click(function(){
  			var $oiid = $(this).attr("oiid");
  			var flag=confirm("确认订单卖家将会立即收到你的款项,请确认是否进行下一步操作");
  			
  			if(flag){
	  			var url="${pageContext.request.contextPath}/user_tmall/submitorder.action";
				params={type:"confirm",oiid:$oiid};
	  			$.ajax({
	  				url:url,
	  				data:params,
	  				type:"post",
	  				success:function(returnData){
	  					window.location.reload();
	  				}
	  			});
  			}
  		});
  		
  	});
  	</script>
  </head>
  
  <body>
  <div style="position: relative; left: 0px;top: 0px">
    <%@ include file="../include/simpleSearchDiv.jsp" %>
    </div>
    <div class="boughtDiv">
        <div class="orderTypeDiv">
            <div class="selectOrderType"><a href="#" orderstatus="all">所有订单</a></div>
            <div><a href="#" orderstatus="waitPay">待付款</a></div>
            <div><a href="#" orderstatus="waitDelivery">待发货</a></div>
            <div><a href="#" orderstatus="waitConfirm">待确认</a></div>
            <div><a href="#" orderstatus="waitReview"  class="noRightborder">待评价</a></div>
            <div class="noneDiv">
            </div>
        </div>
        <!--去除浮动布局-->
        <div style="clear: both"></div>
        
        <!--下面的表格-->
        <div class="orderListTitle">
            <table class="orderListTitleTable">
                <tr>
                    <td width="40%">宝贝</td>
                    <td width="10%">单价</td>
                    <td width="8%">数量</td>
                    <td width="10%">商品操作</td>
                    <td width="10%">实付款</td>
                    <td width="10%">交易状态<span class="glyphicon glyphicon-triangle-bottom"></span> </td>
                    <td width="10%">交易操作</td>
                </tr>
            </table>
        </div>
        <!--下面的订单div-->
           <c:forEach items="${order_list}" var="orderitem">
        	<div class="orderListItem">
            <table class="orderItemTable">
                <thead>
                    <tr>
                        <th width="40%"><b><fmt:formatDate value="${orderitem.order.createDate}"/></b>&nbsp;&nbsp;<span>订单号:${orderitem.order.orderCode}</span></th>
                        <th width="18%"><img width="13px" src="${pageContext.request.contextPath}/img/ordertmallByorder.png"/>天猫自营 </th>
                        <th width="10%">和我联系</th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>

                <tbody>
                <tr class="orderItemProductInfoPartTR">
                    <td width="40%">
                        <div class="orderProductInfo">
                            <div class="orderProductImg"><a><img src="${pageContext.request.contextPath}/productSingle_middle/${orderitem.product.firstProductImage.id}.jpg" width="80px" height="80px"/></a></div>
                            <div style="margin-left: 90px; text-align: left">
                        <a class="orderProductName">${orderitem.product.name}</a>
                        <div class="orderListItemProductLinkInnerDiv">
                            <img src="${pageContext.request.contextPath}/img/d11.png" title="双11狂欢价"/>
                            <img src="${pageContext.request.contextPath}/img/sevenreturn.png" title="七天退换"/>
                            <img src="${pageContext.request.contextPath}/img/promise.png" title="如实描述"/>
                            <img src="${pageContext.request.contextPath}/img/zheng.png" title="正品保证"/>
                            <img src="${pageContext.request.contextPath}/img/ju.png"/>
                        </div>
                            </div>
                        </div>
                    </td>
                    <td class="orderItemProductInfoPartTD" width="8%">
                        <div class="orderListItemProductOriginalPrice">¥${orderitem.product.orignalPrice}</div>
                        <div class="orderListItemProductPrice">¥${orderitem.product.promotePrice}</div>
                    </td>
                    <td width="8%">
                        ${orderitem.number}
                    </td>
                    <td width="10%">
                        <div>违规举报</div>
                        <div>退运保险</div>
                    </td>
                    <td width="12%">
                        <span class="orderListItemProductRealPrice">¥<fmt:formatNumber value="${orderitem.number*orderitem.product.promotePrice}"/> </span>
                        <span class="orderListItemPriceWithTransport">(含运费:¥0.00)</span>
                    </td>
                    
                    <c:if test="${orderitem.order.status==1}">
                    <td width="10%" style="color: #3c3c3c">
                        <div>等待买家付款</div>
                        <div>订单详情</div>
                    </td>
                    <td width="10%">
                        <div class="paymentBtn">
                            <button type="button" class="btn btn-danger" id="payment" oiid="${orderitem.id}">立即付款</button>
                        </div>
                        <div>
                            <a href="#">找朋友帮忙付</a>
                            <a href="#">取消订单</a>
                        </div>
                    </td>
                    </c:if>
                    
                    <c:if test="${orderitem.order.status==2}">
                    <td width="10%" style="color: #3c3c3c">
                        <div>等待卖家发货</div>
                        <div>订单详情</div>
                    </td>
                    <td width="10%">
                        <div class="paymentBtn">
                        	<a>待发货</a>
                            <button type="button" class="btn btn-success" id="deliver" oiid="${orderitem.id}">催卖家发货</button>
                        </div>
                    </td>
                    </c:if>
                    
                    <c:if test="${orderitem.order.status==3}">
                    <td width="10%" style="color: #3c3c3c">
                        <div>卖家已发货</div>
                        <div>订单详情</div>
                    </td>
                    <td width="10%">
                        <div class="paymentBtn">
                        	<a>待确认收货</a>
                            <button type="button" class="btn btn-info" id="confirm" oiid="${orderitem.id}">立即确认</button>
                        </div>
                    </td>
                    </c:if>
                    
                    <c:if test="${orderitem.order.status==4}">
                    <td width="10%" style="color: #3c3c3c">
                        <div>等待买家评价</div>
                        <div>订单详情</div>
                    </td>
                    <td width="10%">
                        <div class="paymentBtn">
                        	<a>待评价</a>
                            <button type="button" class="btn btn-default">立即评价</button>
                        </div>
                    </td>
                    </c:if>
                    <c:if test="${orderitem.order.status==5}">
                    <td width="10%" style="color: #3c3c3c">
                        <div>订单已完成</div>
                        <div>订单详情</div>
                    </td>
                    <td width="10%">
                        <div class="paymentBtn">
                        	<a>订单已完成</a>
                            <button type="button" class="btn btn-default">删除订单</button>
                        </div>
                    </td>
                    </c:if>
                </tr>
                </tbody>
            </table>
        </div>
          </c:forEach>
    </div>
    
    		<c:if test="${empty order_list}">
            	<div class="noMatch">没有任何订单</div>
            </c:if>
            <div style="clear: both"></div>
    <div style="position: relative;">
		<%@ include file="../include/userFooter.jsp"%>
    </div>
    
  </body>
</html>
