<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<div id="urHere">
	<strong>参与列表</strong>
</div>
<div class="mainBox"
	style="height:auto!important;height:550px;min-height:550px;">
	<h3>
		<a href="javaScript:;" class="actionBtn" onclick="exportExcel()">导出全部数据（Excel）</a>用户中奖记录列表
	</h3>
	<div class="filter">
		<!-- <form method="post" id="queryForm">
			状态：
			<select name="status" id="statusId" onchange="loadData(1)">
				<option value="1" selected="selected">兑奖中</option>
				<option value="0">未兑奖</option>
				<option value="2">已兑付</option>
				<option value="3">无效</option>
				<option value="4">过期</option>
				<option value="">全部</option>
			</select> 
			奖项：
			<select name="fkPrize" id="fkPrizeId" onchange="loadData(1)">
				<option value="" selected="selected">全部</option>
				<option value="1">一等奖</option>
				<option value="2">二等奖</option>
				<option value="3">三等奖二元</option>
				<option value="4">三等奖一元</option>
			</select> 
			姓名：<input name="userName" id="userNameId" type="text" class="inpMain" value="" >
			手机：<input name="iphone" id="iphoneId" type="text" class="inpMain" value="">
			兑奖码：<input name="id" id="djmQueryId" type="text" class="inpMain" value="">
			<input type="hidden" name="pageNumber" id="pageNumberId"/>
			<input name="submit" class="btnGray" type="button" value="筛选" onclick="loadData(1)">
		</form> -->

	</div>
	<div id="list">
		<form name="action" method="post" action="article.php?rec=action">
			<table width="100%" border="0" cellpadding="8" cellspacing="0"
				class="tableBasic">
			
					<tr>
						<th width="20" align="center"><input name="chkall"
							type="checkbox" id="chkall" onclick="selectcheckbox(this.form)"
							value="check"></th>
						<th width="20" align="center">制作用户</th>
						<th width="30" align="center">制作时间</th>
						<th width="20" align="center">制作内容</th>
						<th width="20" align="center">接收数据</th>
					</tr>
					<tbody id="dataTbody">
						<c:forEach items="${page.list}" var="userWinning" varStatus="i">
							<tr>
								<td align="center"><input type="checkbox" name="checkbox[]" value="${userWinning.id}-${userWinning.status}"></td>
								<td align="center">${userWinning.userName}</td>
								<td align="center"><fmt:formatDate value="${userWinning.addTime}" type="both"/></td>
								<td align="center">${userWinning.iphone}</td>
								<td align="center">${userWinning.address}</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="9">
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
			<!-- <div class="action">
				<input class="btn" type="button" value="兑付" onclick="df()"/> 
				<input class="btn" type="button" value="拒绝兑付" onclick="jjdf()"/>
			</div> -->
		</form>
	</div>
	<div class="clear"></div>
	
</div>

<script>
function loadData(pageNumber){
	$("#pageNumberId").val(pageNumber);
	var index =layer.load(0,{shade: 0.1});
	$("#dataTbody").load("${ctx}/backstage/userWinningListData #dataTbody tr",{
		pageNumber:pageNumber,
		userName:$("#userNameId").val(),
		id:$("#djmQueryId").val(),
		iphone:$("#iphoneId").val(),
		status:$("#statusId").val(),
		fkPrizeId:$("#fkPrizeId").val()
	},function(){
		layer.close(index);
	});
}

$(function(){
	loadData(1);
});

//兑付
function df(){
	var chk_value =[]; 
	$('input[name="checkbox[]"]:checked').each(function(){ 
		var info = $(this).val();
		var id = info.split("-")[0];
		var status = info.split("-")[1];
		if(status=='1'){
			chk_value.push(id); 
		}
	});
	if(chk_value==''){
		layer.msg('请选择兑奖中的数据！');
	}else{
		layer.confirm('是否确认修改【状态为兑奖中的'+chk_value.length+'条】数据为兑换　', {
			  btn: ['确认修改','取消'] //按钮
		}, function(){
			var idStr = chk_value.join(",");
			updateStatus(idStr,2);
		}, function(){
		});
	}
}

//修改状态
function updateStatus(idStr,status){
	$.ajax({
	  	url: "${ctx}/backstage/updateUserStatus",
	  	dataType: 'json',
	  	data: {idStr:idStr,status:status},
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

//拒绝兑付
function jjdf(){
	var chk_value =[]; 
	$('input[name="checkbox[]"]:checked').each(function(){ 
		var info = $(this).val();
		var id = info.split("-")[0];
		var status = info.split("-")[1];
		if(status=='1'){
			chk_value.push(id); 
		}
	});
	if(chk_value==''){
		layer.msg('请选择兑奖中的数据！');
	}else{
		layer.confirm('是否确认修改【状态为兑奖中的'+chk_value.length+'条】数据为拒绝兑付　', {
			  btn: ['确认修改','取消'] //按钮
		}, function(){
			var idStr = chk_value.join(",");
			updateStatus(idStr,3);
		}, function(){
		});
	}
}


function exportExcel(){
	//1秒自动关闭
	layer.load(0,{shade: 0.1,time: 1000});
	window.location.href="${ctx}/backstage/exportExcelUserWinningData?pageNumber=1";
}

</script>