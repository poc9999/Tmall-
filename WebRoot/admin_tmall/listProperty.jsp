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
			    <li class="active">属性管理</li>
			</ol>
        </div>
         <div class="DataTableDiv">
            <table class="table table-bordered table-hover table-striped table-condensed">
                <!--表头-->
                <thead>
                <tr class="warning">
                <th>ID</th>
                <th>属性名称</th>
                <th>编辑</th>
                <th>删除</th>
                </tr>
                </thead>
                <!--表体-->
                <tbody>
                
            	<c:forEach items="${prop_list}" var="prop">
               <tr>
               		<td>${prop.id}</td>
               		<td>${prop.name}</td>
               		<td><a href="${pageContext.request.contextPath}/admin_tmall/propertylist.action?crud=update&name=${prop.name}"><span class="glyphicon glyphicon-edit"></span></a> </td>
                    <!-- 删除 -->
                    <td><input type="button" id="delBtn" value="删除"class="btn btn-danger btn-default btn-xs"/></td>
               </tr>
               </c:forEach>
                </tbody>
            </table>
         </div>
     </div>
   <hr/>
    <%@include file="../include/adminFooter.jsp" %>
  </body>
</html>
