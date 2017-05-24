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
	<strong>用户参与列表</strong>
</div>
<div class="mainBox"
	style="height:auto!important;height:550px;min-height:550px;">
	<h3>
		<a href="javaScript:;" class="actionBtn" onclick="exportExcel()">导出全部数据（Excel）</a>用户参与列表
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
			<table width="100%" border="0" cellpadding="8" cellspacing="0"
				class="tableBasic">
					<tr>
						
						<th width="20" align="center">制作用户</th>
						<th width="20" align="center">制作时间</th>
						<th width="20" align="center">制作内容</th>
						<th width="50" align="center">接收用户</th>
					</tr>
					<tbody id="dataTbody">
						<c:forEach items="${page.list}" var="partake" >
							<tr>
								<td align="center">${partake.producer}</td>
								<td align="center">${partake.buildTime}
								</td>
								<%-- <td align="center"><fmt:formatDate value="${partake.build_time}" type="both"/></td> --%>
								<td align="center">${partake.greetings}</td>
								<td align="center">${partake.recipient}</td>
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
	<div class="clear"></div>
</div>

<script>
function loadData(pageNumber){
	var index =layer.load(0,{shade: 0.1});
	$("#dataTbody").load("${ctx}/backstage/listPartakeInfo #dataTbody tr",{
		pageNumber:pageNumber,
		producer:$("#producer").val(),
		greetings:$("#greetings").val(),
		flag:0,
	},function(){
		layer.close(index);
	});
}


function exportExcel(){
	//1秒自动关闭
	layer.load(0,{shade: 0.1,time: 1000});
	window.location.href="${ctx}/backstage/exportPartakeData?pageNumber=1&flag=0";
}

</script>