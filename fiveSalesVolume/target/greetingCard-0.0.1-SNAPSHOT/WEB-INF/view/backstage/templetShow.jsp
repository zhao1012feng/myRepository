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
<style type="text/css">  
	input.textFont{text-align:center;padding:10px 20px;width:300px;}
	  
</style>  
<div class="mainBox"
	style="height:auto!important;height:550px;min-height:550px;">
	<div id="list">
		<form name="action" method="post" action="article.php?rec=action">
			<table width="100%" border="0" cellpadding="8" cellspacing="0"
				class="tableBasic">
					<tr>
						
						<th width="20" align="center">标题</th>
						<th width="300" align="center">内容</th>
						<th width="10" align="center">操作</th>
					</tr>
					<tbody id="dataTbody">
						<c:forEach items="${page.list}" var="templet" >
						<tr>
							<td  align="center">
							<c:if test="${templet.name=='shareTitle1'}"><input class="textFont" style="width:400px" value="标题1"/></c:if>
							<c:if test="${templet.name=='shareContent1'}"><input class="textFont" style="width:400px" value="摘要1"/></c:if>
							<c:if test="${templet.name=='shareTitle2'}"><input class="textFont" style="width:400px" value="标题2"/></c:if>
							<c:if test="${templet.name=='shareContent2'}"><input class="textFont" style="width:400px" value="摘要2"/></c:if>
							</td>
							
							<td  align="center"><input class="textFont" name="${templet.name }" style="width:800px" value="${templet.content }"/></td>
							<td><input type="button" id='${templet.name }' value="编辑" onclick="javascript:changeDisable(this,id);" /></td>
						</tr>
						</c:forEach>
						
						
						
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
	function changeDisable(but,id){
		var ss = $("input[name='"+id+"']");
		if(but.value=="编辑"){
			$(ss).attr("disabled",false);
			but.value="保存";
		}else{
			$.ajax({
				type	:	"POST",
				url		:	"${ctx}/backstage/editTemplet",
				data	:	{content:$(ss).val(),
							name	:id},
				dataType:	"json",
				success	:	function(data){
					layer.msg(data.dataResult);
				}
			});
			$(ss).attr("disabled",true);
			but.value="编辑";
		}
	}
$(function(){
	$(".textFont").attr("disabled",true);
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