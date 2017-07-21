<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的购物车</title>

<style type="text/css">
.cartTitle{
margin-left:10%;
width:80%;
}
.cartTitle1 .shopping{
font-size:36px;
color:#FF0036;
}
.cartTitle1 .cart{
font-size:20px;
}
.cartTitle2 .allProductTitle{
width:20%;
padding:20px;
font-size:24px;
color:#FF4400;
border-bottom:2px solid #FF4400;
}
.cartTitle2 .cartTitle2Line{
border-bottom:1px solid #E6E6E6;
margin:0px 0px 30px 0px;;
}
.noOrderItems{
width:80%;
margin-left:10%;
}
.noOrderItems span{
font-size:16px;
}
.cartDiv{
width:80%;
position:relative;
right:-10%;
}
.productImage{
width:60px;
height:60px;
}
thead td{
padding:30px;
}
tbody{
border:1px solid #CCCCCC;
}
tbody tr{
border-bottom:thin solid #CCCCCC;
}
tbody tr td{
width:10%;
padding:10px 30px 10px 30px;
}
.productDescription{
color:black;
}
.originalPrice{
text-decoration:line-through;
color:#9C9C9C;
}
.promotePrice{
font-size:16px;
font-weight:lighter;
}
.numberInput{
width:30px;
text-align:center;
}
.changeNumber input{
margin-left:5px;
margin-right:5px;
border:none;
}
.numberDecrease{
margin-left:10px;
cursor:pointer;
display:inline-block;
width:5px;
}
.numberIncrease{
margin-right:10px;
cursor:pointer;
display:inline-block;
width:5px;
}
.changeNumber{
display:inline-block;
margin:0px auto;
background:#F0F0F0;
border:1px solid #E5E5E5;
}
.totalPrice{
color:#FF0036;
font-size:20px;
}
/* 这里cartFoot的宽度和上边的carDiv保持一致 */
.cartFoot{
width:80%; 
position:relative;
right:-10%;
display:inline-block;
line-height:40px;
}
.cartFoot .selectedAll{
margin-left:30px;
}
.createOrderButton{
width:100px;
font-size:24px;
background:#B0B0B0;
color:white;
border:none;
}
.hasSelectNumber{
margin-right:20px;
}
.hasSelectNumber .totalNumber{
color:#FF5136;
margin:0 2px;
}
.sumandFreight{
margin-right:5px;
}
.sumandFreight .cartSumPrice{
font-size:16px;
color:#FF5136;
}
.selectAll{
cursor:pointer;
}
.selectOne{
cursor:pointer;
}
a.delete{
text-decoration:none;
cursor:pointer;
}
</style>
<script type="text/javascript">
	//如果购物车没有商品，则在页面显示出<div id="noOrderItems">
	function loadnoOrderItems() {
		var noOrderItems = $("#noOrderItems").attr("noOrderItems");
		if (noOrderItems == "noOrderItems") {
			$("#noOrderItems").css("display", "inline");
			$(".cartDiv").css("display", "none");
			$(".cartFoot").css("display", "none");
		} else {
			$("#noOrderItems").css("display", "none");

		}
	}
</script>

<script type="text/javascript">
//格式化数字算法
function formatNumber(num){
    if (num<1000)
    return num;
   var num=num.toString();
   var decimals="";
   var integers;
   if(num.indexOf(".")>=0){//判断是不是有小数的情况
   decimals=decimals+num.substring(num.indexOf(".")+1)
   integers=num.substring(0,num.indexOf("."));
   }
   else{
   integers=num;
   }
   var tag=integers.length/3;
   var end=integers.length;
   var new_integers="";
   for(var i=1;i<=tag;i++){
   new_integers+=","+integers.substring(end-3,end);
   end-=3;
   }
   if(integers.length%3>0)//非3倍长度情况
   new_integers=integers.substring(0,integers.length%3)+new_integers;
   else
   new_integers=new_integers.substring(1);//去掉开端多余的逗号
   
   if(decimals=="")
   return new_integers;
   else
  return new_integers+"."+decimals;
}

