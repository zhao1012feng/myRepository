<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script language="javascript" type="text/javascript"
	src="${ctx }/static/My97DatePickerBeta/My97DatePicker/WdatePicker.js"></script>
<div id="urHere">
	<strong>人员信息</strong>
</div>
<div class="mainBox"
	style="height: auto !important; height: 550px; min-height: 100px;">
	<h3>
		<a href="javaScript:;" class="actionBtn" onclick="exportExcel()">导出全部数据（Excel）</a>员工销量信息
	</h3>

	<input class="Wdate" placeholder="开始时间" type="text"
		onClick="WdatePicker()" id="beginDate">--- <input
		class="Wdate" placeholder="结束时间" type="text" onClick="WdatePicker()"
		id="endDate"> <input style="border: 1px solid #999"
		placeholder="分行" type="text" id="bank"> <input
		style="border: 1px solid #999" placeholder="支行" type="text"
		id="orgName"> <input class="btnGray" type="button" value="筛选"
		id="findByTime">
</div>
<div id="list"
	style="overflow: auto; min-height: 500px; margin-left: 2%; width: 96%">
	<form name="action" method="post" action="article.php?rec=action">
		<table width="100%" border="0" cellpadding="8" cellspacing="0"
			class="tableBasic">

			<tr>
				<!-- <th width="20" align="center"><input name="chkall"
							type="checkbox" id="chkall" onclick="selectcheckbox(this.form)"
							value="check"></th> -->
				<th width="20" align="center">序号</th>
				<th width="20" align="center">提交时间</th>
				<th width="20" align="center">姓名</th>
				<th width="20" align="center">分行</th>
				<th width="30" align="center">支行</th>
				<c:forEach items="${lists}" var="list">
					<th width="30" align="center" attr="${list.id}-${list.code}">${list.content}</th>
				</c:forEach>

				<%-- <c:forEach items="${ja}" var="ja" varStatus="i"> --%>
				<%-- 
						<th width="20" align="center">${ja[8].productName}</th> --%>
				<!-- <th width="20" align="center">火猴金大全套14.5克</th>
						<th width="20" align="center">火候迎新2016猴年生肖贺岁邮票金1.5克</th>
						<th width="20" align="center">火猴银大全套16克</th>
						<th width="20" align="center">2016年熊猫金币套装(PDGS封装评级特别纪念版)</th>
						<th width="20" align="center">2016年版中国熊猫普制银币PCGS封装评级满分套装</th>
						<th width="20" align="center">鸿运招财八宝珠金3克</th>
						<th width="20" align="center">顺心如意健康宝银66克</th>
						<th width="20" align="center">百岁核桃银50克*2</th>
						<th width="20" align="center">真心爱</th>
						<th width="20" align="center">六字真言转运珠念珠版金2.7克</th>
						<th width="20" align="center">朵朵金玫瑰2克含托帕石</th>
						<th width="20" align="center">鸿运金卡2克</th>
						<th width="20" align="center">鸿运招财转经筒金1.5克</th> -->
				<%-- </c:forEach> --%>
				<th width="20" align="center">其它产品</th>
				<th width="20" align="center">合计</th>
			</tr>
			<tbody id="dataTbody">

				<c:forEach items="${listContent}" var="list1" varStatus="i">
					<tr>
						<td>${i.index+1}</td>
						<c:forEach items="${list1}" var="l">
							<td align="center">${l}</td>
						</c:forEach>
					</tr>
				</c:forEach>


				<%-- <c:forEach items="${commonInfoList}" var="commonInfoList" varStatus="i">
							<tr>
								<!-- <td align="center"><input type="checkbox" name="checkbox[]" value=""></td> -->
								<td align="center">${commonInfoList.key}</td>
								<td align="center">${commonInfoList.value.ct}</td>
								<td align="center">${commonInfoList.value.name}</td>
								<td align="center">${commonInfoList.value.team}</td>
								<td align="center">${commonInfoList.value.area}</td>
								<td align="center">${commonInfoList.value.bank}</td>
								<td align="center">${commonInfoList.value.aa}</td>
								<td align="center">${commonInfoList.value.bb}</td>
								<td align="center">${commonInfoList.value.cc}</td>
								<td align="center">${commonInfoList.value.dd}</td>
								<td align="center">${commonInfoList.value.ee}</td>
								<td align="center">${commonInfoList.value.ff}</td>
								<td align="center">${commonInfoList.value.gg}</td>
								<td align="center">${commonInfoList.value.hh}</td>
								<td align="center">${commonInfoList.value.ii}</td>
								<td align="center">${commonInfoList.value.jj}</td>
								<td align="center">${commonInfoList.value.kk}</td>
								<td align="center">${commonInfoList.value.ll}</td>
								<td align="center">${commonInfoList.value.mm}</td>
								<td align="center">${commonInfoList.value.nn}</td>
								<td align="center">${commonInfoList.value.oo}</td>
								<td align="center">${commonInfoList.value.pp}</td>
								<td align="center">${commonInfoList.value.qq}</td>
								<td align="center">${commonInfoList.value.rr}</td>
								
								
							</tr>
						</c:forEach> --%>
				<%-- <tr>
							<td colspan="9">
								<div class="pager">
									总计 ${page.totalRow} 个记录，当前第${page.pageNumber}页 / 共${page.totalPage}页 | 
									<a href="javaScript:;" <c:if test="${page.firstPage==false}">onclick="loadData(1)"</c:if>>首页</a> 
									<a href="javaScript:;" <c:if test="${page.firstPage==false}">onclick="loadData(${page.pageNumber-1})"</c:if>>上一页 </a> 
									<a href="javaScript:;" <c:if test="${page.lastPage==false}">onclick="loadData(${page.pageNumber+1})"</c:if>>下一页</a> 
									<a href="javaScript:;" <c:if test="${page.lastPage==false}">onclick="loadData(${page.totalPage})"</c:if>>尾页</a>
								</div>
							</td>
						</tr> --%>
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
$("#findByTime").click(function(){
	var beginDate = $("#beginDate").val();
	var endDate = $("#endDate").val();
	var index =layer.load(0,{shade: 0.1});
	$("#dataTbody").load("${ctx}/tUser/listUserInfo #dataTbody tr",{
		beginDate:$("#beginDate").val(),
		endDate:$("#endDate").val(),
		bank:$("#bank").val(),
		orgName:$("#orgName").val(),
		flag:0
	},function(){
		layer.close(index);
	});
});
/* function loadData(pageNumber){
	$("#pageNumberId").val(pageNumber);
	var index =layer.load(0,{shade: 0.1});
	$("#dataTbody").load("${ctx}/tUser/userWinningListData #dataTbody tr",{
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
 */
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
	var beginDate = $("#beginDate").val();
	var endDate = $("#endDate").val();
	
	window.location.href="${ctx}/tUser/exportPartakeData?flag=0&beginDate="+beginDate+"&endDate="+endDate;
}

</script>
