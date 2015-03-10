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
	  		<h4 class="title">公告详细</h4>
		</header>
	<div class="content content-inner">
	
	<input type="hidden" name="id" value="${notice.id }" >
	<input type="hidden" name="createUser" value="${notice.createUser }" >
	<input type="hidden" name="createTime" value="${notice.createTime }" >
	  <div class="control-group">
	    <label class="control-label" for="title"></label>
		<div class="controls">
		   ${notice.title } 
		  <div></div>
	    </div>
	  </div>
	  
	  <div class="control-group">
	    <label class="control-label" for="content"></label>
		<div class="controls">
			<p>${notice.content }</p>
			<div align="right" >
				<p>${notice.createUser }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				<p>${notice.createTime }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
			
			</div>
	    </div>
	  </div>
	  
	</div>
	</div>
	</div>


	
</body>
</html>