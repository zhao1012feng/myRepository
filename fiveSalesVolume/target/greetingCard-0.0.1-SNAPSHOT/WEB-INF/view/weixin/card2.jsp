<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id = request.getParameter("id");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>分享</title>
    
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />

  </head>
  
  <body>
  <form action="" method="post" id="formId">
  <input type="hidden" name="referrerId" value="<%=id%>" />
  制作人：<input type="text" name="producer" id="producerId" /><br />
祝福语：
<textarea rows="" cols="" name="greetings" id="greetingsId" >
</textarea><br />
接收人：<input type="text" name="recipient" id="recipientId" />
  </form>
  <button onclick="crud.save()">制作简单贺卡保存</button>
  
  <h2>分享</h2>
  <h3>${openId}</h3>
  
    <script type="text/javascript" src="<%=basePath%>/static/js/zepto.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/static/js/jweixin-1.1.0.js"></script>
    <script type="text/javascript">
    var title = "送你2017年新年贺卡，祝你大吉大利，请查收！";
	var desc = "祝你2017年，大财、小财、意外财，财源滚滚。";
    function ajax(obj){
	   	var data = {};
	   	if(typeof(obj.data)!='undefined'){
	   		 data = obj.data;
	   	}
    	$.ajax({
    		type:"post",
    	  	url: obj.url,
    	  	dataType: 'json',
    	  	data: data,
    	 	success: function(data){
    	 		 obj.success(data);
    	 	},
    	 	error:function(){
    	 		alert('网络请求失败，请稍后再试！');
    	 	}
    	});
    }
    
    var crud = {
   		query:function(){
   			ajax({
   	    		url : "${ctx}/queryParticipation",
   	    		data : {id:id},
   	    		success:function(data){
   	    			if(data.state==true){
   	    				
   	    				console.log(title);
   	    				
   	    				//数据返回成功，渲染吧
   	    				//data.returnData
   	    				console.log(data.returnData[0]);
   	    				title = data.returnData[1];
   	    				desc = data.returnData[2];
   	    				console.log(title+" -- "+desc);
   	    			}else{
   	    				alert(data.errorMessage);
   	    			}
   	    		}
   	    	});
   		},
      	save:function(){
   			ajax({
   	    		url : "${ctx}/saveParticipation",
   	    		data : $("#formId").serializeArray(),
   	    		success:function(data){
   	    			if(data.state==true){
   	    				window.location.href="${ctx}?id="+data.returnData.id;
   	    			}else{
   	    				alert(data.errorMessage);
   	    			}
   	    		}
   	    	});
   		},
   		update:function(state){
   			ajax({
   	    		url : "${ctx}/updateShareNumber",
   	    		data : {id:id,state:state},
   	    		success:function(data){
   	    			//成不成功也不用管了
   	    		}
   	    	});
   		}
    }
       

    
    var id = "<%=id%>";
    if(id==''){
    	alert('直接跳到第制作页面吧');
    }else{
    	crud.query();
    }
    
    
	
	//var link = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx15069e85062d9ae3&redirect_uri=http%3A%2F%2Flottery.yzixin.cn%2Fweixin&response_type=code&scope=snsapi_base&state=1d18efad-b664-42f7-9464-c93dc13c91a11d18efad-b664-42f7-9464-c93dc13c91a11d18efad-b664-42f7-9464-c93dc13c91a1#wechat_redirect";
	var link = location.href.split('#')[0];
	var imgUrl =  "http://xiaowu.tunnel.qydev.com/greetingCard/static/img/icon.jpg";
	
	
	$.ajax({
	  	url: "${ctx}/getTicket",
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
				    	crud.update("0");
				      } 
				 });
				 wx.onMenuShareTimeline({
					 title: title,
					 desc: desc,
					 link: link,
					 imgUrl: imgUrl,
				     success:function(){
				    	crud.update("1");
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
