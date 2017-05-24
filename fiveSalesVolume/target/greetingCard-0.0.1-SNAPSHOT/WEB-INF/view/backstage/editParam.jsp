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
	<strong>修改微信参数</strong>
</div>
<div class="mainBox"
	style="height:auto!important;height:550px;min-height:550px;">
	<div id="list">
		<form name="action" method="post" action="article.php?rec=action">
			<table width="100%" border="0" cellpadding="8" cellspacing="0"
				class="tableBasic">
					<tr>
						
						<th width="20" align="center">微信编码</th>
						<td width="20" align="center">${dataResult.appId}</td>
					</tr>
					<tbody id="dataTbody">
					<tr>
						<th align="center">微信秘钥</th>
						<td id="appSecretId" align="center">${dataResult.appSecret}</td>
						
					</tr>
					<tr>
						<th align="center">操作</th>
						<th align="center"><input id="editSecret" type="button" style="width:800px" value="修改秘钥"/></th>
						
					</tr>
					<!-- <div>
					<tr>
						<th align="center">NewAppSecret</th>
						<td align="center"><input type="text" /></td>
					</tr>
					<tr>
						<th align="center">RepeatNewSecret</th>
						<td align="center"><input type="text" /></td>
					</tr> 
					</div>-->
						<%-- <tr>
							<td colspan="10">
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
			
		</form>
	</div>
	<div class="clear"></div>
</div>

<script>
$(function(){
	$("#editSecret").click(function(){
		var password ="";
		var repeatPassword ="";
			layer.prompt({title: '输入新秘钥，并确认', formType: 0}, function(pass, index){
					password = pass;
					layer.close(index);
					layer.prompt({title: '请重复秘钥，并再次确认', formType: 0}, function(pass, index){
					repeatPassword = pass
					layer.close(index);
					   /*  layer.msg('演示完毕！您的口令：'+ pass +'您最后写下了：'+text); */
					if(password==repeatPassword){
						$.ajax({
							type	:	"POST",
							url		:	"${ctx}/backstage/updatePassword",
							dataType:	"json",
							data	:	{"password"	:	password,
										"repeatPassword"	:	repeatPassword,
										},
							success	:	function(data){
								layer.msg(data.result);
								$("#appSecretId").html(password);
							}
						});
					}else{
						layer.msg('两次输入密码不一致，请重新输入！');
					}
				});
			});
	});
	
});

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