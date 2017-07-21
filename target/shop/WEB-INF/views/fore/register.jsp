<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<!-- 引入bootstrap文件 和jquery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/js/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/bootstrap/js/bootstrap.min.js"></script>

<style type="text/css">
#userForm{
width:50%;
padding-left: 10%;
padding-top:20px;
}
</style>
<!-- 表单验证js控件 -->
<!-- <script type="text/javascript" src="resource/js/jquery-validation-1.14.0/lib/jquery.js"></script> -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-validation-1.14.0/dist/additional-methods.js"></script></head>

<script type="text/javascript">
//定义一个全局变量记住邮箱是否存在
var flag=false;

//代替sumbmit按钮，验证通过之后才能提交
$.validator.setDefaults({
    submitHandler: function(form) {
    	if(flag)//如果邮箱不存在才提交！
    		form.submit();
    }
});

//注意这个验证插件，设置每个属性的位置不能摆错，括号位置错误等问题会导致效果出不来
 $().ready(function(){
 //定义一个变量记住方法
 var validator=$("#userForm").validate(
{
//这里注意是这么一种格式rules{}，messages{} 中间括号，字符等不能有问题，如果对应不上，可能不会报错，但是前端审核效果出不来 
rules:{
//用户名
name:"required",
//密码
password:{
required:true,
minlength:6,
//pattern_lihao:"^(?![0-9]+$)(?![a-zA-Z]+$)\w+{6,}$"
//pattern:"/\w+/"
},
//确认密码
comfirm_password:{
required:true,
minlength:6,
equalTo:"#password"
},
email:{
	required:true,
	email:true
		
	}
}//rules结束大括号
,

messages:{
name:"用户名必填！",
//密码
password:{
required:"密码必填！",
minlength:"密码最小长度是6！",
//pattern_lihao:"密码应该由6位以上数字和字母组成！"
},
//确认密码
comfirm_password:{
required:"密码必填！",
minlength:"密码最小长度是6！",
equalTo:"两次密码不相等！"
},
//邮箱
email:{
	required:"邮箱必填！",
	email:"邮箱格式不对！"
		
	}


}//messages结束大括号

});//validate
 });
</script>
<!-- 使用ajax判断邮箱是否存在 -->
<script type="text/javascript">

function detection(){ 
	var email={email:$("#email").val()};
	//var email=$("#email").val();
$.ajax({
url:"${pageContext.request.contextPath}/user/isexist",
type:"post",
dataType:"text",
data:JSON.stringify(email),
contentType:"'application/json; charset=UTF-8",
success:function(data){
	
	if (data=="0"){
		//邮箱已经注册
		//alert(data);
	document.getElementById("isexist").innerText="邮箱已经注册！";
	flag=false;
		
	}
	else{
		//邮箱可以使用
		document.getElementById("isexist").innerText="邮箱可以使用！";
		flag=true;
	}	
},
error:function(data){
	
	alert("ajax发生错误！");
}

});
}



</script>
</head>


<body>
<sf:form modelAttribute="user" action="${pageContext.request.contextPath}/user/register" class="form" id="userForm" method="post">

<div class="form-group">
<label for="email">邮箱:</label>
<sf:input path="email"  class="form-control" id="email" onchange="detection()"/><span><a name="isexist" id="isexist"  style="color:red;"></a></span>
</div>

<div class="form-group">
<label for="name">用户名:</label>
<sf:input path="name"  class="form-control" id="name"/>
</div>
<div class="form-group">
<label for="password">密码:</label>
<sf:password path="password"  class="form-control" id="password"/>
</div>

<div class="form-group">
<label for="comfirm_password">密码确认：</label>
<input type="password" name="comfirm_password"  class="form-control" id="comfirm_password"/>
</div>

<input type="submit" value="提交" id="submit"  class="btn btn-default">

</sf:form>

</body>
</html>