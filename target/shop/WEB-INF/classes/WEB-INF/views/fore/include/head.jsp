<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入bootstrap文件 和jquery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/js/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/bootstrap/js/bootstrap.min.js"></script>

<style type="text/css">
nav.top a:hover{
color: #C40000;
text-decoration: none;
}
nav.top{
background-color: #f2f2f2;
padding-top: 5px;
padding-bottom: 5px;
border-bottom: 1px solid ;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	if($("#username").text().length>0)
		{
		$("#loginTitle").hide();
		$("#registerTitle").hide();
		}
	
});
</script>
</head>
<body>
<nav class="top">
<a href="">
购物首页
<span class="glyphicon glyphicon-home redColor"></span>
</a>
<span>欢迎</span><span id="username">${user.name}</span>
<a id="loginTitle" href="${pageContext.request.contextPath}/user/login">请登录</a>
<a id="registerTitle" href="${pageContext.request.contextPath}/user/register">注册</a>
<span class="pull-right">
<a href="">我的订单</a>
<span class="glyphicon glyphicon-shopping-cart redColor"></span>
<a>购物车 <strong>0</strong>件</a>
<a></a>
</span>
</nav>
</body>
</html>