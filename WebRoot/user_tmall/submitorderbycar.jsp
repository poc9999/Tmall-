<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isErrorPage="true"%>
<%response.setStatus(HttpServletResponse.SC_OK);%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../include/userHeader.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>提交订单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${pageContext.request.contextPath}/css/submitorder.css" rel="stylesheet"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.0.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
  
  </head>
  <script type="text/javascript">
  	$(function(){
  		$("#orderBtn").click(function(){
  			var $address=$("#address").val();
  			var $revname=$("#revname").val();
  			var $phonenum=$("#phonenum").val();
  			
  			if(0==$address.length){
  				alert("请输入详细地址");
  			}else if(0==$revname){
  				alert("请输入收货人姓名");
  			}else if(0==$phonenum){
  				alert("请输入手机号码");
  			}else{
  				$("#form3").submit();
  			}
  		});
  	});
  
  </script>
  <body>
   <form action="${pageContext.request.contextPath}/user_tmall/submitorder.action?type=submitOrder" method="post" id="form3">
  	<!-- 隐藏域传输总价格和产品的id -->
    <input type="hidden" name="totalPrice" value="${product.promotePrice*buynum}"/>
    <input type="hidden" name="pid" value="${product.id}"/>
    
    <div class="AllOrderItem">
        <!--最上面的两张图片-->
        <div class="twoPhoto">
            <img src="${pageContext.request.contextPath}/img/ordertmall.png" class="pull-left">
            <img src="${pageContext.request.contextPath}/img/jindutiao1.png" class="pull-right">
            <div style="clear:both;"></div>
        </div>

        <div class="address">
            <div class="alert alert-info">收货地址</div>
            <div>
            <table class="detailTable">

            <tr><td class="firstCol">详细地址<span class="redStar">*</span></td><td><textarea placeholder="建议您如实填写详细收货地址，例如接到名称，门牌号码，楼层和房间号等信息" name="address" id="address"></textarea></td></tr>
            <tr><td>邮政编码</td><td><input type="text" name="post" placeholder="如果你不清楚邮政编码,请填写000000"/> </td></tr>
            <tr><td>收货人姓名<span class="redStar">*</span></td><td><input type="text" name="revname" id="revname" placeholder="长度不超过25个字符"/> </td></tr>
            <tr><td>手机号码<span class="redStar">*</span></td><td><input type="text" name="phonenum" id="phonenum" placeholder="请输入11位手机号码"/> </td> <td><span class="redStar">*</span>为必填项</td></tr>
                </table>
            </div>
        </div>
    </div>
        <div class="buyPageDiv">
            <div class="productList">
                <div class="productListTip">确认订单信息</div>
                <table class="productTable">
                    <thead>
                    <tr>
                        <th class="productListTable">
                            <img src="${pageContext.request.contextPath}/img/tmallself.png" class="tmallBuy">
                            <a href="#" class="linkmarket">店铺:天猫自营</a>
                            <a href="#"><img src="${pageContext.request.contextPath}/img/wangwang.png" class="wangwangIcon"/></a>
                        <th></th>
                        <th>店铺宝贝</th>
                        <th>商品属性</th><th>单价</th><th>数量</th><th>优惠方式</th><th>小计</th>
                    </tr>
                    <tr class="rowborder">
                        <td class="firstCol"></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    </thead>
                    <tbody class="productListTbody">
                    <c:forEach items="${ois}" var="orderitem">
                        <tr class="orderItemTR">
                            <td class="orderItemFirstTD"colspan="2"><img width="20px" src="${pageContext.request.contextPath}/productSingle_middle/${orderitem.product.firstProductImage.id}.jpg" class="orderItemImg">
                            <td class="orderItemProductInfo">
                                <a class="orderItemProductLink">${orderitem.product.name}</a>
                            <div>
                                <img title="“天猫新品”是指天猫首次上架的特定商品" src="${pageContext.request.contextPath}/img/newproduct.png"/>
                                <img title="消费者服务保障,卖家承诺7天退换" src="${pageContext.request.contextPath}/img/sevenreturn.png"/>
                                <img title="消费者保障服务,卖家承诺如实描述" src="${pageContext.request.contextPath}/img/promise.png"/>
                                <img title="支持信用卡支付" src="${pageContext.request.contextPath}/img/xcard.png"/></div>
                        </td>
                            <td>
                                <div class="categoryEachOne">商家:天猫自营</div>
                            </td>
                            <td>
                                <span class="orderItemProductPrice">¥${product.promotePrice}</span>
                            </td>
                            <td>
                                <span class="orderItemProdu">${buynum}</span>
                            </td>

                            <td>
                                <span class="preway">省399:爆款促销</span>
                                <img src="${pageContext.request.contextPath}/img/msg.png" title="爆款促销:省399.00元，包邮" style="width: 16px"/>
                            </td>
                            <td class="orderItemLastTD">
                                <span class="orderItemUnitSum">¥<fmt:formatNumber value="${product.promotePrice*buynum}" pattern="#0.00"/></span>
                            </td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <div class="orderItemSumDiv">
                    <div class="pull-left orderItemLeft">
                        <span class="leaveMsgText">给卖家留言:</span>
                        <span><input type="text" name="leaveMsg" class="leaveMsg" placeholder="选填：对本次交易的说明(建议填写已和卖家协商一致的内容)"></span>
                    </div>

                    <div class="orderItemRight">
                        <div class="topTip">运送方式:普通配送 快递 免邮<span class="pull-right redColor">¥0.00</span></div>
                        <div class="topTip">运费险: 卖家送,确认收货前可赔<span class="pull-right redColor">¥0.00</span></div>
                    </div>
                </div> 

                <div class="orderItemTotalSumDiv">
                    <div class="pull-right">
                        <span>实付款:</span>
                        	<span class="orderItemTotalSumSpan">¥<fmt:formatNumber value="${totalPrice}" pattern="#0.00"/></span>
                    </div>
                </div>
                <div class="SubmitOrderDiv">
                    <button class="submitOrderButton" type="button" id="orderBtn">提交订单</button>
                </div>
            </div>
        </div>
        </form>
    <%@ include file="../include/userFooter.jsp"%>
  </body>
</html>
