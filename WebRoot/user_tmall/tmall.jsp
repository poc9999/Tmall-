<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isErrorPage="true"%>
<%response.setStatus(HttpServletResponse.SC_OK);%>
<%@ include file="../include/userHeader.jsp" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>天猫tmall.com</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${pageContext.request.contextPath}/css/tmall.css" rel="stylesheet"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.0.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
  </head>
  <script type="text/javascript">
  	
  	$(function(){
  		$("#searchBtn").click(function(){
  			var $keyword = $("#keyword").val();
  			
  			if(0==$keyword.length){
  				window.location.reload();
  			}else{
  				$("#form1").submit();
  			}
  		
  		});
  	
  	
  	});
  
  </script>
  <body>

<!--搜索区域的div-->
<div class="search_top">
    <!--天猫的图片-->
    <a href="#"><img src="${pageContext.request.contextPath}/img/tmall.gif"id="tmall"class="tmall"name="tmall"></a>

    <div class="searchDiv" align="center">
    	<!-- 根据关键词进行搜索 -->
    	<form action="${pageContext.request.contextPath}/user_tmall/alluserlist.action?user_type=searchKeyProd" method="post" id="form1">
        <input type="text" placeholder="全球好品甄选 " name="keyword" id="keyword">
        <button class="searchButton" type="button" id="searchBtn">搜索</button>
        </form>
        
        <div class="searchBelow">
            <span> <a href="#" style="color: #FF0036"> 牛仔裤 </a> <span>|</span></span>
            <span> <a href="#"> 连衣裙 </a> <span>|</span></span>
            <span> <a href="#"> 四件套 </a> <span>|</span></span>
            <span> <a href="#"style="color: #FF0036"> 剃须刀 </a><span>|</span></span>
            <span><a href="#">  客厅灯 </a> <span>|</span></span>
            <span><a href="#">  口红   </a> <span>|</span></span>
            <span><a href="#"style="color: #FF0036">  小白鞋   </a> <span>|</span></span>
            <span><a href="#">  女包   </a> <span>|</span></span>
            <span><a href="#"style="color: #FF0036">  手机壳   </a> </span>
        </div>
    </div>
</div>

<!--导航和轮播 -->
<nav class="head_show">
    <div class="head">
        <span style="margin-left: 20px" class="glyphicon glyphicon-th-list"></span>
        <span style="margin-left: 10px">商品分类</span>
    </div>

	    <div class="rightMenu">
        <span><a href="#"><img src="${pageContext.request.contextPath}/img/chaoshi.png"/></a> </span>
        <span><a href="#"><img src="${pageContext.request.contextPath}/img/guoji.png"/> </a> </span>
        <!-- 只遍历前几个 -->
		<c:forEach items="${cate_list}" varStatus="status" var="cate">
			<c:if test="${status.count<5}">
        		<span><a href="#">${cate.name}</a></span>
       		</c:if>
        </c:forEach>
        <span><a href="#">营业厅</a> </span>
        <span><a href="#">魅力惠</a> </span>
        <span><a href="#">飞猪旅行</a> </span>
        <span><a href="#">苏宁易购</a> </span>
    </div>
</nav>
	<!-- 分类页面  -->
  <div style="position: relative;">
  	<%@ include file="../home/categoryList.jsp" %>
  </div>
<!--轮播框架 -->
<div style="margin: 0px auto">
<div data-ride="carousel" class="carousel-of-product carousel slide" id="carousel-of-product">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li class="active" data-slide-to="0" data-target="#carousel-of-product"></li>
        <li data-slide-to="1" data-target="#carousel-of-product" class=""></li>
        <li data-slide-to="2" data-target="#carousel-of-product" class=""></li>
        <li data-slide-to="3" data-target="#carousel-of-product" class=""></li>
        <li data-slide-to="4" data-target="#carousel-of-product" class=""></li>
        <li data-slide-to="5" data-target="#carousel-of-product" class=""></li>
    </ol>
    <!-- Wrapper for slides -->
    <div role="listbox" class="carousel-inner">
        <div class="item active">
            <img src="${pageContext.request.contextPath}/img/p1.jpg" class="carousel carouselImage">
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/img/p2.jpg" class="carouselImage">
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/img/p3.jpg" class="carouselImage">
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/img/p4.jpg" class="carouselImage">
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/img/p5.jpg" class="carouselImage">
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/img/p6.jpg" class="carouselImage">
        </div>
    </div>
</div>
	<div class="carouselBackgroundDiv">
	</div>
</div>
	<div style="position: relative;">
		<%@ include file="../home/productList.jsp" %>
	</div>
<%@ include file="../include/userFooter.jsp" %>
  </body>
</html>
