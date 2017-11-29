<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
  <link href="${pageContext.request.contextPath}/css/modal.css" rel="stylesheet" />
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.0.0.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <!-- 页头的样式 -->
    <style type="text/css">
    body{
        font-size: 12px;
        font-family: Arial;
    }

    a{
        color:#999;
    }

    .redColor{
        color: #FF0036!important;
    }

    nav.top{
        /*背景色#f2f2f2*/
        background-color: #f2f2f2;
        /*上内边距5px*/
        padding-top: 5px;
        /*下内边距5px*/
        padding-bottom: 5px;

        border-bottom: 1px solid #e7e7e7;
    }

    nav.top span,nav.top a{
        color: #999;
        margin: 0px 10px 0px 10px;
    }

    nav.top a:hover{

        color:#FF0036;
    }
    div.loginErrorMessageDiv div.alert {
		padding: 5px !important;
	}

    </style>
	<!-- jquery代码  -->
	<script type="text/javascript">
		$(function(){
			$("#loginBtn").click(function(){
				var $name=$("#username").val();
				var $pass=$("#password").val();
			
			if(0==$name.length||0==$pass.length){		
		 			$("span.errorMsg").html("请输入账户名或密码");
		 			$("div.loginerroeMsg").show();
		 			return false;
	 			}
 			var url="${pageContext.request.contextPath}/admin_tmall/login.action";
 			param={username:$name,password:$pass,crud:"loginAjax",type:"user"};
 			$.ajax({
 				url:url,
 				type:"post",
 				data:param,
 				success:function(flag){
 					if("success"==flag){
 						//从新加载该页面
 						window.location.reload();
 					}else{
 						$("span.errorMsg").html("请输入正确的账户名或密码");
		 				$("div.loginerroeMsg").show();
 					}
 				}
 			});
			});
		});
		
		function data() {
			alert('这个按钮啥也没有,食屎啦你');
		}
	</script>
  </head>
  
  <body>
    
    <!--导航栏-->
    <nav class="top">
        <a href="${pageContext.request.contextPath}/user_tmall/tmall.jsp">
            <span class="glyphicon glyphicon-home redColor"></span>
           	 天猫首页
        </a>
        <span>喵，欢迎来到天猫</span>
		<c:if test="${!empty user}">
        	<a href="${pageContext.request.contextPath}/user_tmall/userlogin.jsp">${user.name}</a>
        	<a href="${pageContext.request.contextPath}/admin_tmall/login.action?crud=logout">退出</a>
        </c:if>
        <c:if test="${empty user}">
	        <a href="${pageContext.request.contextPath}/user_tmall/userlogin.jsp">请登录</a>
	        <a href="${pageContext.request.contextPath}/user_tmall/register.jsp">免费注册</a>
        </c:if>
        <!--BootStarp实现 向右浮动-->
        <span class="pull-right">
            <a href="${pageContext.request.contextPath}/user_tmall/myorder.action">我的订单</a>
            
            <c:if test="${!empty totalNum}">
            	<a href="${pageContext.request.contextPath}/user_tmall/joinbuycar.action?type=view">
            	<span class="glyphicon glyphicon-shopping-cart redColor">&nbsp;购物车<strong>${totalNum}</strong>件</span></a>
            </c:if>
            
            <c:if test="${empty totalNum}">
            	<a href="${pageContext.request.contextPath}/user_tmall/joinbuycar.action?type=view">
            	<span class="glyphicon glyphicon-shopping-cart redColor">&nbsp;购物车<strong>0</strong>件</span></a>
            </c:if>
        </span>
    </nav>  
    
  	<!-- 模态框 -->
  <div class="modal fade bs-example-modal-sm" id="mymodal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"  style="margin: 100px auto">
    <div class="modal-dialog modal-sm" role="document">
            <div class="loginDiv">
                <div class="loginSmallDiv">
	                <!-- 错误提示信息 -->
		      		<div class="loginerroeMsg" style="display: none">
			      		<div class="alert alert-danger">
			      			<span class="errorMsg"></span>
			      		</div>
		      		</div>
		      		
                    <div align="center">
                        <img src="${pageContext.request.contextPath}/img/cateye.png"/>
                    </div>
                    <div style="background-color: #ff0036;height: 35px; margin-bottom: 35px">
                        <img src="${pageContext.request.contextPath}/img/smallLogo.png"/>
                    </div>
                    
                    <!--账户登陆Div-->
                    <div class="loginName">密码登陆</div>

                    <div class="loginInput">
                <span class="loginSpanIcon">
                    <span class="glyphicon glyphicon-user"></span>
                </span>
                        <input type="text" name="username" id="username" placeholder="会员名/邮箱/手机号"/>
                    </div>

                    <div class="loginInput">

                <span class="loginSpanIcon">
                    <span class="glyphicon glyphicon-lock"></span>
                </span>
                        <input type="password" name="password" id="password" placeholder="密码"/>
                    </div>

                    <div style="margin-top: 20px">
                        <!--块级的父类元素-->
                        <button type="button" class="btn btn-block redButton" id="loginBtn">登陆</button>
                    </div>

                    <div class="forgetTip pull-right">
                        <a href="Javascript:void(0)" onclick="data()">忘记密码</a>
                        <a href="Javascript:void(0)" onclick="data()">忘记会员名</a>
                        <a href="${pageContext.request.contextPath}/user_tmall/register.jsp">免费注册</a>
                    </div>
                </div>
            </div>
    </div>
</div>
    
     
  </body>
</html>
