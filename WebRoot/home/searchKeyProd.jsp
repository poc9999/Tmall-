<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
   <style type="text/css">
    	
    body{
        font-size: 12px;
        font-family: Arial;
    }
    div.searchProductAllDiv{
        background-color: #faf9f9;
        margin: 40px 20px 20px 20px;
        padding: 4px;
    }
    a.eachone{
        border: 1px solid #cccccc;
        line-height: 17px;
        padding: 3px;
    }
    span.oneSpan{
        background-color: #f1edec;
    }
    /*指定所有a标签和箭头*/
    div.AllItems a,span.glyphicon{
        color:#806f66;
    }
    /*第一个单元格*/
    div.AllItems span.one {
        color: #ff0036;
    }
    span.redColor{
        color: #ff0036;
    }
    div.AllItems span.glyphicon{
        font-size: 10px;
    }

    div.AllItems a.eachone:hover{
        background-color: #f1edec;
        color: #FF0036;
        text-decoration: none;
        cursor: pointer;
    }
    span.inputDiv input{
        /*border-width: 0px;*/
        height: 22px;
        width: 50px;
    }
    /*最外层的div*/
    div.categoryPageDiv{
        max-width: 1300px;
        margin: 10px auto;
    }
    div.categoryPage{
        padding: 0px 20px 40px 20px;
    }
    /*外层框架*/
    div.productUnit{
        width: 225px;
        height: 345px;
        /*外边框3个像素的白色*/
        border: 3px solid #fff;
        /*背景色白色*/
        background-color: white;
        /*上下12个像素的边距*/
        margin: 12px 5px;
        /*向左悬浮*/
        float: left;
    }
    /*外层边框的悬浮状态*/
    div.productUnit:hover{
        border: 3px solid #FF0036;
    }
    /*内层边框*/
    div.productUnitFrame{
        border: 1px solid #ffffff;
        height: 100%;
    }
    /*内层边框的悬浮状态*/
    div.productUnitFrame:hover{
        border: 1px solid #ff0036;
    }

    div.productUnit img.productImage{
        width: 95%;
        height: 190px;
    }
    /*产品价格*/
    div.productUnit span.productPrice{
        font-size: 20px;
        color: #cc0000;
        display: block;
        padding-left: 4px;
    }
    /*产品链接*/
    div.productUnit a.productLink{
        margin: 10px 0px;
        color: #333333;
        display: block;
        height: 34px;
    }
    /*悬浮*/
    div.productUnit a.productLink:hover{
        text-decoration: underline;
        color: #FF0036;
    }
    div.productUnit a.tmallLink{
        margin: 10px 0px;
        color: #999999;
        display: block;
        text-decoration: underline;
    }
    /*悬浮效果*/
    div.productUnit a.tmallLink:hover{
        text-decoration: underline;
        color: #FF0036;
    }
    /*成交，评价，旺旺所在的div*/
    div.productUnit div.productInfo{
        /*上边框线1px*/
        border-top-width: 1px;
        border-top-style: solid;
        border-top-color: #eeeeee;
        color: #999999;
    }
    /*月成交数量*/
    div.productUnit span.productDealNum{
        font-weight: bold;
        color: #b57c5b;
    }
    /*评价所在span*/
    div.productUnit span.productReview{
        border-left-style: solid;
        border-left-width: 1px;
        border-left-color: #eeeeee;
        border-right-width: 1px;
        border-right-style: solid;
        border-right-color: #eeeeee;
    }
    /*旺旺图标*/
    span.wangwang{
        padding-left: 5px;
    }
    /*评价数量所在div*/
    span.productReviewNum{
        font-weight: bold;
        color: #3388bb;
    }
    /*月成交和评价所在span*/
    span.monthDeal,span.productReview{
        display: inline-block;
        width: 90px;
        height: 29px;
        padding-top: 5px;
        padding-left: 5px;
    }
    
   	div.noMatch {
		font-size: 20px;
		width: 200px;
		margin: 100px auto;
		color: #888;
	}  
   </style>
  </head>
  
  <body>
  <!-- 根据关键词拿到所有产品 -->
   <div class="categoryPageDiv">
        <div class="searchProductAllDiv">
            <div class="AllItems">
                <a class="eachone oneSpan">
                    <span class="one">综合</span>
                    <span class="glyphicon glyphicon-arrow-down redColor"></span>
                </a>
                <a class="eachone">
                    <span>人气</span>
                    <span class="glyphicon glyphicon-arrow-down"></span>
                </a>
                <a class="eachone">
                    <span>新品</span>
                    <span class="glyphicon glyphicon-arrow-down"></span>
                </a>
                <a class="eachone">
                    <span>销量</span>
                    <span class="glyphicon glyphicon-arrow-down"></span>
                </a>
                <a class="eachone">
                    <span>价格</span>
                    <span class="glyphicon glyphicon-resize-vertical"></span>
                </a>
                <span class="inputDiv">
                <input type="text" class="content" placeholder="¥请输入"/>
                <span class="line">-</span>
                <input type="text" class="content" placeholder="¥请输入"/>
            </span>
            </div>
        </div>
          </div>
          
          <!--产品信息-->
        <div class="categoryPage">
            <c:forEach items="${prod_list}" var="prod">
            <div class="productUnit" price="${prod.promotePrice}">
                <div class="productUnitFrame">
                    <a href="${pageContext.request.contextPath}/user_tmall/alluserlist.action?user_type=eachOneProd&id=${prod.id}&order=no">
                        <img src="http://oz38alnl2.bkt.clouddn.com/productSingle_middle/${prod.firstProductImage.id}.jpg" width="100px" class="productImage"/>
                    </a>
                    <span class="productPrice">¥${prod.orignalPrice}</span>
                    <a href="${pageContext.request.contextPath}/user_tmall/alluserlist.action?user_type=eachOneProd&id=${prod.id}&order=no" class="productLink">
                        ${fn:substring(prod.name,0,20)}
                    </a>
                    <a href="#" class="tmallLink">天猫专卖</a>
                    <div class="productInfo">
                        <span class="monthDeal">月成交<span class="productDealNum">${prod.reviewCount}笔</span></span>
                        <span class="productReview">评价<span class="productReviewNum">${prod.saleCount}</span></span>
                        <span class="wangwang">
                            <a href="#">
                                <img src="${pageContext.request.contextPath}/img/wangwang1.png"/>
                            </a>
                        </span>
                    </div>
                    
                	</div>
            	</div>
            	</c:forEach>
            </div>
            <c:if test="${empty prod_list}">
            	<div class="noMatch">没有满足条件的产品</div>
            </c:if>
            <div style="clear: both"></div>
  </body>
</html>
