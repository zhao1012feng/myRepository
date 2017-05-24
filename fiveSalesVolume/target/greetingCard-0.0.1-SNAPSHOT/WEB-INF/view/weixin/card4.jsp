<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String id = request.getParameter("id");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ page isELIgnored="false"%>



<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="user-scalable=no, width=device-width, initial-scale=1, maximum-scale=1">
<title>大吉大利</title>
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<meta content="email=no" name="format-detection">
<link rel="stylesheet" href="${ctx}/static/weixin/css/swiper.min.css">
<link rel="stylesheet" href="${ctx}/static/weixin/css/css.css" />
<link rel="stylesheet" href="${ctx}/static/weixin/css/animate.min.css" />

</head>
<body>
	<input type="hidden" name="referrerId" id="hiddenReferrerId"
		value="<%=id%>" />
	<div class="main"></div>
	<div class="ji">
		<img src="${ctx}/static/weixin/img/ji.jpg" />
		<p>加载中...</p>
	</div>
	<canvas id="myCanvas" height="40" width="40">
	Your browser does not support the canvas element.
</canvas>
	<audio loop="true" src="${ctx}/static/weixin/mp3/chicken.mp3"  id="media" autoplay="" preload="auto"></audio>
	<div class="index2_box1">
		<div class="fuk">
			<img src="${ctx}/static/weixin/img/1.jpg">
		</div>
		<div class="index2_box1_son1">
			<div class="son_1">
				<img src="${ctx}/static/weixin/img/b14.png" />
			</div>
			<input class="ffkk" type="text" maxlength="5" />
		</div>
		<div class="index2_main">
			<p>&nbsp;</p>
			<input class="input1 ffkk" type="text" maxlength="12"
				placeholder="祝您2017年" /> <input class="input2 ffkk" type="text"
				maxlength="12" placeholder="快乐连连 福气满满 好梦圆圆" />
			<!--<p>● 祝福语二</p>
		<input class="input3" type="text" maxlength="12" placeholder="$祝您新的一年$" />
		<input class="input4" type="text" maxlength="12" placeholder="大财、小财、意外财，财源滚滚"/>-->
			<p>
				<span style="font-weight: 700;">注：</span>每行限制12字符
			</p>
		</div>
		<div class="index2_box1_son2">
			<div class="son_1">
				<img src="${ctx}/static/weixin/img/b15.png" />
			</div>
			<input class="ffkk" type="text" maxlength="5" />
		</div>
		<div class="index2_box1_son3">
			<p style="display: none;">信息请填写完整！</p>
			<button class="btn1" >提交</button>
			<button class="btn2">返回</button>

		</div>
	</div>
	<div class="swiper-container">
		<div class="swiper-wrapper">
			<div class="swiper-slide box1">
				<div class="hand ani"  data-slide-in="at 300 from flash use swing during 1000"><img src="${ctx}/static/weixin/img/b16.png"/></div>
				<div class="ani box1_c1_b1"
					data-slide-in="at 500 from bounceInLeft use swing during 2000"></div>
				<div class="ani box1_c2_b2"
					data-slide-in="at 1000 from bounceInRight use swing during 2000"></div>
				<div class="ani box1_c3_b3"
					data-slide-in="at 500 from shake use swing during 10000"></div>
				<div class="ani box1_c4_b4"
					data-slide-in="at 500 from tada use swing during 20000"></div>
				<div class="ani box1_c5_b5"
					data-slide-in="at 500 from pulse use swing during 2000"></div>

				<div class="ani box1_test1"
					data-slide-in="at 800 from zoomIn use swing during 1000">
					<i class="icon iconfont">&#xe609;</i><span class="name">小明</span><i
						class="icon iconfont">&#xe609;</i>
				</div>
				<div class="ani box1_test2" data-slide-in="at 800 from zoomIn use swing during 1000"></div>

				<i class="icon iconfont1 ani"
					data-slide-in="at 2000 from fadeOutUp use swing during 1500">&#xe614;</i>
			</div>
			<div class="swiper-slide box2">
				<div class="ani box2_c1_b2"
					data-slide-in="at 500 from bounceIn use swing during 2000"></div>
				<div class="ani box2_c1_b1"
					data-slide-in="at 500 from fadeInLeft use swing during 1000"></div>



				<div class="box2_test">
					<p class="ani"
						data-slide-in="at 0 from slideInLeft use swing during 1000"
						swiper-animate-effect="slideInLeft" swiper-animate-duration="1s"
						swiper-animate-delay="0s">新年到 福气到</p>
					<p class="ani"
						data-slide-in="at 1000 from flip use swing during 1000"
						swiper-animate-effect="flip" swiper-animate-duration="1s"
						swiper-animate-delay="1s">
						<i class="icon iconfont">&#xe609;</i><span class="name">小明</span><i
							class="icon iconfont">&#xe609;</i>
					</p>
					<p class="ani section1"
						data-slide-in="at 2000 from slideInLeft use swing during 1000"
						swiper-animate-effect="slideInLeft" swiper-animate-duration="1s"
						swiper-animate-delay="2s">祝您2017年</p>
					<p class="ani section2"
						data-slide-in="at 2400 from slideInRight use swing during 1000"
						swiper-animate-effect="slideInRight" swiper-animate-duration="1s"
						swiper-animate-delay="2.4s">快乐连连 福气满满 好梦圆圆</p>
				</div>
				<i class="icon iconfont1 ani"
					data-slide-in="at 2000 from fadeOutUp use swing during 1500">&#xe614;</i>
			</div>


			<div class="swiper-slide box3">
				<div class="ani box3_c1_b2"
					data-slide-in="at 500 from fadeInLeft use swing during 500"></div>
				<div class="ani box3_c1_b1"
					data-slide-in="at 500 from fadeInRight use swing during 500"></div>
				<div class="ani box3_c1_b3"
					data-slide-in="at 500 from bounceIn use swing during 8000"></div>

				<div class="box3_test">
					<p class="ani"
						data-slide-in="at 1000 from rotateIn use swing during 800"></p>
					<p class="ani"
						data-slide-in="at 1400 from rotateIn use swing during 800">
						<i class="icon iconfont">&#xe609;</i><span class="name">小明</span><i
							class="icon iconfont">&#xe609;</i>
					</p>
					<p class="ani section1"
						data-slide-in="at 1800 from rotateIn use swing during 800">祝您2017年</p>
					<p class="ani section2"
						data-slide-in="at 2500 from rotateIn use swing during 800">快乐连连
						福气满满 好梦圆圆</p>
				</div>
				<i class="icon iconfont1 ani"
					data-slide-in="at 2000 from fadeOutUp use swing during 1500">&#xe614;</i>
			</div>


			<div class="swiper-slide box4">
				<div class="ani box4_c1_b1"
					data-slide-in="at 500 from fadeInLeft use swing during 500"></div>
				<div class="ani box4_c1_b2"
					data-slide-in="at 500 from fadeInRight use swing during 500"
					data-slide-out="at 1800 to pulse use swing during 1500 force"></div>
				<div class="ani box4_c1_b3"
					data-slide-in="at 500 from bounceInLeft use swing during 500"
					data-slide-out="at 1000 to fadeOutRight use swing during 1500 force"></div>
				<div class="ani box4_c1_b4"
					data-slide-in="at 3200 from zoomIn use swing during 500"
					data-slide-out="at 500 to pulse use swing during 1500 force"></div>

				<div class="box4_test">
					<p class="ani"
						data-slide-in="at 500 from zoomInUp use swing during 2500">收卡人</p><p class="ani"
					data-slide-in="at 800 from zoomInUp use swing during 2500">发卡人</p>
					
					<input class="ani"
						data-slide-in="at 600 from zoomInUp use swing during 2500"
						value="" id="userName" type="text" placeholder="请输入姓名"><input class="ani"
					data-slide-in="at 900 from zoomInUp use swing during 2500"
					value=""  placeholder="请输入姓名" id="userName1" type="text">
					
					
					<button class="ani saveSubmit"
						data-slide-in="at 1000 from zoomInUp use swing during 2500"
						>提交</button>
					<!--<button class="ani gd"
						data-slide-in="at 1100 from zoomInUp use swing during 2500">更多</button>-->
				</div>
			</div>
		</div>
	</div>





	<script src="${ctx}/static/weixin/js/zepto.min.js"></script>
	<script src="${ctx}/static/weixin/js/autofont.js"></script>
	<script src="${ctx}/static/weixin/js/swiper-3.4.1.jquery.min.js"></script>
	<script type="text/javascript"
		src="${ctx}/static/weixin/css/swiper.animate1.0.2.min.js"></script>
	<script src="${ctx}/static/weixin/js/swiper.animate-twice.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/js/jweixin-1.1.0.js"></script>
	<script>
		var timer = null;
		var bok = true;
		var canvas=document.getElementById('myCanvas');
		var ctx=canvas.getContext('2d');
		var img = new Image();
		img.src= "${ctx}/static/weixin/img/music.png";
		

		var myAudio = document.getElementById('media');
		var img1 = new Image();
		var ua = navigator.userAgent.toLowerCase();
		img1.src = '${ctx}/static/weixin/img/b12.png';
	   	img1.onload = function(){
	   		clearTimeout(timer);
			rotate();
			$('.main,.ji').remove();
			swiperRuning();
	   	}
			

		function autoPlayAudio1() {
	      /* wx.config({
	         // 配置信息, 即使不正确也能使用 wx.ready
	         debug: false,
	         appId: '',
	         timestamp: 1,
	         nonceStr: '',
	         signature: '',
	         jsApiList: []
	      });
	      wx.ready(function() {
	         myAudio.play();
	      }); */
	    }
		function swiperRuning(){
			var swiper = new Swiper('.swiper-container', {
			    pagination: '.swiper-pagination',
			    paginationClickable: true,
			    longSwipesRatio:0.1,
			    direction: 'vertical',
			    onInit: function(swiper){
		 			swiperAnimateCache(swiper); //隐藏动画元素 
		   			swiperAnimate(swiper); //初始化完成开始动画
		   			setTimeout(function(){
		   				$('.hand').remove();
		   				$('#myCanvas').css('display','block');
		   			},4000);
			    },
			    onSlideChangeEnd: function(Swiper){
			    	swiperAnimate(swiper);
			    },
		    });
		}
		function rotate(){
			ctx.save();
		   	var x = 40/2; //画布宽度的一半
		    var y = 40/2;//画布高度的一半
		    ctx.clearRect(0,0, canvas.width, canvas.height);//先清掉画布上的内容
		    ctx.translate(x,y);//将绘图原点移到画布中点
		    ctx.rotate(10*Math.PI/180);
		    ctx.translate(-x,-y);//将画布原点移动*/
			ctx.drawImage(img,0,0,40,40);//绘制图片     
			timer = setTimeout(rotate,30);
		}

		
		function playPause(){
		    if(myAudio.paused){
		        myAudio.play();
		    }else{
		        myAudio.pause();
		    }
		}
		$('.gd').on('touchend',function(){
			$('.index2_box1').css('display','block');
		});
		$('.btn1').on('touchend',function(){
			for(var i=0;i<$('.ffkk').length;i++){
				if($('.ffkk').eq(i).val() == ''||$('.ffkk').eq(i).val() == null){
					$('.index2_box1_son3 p').css('display','block');
					return false;
				}
			}
			saveMore();
			$('.index2_box1').css('display','none');
			
		});
		$(".saveSubmit").on("touchend",function(){
			save();
		})
		$('.btn2').on('touchend',function(){
			$('.index2_box1').css('display','none');
		});
		
		$('#myCanvas').on('touchstart',function(){
			playPause();
			if(bok){
				clearTimeout(timer);
				bok = false;
			}else{
				clearTimeout(timer);
				rotate();
				bok = true;
			}  	
		});
		if (/Android/gi.test(navigator.userAgent)) {
	        window.addEventListener('resize', function () {
	            if (document.activeElement.tagName == 'INPUT' || document.activeElement.tagName == 'TEXTAREA') {
					window.setTimeout(function () {
					document.activeElement.scrollIntoViewIfNeeded();
					}, 0);
	            }
	        })
	    }
		$('.box4_test input').on('focus',function(){
			
		});
		$('.box4_test input').on('blur',function(){
			
		});
		// 后台交互
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
			    				//数据返回成功，渲染吧
			    				//data.returnData
			    				var info = data.returnData[0];
			    				
			    				$(".name").html(info.recipient);
			    				if(info.greetings == null||info.greetings == false){
			    					//$('.section1').html(title);
			    					//$('.section2').html(desc);
			    				}else{
			    					var str = info.greetings;
			    					var arr =str.split(','); 
			    					$('.section1').html(arr[0]);
			    					$('.section2').html(arr[1]);
			    				}
			    				title = data.returnData[1];
			    				desc = data.returnData[2];
			    				
			    			}else{
			    				console.log(data.errorMessage);
			    			}
			    		}
			    	});
				},
		  	save:function(obj){
					ajax({
			    		url : "${ctx}/saveParticipation",
			    		data : obj,
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
			    		success:function(data){}
			    	});
				}
		}
		 //保存简单的
		function save(){
			var userName = $("#userName").val();
			var userName1 = $("#userName1").val();
			if(userName == ''||userName1 == ''){
				alert('请输入姓名');
			}else{
				crud.save({
					recipient:userName,
					producer:userName1,
					referrerId:$("#hiddenReferrerId").val()
				});
			}
		}
		//保存复杂的
		function saveMore(){
		
			var postName = $(".ffkk").eq(0).val();
			var says = $(".ffkk").eq(1).val()+','+$(".ffkk").eq(2).val();
			var ReceiveName = $(".ffkk").eq(3).val();
			crud.save({
				producer:postName,
				greetings:says,
				recipient:ReceiveName,
				referrerId:$("#hiddenReferrerId").val()
			});
		}
		var id = "<%=id%>";
		if (id == '') {
			//alert('直接跳到第制作页面吧');
		} else {
			crud.query();
		}

		//var link = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx15069e85062d9ae3&redirect_uri=http%3A%2F%2Flottery.yzixin.cn%2Fweixin&response_type=code&scope=snsapi_base&state=1d18efad-b664-42f7-9464-c93dc13c91a11d18efad-b664-42f7-9464-c93dc13c91a11d18efad-b664-42f7-9464-c93dc13c91a1#wechat_redirect";
		var link = location.href.split('#')[0];
		var imgUrl = "http://mv.guojingold.com.cn/greetingCard/static/img/icon.jpg";

		$.ajax({
			url : "${ctx}/getTicket",
			dataType : 'json',
			data : {
				url : link
			},
			success : function(data) {
				console.log(data);
				wx.config({
					appId : data.appId,
					timestamp : data.timestamp,
					nonceStr : data.nonceStr,
					signature : data.signature,
					jsApiList : [ 'onMenuShareTimeline',
							'onMenuShareAppMessage' ]
				});

				wx.ready(function() {
					wx.onMenuShareAppMessage({
						title : title,
						desc : desc,
						link : link,
						imgUrl : imgUrl,
						success : function() {
							crud.update("0");
						}
					});
					wx.onMenuShareTimeline({
						title : title,
						desc : desc,
						link : link,
						imgUrl : imgUrl,
						success : function() {
							crud.update("1");
						}
					});
					if(/iphone|ipad|ipod/.test(ua)){
						myAudio.play();
					}
				});

			},
			error : function() {
				//alert("网络链接失败，请稍候再试!");
			}
		});
	</script>
</body>
</html>








