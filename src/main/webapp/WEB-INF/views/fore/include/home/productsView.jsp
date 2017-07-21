<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${subdivide.name}${keysName}</title>
<style type="text/css">
div.productView{
position:relative;
top:0px;
left:5%;
}
div.productView div.eachProduct{

width:15%;
padding:1%;
float:left;
}
div.eachProduct img{
width:100%;
height:auto;
}

 div.eachProduct .price{
 font-size: 16px;
 color:red;
 }
 
  div.eachProduct .productName{
 
 }
</style>

</head>
<body >
<div class="wrapper">
	<div class="productView">
	<h2>${noproducts}</h2>
<c:forEach items="${products}" var="product">
		<div class="eachProduct">
		<c:forEach items="${product.productImage}" var="pi">
		<a href="${pageContext.request.contextPath}/fore/showProduct/${product.uuid}"><img src="${pageContext.request.contextPath}/${pi.value}"></a><br>
		</c:forEach>
		<a class="price">${product.originalPrice}</a><br><a class="productName" href="${pageContext.request.contextPath}/fore/showProduct/${product.uuid}">${product.name}</a>
		</div>
</c:forEach>

</div>
</div>
</body>
</html>