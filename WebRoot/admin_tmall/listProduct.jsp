<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 网页头 -->
<%@ include file="../include/adminNavigator.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>产品属性管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- 引入各种css包和js包 -->
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.0.0.js"></script>
  </head>
  
  <script type="text/javascript">
  
 	$(function(){
 	
 		$("#delBtn").click(function(){
 			
 			var confirmDel=confirm("该操作不可恢复,请确认是否操作?");
 			
 			if(confirmDel){
 				window.location.href="";
 			}
 			
 		});
 	
 	
 	});
 
 
 </script>
  <body>
  <div class="workingArea">
        <!-- 面包屑导航栏 -->
        <div>
			<ol class="breadcrumb">
			    <li><a href="./categorylist.action?crud=select">所有分类</a></li>
			    <li><a href="#">${name}</a></li>
			    <li class="active">产品管理</li>
			</ol>
        </div>
         <div class="DataTableDiv">
            <table class="table table-bordered table-hover table-striped table-condensed">
                <!--表头-->
                <thead>
                <tr class="warning">
                <th>ID</th>
                <th>图片</th>
                <th>产品名称</th>
                <th>产品小标题</th>
                <th>原价格</th>
                <th>优惠价</th>
                <th>库存数量</th>
                <th>图片管理</th>
                <th>属性设置</th>
                <th>编辑</th>
                <th>删除</th>
                </tr>
                </thead>
                <!--表体-->
                <tbody>
                
            	<c:forEach items="${page.data}" var="prod">
               <tr>
               		<td>${prod.id}</td>
               		<td><img src="" alt="未找到"/></td>
               		<td>${prod.name}</td>
               		<td>${prod.subTitle}</td>
               		<td>${prod.orignalPrice}</td>
               		<td>${prod.promotePrice}</td>
               		<td>${prod.stock}</td>
               		<td><a href="#" class="glyphicon glyphicon-picture"></a></td>
               		<td><a href="#" class="glyphicon glyphicon-th-list"></a></td>
               		<!-- 编辑 -->
               		<td><a href="#"><span class="glyphicon glyphicon-edit"></span></a> </td>
                    <!-- 删除 -->
                    <td><input type="button" id="delBtn" value="删除"class="btn btn-danger btn-default btn-xs"/></td>
               </tr>
               </c:forEach>
                </tbody>
            </table>
         </div>
     </div>
     <!-- 分页Div -->
   	<div class="pageDiv">
   		<%@include file="../include/ProductPage.jsp" %>
   	</div>
   	<hr/>
    <%@include file="../include/adminFooter.jsp" %>
  </body>
</html>
