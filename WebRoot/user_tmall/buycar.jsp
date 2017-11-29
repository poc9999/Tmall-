<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../include/userHeader.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>我的购物车</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
 	
 	<link href="${pageContext.request.contextPath}/css/reset.css" rel="stylesheet"/>
 	<link href="${pageContext.request.contextPath}/css/carts.css" rel="stylesheet"/>
  	<style type="text/css">
  		div.noMatch {
		font-size: 20px;
		width: 200px;
		margin: 100px auto;
		color: #888;
	} 
  	
  	</style>
  </head>
  
  <body>
	  <div style="position: relative; left: 0px;top: 0px">
	    <%@ include file="../include/simpleSearchDiv.jsp" %>
	    </div>
	    
        <section class="cartMain">
        <div class="cartMain_hd">
            <ul class="order_lists cartTop">
                <li class="list_chk">
                    <!--所有商品全选-->
                    <input type="checkbox" id="all" class="whole_check">
                    <label for="all"></label>
                    全选
                </li>
                <li class="list_con">商品信息</li>
                <li class="list_info">商品参数</li>
                <li class="list_price">产品原始价格</li>
                <li class="list_amount">数量</li>
                <li class="list_sum">金额</li>
                <li class="list_op">操作</li>
            </ul>
        </div>

        <div class="cartBox">
            <div class="shop_info">
                <div class="all_check">
                    <!--店铺全选-->
                    <input type="checkbox" id="shop_a" class="shopChoice">
                    <label for="shop_a" class="shop"></label>
                </div>
                <div class="shop_name">
                    店铺：<a href="javascript:;">天猫自营</a>
                </div>
            </div>
            <div class="order_content">
            <c:forEach items="${oi_list}" var="orderitem">
                <ul class="order_lists">
                    <li class="list_chk" oiid="${orderitem.id}">
                        <input type="checkbox" id="checkbox${orderitem.product.firstProductImage.id}" class="son_check">
                        <label for="checkbox${orderitem.product.firstProductImage.id}"  class="labelOiid"></label>
			            <input type="hidden" value="${orderitem.id}" class="orderitem_id"/>
                    	<input type="hidden" value="${orderitem.product.id}" class="orderitem_pid"/>
                    </li>
                    <li class="list_con">
                        <div class="list_img"><a href="javascript:;"><img src="${pageContext.request.contextPath}/productSingle_middle/${orderitem.product.firstProductImage.id}.jpg" alt=""></a></div>
                        <div class="list_text"><a href="javascript:;">${orderitem.product.name}</a></div>
                    </li>
                    <li class="list_info">
                        <p>规格：默认</p>
                    </li>
                    <li class="list_price">
                        <p class="price">¥<fmt:formatNumber value="${orderitem.product.promotePrice}" pattern="#0.00"/></p>
                    </li>
                    <li class="list_amount">
                        <div class="amount_box">
                            <a href="javascript:;" class="reduce reSty">-</a>
                            <input type="text" value="${orderitem.number}" class="sum">
                            <a href="javascript:;" class="plus">+</a>
                        </div>
                    </li>
                    <li class="list_sum">
                        <p class="sum_price">¥<fmt:formatNumber value="${orderitem.product.promotePrice}" pattern="#0.00"/></p>
                    </li>
                    <li class="list_op">
                        <p class="del"><a href="javascript:;" class="delBtn">移除商品</a></p>
                    </li>
                </ul>
           </c:forEach>
            </div>
        </div>
        <!--底部-->
        <div class="bar-wrapper">
            <div class="bar-right">
                <div class="piece">已选商品<strong class="piece_num">0</strong>件</div>
                <div class="totalMoney">共计: <strong class="total_text">0.00</strong></div>
                <div class="calBtn"><a>结算</a></div>
            </div>
        </div>
    </section>
    <section class="model_bg"></section>
    <section class="my_model">
        <p class="title">删除宝贝<span class="closeModel">X</span></p>
        <p>您确认要删除该宝贝吗？</p>
        <div class="opBtn"><a href="javascript:;" class="dialog-sure">确定</a><a href="javascript:;" class="dialog-close">关闭</a></div>
    </section>
    <c:if test="${empty oi_list}">
            	<div class="noMatch">购物车为空</div>
            </c:if>
            <div style="clear: both"></div>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.0.0.js"></script>
 	<script type="text/javascript" src="${pageContext.request.contextPath}/js/carts.js"></script>
    <%@ include file="../include/userFooter.jsp" %>
  </body>
</html>
