<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ page isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>分享</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<h2>分享</h2>
	<script type="text/javascript"
		src="<%=basePath%>/static/js/jquery-1.8.2.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/js/jweixin-1.1.0.js"></script>
	<script type="text/javascript">
	//js获取项目根路径，
	function getRootPath(){
	    //获取当前网址
	    var curWwwPath=window.document.location.href;
	    //获取主机地址之后的目录，
	    var pathName=window.document.location.pathname;
	    var pos=curWwwPath.indexOf(pathName);
	    //获取主机地址
	    var localhostPaht=curWwwPath.substring(0,pos);
	    //获取带"/"的项目名，
	    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	    return(localhostPaht+projectName);
	}
	
	
	var title = "工商银行邀您欢乐迪士尼！";
	var desc = "《迪士尼米奇米妮幸福之旅纪念册大套装》迪士尼官方授权，工商银行专属发售，超值实惠，大大大，多多多！送给家人、孩子、亲戚朋友，特别有心意特别有意义的礼物！开启幸福之旅，无论男女老少，无论年龄大小都能因为它而收获幸福和快乐，创造值得珍藏一生的回忆！";
	//var link = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx15069e85062d9ae3&redirect_uri=http%3A%2F%2Flottery.yzixin.cn%2Fweixin&response_type=code&scope=snsapi_base&state=1d18efad-b664-42f7-9464-c93dc13c91a11d18efad-b664-42f7-9464-c93dc13c91a11d18efad-b664-42f7-9464-c93dc13c91a1#wechat_redirect";
	var link = location.href.split('#')[0];
	var imgUrl =  "http://lottery.yzixin.cn/static/icon/icon.png";
	
	
	function sharePlusTimes(obj){
		alert(obj);
	}
	
	$.ajax({
	  	url: "${ctx}/weixin/getTicket",
	  	dataType: 'json',
	  	data: {url:link},
	 	success: function(data){
	 		console.log(data);
	 		wx.config({
	 		      debug: false,
	 		      appId: data.appId,
	 		      timestamp: data.timestamp,
	 		      nonceStr: data.nonceStr,
	 		      signature: data.signature,
	 		      jsApiList: [
	 		        'onMenuShareTimeline',
	 		        'onMenuShareAppMessage'
	 		      ]
	 		});
	 		
	 		wx.ready(function () {
				 wx.onMenuShareAppMessage({
					  title: title,
					  desc: desc,
					  link: link,
					  imgUrl: imgUrl,
				      success:function(){
				    	sharePlusTimes("分享给朋友");
				      } 
				 });
				 wx.onMenuShareTimeline({
					 title: title,
					 desc: desc,
					 link: link,
					 imgUrl: imgUrl,
				     success:function(){
				    	sharePlusTimes("分享到朋友圈");
				     }
				 });
	 		});
	 		
	 	},
	 	error:function(){
	 		//alert("网络链接失败，请稍候再试!");
	 	}
	});
	</script>
</body>
</html>
