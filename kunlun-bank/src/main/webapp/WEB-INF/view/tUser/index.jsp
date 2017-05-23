<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%session.setMaxInactiveInterval(60*15); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>报单后台系统</title>
<link href="${ctx}/static/background/css/public.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript"
	src="${ctx}/static/background/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx}/static/background/js/global.js"></script>
<script src="${ctx}/static/layer/layer.js" type="text/javascript"
	charset="utf-8"></script>
</head>
<body>
	<div id="dcWrap">
		<div id="dcHead">
			<div id="head">
				<div class="logo"
					style="font-size: 20px; text-align: center; margin-top: 3px; color: #fff">报单后台管理</div>
				<div class="nav">
					<ul class="navRight">
						<li class="noRight"><a href="javaScript:;" onclick="exit()">退出</a></li>
					</ul>
				</div>
			</div>
		</div>
		<!-- dcHead 结束 -->
		<div id="dcLeft">
			<div id="menu">
				<ul class="top">
					<li><a><i class="home"></i><em>管理首页</em></a></li>
				</ul>

				<ul id="listClick">
					<li class="cur" data="${ctx}/tUser/listUserInfo0?flag=0"><a><i
							class="article"></i><em>员工销量信息</em></a></li>
					<li data="${ctx}/tUser/listUserInfo0?flag=1"><a><i
							class="articleCat"></i><em>员工件数列表</em></a></li>
					<li data="${ctx}/tUser/listUserInfo0?flag=2"><a><i
							class="articleCat"></i><em>员工销量件数总列表</em></a></li>
					<li data="${ctx}/tUser/listUserInfo0?flag=3"><a><i
							class="articleCat"></i><em>员工分批次销量件数总列表</em></a></li>
					<%-- <li data="${ctx}/backstage/editParam"><a ><i class="articleCat"></i><em>微信参数修改</em></a></li>
  <li data="${ctx}/backstage/templetShow"><a ><i class="articleCat"></i><em>模板列表</em></a></li> --%>
				</ul>

			</div>
		</div>
		<div id="dcMain">
			<div id="urHere">
				<strong>用户中奖列表</strong>
			</div>
			<div class="mainBox"
				style="height: auto !important; height: 550px; min-height: 550px;">
				数据加载中...</div>

		</div>
		<div class="clear"></div>
		<div id="dcFooter">
			<div id="footer">
				<div class="line"></div>
				<ul>
				</ul>
			</div>
		</div>
		<!-- dcFooter 结束 -->
		<div class="clear"></div>
	</div>
	<script type="text/javascript">
$("#listClick li").click(function(){
	
	$("#listClick li").removeClass("cur");
	
	$(this).addClass("cur");
	
	var url = $(this).attr("data");
	
	//弹出层
	var layerLoad =layer.load(0,{shade: 0.1});
	
	$("#dcMain").load(url,function(){
		layer.close(layerLoad);
	});
	
});

function exit(){
	layer.confirm('是否退出系统？', {
		  btn: ['退出','取消'] //按钮
	}, function(){
	  	window.location.href="${ctx}/manage/exit";
	}, function(){
	});
	
}

$(function(){
	
	$("#listClick li:eq(0)").click(); 
	
});


</script>
</body>
</html>