//加载页面时初始化每一行的总金额
function initialTotalPrice(){
	$(".totalPrice").each(function(){
		var oid=$(this).attr("oid");
		var number=parseInt($("input[oid="+oid+"]").val());
		var unitPrice=parseInt($("span.promotePrice[oid="+oid+"]").text());
		var totalPrice=number*unitPrice;
		$(this).html(formatNumber(totalPrice));
	});
	
}
//初始化函数，所有页面加载时执行的函数都放里面
function initial(){
	loadnoOrderItems();
	initialTotalPrice();
}
//改变结算按钮的显示状态
function changeOrderButtonTrue(bElement){
bElement.removeAttr("disabled");
bElement.css("background","#FF0036");
}
function changeOrderButtonFalse(bElement){
bElement.attr("disabled","disabled");
bElement.css("background","#B0B0B0");
}
//检查所有的行次是不是是都是选中状态，如果都选中了，则把全选打上勾
function checkStates(){
var state=true;
$("img.selectOne").each(function(){
	if($(this).attr("selectit")=="false"){
		state=false;
	}
});
   if(state){
	$(".selectAll").each(function(){
		$(this).attr("selectit","true");
		$(this).attr("src","${pageContext.request.contextPath}/static/image/selected.png");
	});
	
	}
	else{
		$(".selectAll").each(function(){
			$(this).attr("selectit","false");
			$(this).attr("src","${pageContext.request.contextPath}/static/image/notSelected.png");

		});
	
	}
}
//当取消勾选一个商品时，检查是不是所有的商品都没有被勾选上,如果是，则把提交菜单变成灰色
function checkIsAllNotSelected(){
	var state=false;
	$("img.selectOne").each(function(){
		if($(this).attr("selectit")=="true"){
			state=true;
		}
	});
	   if(!state){
		   changeOrderButtonFalse($(".createOrderButton"));
	   }
	
}
//计算出当前行次的商品价格
function calculateCurrentPrice(oid){
	var number=parseInt($("input[oid="+oid+"]").val());
	var unitPrice=parseInt($("span.promotePrice[oid="+oid+"]").text());
	var totalPrice=number*unitPrice;
	$("span.totalPrice[oid="+oid+"]").html(formatNumber(totalPrice));
}
function calculateCurrentPriceWhenChange(oid,number){
	var unitPrice=parseInt($("span.promotePrice[oid="+oid+"]").text());
	var totalPrice=number*unitPrice;
	$("span.totalPrice[oid="+oid+"]").html(formatNumber(totalPrice));
}
//计算所有商品的总金额和商品总数量
function calculateTotalPrice(){
	var totalAllPrice=0;//所有商品金额
	var totalNumber=0;
	$(".totalPrice").each(function(){
		var oid=$(this).attr("oid");
		if($("img.selectOne[oid="+oid+"]").attr("selectit")=="true"){//选中状态才叠加
		var number=parseInt($("input[oid="+oid+"]").val());
		var unitPrice=parseInt($("span.promotePrice[oid="+oid+"]").text());
		var totalPrice=number*unitPrice;
		totalAllPrice=totalAllPrice+totalPrice;
		totalNumber=totalNumber+number;
		}
	});
	$(".cartSumPrice").html(formatNumber(totalAllPrice));
	$(".totalNumber").html(formatNumber(totalNumber));
}


$(document).ready(function(){
//点击某一行，改变选中状态
$("img.selectOne").click(function(){
	var selectit=$(this).attr("selectit");
	var oid=$(this).attr("oid");
	if(selectit=="false"){
		$(this).attr("selectit","true");
		$(this).attr("src","${pageContext.request.contextPath}/static/image/selected.png");
		changeOrderButtonTrue($(".createOrderButton"));
	}
	else{
		$(this).attr("selectit","false");
		$(this).attr("src","${pageContext.request.contextPath}/static/image/notSelected.png");
		checkIsAllNotSelected();
	}
	checkStates();
	calculateCurrentPrice(oid);
	calculateTotalPrice();
});
//点击全选,改变状态
$("img.selectAll").click(function(){
if($(this).attr("selectit")=="false")
{
$("img.selectAll").each(function(){
$(this).attr("selectit","true");
$(this).attr("src","${pageContext.request.contextPath}/static/image/selected.png");
});
$(".selectOne").each(function(){
		$(this).attr("selectit","true");
		$(this).attr("src","${pageContext.request.contextPath}/static/image/selected.png");
	});
	
changeOrderButtonTrue($(".createOrderButton"));
	
}
else{
$("img.selectAll").each(function(){
$(this).attr("selectit","false");
$(this).attr("src","${pageContext.request.contextPath}/static/image/notSelected.png");
});
$(".selectOne").each(function(){
		$(this).attr("selectit","false");
		$(this).attr("src","${pageContext.request.contextPath}/static/image/notSelected.png");
	});
changeOrderButtonFalse($(".createOrderButton"));
}

calculateTotalPrice();
});




//数量减少函数
$(".numberDecrease").click(function(){
	var oid=$(this).attr("oid");
	var currentNumber=parseInt($("input[oid="+oid+"]").val());
	if(currentNumber==1)
		currentNumber=1;
	else
	currentNumber--;
	$("input[oid="+oid+"]").val(currentNumber);
	calculateCurrentPriceWhenChange(oid,currentNumber);
	calculateTotalPrice();
});
//数量增加函数
$(".numberIncrease").click(function(){
	var oid=$(this).attr("oid");
	var stock=parseInt($("span.orderItemStock[oid="+oid+"]").text());
	var currentNumber=parseInt($("input[oid="+oid+"]").val());
	if(currentNumber<stock)
		currentNumber++;
	$("input[oid="+oid+"]").val(currentNumber);
	calculateCurrentPriceWhenChange(oid,currentNumber);
	calculateTotalPrice();
});
//点击结算按钮操作
$(".createOrderButton").click(function(){
    var params="";
    $("img.selectOne").each(function(){
    	if($(this).attr("selectit")=="true"){
    		var oid=$(this).attr("oid");
    		params=params+"&oids="+oid;
    	}
    	
    });
    if(params!=="")
    params=params.substring(1);
   window.location.href="${pageContext.request.contextPath}/forePermission/showOrder?"+params;
	
});

});
</script>
<script type="text/javascript">
//删除一个订单操作
function deleteItem(obj){
	var oid=$(obj).attr("oid");
	var selectStates=$("img.selectOne[oid="+oid+"]").attr("selectit");
	if(selectStates=="false"){
		alert("请选中要删除的订单");
		return;
	}
	$.ajax({
		url:"${pageContext.request.contextPath}/forePermission/deleteItem/"+oid,
		method:"post",
		dataType:"text",
		success:function(data){
			if(data=="success"){
				$("tr[oid="+oid+"]").remove();//删除当前选中的行次
				checkIsAllNotSelected();//检查订单被选中的状态，更新结算按钮状态
				calculateTotalPrice();//重新计算价格
			};
			if(data=="fail")
				{
				alert("删除订单失败！");
				}
		},
		error:function(data){
			alert("系统繁忙，请稍后重试！");
		}
		
	});
	
	
}

