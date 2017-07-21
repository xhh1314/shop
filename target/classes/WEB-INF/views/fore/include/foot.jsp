<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
/*这个CSS保持页脚始终保持在页面底部*/
body,html{
height:100%;
}
.wrapper{
min-height: 80%;
height: auto !important;
height: 100%;
}


div.footer_desc{
border-top-style: solid ;
border-top-width: 1px;
border-bottom-width:1px;
border-top-color: #e7e7e7;
position:static;
width:100%;
overflow: auto;
}

div.footer_desc div.descColumn{
width:30%;
float:left;
padding-left:15px;
}
/* 每个DIV宽度30%，这里设置左边距10%，让布局看起来居中 */
div.fristDescColumn{
margin-left:10%;
}
div.footer_desc div.descColumn a{
display:block;
padding-top:3px;

}
.descColumn a{
color:#999;
}
.descColumn a:hover{
text-decoration:none;
color:#C40000;
}

</style>
<div style="clear:both"></div>
<div class="footer_desc">
<div class="descColumn fristDescColumn">
<span>购物指南</span>
<a href="">注册</a>
<a href="">收货地址</a>
<a href="">开通支付宝</a>
</div>

<div class="descColumn">
<span>支付方式</span>
<a href="">信用卡</a>
<a href="">支付宝</a>
<a href="">蚂蚁花呗</a>
</div>

<div class="descColumn">
<span>商家服务</span>
<a href="">商家入驻</a>
<a href="">物流服务</a>
<a href="">运营服务</a>
</div>

</div>
</html>