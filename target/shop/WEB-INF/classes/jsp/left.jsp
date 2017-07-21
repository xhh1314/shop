<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<div>
<div>
<a >商品种类</a>
<div>
<a href="${pageContext.request.contextPath}/category/insert" target="body">新增</a>
<a href="${pageContext.request.contextPath}/category/categoryView" target="body">查看</a>
</div>
</div>
<div>
<a >种类细分</a>
<div>
<a href="${pageContext.request.contextPath}/subdivide/insert" target="body">新增</a>
<a href="${pageContext.request.contextPath}/subdivide/subdivideView" target="body">查看</a>
</div>
</div>
<div>
<a>属性</a>
<a href="${pageContext.request.contextPath}/property/add" target="body">新增</a>
<a href="${pageContext.request.contextPath}/property/propertyView" target="body">查看</a>
</div>
<div>
<a>属性值</a>
<div>
<a href="" target="body">新增</a>
<a href="" target="body">查看</a>
</div>
</div>

<div>
<a>商品</a>
<div>
<a href="${pageContext.request.contextPath}/product/add" target="body">新增</a>
<a href="${pageContext.request.contextPath}/product/productView" target="body">查看</a>
</div>
</div>


</div>
</body>
</html>