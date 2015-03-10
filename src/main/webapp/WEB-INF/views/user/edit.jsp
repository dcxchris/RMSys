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
	  		<h4 class="title">修改用户</h4>
		</header>
	<div class="content content-inner">
	
	<form id="form" method="post" action="<%=path %>/user/user-editsave.do" class="form-horizontal" >
	<input type="hidden" name="id" value="${user.id }" >
	<input type="hidden" name="flag" value="1"  >
	  <div class="control-group">
	    <label class="control-label" for="username">用户名</label>
		<div class="controls">
		  <input id="username" type="text" name="username" value="${user.username }"  validate="{required:true,maxlength:10}" >
		  <div></div>
	    </div>
	  </div>
	  
	  <div class="control-group">
	    <label class="control-label" for="password">密码</label>
		<div class="controls">
		  <input id="password" type="password" name="password" value="${pw }" validate="{required:true}" >
		  <div></div>
	    </div>
	  </div>
	  <div class="control-group">
	    <label class="control-label" for="tel">手机号</label>
		<div class="controls">
		  <input id="tel" type="text" name="tel" value="${user.tel }" validate="{required:true}">
		  <div></div>
	    </div>
	  </div>
	  <div class="control-group">
	    <label class="control-label" for="location">地区</label>
		<div class="controls">
		  <input id="location" type="text" name="location" value="${user.location }" validate="{required:true}">
		  <div></div>
	    </div>
	  </div>
	  <div class="control-group">
	    <label class="control-label" for="enable">启用</label>
		<div class="controls">
		  <input id="enable" type="checkbox" name="enable" value="0" <c:if test="${user.enable == '0' }"> checked</c:if> >
	    </div>
	  </div>
	
	  
	  <div class="control-group">
	    <label class="control-label" for="roleid">角色</label>
		<div class="controls">
		  <select name="role.id" >
		  <option>请选择</option>
		  	<c:forEach items="${rlist}" var="r" >
		  		<option value="${r.id }" <c:if test="${user.role.id == r.id }"> selected</c:if> >${r.roleName }</option>
		  	</c:forEach>
		  </select>
		  <div></div>
	    </div>
	  </div>
	  
	  <div class="control-group">
	    <label class="control-label" for="groupid">部门</label>
		<div class="controls">
		  <select name="group.id">
		  	<option>请选择</option>
		  	<c:forEach items="${glist}" var="g" >
		  		<option value="${g.id }" <c:if test="${user.group.id == g.id }"> selected</c:if> >${g.groupName }</option>
		  	</c:forEach>
		  </select>
		  <div></div>
	    </div>
	  </div>
	  
	  <div class="control-group">
	    <div class="controls">
	      <button name="submitBtn" type="submit" title="确定" class="btnBox_confirm" ></button>
		  <button name="cancelBtn" type="button" title="取消" class="btnBox_cancel btn_next" onclick="history.back()"></button>
	    </div>
	  </div>
	</form>
	</div>
	</div>
	</div>


	
</body>
</html>