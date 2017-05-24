<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div id="urHere">
	<strong>分享列表</strong>
</div>
<!-- 超连接的样式  虽然很丑 可是找了很久！-->
<style type="text/css">  
	.showBtn{ text-decoration:none;  /*超链接无下划线*/
				color:red;/*超链接红色*/
			    display: inline-block;
			    background-color: #28B779;
			    padding: 0 20px;
			    height: 27px;
			    line-height: 27px;
			    color: #FFFFFF;
			    font-size: 13px;
			    font-weight: bold;
	}
</style>  
<script src="${ctx}/static/js/echarts.js" type="text/javascript"></script>
<div class="mainBox"
	style="height:auto!important;height:550px;min-height:550px;">
	<h3>
		<a href="javaScript:;" class="actionBtn" onclick="exportExcel()">导出全部数据（Excel）</a>
		用户分享列表
	</h3>
	<div class="filter">
		<form  method="post">
			制作用户：<input name="producer" id="producer" type="text" class="inpMain" value="" >
			制作内容：<input name="greetings" id="greetings" type="text" class="inpMain" value="">
			<input name="submit" class="btnGray" type="button" value="筛选" onclick="loadData(1)">
		</form>
	</div>
	<div id="list">
		<form name="action" method="post" action="article.php?rec=action">
			<div class="action">
				<input class="btn" type="button" value="删除" onclick="df()"/> 
			</div>
			<table width="100%" border="0" cellpadding="8" cellspacing="0"
				class="tableBasic">
					<tr>
						<th width="20" align="center"><input name="chkall"
							type="checkbox" id="chkall" onclick="selectcheckbox(this.form)"
							value="check"></th>
						<th width="20" align="center">制作用户</th>
						<th width="20" align="center">制作时间</th>
						<th width="20" align="center">制作内容</th>
						<th width="50" align="center">接收用户</th>
						<th width="50" align="center">浏览数量</th>
						<th width="50" align="center">分享朋友圈数量</th>
						<th width="50" align="center">分享好友数量</th>
					</tr>
					<tbody id="dataTbody">
						<c:forEach items="${page.list}" var="partake" >
							<tr>
								<td align="center"><input type="checkbox" name="checkbox[]" value="${partake.id}"></td>
								<td align="center">${partake.producer}</td>
								<td align="center">${partake.buildTime}</td>
								<%-- <td align="center"><fmt:formatDate value="${partake.build_time}" type="both"/></td> --%>
								<td align="center">${partake.greetings}</td>
								<td align="center">${partake.recipient}</td>
								<td align="center">${partake.viewCount}</td>
								<td align="center">${partake.shareFriendCircleCount}</td>
								<td align="center">${partake.shareFriendCount}</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="10">
								<div class="pager">
									总计 ${page.totalRow} 个记录，当前第${page.pageNumber}页 / 共${page.totalPage}页 | 
									<a href="javaScript:;" <c:if test="${page.firstPage==false}">onclick="loadData(1)"</c:if>>首页</a> 
									<a href="javaScript:;" <c:if test="${page.firstPage==false}">onclick="loadData(${page.pageNumber-1})"</c:if>>上一页 </a> 
									<a href="javaScript:;" <c:if test="${page.lastPage==false}">onclick="loadData(${page.pageNumber+1})"</c:if>>下一页</a> 
									<a href="javaScript:;" <c:if test="${page.lastPage==false}">onclick="loadData(${page.totalPage})"</c:if>>尾页</a>
								</div>
							</td>
						</tr>
						
					</tbody>
			</table>
		</form>
	</div>
	<a href="javaScript:;" id="aShowPic" class="showBtn" onclick="showPic()">显示/隐藏折线图</a>
	<div id="picDiv" style="width: 100%;height:600px ;"></div>
	<div class="clear"></div>
</div>

<script>
$(function(){
	$("#picDiv").hide();//隐藏 折线图的div
});
function loadData(pageNumber){
	var index =layer.load(0,{shade: 0.1});
	$("#dataTbody").load("${ctx}/backstage/listPartakeInfo #dataTbody tr",{
		pageNumber:pageNumber,
		producer:$("#producer").val(),
		greetings:$("#greetings").val(),
		flag:1,
	},function(){
		layer.close(index);
	});
}
//点击按钮  展示出 折线图
function showPic(){
	$("#picDiv").toggle();
	$.ajax({
			type:"POST",
			url:"${ctx}/backstage/showPic",
			dataType:"JSON",
			success:function(data){
				var myChart = echarts.init(document.getElementById('picDiv'));
				
				option = {
				    title: {
				        text: '浏览分享次数展示'
				    },
				    tooltip: {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['浏览次数','分享朋友次数','分享朋友圈次数']
				    },
				    toolbox: {
				        feature: {
				            saveAsImage: {}
				        }
				    },
				    dataZoom:{
				    	 start: 50,
				         end: 100
				    },
				    xAxis: {
				        type: 'category',
				        boundaryGap: false,
				        data: data[0].split(',')
				    },
				    yAxis: {
				        type: 'value'
				    },
				    series: [
				        {
				            name:'浏览次数',
				            type:'line',
				            stack: '总量',
				            data:data[1].split(',')
				        },
				        {
				            name:'分享朋友次数',
				            type:'line',
				            stack: '总量',
				            data:data[2].split(',')
				        },
				        {
				            name:'分享朋友圈次数',
				            type:'line',
				            stack: '总量',
				            data:data[3].split(',')
				        }
				    ]
				};
				myChart.setOption(option);
			}
		});
}
function df(){
	var chk_value =[]; 
	$('input[name="checkbox[]"]:checked').each(function(){ 
		var info = $(this).val();
		var id = info.split("-")[0];
		var status = info.split("-")[1];
		chk_value.push(id); 
	});
	if(chk_value==''){
		layer.msg('请选择要删除的数据！');
	}else{
		layer.confirm('是否确认删除该条数据？　', {
			  btn: ['确认','取消'] //按钮
		}, function(){
			var idStr = chk_value.join(",");
			updateStatus(idStr);
		}, function(){
		});
	}
}

//删除
function updateStatus(idStr,status){
	$.ajax({
	  	url: "${ctx}/backstage/delData",
	  	dataType: 'json',
	  	data: {idStr:idStr},
	 	success: function(data){
	 		var code = data.code;
	 		var message = data.message;
	 		if(code=='200'){
	 			layer.msg(message);
	 			loadData(1);
	 		}else{
	 			layer.msg(message);
	 		}
	 	},
	 	error:function(){
 			layer.msg("系统异常，请稍候再试！");
	 	}
	});
}
function exportExcel(){
	//1秒自动关闭
	layer.load(0,{shade: 0.1,time: 1000});
	window.location.href="${ctx}/backstage/exportPartakeData?pageNumber=1&flag=1";
}

</script>