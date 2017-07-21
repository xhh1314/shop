<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>Insert title here</title>

<!-- 引入bootstrap文件 和jquery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/js/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/bootstrap/js/bootstrap.min.js"></script>


<script type="text/javascript">
var message="${message}";
if(message==null || message == undefined || message =="")
	{}
else{
	alert(message);
}

</script>

</head>
<body>
<sf:form modelAttribute="category" class="form-inline" action="${pageContext.request.contextPath}/category/insert">

<div class="form-group">
<label for="name" >name</label>
<sf:input path="name" id="name" class="form-control"/>
</div>
<div class="form-group">
<label for="description">description</label>
<sf:input path="description" id="description" class="from-control"/>
</div>

<input type="submit" value="submit" class="btn btn-default">
</sf:form>

</body>
</html>