<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- 引入bootstrap文件 和jquery -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/js/bootstrap/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/js/bootstrap/js/bootstrap.min.js"></script>
<style type="text/css">
div.mainbody{
position:relative;
top:0px;
left:0px;
height: 70%;
padding-bottom: 40%;
}

.category{
position:absolute;
top:5%;
left:0px;
}

.subdivide{
position:absolute;
top:5%;
left:10%;
}
</style>
</head>


<body>

	<div class="mainbody">
		<div class="category">
		<c:forEach items="${categorys}" var="ct">
		<div class="eachCategory">
		<span class="glyphicon glyphicon-link"></span>
		<a>${ct.name}</a>
		</div>
		</c:forEach>
		</div>
		<div class="subdivide">
		<div>
		<c:forEach items="${categorys}" var="category">
		<div class="eachSubdivide" cid="${category.uuid}">
		<c:forEach items="${category.subdivide}" var="subdivide" >
		<a href="${pageContext.request.contextPath}/fore/showProducts/${subdivide.uuid}">${subdivide.name}</a>
		</c:forEach>
		</div>
		</c:forEach>
		</div>
		</div>
	</div>

</body>
</html>