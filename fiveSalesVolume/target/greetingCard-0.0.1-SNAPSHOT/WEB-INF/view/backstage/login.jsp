<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>大吉大利贺卡后台系统</title>
		 <meta charset="UTF-8" />
		<meta HTTP-EQUIV="pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
		<meta HTTP-EQUIV="expires" CONTENT="0">
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		
		<link href="${ctx}/static/css/login.css" rel="stylesheet" type="text/css" />
		<script src="${ctx}/static/js/jquery-1.7.min.js" type="text/javascript"></script>
  		<link href="${ctx}/static/js/jquery-validate/css/jquery-validation/validate.css" rel="stylesheet" type="text/css"/>
    	<script src="${ctx}/static/js/jquery-validate/js/jquery.validate.min.js" type="text/javascript"></script>
   	 	<script src="${ctx}/static/js/jquery-validate/js/messages_cn.js" type="text/javascript"></script>
   	 	<script src="${ctx}/static/js/echarts.js" type="text/javascript"></script>
   	 	
		<!-- bootstrap -->
		<link rel="stylesheet" type="text/css" href="${ctx}/static/css/bootstrap/bootstrap.min.css" />
		<script src="${ctx}/static/js/demo-rtl.js"></script>
		<link rel="stylesheet" type="text/css" href="${ctx}/static/css/libs/font-awesome.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/static/css/libs/nanoscroller.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/static/css/rebuild.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/static/css/compiled/theme_styless.css" />
		<style>
		html,body{
			width:100%;
			height:100%;
			/* overflow:hidden; */
		} 
		body{
			position:relative;
		}
		.box {
			width:100%;
			height:100%;
		}
		.box>div{
			width:100%;
			height:auto;
		}
		.box>div>img {
			display:block;
			width:100%;
			height:auto;
		}
		.cover {
		    height:360px;
		    width:1920px;
			position:fixed;
			top:50%;
			left:50%;
			margin-top:-180px;
			margin-left:-960px;

		}
		.cover>div{
			width:100%;
			height:auto;
		}
		.cover>div>img {
			display:block;
			width:100%;
			height:auto;
		}
		.banklogo {
			font-size: 40px;
			letter-spacing:8px;
			font-weight:bold;
			height:88px;
		    width:700px;
			position:fixed;
			top:50%;
			left:50%;
			margin-top:-300px;
			margin-left:-280px;
		}
		
		
		.cover-top {
		    height:200px;
		    width:430px;
			position:fixed;
			top:50%;
			left:50%;
			margin-top:-100px;
			margin-left:-218px;
			padding-top:30px;
		}
		
		.cover-top>input {
			margin-bottom:20px;
			width:430px;
			border:1px solid #c1cedb;
			border-radius:6px;
			height:50px;
			font-size:20px;
			text-indent:3em;
			line-height:50px;
			padding-right:40px;
			font-family: 'Microsoft Yahei', 'simhei';
			background: url('${ctx}/static/images/newindex/name.gif') no-repeat scroll 10px 10px #ecf5fa;
			letter-spacing:1px;
		}
		.cover-top>input.lastchild {
			background: url('${ctx}/static/images/newindex/code.gif') no-repeat scroll 10px 10px #ecf5fa;
			margin-bottom:20px;
		}
		.cover-top button:hover {
			background:#2c83b3;
			transition: all  ease 0.2s;	
		}
		.cover-top>button {
			width:430px;
			border:1px solid #1978ad;
			border-radius:6px;
			height:50px;
			font-size:20px;
			color:#fff;
			background:#3b95c7;
			font-family: 'Microsoft Yahei', 'simhei';
			transition: all  ease 0.2s;	
		}
		</style>  	
   	 	<script type="text/javascript">
		    $(document).ready(function() {
		    	//document.getElementById("userName").focus();
		    	$("#myFrm").validate({
		    		submitHandler: function() { 
		    			form.submit();
		    		},
		    		rules: {
		    			userName: {
		    				required: true
		    			},
		    			userRealPassword: {
		    				required: true
		    			},
		    			rand:{
		    				required: true
		    			}
		    		}
		    	});
		    });
		</script>
	</head>
<body id="login-page" style="background:#dad4d6;">
<div class="box">
		<div><img src="${ctx}/static/images/newindex/1_01.jpg"/></div>
		<div><img src="${ctx}/static/images/newindex/1_02.jpg"/></div>
		<div><img src="${ctx}/static/images/newindex/1_03.jpg"/></div>
		<div><img src="${ctx}/static/images/newindex/1_04.jpg"/></div>
		<div><img src="${ctx}/static/images/newindex/1_05.jpg"/></div>
		<div><img src="${ctx}/static/images/newindex/1_06.jpg"/></div>
		<div><img src="${ctx}/static/images/newindex/1_07.jpg"/></div>
		<div><img src="${ctx}/static/images/newindex/1_08.jpg"/></div>
		<div><img src="${ctx}/static/images/newindex/1_09.jpg"/></div>
		<div><img src="${ctx}/static/images/newindex/1_10.jpg"/></div>
	</div>
	<h1 class="banklogo">大吉大利贺卡后台系统</h1>
	<div class="cover">
		<div><img src="${ctx}/static/images/newindex/1_01.gif"/></div>
		<div><img src="${ctx}/static/images/newindex/1_02.gif"/></div>
		<div><img src="${ctx}/static/images/newindex/1_03.gif"/></div>
		<div><img src="${ctx}/static/images/newindex/1_04.gif"/></div>
		<div><img src="${ctx}/static/images/newindex/1_05.gif"/></div>
		<div><img src="${ctx}/static/images/newindex/1_06.gif"/></div>
		<div><img src="${ctx}/static/images/newindex/1_07.gif"/></div>
		<div><img src="${ctx}/static/images/newindex/1_08.gif"/></div>
		<div><img src="${ctx}/static/images/newindex/1_09.gif"/></div>
		<div><img src="${ctx}/static/images/newindex/1_10.gif"/></div>
	</div>
<form role="form" action="${ctx}/manage/login" method="post">	
	<div class="cover-top" >
			<input type="text" placeholder="请输入用户名" name="account" />
			<input class="lastchild" type="password" placeholder="请输入密码" name="password" />
			<div class="" style="text-align: center;padding:0px 10px 10px;font-size: 14px;color: red">
			${errorMessage}
			</div>	
			<button type="submit" >登录</button>
		
	</div>
</form>							
</body>
</html>