</script>
</head>
<body onload="initial()">
<div class="wrapper">
<div class="cartTitle">
<div class="cartTitle1">
<span class="shopping">shopping</span><span class="cart">购物车</span>
</div>
<div class="cartTitle2">
<div class="allProductTitle">
<span>所有商品</span>
</div>
<div class="cartTitle2Line"></div>
</div>
</div>
<div id="noOrderItems"  class="noOrderItems" style="display: none"
		noOrderItems="${noOrderItems}">
		<h2>
			你还没有添加任何商品到购物车<a href="${pageContext.request.contextPath}/fore/index">继续购物</a>
		</h2>
</div>
<div class="cartDiv">
		<table class="cartProductList">
			<thead>
				<tr>
					<td class="selectAndImage"><img src="${pageContext.request.contextPath}/static/image/notSelected.png" class="selectAll" selectit="false" > 全选
					</td>
					<td>商品信息</td>
					<td>单价</td>
					<td>数量</td>
					<td>金额</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orderItems}" var="orderItem">
					<tr class="cartProductItemTR" oid="${orderItem.id}">
						<td>
						<img src="${pageContext.request.contextPath}/static/image/notSelected.png"  oid="${orderItem.id}" class="selectOne" selectit="false"> 
						<a href="#nowhere" style="diplay: none"></a>
							<c:forEach begin="0" end="0"
								items="${orderItem.product.productImage}" var="image">
								<img src="${pageContext.request.contextPath}/${image.value}" class="productImage">
							</c:forEach></td>
						<td><a
							href="${pageContext.request.contextPath}/fore/showProduct/${orderItem.product.uuid}"
							class="productDescription">${orderItem.product.name}</a></td>
						<td><span class="originalPrice">${orderItem.product.originalPrice}</span>
							<span class="promotePrice" oid="${orderItem.id}">${orderItem.product.promotePrice}</span>
						</td>
						<td>
							<div class="changeNumber">
								<span oid="${orderItem.id}"
									class="hidden orderItemStock">${orderItem.product.stock}</span>
								<a oid="${orderItem.id}" class="numberDecrease">-</a>
								<input type="text" name="number" class="numberInput"
									value="${orderItem.number}"  oid="${orderItem.id}"/> <a
									oid="${orderItem.id}" class="numberIncrease">+</a>
							</div>
						</td>
						<td>
						<span class="totalPrice" oid="${orderItem.id}">${totalPrice}</span>
						</td>
						<td><a class="delete" onclick="deleteItem(this)"  oid="${orderItem.id}">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="cartFoot">
		<img src="${pageContext.request.contextPath}/static/image/notSelected.png"
			class="selectAll" selectit="false"> 
		全选</span>
		<div class="  pull-right">
			<span class="hasSelectNumber">已选商品<span class="totalNumber"></span>件
			</span> <span class="sumandFreight">合计（不含运费）:<span
				class="cartSumPrice"></span></span>
			<button class="createOrderButton" disabled="disabled">结算</button>

		</div>
	</div>

</div>
</body>
</html>