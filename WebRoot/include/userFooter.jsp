<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
  	<link href="${pageContext.request.contextPath}/css/userfooter.css" rel="stylesheet"/>
  	
  </head>
  <body>
    <!--页脚是一个最大的div-->
    <div style="display: block" class="footer1" id="footer1">
        <!--最上面的图片-->
        <div class="ensureDiv">
            <a>
                <img src="${pageContext.request.contextPath}/img/ensure1.png">
            </a>
        </div>

        <div class="foot_desc" id="foot_desc">

            <div class="desc_Div">
                <span class="Title">购物指南</span>
                <a href="#">免费注册</a>
                <a href="#">开通支付宝</a>
                <a href="#">支付宝充值</a>
            </div>

            <div class="desc_Div">
                <span class="Title">天猫保障</span>
                <a href="">发票保障</a>
                <a href="">售后规划</a>
                <a href="">缺货赔付</a>
            </div>

            <div class="desc_Div">
                <span class="Title">支付方式</span>

                <a href="#">快捷支付</a>
                <a href="#">信用卡</a>
                <a href="#">余额宝</a>
                <a href="#">蚂蚁花呗</a>
                <a href="#">货到付款</a>
            </div>


            <div class="desc_Div">
                <span class="Title">商家服务</span>

                <a href="#">天猫规则</a>
                <a href="#">商家入驻</a>
                <a href="#">商家中心</a>
                <a href="#">天猫智库</a>
                <a href="#">物流服务</a>
                <a href="#">喵言喵语</a>
                <a href="#">运营服务</a>
            </div>

            <div class="desc_Div">
                <span class="Title">手机天猫</span>
                <a><img src="${pageContext.request.contextPath}/img/ma.png"/></a>
            </div>
        </div>
        <!-- 去除浮动布局效果 -->
        <div style="clear: both"></div>
    </div>
    <!--页脚二-->
    <div class="footer2" id="footer2">
        <img src="${pageContext.request.contextPath}/img/cateye.png" class="cateye" id="cateye"/>
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
                <span><img src="${pageContext.request.contextPath}/img/police.png"/>浙公网安备 33010002000120号</span>
                </div>
                <div class="copyRightYear">© 2003-2017 TMALL.COM 版权所有</div>
                <div>
                    <img src="${pageContext.request.contextPath}/img/copyRight1.jpg"/>
                    <img src="${pageContext.request.contextPath}/img/copyRight2.jpg"/>
                </div>
            </div>
        </div>
    </div>
  </body>
</html>
