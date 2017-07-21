<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入bootstrap文件 和jquery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/js/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/bootstrap/js/bootstrap.min.js"></script>

<style type="text/css">
.productImage{
float:left;
margin-left:5%;
margin-top:1%;
width:20%;
height:30%;
padding:0px auto;
border:1px solid ;
}
.productImage img{
margin:0px auto;
}
.infoandinfo{
float:left;
margin-top:1%;
margin-left:2%;
border:1px solid ;
}
.productTitle{
font-size:24px;
}
.priceWrap{
background:#ECEBEB;
}
.producSubTitle{
font-size:12px;
}
.productPrice{
font-size:12px;
margin:10px auto;
margin-bottom:10px;
}

.originalPriceDesc{
margin-left:5px;
}
.originalPriceYuan{
margin-left:10px;
font-size:16px;
}
.originalPrice{
font-size:16px;
text-decoration: line-through;
}

.promotePriceDesc{
margin-left:5px;
}
.promotePriceYuan{
margin-left:10px;
color:red;
font-size:16px;
}
.promoteProce{
color:red;
font-size:32px;
}
.productSaleAndReviewNumber{
margin-bottom:10px;
}
.productSaleAndReviewNumber div{
display:inline-block;
text-align:center;
margin:0px 10px;
}
.productSale{
margin-left:5px;
}

.productSaleAndReviewNumber .productSaleNumber{
margin-left:5px;
color:red;
font-size:16px;
}
.productSaleAndReviewNumber .productReviewNumber{
margin-left:5px;
margin-right:5px;
color:red;
font-size:16px;
}

.numberSetting{
width:30px;
}
.productNumber{
border:1px solid;
}
.productNumberSettingSpan{
border:0px solid;
display:inline-block;
width:40px;
padding-right:10px;
}
span.updown img{
    display:inline-block;
    vertical-align:top;
}
span.updown{
    border: 1px solid #999;
    display: block;
    width: 20px;
    height: 14px;
    text-align: center;
    padding-top:4px;
}
.arrow{
top:5px;
border:0px solid;
display:inline-block;
}
.buyDiv{
margin-top:10px;
}
.buyDiv a{
display:inline-block;
width:30%;
float:right;
margin:0px 10px;
border:1px solid red;
background:#FFEDED;
}

.addCartButton{
width:100%;
background:#FF0036;
border:none;

}
.buyButton{
width:100%;
background:#FFEDED;
border:none;
}
.buyButton span{
color:red;
font-size:20px;
}
.addCartButton span{
color:white;
font-size:20px;
}

.productDetails{
position:relative;
margin-top:2%;
margin-left:26%;
border:1px solid black;
width:50%;
}

.detailHead{
border:1px solid red;
width:100%;
}

.detailHead a{
margin-left:10px;
display:inline-block;
border:1px solid;
width:10%;
line-height:50px;

}
.productParameter{
margin:10px;
}
.productParameterList{
border:1px solid red;
width:100%;
height:40%;
*zoom:1;
}
.productParameterList span{
display:block;
margin:10px;
}
.productParameterList:after{
content:"\200B"; display:block; height:0; clear:both;
}
</style>

<script type="text/javascript">
var stock=${product.stock};
$(document).ready(function(){
$("#increaseNumber").click(function(){
	var num=$("#number").val();
	if(num<stock)
		num++;
	else
		num=stock;
	$("#number").val(num);
});
$("#decreaseNumber").click(function(){
	var num=$("#number").val();
	if (num==1){
		num=1;
	}
	else
		num--;
	$("#number").val(num);
});
var cartNum=$("strong").text();
var cartNum=parseInt(cartNum, 0);
$(".addCartButton").click(function(){
	$.get(
			"${pageContext.request.contextPath}/forePermission/addOrderItem",
			{"product.uuid":${product.uuid},"number":$("#number").val()},
			function(result){
				if(result=="success"){
					$(".addCartButton").html("已加入购物车");
					$(".addCartButton").attr("disabled","disabled");
					$(".addCartButton").css("background-color","lightgray");
					$(".addCartButton").css("color","black");
					cartNum++;
					$("strong").text(cartNum);
				}
				
			}
					);
	
});
	
});



</script>
</head>
<body>
<div class="imgAndInfo">
<div class="productImage">
<c:forEach items="${product.productImage}" var="image">
<img  src="${pageContext.request.contextPath}/${image.value}">
</c:forEach>
</div>

<div class="infoandinfo" >
<div class="productTitle">
${product.name}
</div>
<div class="priceWrap">
<div class="producSubTitle">
【领卷促销】（领券满199减60,299减100,999减200）2017.6.9--2017.6.12期间更多618促销请点击
</div>
<div class="productPrice">
<div class="originalPriceDiv">
<span class="originalPriceDesc" >原价</span>
<span class="originalPriceYuan">¥</span>
<span class="originalPrice">${product.originalPrice}</span>
</div>
<div class="promotePriceDiv">
<span class="promotePriceDesc">促销价</span>
<span class="promotePriceYuan">¥</span>
<span class="promoteProce">${product.promotePrice}</span>
</div>
</div>
</div>
<div class="productSaleAndReviewNumber">
<div class="productSale">销量<span class="productSaleNumber">10</span></div>   <div class="productReview">评价<span class="productReviewNumber">10</span> <span>库存${product.stock}</span></div>
</div>

<div  class="productNumber">
<span>数量</span>
<span class="productNumberSettingSpan">
<input type="text" value="1" name="number" id="number" class="numberSetting">
</span>
<span class="arrow">
<a class="increaseNumber" id="increaseNumber">
<span class="updown"><img src="${pageContext.request.contextPath}/static/image/up.png"></span>
</a>
<a class="decreaseNumber" id="decreaseNumber">
<span class="updown"><img src="${pageContext.request.contextPath}/static/image/down.png"></span>
</a>
</span>
</div>

<div class="buyDiv">
<a class="addCartLink"><button class="addCartButton"><span class="glyphicon glyphicon-shopping-cart"></span> <span>加入购物车</span></button></a>
<a href="" class="buyLink"><button class="buyButton"> <span>立即购买</span></button></a>
</div>
</div>
<div style="clear:both"></div>

</div>
<div class="productDetails">
<div class="detailHead">
<a class="detailLine1">商品详情</a>
<a class="detailLine2">累计评价</a>
</div>
<div class="productParameterPart">
<div class="productParameter">产品参数</div>
<div class="productParameterList">
<c:forEach items="${productPropertyValues}" var="ppv">
<span>${ppv.propertyName}:${ppv.propertyValue}</span>
</c:forEach>
</div>
</div>

</div>
<div style="clear:both"></div>
</body>
</html>