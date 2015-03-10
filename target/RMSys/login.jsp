<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="./common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
<title>退费审批系统</title>
	<%@include file="/common/meta.jsp"%>
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/login.css" />
	<script type="text/javascript" src="<%=path%>/js/jquery/jquery.min.js"></script>
	
<script type="text/javascript">
var Signature = "";
	if(window.top != window){
		window.top.location.href = location.href;
	}
	function _check(){
		var userLogin = document.getElementById("userLogin");
		var flag = true;
		if(!userLogin.value){
			$("#txt_userLogin").text('请输入用户名');
			flag = false;
			userLogin.focus();
		}else{
			$("#txt_userLogin").text("");
		}
		
		return flag;
	}
	
	
	
</script>
</head>
<body>
<div class="main">
	<form id="loginForm" action="<%=path%>/login.do" method="post" onsubmit="return _check()">
	    <ul>
	      <li><span style="display: inline-block;width: 50px;">用户名：</span><input id="userLogin"  name="username" type="text" value="" style="width: 130px;border: 1px solid #C2D4E8;" /><span id="txt_userLogin" style="margin-left: 5px;color: red;"><c:if test="${info eq '1' }">用户名不存在！</c:if></span></li>
	      <li><span style="display: inline-block;width: 50px;">密　码：</span><input id="account.userPassword" name="password" value="" type="password" style="width: 130px;border: 1px solid #C2D4E8;"  /><span id="txt_userPassword" style="margin-left: 5px;color: red;"><c:if test="${info eq '2' }">密码错误！</c:if></span></li> 
	    </ul>
	    <p><input type="submit" value="" style="border:0px none;width:76px;height:26px;background-image: url('<%=path%>/images/button_03.jpg');" /></p>
	</form>
</div>
</body>
<script type="text/javascript">
document.getElementById("userLogin").focus();
</script>
</html>