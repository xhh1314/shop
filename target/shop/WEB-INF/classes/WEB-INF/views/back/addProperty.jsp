<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<!-- 引入bootstrap文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/js/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/bootstrap/js/bootstrap.min.js"></script>


<title>Insert title here</title>

<!-- 如果名称输入为空，则弹出窗口提示！ -->
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


<sf:form modelAttribute="property" class="form-inline" action="${pageContext.request.contextPath}/property/add">

<div class="form-group">
<label for="name" >name</label>
<sf:input path="name" id="name" class="form-control"/>
</div>
<div class="form-group">
<label for="subdivide_uuid">subdivide</label>
<sf:select id="subdivide_uuid"  path="subdivide.uuid">
<sf:option value="--" label="请选择"/>
<c:forEach items="${subdivides}" var="subdivide">
<sf:option  value="${subdivide.uuid}"  label="${subdivide.name}"/>
</c:forEach>
</sf:select>
</div> 
<input type="submit" value="submit" class="btn btn-default">
</sf:form>
</body>

</html>