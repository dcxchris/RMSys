<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
	<title>退费审批系统</title>
	<%@include file="/common/meta.jsp"%>
	<%@include file="/common/script.jsp"%>
	<style type="text/css">
	
	html,body{
		width:100%;
		height:100%;
	}
	body{font-size: 14px; padding:0px; margin:0;   overflow:hidden;}  
	.navbar{
		margin-bottom:1px;
	}
	</style>
	<script type="text/javascript">
	if(window.top != window){
		window.top.location.href = location.href;
	}
	$(function() {
		$('#menu a').click(function() {
	        $('#menu .active').removeClass('active');
	        $(this).parents('li').addClass('active');
	       	$('#mainFrame').attr('src', $(this).attr('rel'));
	    });
	});
	
	
	function editpw(){
		$('#mainFrame').attr('src', '<%=path%>/user/editpw_.do');
	}
	
	</script>
	
</head>
<body>
	<%@include file="/common/menu.jsp"%>
	
	<div id="center" style="width:100%;height:93%">
        <iframe id="mainFrame" style="width:100%;height:100%" src="<%=path %>/portlet_.do" frameborder="0"></iframe>
    </div>
    
</body>
</html>
