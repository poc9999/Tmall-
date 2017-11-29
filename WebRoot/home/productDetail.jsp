<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/productdetail.css"/>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.fly.min.js"></script>
  	<script type="text/javascript">
  		$(function(){
  				//指针悬浮改变图片
  			$("img.smallImage").mouseenter(function(){
  				
  				var $smallImageURL=$(this).attr("smallImageURL").match("[0-9]+");
  				
  				$("img.bigImage").attr("src","http://oz38alnl2.bkt.clouddn.com/productSingle/"+$smallImageURL+".jpg");
  			
  			});
  				//输入框数字判断
  			$("input.productNumberSetting").keyup(function(){
  				var stock="${product.stock}";
  				var $num=$("input.productNumberSetting").val();
  				
  				num=parseInt($num);
  				
  				if(isNaN(num)){
  					num=1;
  					}
  				if(num<=0){
  					num=1;
  					}
				if(num>stock){
					num=stock;
				}
				$("input.productNumberSetting").val(num);
  			});
  		
  		$(".increaseNumber").click(function(){
  			var stock="${product.stock}";
  			var $num=$("input.productNumberSetting").val();
  			//需要把字符串类型的数字转换成int类型的
  			num=parseInt($num);
  			if($num<stock){
  				$num++;
  				}
  			if($num>=stock){
  				$num=stock;
  				}
			$("input.productNumberSetting").val($num);
  			});
  			
		$(".decreaseNumber").click(function(){
  			var $num=$("input.productNumberSetting").val();
  			num=parseInt($num);
  			if(num>0){
  				num--;
  				}
  			if(num<=0){
  				num=1;
  				}
			$("input.productNumberSetting").val(num);
		
		});
		//点击立即购买和加入购物车按钮,需要判断用户是否登录。使用ajax
		$("a.buyLink").click(function(){
			//拿到输入的总数量
			var $num=$("input.productNumberSetting").val();
			
			url="${pageContext.request.contextPath}/admin_tmall/login.action";
			param={crud:"checkLogin"};
			$.ajax({
				url:url,
				data:param,
				type:"post",
				success:function(returnData){
					if(returnData=="success"){
						//跳到购物页面
						window.location.href="${pageContext.request.contextPath}/user_tmall/alluserlist.action?user_type=eachOneProd&id=${product.id}&order=ok&buynum="+$num;
					}else{
						$("#mymodal").modal("show");
						 }
				}
			});
		});
		/*
		 *点击加入购物车按钮,首先判断用户是否登录,然后判断运行加入购物车的样式,在跳转到相应的servlet
		 */
		$("a.addCartLink").click(function(event){
			var $num=$("input.productNumberSetting").val();
			var url="${pageContext.request.contextPath}/admin_tmall/login.action";
			param={crud:"checkLogin"};
			$.ajax({
				url:url,
				data:param,
				type:"post",
				success:function(returnData){
				
					if(returnData=="success"){
						//加入购物车样式
					var offset = $(".glyphicon-shopping-cart").offset();

               			//顶层div导航栏
		                var addcar = $("div.smallImageDiv");
		                //按钮
		                var buycarBtn=$(".addCartButton");
		                var img = addcar.find('img').attr('src');
		                var flyer = $('<img class="u-flyer" src="'+img+'">');
		                flyer.fly({
		                    start: {
		                        left: event.pageX, //开始位置（必填）#fly元素会被设置成position: fixed
		                        top: event.pageY //开始位置（必填）
		                    },
		                    end: {
		                        left: offset.left+10, //结束位置（必填）
		                        top: offset.top+10, //结束位置（必填）
		                        width: 0, //结束时宽度
		                        height: 0 //结束时高度
		                    },
		                    onEnd: function(){ //结束回调
		                        //$("#msg").show().animate({width: '250px'}, 200).fadeOut(1000); //提示信息
		                        //alert("已成功加入购物车");
		                        //buycarBtn.css("cursor","default").removeClass('addCartButton').unbind('click');
		                        //buycarBtn.removeClass("addCartButton");
		                        alert('已成功加入购物车,请进入购物车页面查看哟。');
	                        	$("a.addCartLink").attr("href","javascript:void(0)");
		                        //buycarBtn.css({"background-color":"#a8a8a8","border":"1px solid #a8a8a8","text-align":"center","font-size":"16px","color":"white","line_height":"40px"});
		                        //this.destory(); //移除dom
		                    }
		                });
		                //使用ajax让购物车请求servlet,但是页面不跳转
						//window.location.href="${pageContext.request.contextPath}/user_tmall/joinbuycar.action?pid=${product.id}&buynum="+$num+"&type=join";		
						var url="${pageContext.request.contextPath}/user_tmall/joinbuycar.action";
						param={pid:${product.id},buynum:$num,type:"join"};
						$.ajax({
							url:url,
							data:param,
							type:"post",
							success:function(){
							}
							
						});
					
					}else{
						$("#mymodal").modal("show");
						 }
				}
			});
		});
		
		
		
		
		
		//切换商品详情和累计评价的页面和按钮样式
		
			$("a.productDetailTopReviewLink").click(function(){
				
				$("a.productDetailTopReviewLink").addClass("selected");
				$("a.productDetailTopPartSelectedLink").removeClass("selected");
			
			});
			
			$("a.productDetailTopPartSelectedLink").click(function(){
				
				$("a.productDetailTopPartSelectedLink").addClass("selected");
				$("a.productDetailTopReviewLink").removeClass("selected");
			
			});
  		});
  	
  	</script>
  
  </head>
  <body>
  
  <div class="AllImageAndInfoDiv" align="center">
  
   <div class="AllImageDiv">

        <div class="ImageDiv">
        
        	<!-- 显示的5张小图片和一张大图片 -->
            <img src="http://oz38alnl2.bkt.clouddn.com/productSingle/${product.firstProductImage.id}.jpg" class="bigImage" width="100px" />
            <div class="smallImageDiv"align="center">
			<c:forEach items="${product.productSingleImages}" var="prod_small_image">
                <img src="http://oz38alnl2.bkt.clouddn.com/productSingle_small/${prod_small_image.id}.jpg" smallImageURL="${pageContext.request.contextPath}/productSingle_small/${prod_small_image.id}.jpg" width="60px" class="smallImage"/>
            </c:forEach>
            </div>
            
        </div>
       <div class="infoInimgAndInfo">
           <div class="productTitle">
              ${product.name}
           </div>
           <div class="productSubTitle">
               ${product.subTitle}
           </div>
           <div class="productPrice">
               <div class="juhuasuan">
                   <span class="juhuasuanImg"><img src="${pageContext.request.contextPath}/img/double11.png"/></span>
                   <span>此商品11月11日开卖，请提前加入购物车</span>
               </div>
               <div class="productPriceDiv"style="background-image: url('${pageContext.request.contextPath}/img/priceBackground.png');">
                   <div class="gouwujuanDiv"><img height="16px" src="${pageContext.request.contextPath}/img/gouwujuan.png">
                       <span> 全天猫实物商品通用 </span>
                   </div>
                   <div class="originalDiv">
                       <span class="originalPriceDesc">价格</span>
                       <span class="originalPriceYuan">¥</span>
                       <span class="originalPrice">
                        ${product.orignalPrice}
                    </span>
                   </div>
                   <div class="promotionDiv">
                       <span class="promotionPriceDesc">促销价 </span>
                       <span class="promotionPriceYuan">¥</span>
                       <span class="promotionPrice">
                        ${product.promotePrice}
                    </span>
                   </div>
               </div>
           </div>
           <div class="productSaleAndReviewNumber">
               <div>月销量 <span class="redColor boldWord">${product.saleCount}</span></div>
               <div>累计评价 <span class="redColor boldWord">${product.reviewCount}</span></div>
               <div>送天猫积分<span class="greenColor boldWord">20</span></div>
           </div>
           <div class="productNumber">
               <span>数量</span>
               <span>
                <span class="productNumberSettingSpan">
                <input type="text" value="1" class="productNumberSetting">
                </span>
                <!-- 上下键按钮  -->
                <span class="arrow">
                    <a class="increaseNumber" href="javascript:void(0)">
                    <span class="updown">
                       <img src="${pageContext.request.contextPath}/img/up.png">
                    </span>
                    </a>
                    <a class="decreaseNumber" href="javascript:void(0)">
                    <span class="updown">
                        <img src="${pageContext.request.contextPath}/img/down.png">
                    </span>
                    </a>
                </span>
            件</span>
               <span>库存${product.stock}件</span>
           </div>
           
           <div class="buyDiv">
           	   
               <a href="javascript:void(0)" class="buyLink" data-toggle="modal"><button class="buyButton">立即购买</button></a>
               <a href="javascript:void(0)" class="addCartLink" data-toggle="modal"><button class="addCartButton"><span class="glyphicon glyphicon-shopping-cart"></span>加入购物车</button></a>
           </div>
           <div class="serviceCommitment">
               <span class="serviceCommitmentDesc">服务承诺</span>
               <span class="serviceCommitmentLink">
                <a href="#nowhere">正品保证</a>
                <a href="#nowhere">极速退款</a>
                <a href="#nowhere">赠运费险</a>
                <a href="#nowhere">七天无理由退换</a>
            </span>
           </div>
       </div>
       <div style="clear:both"></div>
   </div>
   
   <div class="productDetailDiv" style="display: block;">
   			<!-- 两个a标签 -->
   			<div class="productDetailTopPart">
   				<a class="productDetailTopPartSelectedLink selected" href="javascript:void(0)">商品详情</a>
   				<a class="productDetailTopReviewLink" href="javascript:void(0)">累计评价</a>
   			</div>
   		<div class="productParamterPart">
   			<div class="productParamter">产品参数：</div>
        <div class="productParamterList">
        		<c:forEach items="${prop_value_list}" var="prop_value">
	                <span>${prop_value.property.name}:${prop_value.value}</span>
                </c:forEach>
        </div>
        <div style="clear:both"></div>
   		</div>
   <div class="productDetailImagesPart">
   				<c:forEach items="${product.productDetailImage}" var="prod_big_image">
                	<img src="http://oz38alnl2.bkt.clouddn.com/productDetail/${prod_big_image.id}.jpg">
                </c:forEach>
    </div>
   </div>
   </div>
  </body>
</html>
