<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>Tmall管理员登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminlogin.css">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.0.0.js"></script>
  </head>
  
  <script type="text/javascript">
  	
  	$(function(){
  	
  		$("#loginBtn").click(function(){
  		
  			var $name=$("#username").val();
  			var $pass=$("#password").val();
  			
  			if($name==null||$pass==null){
  				alert("请正确输入用户名和密码！");
  				return;
  			}else if(($name=="请输入用户名")||($pass=="请输入密码")){
  				alert("请不要使用提示用户名或密码");
  				return;
  			}else{
  				$("#form1").submit();
  			}
  		});
  	
  	
  	});
  </script>
  
  <body background="${pageContext.request.contextPath}/img/background.jpg">
    <div id="login">

		<h2><span class="fontawesome-lock"></span>天猫管理员登录</h2>

		<form action="${pageContext.request.contextPath}/admin_tmall/login.action?property=admin&crud=select" method="POST" id="form1">

			<fieldset>
				<p><label for="text">用户名:</label></p>
				<p><input type="text" id="username" name="username"value="请输入用户名" onBlur="if(this.value=='')this.value='请输入用户名'" onFocus="if(this.value=='请输入用户名')this.value=''"></p> 

				<p><label for="password">密码:</label></p>
				<p><input type="password" id="password"name="password" value="请输入密码" onBlur="if(this.value=='')this.value='请输入密码'" onFocus="if(this.value=='请输入密码')this.value=''"></p> 
				
				<p><input type="button" id="loginBtn" value="登录"></p>
			</fieldset>
		</form>
	 </div>
	 <!--登录失败信息 -->
	 <div align="center" class="errorLogin"><h2 style="color: #fff">${error}</h2></div>
  </body>
</html>
