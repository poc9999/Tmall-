<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ include file="../include/adminNavigator.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>天猫后台管理</title>
    
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
   
   		$(".delete").click(function(){
   			
   			var confirmDel=confirm("该操作不可恢复,请确认是否操作?");
   			
   			if(confirmDel){
   				url="${pageContext.request.contextPath}/admin_tmall/categorylist.action";
   				var $cid=$(this).attr("cid");
   				//alert($cid);
   				param={crud:"delete",cid:$cid};
   				$.ajax({
   					url:url,
   					data:param,
   					type:"post",
   					success:function(returnData){
   						//刷新当前页面
   						window.location.reload();
   					}
   				});
   				
   			}
   		});
   		
   		$("#insertBtn").click(function(){
   			
   			var $cname = $("#cname").val();
   			
   			if(0==$cname.length){
   				alert("请输入分类名称");
   			}else{
	   			$("#addForm").submit();
   			}
   			
   		});
   		
   		
   });
  
  
  </script>
  <body>
  
   	<div class="workingArea">
        <h1 class="label label-info">分类管理</h1>
        <br/>
        <br/>
         <div class="DataTableDiv">
            <table class="table table-bordered table-hover table-striped table-condensed">
                <!--表头-->
                <thead>
                <tr class="warning">
                <th>ID</th>
                <th>分类名称</th>
                <th>属性管理</th>
                <th>产品管理</th>
                <th>编辑</th>
                <th>删除</th>
                </tr>
                </thead>
                <!--表体-->
                <tbody>
                <c:forEach items="${page.data}" var="cate">
               <tr>
               		<td>${cate.id}</td>
               		<td>${cate.name}</td>
               		<!-- 属性管理 -->
                    <td><a href="${pageContext.request.contextPath}/admin_tmall/propertylist.action?cid=${cate.id}&name=${cate.name}&crud=select"><span class="glyphicon glyphicon-th-list"></span></a> </td> 
                    <!-- 产品管理 -->
                    <td><a href="${pageContext.request.contextPath}/admin_tmall/productlist.action?name=${cate.name}&crud=select"><span class="glyphicon glyphicon-shopping-cart"></span></a></td>
                    <!-- 编辑 -->
                    <td><a href="${pageContext.request.contextPath}/admin_tmall/editCategory.jsp"><span class="glyphicon glyphicon-edit"></span></a> </td>
                    <!-- 特别小的删除按钮 -->
                    <td><input type="button" id="delBtn" cid="${cate.id}" value="删除" class="btn btn-danger btn-default btn-xs delete"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
         </div>
     </div>
   	
   <!-- 分页Div -->
   <div class="pageDiv">
   		<%@include file="../include/CategoryPage.jsp" %>
   </div>
   <hr/>
   <div class="panel panel-warning addDiv">
      <div class="panel-heading">新增分类</div>
      <div class="panel-body">
            <form method="post" id="addForm" action="${pageContext.request.contextPath}/admin_tmall/categorylist.action?crud=insert">
                <table class="addTable">
                    <tr>
                        <td>分类名称</td>
                        <td><input  id="cname" name="cname" type="text" class="form-control"></td>
                    </tr>
                    <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<td></tr>
                    <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<td></tr>
                    <tr class="submitTR">
                        <td colspan="3" style="text-align: center">
                            <button type="button" class="btn btn-success" id="insertBtn">提 交</button>
                        </td>
                    </tr>
                </table>
            </form>
      </div>
    </div>
   <div>
   <hr/>
   	<%@include file="../include/adminFooter.jsp" %>
   </div>
  </body>
</html>
