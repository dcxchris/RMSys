<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<c:if test="${ro.url!=null}"><meta http-equiv="Refresh" content="2; url=<%=path %>${ro.url}" /></c:if>
	<title>操作成功</title>
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/default.css" />
</head>
<body class="index-bg">
	<div style="height: 100px;"></div>
	<div class="messagebox successbox">
		<div class="message">
			<div class="content">
				<div class="text">
					<p> ${ro.info}</p>
					<c:if test="${ro.url!=null}"><p>2秒后自动跳转 <a href="<%=path %>${ro.url}  ">跳转</a></p></c:if>
				</div>
			</div>
		</div>
	</div>
	<div class="error-footer">Copyright © 2013-2014        技术支持 三峡大学 三峡大学 </div>
</body>
</html>