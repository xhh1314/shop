<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${subdivide.name}${keysName}</title>
<style type="text/css">
div.productView {
	position: relative;
	width: 100%;
	top: 0px;
	box-sizing: border-box;
}

.productViewChild {
	position: relative;
	width: 90%;
	height: auto;
	margin: 0px auto;
	border: 1px solid #CCC;
	overflow: auto;
}

div.productView div.eachProduct {
	width: 20%;
	height: 350px;
	padding: 10px;
	float: left;
	border: 1px solid white;
}

div.productView div.eachProduct:hover {
	border: 1px solid #CCC;
}

div.eachProduct a img {
	width: 200px;
	height: 220px;
}

div.eachProduct .price {
	font-size: 16px;
	color: red;
}

div.eachProduct .productName {
	
}

.filtrate {
	width: 100%;
	height: 29px;
	background: #F1F1F1;
	padding: 3px 2px;
}

.filtrate .filtrateSort {
	display: inline-block;
	float: left;
	margin-left: 5%;
	line-height: 25px;
}

.filtrate .filtrateSort a {
	border: 1px solid #CCC;
	color: #333;
	text-decoration: none;
	padding: 2px 10px;
	cursor: pointer;
	background: #FFF;
}

.filtrate .filtrateSort a:hover {
	border: 1px solid #E4393C;
	color: #E4393C !important;
}
.filtrate .filtrateSort .price i{color:#E4393C}
.filtrate .filtratePage {
	display: inline-block;
	float: right;
	margin-right: 5%;
	line-height: 25px;
}

.filtrate .filtratePage a {
	border: 1px solid #CCC;
	color: #333;
	text-decoration: none;
	padding: 2px 10px;
	cursor: pointer;
	background: #FFF;
}
</style>
<script type="text/javascript">
var price_href;//定义价格全局变量
	function changePriceButton() {
		uri = $(".filtrateSort .price").attr("price_href");
		var sort = uri.substring(uri.indexOf("&") + 1);
		if (sort != "sort=descending" && sort != "sort=ascending") {
			var uri = uri + "&sort=ascending";
			var uri_begin = uri.substring(0, uri.indexOf("?")+1);
			var selectInfoMessage=uri.substring(uri.indexOf("selectInfo=")+11,uri.indexOf("&"));
			//encodeURI需进行2次编码才行，一次编码后，赋值给href，又变成了中文！
			selectInfoMessage=encodeURI(encodeURI(selectInfoMessage));//编码
			var selectInfo="selectInfo="+selectInfoMessage;
			var uri_end = uri.substring(uri.indexOf("&"));
			price_href = uri_begin + selectInfo+uri_end;
			//$(".filtrateSort .price").attr("href", uri);
			return true;
		}
		if (sort == "sort=ascending") {
			$(".price i").html("降序");
		} else if (sort == "sort=descending")
			$(".price i").html("升序");
		var uri_begin = uri.substring(0, uri.indexOf("?")+1);
		var selectInfoMessage=uri.substring(uri.indexOf("selectInfo=")+11,uri.indexOf("&"));
		selectInfoMessage=encodeURI(encodeURI(selectInfoMessage));//编码
		var selectInfo="selectInfo="+selectInfoMessage;
		var uri_end = uri.substring(uri.indexOf("&"));
		price_href = uri_begin + selectInfo+uri_end;
		//$(".filtrateSort .price").attr("href", uri);
		return true;
	}

function submitPriceSort(){
	window.location.href=price_href;
}
</script>

</head>
<body onload="changePriceButton()">
	<div class="wrapper">

		<div class="filtrate">
			<div class="filtrateSort">
				<a class="comprehensive"><span>综合排序</span></a> <a class="sales"><span>销量</span></a>
				<a class="price" price_href="${uriPath}" onclick="submitPriceSort()"><span>价格</span><i></i></a> <a
					class="comment"><span>评论数</span></a>
			</div>

			<div class="filtratePage">
				<span>1/1</span> <a><span>前一页</span></a> <a><span>下一页</span></a>
			</div>

		</div>

		<div class="productView">
			<div class="productViewChild">
				<h2>${noproducts}</h2>
				<c:forEach items="${products}" var="product">
					<div class="eachProduct">
						<c:forEach items="${product.productImage}" var="pi">
							<a
								href="${pageContext.request.contextPath}/fore/showProduct/${product.uuid}"><img
								src="${pageContext.request.contextPath}/${pi.value}"></a>
							<br>
						</c:forEach>
						<a class="price">${product.originalPrice}</a><br> <a
							class="productName"
							href="${pageContext.request.contextPath}/fore/showProduct/${product.uuid}">${product.name}</a>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>