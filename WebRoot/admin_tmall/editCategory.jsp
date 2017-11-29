<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../include/adminNavigator.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>编辑分类</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.0.0.js"></script>

  </head>
  
  <body>
  		<div>
			<ol class="breadcrumb">
			    <li><a href="./categorylist.action?crud=select">所有分类</a></li>
			    <li class="active">编辑分类</li>
			</ol>
        </div>
    <div class="panel panel-warning addDiv">
      <div class="panel-heading">分类编辑</div>
      <div class="panel-body">
            <form method="post" id="addForm" action="" enctype="multipart/form-data">
                <table class="addTable">
                    <tr>
                        <td>分类名称</td>
                        <td><input  id="name" name="name" type="text" class="form-control"></td>
                    </tr>
                    <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<td></tr>
                    <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;<td></tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <button type="submit" class="btn btn-success">提 交</button>
                        </td>
                    </tr>
                </table>
            </form>
      </div>
    </div>
  </body>
</html>
