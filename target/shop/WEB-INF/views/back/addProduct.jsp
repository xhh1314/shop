<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<!-- 引入bootstrap文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/js/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/bootstrap/js/bootstrap.min.js"></script>

<!-- 打印错误信息 -->
<script type="text/javascript">
var message=${message};
if(message !=null && message!=undefined && message!="" ){
	alert(message);
}

</script>
</head>
<body>
<sf:form action="${pageContext.request.contextPath}/product/add" modelAttribute="product"  enctype="multipart/form-data">
<div class="form-group">
<label for="name">name</label>
<sf:input path="name" id="name" class="form-control" cssStyle="width:50%"/>
</div>
<div class="form-group">
<label for="originalPrice">originalPrice</label>
<sf:input path="originalPrice" id="originalPrice" class="form-control" cssStyle="width:50%"/>
</div>
<div class="form-group">
<label for="promotePrice">promotePrice</label>
<sf:input path="promotePrice" class="form-control" id="promotePrice" cssStyle="width:50%"/>
</div>

<div class="form-group">
<label for="stock">stock</label>
<sf:input path="stock" class="form-control" id="stock" cssStyle="width:50%"/>
</div>

<!-- 这里的日期使用type=“date” Chrome 浏览器可以识别 -->
<div class="form-group">
<label for="createTime">createTime</label>
<sf:input path="createTime"  type="date" class="form-control" cssStyle="width:50%"/>
</div>

<div class="form-group">
<label for="subdivide.uuid">subdivide</label>
<sf:select path="subdivide.uuid" class="form-control" cssStyle="width:25%">
<sf:options items="${subdivides}" itemValue="uuid" itemLabel="name"/>
</sf:select>
</div>
<div class="form-group">
<label for="iamge">图片上传</label>
<input name="image" type="file"></input>
</div>

<sf:button name="submit">submit</sf:button>


</sf:form>

</body>
</html>