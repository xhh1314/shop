<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
border-bottom: 1px solid #CCC;
}
nav.top a{
color:#999999;
}
.glyphicon-home {
color:#FF0036;
}
.glyphicon-shopping-cart{
color:#FF0036;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	//如果用户已经登录，则隐藏登录注册菜单,未登录则隐藏注销菜单
	if($("#username").text().length>0)
		{
		$("#loginTitle").hide();
		$("#registerTitle").hide();
		}else{
			$("#logout").hide();
		}

//如果购物车数量为空，则默认显示为0件
  if($("strong").text()=="" || $("strong")==undefined)
	  $("strong").text("0");
	
});
</script>
<nav class="top">
<a href="${pageContext.request.contextPath}/fore/index">
购物首页
<span class="glyphicon glyphicon-home redColor"></span>
</a>
<span>欢迎</span><span id="username">${user.name}</span>
<a id="loginTitle" href="${pageContext.request.contextPath}/user/login">请登录</a>
<a id="registerTitle" href="${pageContext.request.contextPath}/user/register">注册</a>
<span class="pull-right">
<a href="">我的订单</a>
<span class="glyphicon glyphicon-shopping-cart redColor"></span>
<a href="${pageContext.request.contextPath}/forePermission/showCart">购物车 <strong>${cartNumber}</strong>件</a>
<a id="logout" href="${pageContext.request.contextPath}/user/logout">注销</a>
</span>
</nav>
