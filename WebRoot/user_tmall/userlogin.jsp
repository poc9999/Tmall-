<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" isErrorPage="true"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%response.setStatus(HttpServletResponse.SC_OK);%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>理想生活上天猫</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="./css/userlogin.css" rel="stylesheet"/>
	<script type="text/javascript" src="./js/jquery-3.0.0.js"></script>
	<script type="text/javascript" src="./js/bootstrap.js"></script>
	<link href="./css/bootstrap.min.css" rel="stylesheet"/>
  </head>
  
 <script type="text/javascript">
 
 	$(function(){
 	
 		<c:if test="${!empty error}">
	 		$("span.errorMsg").html("${error}");
	 		$("div.loginerroeMsg").show();
 		</c:if>
 	
 		$("#loginBtn").click(function(){
	 		var $name=$("#name").val();
	 		var $pass=$("#password").val();
	 		
	 		if(0==$name.length||0==$pass.length){		
		 			$("span.errorMsg").html("请输入账户名或密码");
		 			$("div.loginerroeMsg").show();
	 			}else{
	 				$("#form1").submit();
	 			
	 			}
	 		});
 	});
 	
 	function data() {
			alert('这个按钮啥也没有,食屎啦你');
		};
 </script>
  <body>
   <div class="loginDiv">
       <div class="simpletmall">
           <img src="./img/tmall.png" rel="图片丢失"/>
       </div>
       <!--背景图片-->
            <img src="./img/loginBackground.png" rel="图片丢失" class="loginbackground"/>
        <form action="./admin_tmall/login.action?property=user&crud=select" method="post" id="form1">
	         	
        <div class="loginSmallDiv">
       		<!-- 错误提示信息 -->
      				<div class="loginerroeMsg">
			      		<div class="alert alert-danger">
			      			<span class="errorMsg"></span>
			      		</div>
		      		</div>
            <!--账户登陆Div-->
            <div class="loginName">账户登陆</div>

            <div class="loginInput">
                <span class="loginSpanIcon">
                    <span class="glyphicon glyphicon-user"></span>
                </span>
                <input type="text" name="username" id="name" placeholder="会员名/邮箱/手机号"/>
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
                <a href="javascript:void(0)" onclick="data()">忘记密码</a>
                <a href="javascript:void(0)" id="no" onclick="data()">忘记会员名</a>
                <a href="./user_tmall/register.jsp">免费注册</a>
            </div>
        </div>
        </form>
   </div>


   <div class="footer2" id="footer2">
       <div class="copyright" id="copyright">
           <div class="link">
               <a href="#">关于天猫</a>
               <a href="#">帮助中心</a>
               <a href="#">开放平台</a>
               <a href="#">诚聘英才</a>
               <a href="#">联系我们</a>
               <a href="#">网站合作</a>
               <a href="#">法律声明及隐私权政策</a>
               <a href="#">知识产权</a>
               <a href="#">廉正举报</a>
               <a href="#">规则意见征集</a>
           </div>
           <div class="link">
               <a href="#">阿里巴巴集团</a><span class="slash">|</span>
               <a href="#">淘宝网</a><span class="slash">|</span>
               <a href="#">天猫</a><span class="slash">|</span>
               <a href="#">聚划算</a><span class="slash">|</span>
               <a href="#">全球速卖通</a><span class="slash">|</span>
               <a href="#">阿里巴巴国际交易市场</a><span class="slash">|</span>
               <a href="#">1688</a><span class="slash">|</span>
               <a href="#">阿里妈妈</a><span class="slash">|</span>
               <a href="#">飞猪</a><span class="slash">|</span>
               <a href="#">阿里云计算</a><span class="slash">|</span>
               <a href="#">YunOs</a><span class="slash">|</span>
               <a href="#">阿里通信</a><span class="slash">|</span>
               <a href="#">万网</a><span class="slash">|</span>
               <a href="#">高德</a><span class="slash">|</span>
               <a href="#">UC</a><span class="slash">|</span>
               <a href="#">友盟</a><span class="slash">|</span>
               <a href="#">虾米</a><span class="slash">|</span>
               <a href="#">阿里星球</a><span class="slash">|</span>
               <a href="#">来往</a><span class="slash">|</span>
               <a href="#">钉钉</a><span class="slash">|</span>
               <a href="#">支付宝</a><span class="slash">|</span>
           </div>

           <div class="license">
               <div class="copyRightPolice">
                   <span>增值电信业务经营许可证： 浙B2-20110446</span>
                   <span>网络文化经营许可证：浙网文[2015]0295-065号</span>
                   <span>12318举报</span>
               </div>
               <div class="copyRightPolice">
                   <span>互联网医疗保健信息服务 审核同意书 浙卫网审【2014】6号</span>
                   <span>互联网药品信息服务资质证书编号：浙-（经营性）-2012-0005</span>
                   <span><img src="./img/police.png"/>浙公网安备 33010002000120号</span>
               </div>
               <div class="copyRightYear">© 2003-2017 TMALL.COM 版权所有</div>
           </div>
       </div>
   </div>   
  </body>
</html>
