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

	<script type="text/javascript" src="<%=path%>/js/mossle/js/WorkCalendar.js"></script>
    <script type="text/javascript">
$(function() {
	var workCalendar = new WorkCalendar(${param.year});
	workCalendar.render('#m-main');
	workCalendar.activeByWeek(${weeks});
	workCalendar.markHolidays(${holidays});
	workCalendar.markWorkdays(${workdays});
	workCalendar.markExtrdays(${extrdays});
});
    </script>
  </head>

  <body>

    <div class="row-fluid">

	  <!-- start of main -->
      <div id="m-main" >

      </div>
	  <!-- end of main -->
	</div>

  </body>

</html>
