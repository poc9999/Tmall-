<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
  <style type="text/css">
    div.simpleSearchDiv{
        background-color: #FF0036;

        width: 45%;
        /*外边距*/
        margin: 10px 20px 40px;

        padding: 1px;

        height: 40px;
        display: block;
    }
    /*输入框*/
    div.simpleSearchDiv input{
        width: 80%;
        /*边框设置透明*/
        border:1px solid transparent;
        height: 90%;
        margin: 2px;
        /*去掉输入框轮廓*/
        outline: none;
    }
    div.simpleSearchDiv button{
        width: 18%;
        border: 1px solid transparent;

        background-color:#FF0036;
        /*文字颜色白色*/
        color: white;
        /*字体大小14px*/
        font-size: 14px;
        font-weight: bold;
    }

    /*关键字所在DIV*/
   div.serchBelow{
       margin-top: 3px;
       /*缩进20px*/
       margin-left: -20px;
   }
    /*关键字之间的棒棒*/
    div.serchBelow span{
        color: #999;
    }
    div.serchBelow a{
        /*超链接的左右内边距*/
        padding: 0px 20px 0px 20px;
        /*超链接的文字大小*/
        font-size: 14px;
    }
    /*图片采用绝对定位*/
    img.simpleLogo{
        position: absolute;
        left: 10px;
        top:20px;
        /*宽度为140px*/
        width:140px;
    }

    body{
        font-size:12px;
        font-family: Arial;
    }
    a{
        color: #999;
    }
    a:hover{
        text-decoration: none;
        cursor: pointer;
        color: #ff0036;
    }
</style>
   
</head>
  
  <body>
    
    <div>
        <a href="#">
            <img src="./img/tmall.png" class="simpleLogo" id="simpleLogo"/>
        </a>
        <!--预留一个Div -->
            <div></div>
        <!--右边浮动的Div-->
        <div class="simpleSearchDiv pull-right">
        	<form action="${pageContext.request.contextPath}/user_tmall/alluserlist.action?user_type=searchKeyProd" method="post">
        	
	            <input type="text" name="keyword" placeholder="搜索 天猫 商品/品牌/店铺" value="${keyword}">
	
	            <button type="submit" class="searchTmall">搜天猫</button>
            
            </form>
            <div class="serchBelow">

                <span>
                    <a href="${pageContext.request.contextPath}/user_tmall/alluserlist.action?user_type=searchKeyProd&keyword=鞋子">
                        鞋子
                    </a>
                    <span>|</span>
                </span>

                <span>
                    <a href="${pageContext.request.contextPath}/user_tmall/alluserlist.action?user_type=searchKeyProd&keyword=平板电视">
                        平板电视
                    </a>
                    <span>|</span>
                </span>

                <span>
                    <a href="${pageContext.request.contextPath}/user_tmall/alluserlist.action?user_type=searchKeyProd&keyword=沙发">
                        沙发
                    </a>
                    <span>|</span>
                </span>

                <span>

                    <a href="${pageContext.request.contextPath}/user_tmall/alluserlist.action?user_type=searchKeyProd&keyword=马桶">
                        马桶
                    </a>
                    <span>|</span>
                </span>

                <span>
                    <a href="${pageContext.request.contextPath}/user_tmall/alluserlist.action?user_type=searchKeyProd&keyword=iphone6">
                        iphone6
                    </a>
                    <span>|</span>
                </span>

                <span>
                    <a href="${pageContext.request.contextPath}/user_tmall/alluserlist.action?user_type=searchKeyProd&keyword=空调">
                        空调
                    </a>
                </span>
            </div>
        </div>
        <!--清除浮动布局-->
  <div style="clear: both"></div>
    </div>    
  </body>
</html>
