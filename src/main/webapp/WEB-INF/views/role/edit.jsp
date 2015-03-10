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
		var count = 0;
		$(function () {
			$.metadata.setType("attr", "validate");
			$("html").css("overflowY","scroll");
			 <c:forEach items="${group}"  var="key" varStatus="sa">
			 count =${sa.index};
			 var data${sa.index } = [];
			 data${sa.index }.push({id:"0", pid:"-1", text:"${key.key}",  isexpand:true, attribute:{id:"0"} });
			 <c:forEach items="${key.value}" var="al" >
			 data${sa.index }.push({id:"${al.id}", pid:"0", text:"${al.name}",  isexpand:true, attribute:{id:"${al.id}"} ${util:contains(alist,al.authority)} });
			 </c:forEach>	 
			 window["t_${sa.index}"] = $("#group_${sa.index}").ligerTree({
					data : data${sa.index },
			 		idFieldName :'id',
	            	parentIDFieldName :'pid',
	            	nodeWidth:220
				});
			 </c:forEach> 
			
			var formCfg = $.extend({}, validateConfig,{
				submitHandler: function (form) {
					_setUserAuth();
					form.submit();
				}
			});
			var v = $("#form").validate(formCfg);
		});
		
		function _setUserAuth(){
			var ids = "";
			for(var j=0;j<=count;j++){
				var selected = window["t_" + j].getChecked();
				for (var i = 0; i < selected.length; i++){
					if(selected[i].data.attribute.id!=0)
						ids += selected[i].data.attribute.id + ",";
	            }
			}
			if(ids == ""){
				$.ligerDialog.warn('请选择权限');
				return ;
			}
			$("#ids").val(ids);
		}
		
		
		
	</script>
	
	<style type="text/css">
	
	
	.validate-error{
		background:url(<%=path%>/images/unchecked.gif) no-repeat 0px 0px;
		padding-left: 16px;
		padding-bottom: 2px;
		font-weight: bold;
		color: #EA5200;
		vertical-align: inherit;
		display: inline;
	}
	
	</style>
</head>
<body>
<form id="form" action="<%=path %>/role/role-editsave.do" method="post">
<input type="hidden" id="ids" name="ids"/>
<input type="hidden" name="id" value="${role.id }" />
<input type="hidden" name="groupId" value="${role.groupId }"/>
<input type="hidden" name="enable" value="${role.enable }" />
<div class="form_page">
	<div class="form_group">
	<div class="form_group"><img src="<%=path %>/images/communication.gif" alt="" /><span>修改角色</span></div>
	</div>
	<div class="space20"></div>
	<div class="form_content">
		<table cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td align="left">角色名：</td>
				<td><span class="inputBox"><span class="bg"><input name="roleName" value="${role.roleName }"  type="text"  validate="{required:true}" /><div></div></span></span></td>
				<td align="left"></td>
			</tr>
			
			
		</table>
	</div>
	<div class="space20"></div>
	<button   type="submit" title="确定" class="btnBox_confirm" ></button>
	<button   type="button" title="取消" class="btnBox_cancel btn_next" onclick="history.back()"></button>
	
	<div class="space20"></div>
	
	<div class="form_group"><img src="<%=path %>/images/communication.gif" alt="" /><span>角色授权</span></div>
	<div >
		<c:forEach items="${group}" varStatus="sa">
			<ul id="group_${sa.index }" style="width:210px;  float: left;"></ul>
		</c:forEach>
		<div class="clear"></div>
		
	</div>

</div>
</form>
</body>
</html>