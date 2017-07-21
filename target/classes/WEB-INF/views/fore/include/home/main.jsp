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
.wrapper{background:#F5F5F5}

/*把整个主体的宽度设置为1000px*/
div.mainbody{
position:relative;
top:0px;
left:0px;
width:1000px;
margin:0px auto;
}
/* 这里把定位设置为绝对定位，top不设置，让其自动跟随页面移动，left设置值 */
.category{
position:absolute;
background:#E4EBEE;
width:10%;
}
.eachCategory{
margin-top:20px;
width:100%;
}

.eachCategory a{
text-decoration:none;
color:black;
cursor:pointer;
}
.eachCategory a:hover{
color:#E54077;
}

.subdivide{
position:absolute;
left:10%;
padding:0px 20px 20px 0	px;
/*设置宽度60%*/
width:60%;
}
.eachSubdivide{
position:absolute;
border:1px solid #E7E7E7;
display:none;
min-height;30%;
/*相对父div宽度100%*/
width:100%;
}
.eachSubdivide a{
color:#BABABA;
text-decoration:none;
margin:auto 5px;
}
.eachSubdivide a:hover{
color:#E54077;
}

.selectBodyParent{width:100%;background:white;}
.selectBody{overflow:auto;height:100px;width:1000px;margin:auto;}

.selectTite{display:inline-block;width:10%;margin-left:2%;}
.selectTitel1{font-size:24px;color:#FF0136;font-weight:bold;}
.selectTitel2{font-size:14px;color:#FF6B8A;}

.importMessage{display:inline-block;line-height:35px;width:50%;margin-left:15%;margin-top:30px;}
.selectInfo{border:2px solid red;width:70%;float:left;}
.selectInfo:focus{outline:none}
.selectButton{border:2px solid #FF0036;font-size:16px;background:#FF0036;width:30%;float:left;}
.buttonWord{color:white;font-size:20px;}


.categoryTitleParent{width:100%;background:#FF0036;overflow:auto;}
.categoryTitle{width:1000px;margin:0px auto;color:white;height:40px;}
.categoryTitle div{float:left;width:100px;margin-top:9px;text-align:center;font-size:16px;}
.fristCategoryTitle{background:#E22127;height:100%;margin-top:0px !important;text-align:center;}
.fristCategoryTitle span{margin-top:9px !important;}
</style>
<script type="text/javascript">
/* js控制category 和subdivide父节点div的高度*/
function initialHeight(){
var heightSum=0;
var eachCategory=$(".eachCategory").each(function(){
   heightSum=heightSum+$(this).outerHeight(true);
});
heightSum+=20;//让底部多出20px的高度,好看
$(".category").css("height",heightSum+"px");
$(".eachSubdivide").css("height",heightSum+"px");
$(".footer_desc").css("buttom","1px");
$(".mainbody").css("height",heightSum+"px");

}

//产品种类效果交互
$(document).ready(function(){
/* 当鼠标指针移动到种类上时触发效果 */
$(".eachCategory").mouseover(function(){
	var cid=$(this).attr("cid");
	$(this).css("background","white");
	$(".eachSubdivide[cid='"+cid+"']").css("display","initial");
	$(".eachSubdivide").each(function(){
	if($(this).attr("cid")!=cid)
	$(this).css("display","none");
	else
	{
	$(this).css("display","initial");
	$(this).css("margin-top","0px");
	$(this).css("padding-top","20px");
	$(this).css("background","white");
	}
	});
	
});
$(".eachCategory").mouseout(function(){
  $(this).css("background","initial");
});

$(".eachSubdivide").mouseover(function(){
  var cid=$(this).attr("cid");
  $(".eachSubdivide[cid='"+cid+"']").css("display","initial");

});
/* 当鼠标指针从具体明细上移动出去上时触发效果 */
$(".eachSubdivide").mouseout(function(){
	var cid=$(this).attr("cid");
	$(this).css("display","none");
	$(".eachCategory[cid='"+cid+"']").css("background","initial");
});


});

//提交搜索表单动作
function submitForm(){
	var selectInfo=$(".selectInfo").val();
	selectInfo=selectInfo.replace(/\s+/g,"");
	if(selectInfo==null || selectInfo=="" || selectInfo==undefined)
		{
		$(".selectInfo").val("");
		return false;
		}
		
	document.getElementById("selectForm").submit();
	
}
</script>

</head>


<body onload="initialHeight()">
<div class="wrapper">

<div class="selectBodyParent">
<div class="selectBody">
<div class="selectTite">
<span class="selectTitel1"></span><br>
<span class="selectTitel2"></span>
</div>
<div class="importMessage">
<form action="fore/select" method="post" target="_blank"  id="selectForm">
<input type="text" name="selectInfo" placeholder="请输入商品种类、名称"  class="selectInfo"/>
</form>
<button class="selectButton"  onclick="submitForm()"><span class="buttonWord">搜索</span></button>
</div>
</div>
</div>

<div class="categoryTitleParent">
<div class="categoryTitle">
<div class="fristCategoryTitle">
<span><span class="glyphicon glyphicon-list"></span>商品分类</span>
</div>
<div><span>shopping超市</span></div>
<div><span>shopping国际</span></div>
<div><span>shopping会员</span></div>
<div><span>品牌街</span></div>
<div><span>电器城</span></div>
<div><span>海洋馆</span></div>
<div><span>养生庭</span></div>
<div><span>梦想远行</span></div>
<div><span>购物狂</span></div>
</div>
</div>

	<div class="mainbody">
		<div class="category">
		<c:forEach items="${categorys}" var="ct">
		<div class="eachCategory" cid="${ct.uuid}">
		<span class="glyphicon glyphicon-link"></span>
		<a>${ct.name}</a>
		</div>
		</c:forEach>
		</div>
		<div class="subdivide">
		<c:forEach items="${categorys}" var="category">
		<div class="eachSubdivide" cid="${category.uuid}">
		<c:forEach items="${category.subdivide}" var="subdivide" >
		<a href="${pageContext.request.contextPath}/fore/showProducts/${subdivide.uuid}" target="_blank">${subdivide.name}</a>
		</c:forEach>
		</div>
		</c:forEach>
		</div>
	</div>
</div>
</body>
</html>