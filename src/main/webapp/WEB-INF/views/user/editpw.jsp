<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title></title>
	<link rel="stylesheet" type="text/css" href="<%=path%>/js/ligerUI/skins/Aqua/css/ligerui-all.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/default.css" />
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/style/css/style.css" />
	<script type="text/javascript" src="<%=path%>/js/jquery/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/jquery-validation/jquery.metadata.js" ></script>
	<script type="text/javascript" src="<%=path %>/js/jquery-validation/messages_cn.js" ></script>
    
	<script type="text/javascript" src="<%=path%>/js/config.js"></script>
	<script type="text/javascript">
		$(function () {
			$.metadata.setType("attr", "validate");
			var formCfg = $.extend({}, validateConfig,{});
			var v = $("form").validate(formCfg);
			
		});
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
<form action="<%=path %>/user/editsavepw_.do" method="post">
<div class="form_page">
	<div class="form_group"><img src="<%=path %>/images/communication.gif" alt="" /><span>修改密码</span></div>
	<div class="form_content">
		<table cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td width="80" align="left">新&nbsp;密&nbsp;码：</td>
				<td width="160"><span class="inputBox"><span class="bg"><input  name="password" type="password" style="width: 150px;" value="" validate="{required:true,maxlength:20}" /><div></div></span></span></td>
				<td align="left"></td>
			</tr>
		</table>
	</div>
	<div class="space20"></div>
	<button  type="submit" title="确定" class="btnBox_confirm"></button>
</div>
</form>
</body>
</html>