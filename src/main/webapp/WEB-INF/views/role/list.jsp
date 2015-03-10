<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
	<title></title>
    <base href="<%=basePath %>" />
	<%@include file="/common/meta.jsp"%>
	<!-- jquery -->
    <script type="text/javascript" src="<%=path%>/js/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/jquery/jquery-migrate-1.2.1.min.js"></script>
   	
   	<link rel="stylesheet" type="text/css" href="<%=path%>/css/default.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/style/css/style.css" />
   
	<!-- Ligerui -->
    <link rel="stylesheet" type="text/css" href="<%=path%>/js/ligerUI/skins/Aqua/css/ligerui-all.css" /> 
    <link rel="stylesheet" type="text/css" href="<%=path%>/js/ligerUI/skins/ligerui-icons.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/js/ligerUI/skins/Gray/css/grid.css" />
    <script type="text/javascript" src="<%=path%>/js/ligerUI/js/ligerui.min.js" ></script>
    <!-- jquery validata -->
    <script type="text/javascript" src="<%=path %>/js/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/jquery-validation/jquery.metadata.js" ></script>
	<script type="text/javascript" src="<%=path %>/js/jquery-validation/messages_cn.js" ></script>
    <script type="text/javascript" src="<%=path %>/js/jquery-validation/additional-methods.js" ></script>
    
	<script src="<%=path%>/js/config.js" type="text/javascript"></script>
	<script type="text/javascript">
	var rows =null;
		$(function () {
			$("html").css("overflowY","hidden");
			var cfg = $.extend({}, gridConfig,{
				loadOnStart:false,
                url: "<%=path%>/role/json-list.do",
                toolbar: { 
                	items: [
						{ line: true },{ text: '添加', click : _openSearch, icon: 'add' },
						{ line: true },{ text: '修改', click : _modify, icon: 'modify' }
						/**,
						{ line: true },{ text: '删除', click : _del, icon: 'delete' }
						*/
						]
				},
				columns: [
		                	{ display: 'id ', name: 'id',  width:'10%',hide:true },
		                	{ display: '角色 ', name: 'roleName',width:'50%' }
		                	
		                ],
                onSelectRow: function(rowdata, rowindex, rowDomElement){
					rows = this.getSelectedRows();},
				onUnSelectRow : function(rowdata, rowindex,rowDomElement) {
					rows = this.getSelectedRows();
				}
            });
			 window["g"] = manager = $("#grid").ligerGrid(cfg);
		});
		
		function _del(){
			if(rows == null || rows.length==0){
				$.ligerDialog.warn("请至少选择一条数据！");
				return;
			}
			$.ligerDialog.confirm('确认删除？', function (yes){
				if(yes){
					var ids ="";
					for(var i=0;i<rows.length-1;i++){
						ids+=rows[i].id;
						ids+=",";
					}
					ids+=rows[rows.length-1].id;
				$.ajax({
					url : "<%=path%>/role/role-del.do",
					data : {"ids":ids},
					type : "post",
					dataType : "json",
					cache : false,
					error : function(XMLHttpRequest, textStatus, errorThrown){
						$.ligerDialog.error("出现错误,请重试或联系管理员!");
					},
					success : function(data, textStatus){
						for(var i=0;i<rows.length;i++){
							manager.deleteRow(rows[i].__id);
						}
							
					}
				});
				}
			});
		}
		
		function _openSearch(){
			if(!window["searchDlg"]){
				window["searchDlg"] = $.ligerDialog.open({target:$("#searchBox"), title:"添加角色", width:300});
			}else{
				window["searchDlg"].show();
			}
		}
		
		
		
		function _add(){
			var roleName = $("#roleName").val();
			if(roleName == ""){
				$.ligerDialog.warn("角色名不能为空！");
				return;
			}
			$.ajax({
				url : "<%=path%>/role/role-addsave.do",
				data : {"roleName":roleName},
				type : "post",
				dataType : "json",
				cache : false,
				error : function(XMLHttpRequest, textStatus, errorThrown){
					$.ligerDialog.error("出现错误,请重试或联系管理员!");
				},
				success : function(data, textStatus){
					searchDlg.hidden();
					reloadGrid(g, {});
					$.ligerDialog.warn(data.info);
				}
			});
		}
		
		function _modify(){
		if(rows == null || rows.length==0){
			$.ligerDialog.warn("请至少选择一条数据！");
			return;
		}
		if(rows.length >1){
			$.ligerDialog.warn("每次只能修改一条数据！");
			return;
		}
		var id = "";
		for(var i = 0; i<rows.length-1; i++){
			id+=rows[i].id;
			id+=",";
		}
		id+=rows[rows.length-1].id;
		location.href="<%=path%>/role/role-edit/"+id+".do";
		
		rows = null;
		
		}
		
		
	
		</script>
</head>
<body>
<div class="form_page">
	<div class="form_group"><img src="<%=path %>/images/communication.gif" alt="" /><span>系统角色</span></div>
	<div class="form_content">
		<div id="searchBox" class="query_box hiden">
		<table class="query_table" width="250" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td class="query_title">角色名称：</td>
				<td class="query_content"><span class="inputBox"><span class="bg"><input id="roleName" type="text" style="width: 150px;" /></span></span></td>
			</tr>
			<tr>
				<td colspan="2" class="query_btn">
					<input type="submit" value="" title="确定" class="btnBox_confirm" onclick="_add()" />
					<input type="button" value="" title="取消" class="btnBox_cancel" style="margin-left: 20px;" onclick="searchDlg.hidden()" />
				</td>
			</tr>
		</table>
	</div>
	</div>
</div>
<div id="grid"></div>
	
</body>
</html>