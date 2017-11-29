<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	
	<link href="${pageContext.request.contextPath}/css/prod_list.css" rel="stylesheet"/>
	
	</head>
  <body>
    <div class="CategroyProducts">
    <c:forEach items="${cate_list}" var="cate">
        <div class="eachHomepageCategoryProducts">
            <div class="left-mark"></div>
            <span class="CategoryTitle">${cate.name}</span>
            <br/>
            <c:forEach items="${cate.prod_list}" var="prod">
                <div class="productItem">
                    <a href="${pageContext.request.contextPath}/user_tmall/alluserlist.action?user_type=eachOneProd&id=${prod.id}&cid=${cate.id}&order=no"><img src="http://oz38alnl2.bkt.clouddn.com/productSingle_middle/${prod.firstProductImage.id}.jpg" width="100px"></a>
                    <a href="${pageContext.request.contextPath}/user_tmall/alluserlist.action?user_type=eachOneProd&id=${prod.id}&order=no" class="productItemLink">
                    <span class="productItemDesc">
                        	${fn:substring(prod.name,0,20)}
                    </span>
                    </a>
                    <span class="productPrice">
                        ¥${prod.orignalPrice}0
                    </span>
                </div>
                </c:forEach>
                <div style="clear: both"></div>
       	</div>
      </c:forEach>
        <img src="${pageContext.request.contextPath}/img/end.png" class="endpng"/>
    </div>
  </body>
</html>
