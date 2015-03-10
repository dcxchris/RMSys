<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
	<base href="<%=basePath %>" />
	<%@include file="/common/meta.jsp"%>
	
	<%@include file="/common/script.jsp"%>
	
	<link type="text/css" rel="stylesheet" href="<%=path%>/css/layout3.css">
	<link type="text/css" rel="stylesheet" href="<%=path%>/css/style/css/style_btn.css">
	
	<script type="text/javascript" src="<%=path %>/js/config.js"></script>
	<script type="text/javascript">
		$(function () {
			$.metadata.setType("attr", "validate");
			var formCfg = $.extend({}, validateConfig,{
				submitHandler: function(form) { 
				if($("#form").valid()){
					$.ligerDialog.waitting('正在处理,请稍候...<br/><br/><br/><br/><br/><br/>');
					form.submit();
		    	}
		  	}});
			var v = $("#form").validate(formCfg);
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
<div  class="m-main">
	<div class="m-widget">
		<header class="header">
	  		<h4 class="title">添加公告</h4>
		</header>
	<div class="content content-inner">
	
	<form id="form" method="post" action="<%=path %>/notice/notice-addsave.do" class="form-horizontal" >
	  <input type="hidden" name="createUser" value="${loginUser.username }" >
	  <div class="control-group">
	    <label class="control-label" for="title">标题</label>
		<div class="controls">
		  <input id="title" type="text" name="title"  validate="{required:true,maxlength:30}" >
		  <div></div>
	    </div>
	  </div>
	  
	  <div class="control-group">
	    <label class="control-label" for="content">内容</label>
		<div class="controls">
		  <textarea name="content" style="height:100px;width:200px" ></textarea>
	    	<div></div>
	    </div>
	  </div>
	
	  
	  
	  <div class="control-group">
	    <div class="controls">
	      <button   type="submit" title="确定" class="btnBox_confirm" ></button>
		  <button   type="button" title="取消" class="btnBox_cancel btn_next" onclick="history.back()"></button>
	    </div>
	  </div>
	</form>
	</div>
	</div>
	</div>


	
</body>
</html>