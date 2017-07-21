<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<!-- 引入bootstrap文件 和jquery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/js/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/bootstrap/js/bootstrap.min.js"></script>


<%-- <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/particles/particles.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/particles/app.js"></script>
 <link rel="stylesheet" media="screen" href="${pageContext.request.contextPath }/static/js/particles/style.css"> --%>
 
<style type="text/css">
body{
background:#F7FAFC;

}
.login{
width:25%;
position:relative;
margin:auto auto;
margin-top:5%;
border:1px solid #F7F7F9;
}
.loginGroup input{
display:block;
height:40px;
width:100%;
border:1px solid #D5D5D5;
}
button{
width:100%;
margin-top:20px;
font-size:20px !important;
}
.login-fault{
text-align:right;
margin-top:10px;
}
.title{
margin-top:10%;
text-align:center;
}
.title h1{
font-size:50px;
color:#0F88EB;
}
.message{
color:red;
}
</style>

</head>
<body>
<div class="title">
<h1>shopping</h1>
</div>
<div class="login" >
<sf:form action="${pageContext.request.contextPath}/user/login" modelAttribute="user" method="post" >
<div class="loginGroup">
<sf:input path="email" /><span class="message">${message}</span>
<sf:password path="password" />
</div>
<button type="submit" class="btn btn-primary">登录</button>
<!-- previousUri 用于记录登录之前的页面 -->
<input type="hidden"  name="previousUri" value="${previousUri}">
</sf:form>
<div class="login-fault">
<a>无法登陆？</a>
</div>
</div>

</body>
</html>