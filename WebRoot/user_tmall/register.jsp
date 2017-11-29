<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isErrorPage="true"%>
<%response.setStatus(HttpServletResponse.SC_OK);%>
<%@ include file="../include/userHeader.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>欢迎注册天猫</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.0.0.js"></script>
	<style type="text/css">
    /*注册的div*/
    div.registerDiv{
        /*上下外边距10px，左右外边距20px*/
        margin: 10px 20px;
        /*文本内容居中*/
        text-align: center;
    }
    /*表格*/
    table.registerTable{

        color: #3c3c3c;

        font-size: 16px;

        margin-top: 50px;

    }
        table.registerTable td{
            padding: 10px 30px;
        }

    table.registerTable input{
        border:1px solid black;

        width: 213px;
        height: 36px;
        font-size: 14px;
    }

    table.registerTable button{

        width: 170px;
        height: 36px;
        /*实现圆角*/
        border-radius: 2px;
        color: white;
        background-color: #FF0036;
        /*没有边框*/
        border-width: 0px;
    }
        /*提示信息*/
    td.registerTipTableLeft{
        font-weight: bold;
    }
    /*按钮文本居中*/
    td.registerbutton{
        text-align: center;
    }
	</style>
  </head>
  
  	<script>
  	
  		$(function(){
	  		<c:if test="${!empty error}">
			    $("span.errorMessage").html("${error}");
			    $("div.registerErrorMessageDiv").css("visibility","visible");       
		    </c:if>
		    
  			$("#registerBtn").click(function(){
  			
	  			var $name=$(".username").val();
	  			var $pass1=$(".password").val();
	  			var $pass2=$(".repeatpassword").val();
  				
	  			if(0==$name.length){
	  				$("span.errorMessage").html("请输入用户名");
	  				$("div.registerErrorMessageDiv").css("visibility","visible");
	  				return false;
	  				}
	  			else if(0==$pass1.length){
	  				$("span.errorMessage").html("请输入密码");
	  				$("div.registerErrorMessageDiv").css("visibility","visible");
	  				return false;
	  				}
  				else if(0==$pass2.length){
	  				$("span.errorMessage").html("请输入重复密码");
	  				$("div.registerErrorMessageDiv").css("visibility","visible");
	  				return false;
	  				}
  				else if($pass1 != $pass2){
  					$("span.errorMessage").html("重复密码不一致");
	  				$("div.registerErrorMessageDiv").css("visibility","visible");
  					return false;
  					}else{
  						 $("#form2").submit();
					 }
					 
  			});
  			
  		});
  	</script> 
  <body>
  
  <form action="${pageContext.request.contextPath}/admin_tmall/login.action?crud=insert" method="post" id="form2">
    <div class="registerDiv">
    
    	<!-- 错误信息显示 -->
	    <div class="registerErrorMessageDiv" style="visibility: hidden;">
	        <div class="alert alert-danger" role="alert">
	            <span class="errorMessage"></span>
	        </div>        
    	</div>
	    
        <table align="center" class="registerTable">
            <tbody>
                <tr>
                    <td class="registerTipTableLeft">设置会员名</td>
                    <td></td>
                </tr>
                <tr>
                    <td class="registerTipLogin">登陆名</td>
                    <td class="registerTableInput"><input type="text" name="username" class="username" placeholder="会员名一旦设置成功，无法修改" value="${username}"/> </td>
                </tr>
                <tr>
                    <td class="registerTipTableLeft">设置登陆密码</td>
                    <td class="registerTableTip">登陆时验证，保护账户信息</td>
                </tr>
                <tr>
                    <td>登陆密码</td>
                    <td class="registerTableInput"><input type="password" name="password" class="password" placeholder="设置你的登陆密码 " value="${password}"/> </td>
                </tr>
                <tr>
                    <td>确认密码</td>
                    <td class="registerTableInput"><input type="password" class="repeatpassword" placeholder="请再次输入你的密码" value="${password}"/> </td>
                </tr>
                <tr>
                    <td colspan="2" class="registerButton">
                        	<button id="registerBtn">提 交</button>
                    	</td>
                	</tr>
            	</tbody>
        	</table>
    	</div>
    </form>
    <!-- 引入页脚 -->
    <%@ include file="../include/userFooter.jsp" %>
    
  </body>
</html>
