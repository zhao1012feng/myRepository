/*全选按钮的class属性为selectAll，待选择项的checkbox的class属性为selectBox*/
//获得要删除的id
//var deleFlag = false;
function getDeleteIds() {
	var ids = new Array();
	var deleteBox = $(".selectBox");
	var i = 0;
	deleteBox.each(function() {
		if ($(this).attr("checked")) {
			ids[i] = $(this).val();
			i++;
		}
	});
	return ids;
}
// 提交验证
/**
 * url 请求路径 totalRootCode 权限总和值
 */
function selectSubmit(url, title) {
	// 获得待删除的id
	var ids = getDeleteIds();
	if (ids.length > 0) {
		if (title == null || title == "" || title.length == 0) {
			if (url.indexOf("?") > 0) {
				window.location.href = url + "&ids=" + ids + "&";
			} else {
				window.location.href = url + "?ids=" + ids + "&";
			}
		} else {
			if (confirm(title)) {
				if (url.indexOf("?") > 0) {
					window.location.href = url + "&ids=" + ids + "&";
				} else {
					window.location.href = url + "?ids=" + ids + "&";
				}
			}
		}
	} else {
		alert("未选择任何数据");
	}
}
// 全选
function noSelectAll() {
	if ($(".selectAll").attr("checked")) {
		$(".selectBox").each(function() {
			$(this).attr("checked", true);
		});
	} else {
		$(".selectBox").each(function() {
			$(this).attr("checked", false);
		});
	}
}

function checkSelectAll(obj) {
	var count = 0, countTrue = 0, countFalse = 0;
	$(".selectBox").each(function() {
		count++;
		if ($(this).attr("checked")) {
			countTrue++;
		} else {
			countFalse++;
			$(".selectAll").attr("checked", true);
		}
	});
	if (count == countTrue) {
		$(".selectAll").attr("checked", true);
	} else {
		$(".selectAll").attr("checked", false);
	}
}
/**
 * 页面点击修改的时候通过异步加载的方式获得对象值
 */
function editClicks() {
	$("a[class*='btn edit']")
			.on(
					'click',
					function() {
						// 1.$.ajax带json数据的异步请求
						var thisObj = $(this);
						var url = thisObj.attr("data-url");
						var aj = $
								.ajax({
									url : url,// 跳转到 action
									type : 'get',
									dataType : 'json',
									success : function(data) {
										if (data.msg == "true") {
											var json = eval("("+data.data+")");
											 //console.info(json);
											var prefix = data.prefix; // 获得前缀
											var ids = data.ids;
											var fkId = data.fkId; 
											$("#ids","#myModal1").val(ids);
												if($("select","#myModal1")){
												$("select","#myModal1").val(fkId);
											}
											
											for ( var o in json) {
												//遍历所有的输入框
												$("input", "#myModal1").each(function() {
													if ((prefix + o) == this.name) {
														$(this).attr("value",json[o]);	
													}
												});
												//遍历所有的textarea
												if($("textarea","#myModal1")){
													$("textarea","#myModal1").each(function(){
														if ((prefix + o) == this.name) {
															$(this).attr("value",json[o]);	
														}
													});
												}
												//遍历所有的select
												if($("select","#myModal1")){
													$("select","#myModal1").each(function(){
														if ((prefix + o) == this.name) {
														$(this).attr("value",json[o]);	
													}
													if(o=="fatherId"){
														var id = json[o];
														console.log("父级网点id:"+o+"="+id);
														/*定位专用*/
														getPos( id,"netWorkManage2");
													}
												});	
												} 
											}
										$("input[name='ids']","#myModal1").attr("value",ids);
										
											if($("#beginDate2")){
												$("#beginDate2").val(data.startTime);
												$("#endDate2").val(data.endTime);
											}
											if($("#url1")){		
												KindEditor.html("#url1",data.url);
											}
											if($("#thuPath1")){
												KindEditor.html("#thuPath1",data.thuPath);
											}
											if($("#path1")){	
												KindEditor.html("#path1",data.path);
											}
											// $("#myModal1").show();
											//console.info($("#parentId1").val());
											$("#myModal1").modal('show');
										} else {
											alert("获取数据失败");
										}
									},
									error : function() {
										alert("异常！1111");
									}
								});
					});
}
function resetClicks1() {
	$("a[class*='updateBtn']")
			.on(
					'click',
					function() {
						// 1.$.ajax带json数据的异步请求
						var thisObj = $(this);
						var url = thisObj.attr("data-url");
						var aj = $
								.ajax({
									url : url,// 跳转到 action
									type : 'get',
									dataType : 'json',
									success : function(data) {
										if (data.msg == "true") {
											var json = eval("("+data.data+")");
											 //console.info(json);
											var prefix = data.prefix; // 获得前缀
											var ids = data.ids;   //主键
											var pay = data.payCode;   //支付密码
											for ( var o in json) {
												//遍历所有的输入框
												$("input", "#myModal1").each(function() {
													if ((prefix + o) == this.name) {
														$(this).attr("value",json[o]);	
													}
												});
											}
										$("input[name='ids']","#myModal1").attr("value",ids);
										$("input[id='paymentCode1']","#myModal1").attr("value",pay);
										$("input[name='node.paymentCode']","#myModal1").attr("value","");
											$("#myModal1").modal('show');
										} else {
											alert("获取数据失败");
										}
									},
									error : function() {
										alert("异常！");
									}
								});
					});
}
//重置密码调用的方法
function resetClicks() {
	$("a[class*='resetBtn']")
			.on(
					'click',
					function() {
						// 1.$.ajax带json数据的异步请求
						var thisObj = $(this);
						var url = thisObj.attr("data-url");
						var aj = $
								.ajax({
									url : url,// 跳转到 action
									type : 'get',
									dataType : 'json',
									success : function(data) {
										if (data.msg == "true") {
											var json = eval("("+data.data+")");
											 //console.info(json);
											var prefix = data.prefix; // 获得前缀
											var ids = data.ids;
										
											for (var o in json) {
												//遍历所有的输入框
												$("input", "#myModal2").each(function(){
													if ((prefix + o) == this.name) {
														$(this).attr("value", json[o]);
													}
												});
											}
										$("input[name='ids']","#myModal2").attr("value",ids);
											$("input[name='node.userRealPassword']","#myModal2").attr("value","");
											$("input[name='node.paymentCode']","#myModal2").attr("value","");
											$("#myModal2").modal('show');
										} else {
											alert("获取数据失败");
										}
									},
									error : function() {
										alert("异常！");
									}
								});
					});
}
	function addClick(){
		$('#myModal').on('shown.bs.modal', function (e) {
			
			$('form','#myModal')[0].reset();
				selectCont(classs)
			
			}).modal('toggle');
		}
