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
			
			$("html").css("overflowY","scroll");
			 var data  = [];
			 <c:forEach items="${list}" var="g" >
			 data.push({id:"${g.id}", pid:"${g.pid}", text:"${g.groupName}",  isexpand:true, attribute:{id:"${g.id}"}  });
			 </c:forEach>	 
			 window["t"] = $("#tree").ligerTree({
					data : data,
			 		idFieldName :'id',
	            	parentIDFieldName :'pid',
	            	nodeWidth:150,
	            	checkbox :false
				});
			
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
	
	.content-inner-left30{
		float: left;
		width:30%;
		margin-top:5px
	}
	.content-inner-left50{
		float: left;
		width:60%;
		margin-top:5px
	}
	</style>
	
</head>
<body>
<div  class="m-main">
	<div class="m-widget">
		<header class="header">
	  		<h4 class="title">添加组织机构</h4>
		</header>
	<div class="content">
	<div class="content-inner content-inner-left30"   >
		<ul id="tree" style="width:150px;  float: left;"></ul>
	
	</div>
	<div class="content-inner content-inner-left50" >
	<c:if test="${!empty message}">
	    <div class="alert m-alert-success fade in">
            <button type="button" class="close" data-dismiss="alert">×</button>
            <strong>${message }</strong>
         </div>
	</c:if>
	<form id="form" method="post" action="<%=path %>/group/group-addsave.do" class="form-horizontal" >
	
	  <div class="control-group">
	    <label class="control-label" for="groupName">组织名</label>
		<div class="controls">
		  <input id="groupName" type="text" name="groupName"  validate="{required:true,maxlength:15}" >
		  <div></div>
	    </div>
	  </div>
	  
	  
	  
	  <div class="control-group">
	    <label class="control-label" for="pid">上级组织</label>
		<div class="controls">
		  <select id="pid" name="pid" validate="{required:true}" >
		  <option value="">请选择</option>
		  	<c:forEach items="${list}" var="g" >
		  		<option value="${g.id }" >${g.groupName }</option>
		  	</c:forEach>
		  </select>
		  <div></div>
	    </div>
	  </div>
	  
	  <div class="control-group">
	    <label class="control-label" for="type">类型</label>
		<div class="controls">
		  <select id="type" name="type" validate="{required:true}">
		  	<option value="">请选择</option>
		  	<c:forEach items="${dlist}" var="d" >
		  		<option value="${d.value }" >${d.name }</option>
		  	</c:forEach>
		  </select>
		  <div></div>
	    </div>
	  </div>
	  
	  <div class="control-group">
	    <div class="controls">
	      <button   type="submit" title="确定" class="btnBox_confirm" ></button>
		  <button   type="button" title="取消" class="btnBox_cancel"  onclick="history.back()"></button>
	    </div>
	  </div>
	</form>
	</div>
	</div>
	</div>
	</div>


	
</body>
</html>