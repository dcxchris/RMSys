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
	<script type="text/javascript" src="<%=path%>/js/ajaxupload/ajaxfileupload.js"></script>
	<script type="text/javascript" src="<%=path%>/js/config.js"></script>
	<script type="text/javascript" src="<%=path%>/js/dict.js"></script>
	<script type="text/javascript">
		$(function () {
			$.metadata.setType("attr", "validate");
			var formCfg = $.extend({}, validateConfig,{
				submitHandler: function(form) { 
				if($("#form").valid()){
					getTels();
					$.ligerDialog.waitting('正在处理,请稍候...<br/><br/><br/><br/><br/><br/>');
					form.submit();
		    	}
		  	}});
			var v = $("#form").validate(formCfg);
			
		});
		var count=1;
		function addrow(){
			var $div = $("#tel"+count);
			count++;
			$div.after("<div id='tel"+count+"'  class='controls'><input id='phoneNum"+count+"' type='text'  validate='{required:true,maxlength:11}' onblur='checkPhoneNum()' ><div></div></div>");
			

		}
		
		function getTels(){
			var result = "";
			for(var i=2;i<count;i++){
				result += $("#phoneNum"+i).val();
				result +=",";
			}
			result += $("#phoneNum"+i).val();
			$("#addtels").val(result);
		}
		
		
		function ajaxFileUpload()
	    {
		 	
			var file = $("#file")[0];
			var filePath = file.value;
			var fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
			var suffix = filePath.substring(filePath.lastIndexOf(".") + 1);
			var rgx = /(jpg|txt|xls|doc)/i;
			if (rgx.test(suffix)) {
	        $.ajaxFileUpload
	        (
	            {
	                url:"<%=path%>/record/fileUpLoad_.do", 
	                type:'post',
	                secureuri:false,
	                fileElementId:'file',
	                data:null,
	                dataType: 'json',
	                success: function (data, status)
	                {	fileName = data.info;
	                	$("#remark").val($("#remark2").val());
	        		 	$("#mediaPath").val(fileName);
	                	$("#alert").show();
	                	
	                	var $div = $("#fujian");
	                	$div.after("<div id='fujian1' class='control-group'><label class='control-label' ></label><div   class='controls'><strong>"+fileName+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong><a href='javascript:void(0)' onclick='delfile()' >删除</a></div></div>");
		        		
	        			var $div = $("#beizhu");
	        			$div.after("<div id='fujian2' class='control-group'><label class='control-label' ></label><div   class='controls'><strong>"+fileName+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong><a href='javascript:void(0)' onclick='delfile()' >删除</a></div></div>");
	        		
	        			
	                },
	                error: function (data, status, e)
	                {
	                	$.ligerDialog.error("上传失败，请联系管理员！");
	                }
	            }
	        );

	    	}else{
	    		$.ligerDialog.error("只能上传图片(.jpg) ，word(.doc) ，Excel（.xsl）,压缩文件（.zip） ");
	    	}
	    }
		
		function delfile(){
			$("#file").val();
			$("#remark").val("");
		 	$("#mediaPath").val("");
			$("#fujian1").remove();
			$("#fujian2").remove();
			
		}
		
		
		function checkPhoneNum(){
			var phoneNum = $("#phoneNum").val();
			$.ajax({
				url : "<%=path%>/record/checkPhoneNum_.do",
				data : {"phoneNum":phoneNum},
				type : "post",
				dataType : "json",
				cache : false,
				error : function(XMLHttpRequest, textStatus, errorThrown){
					$.ligerDialog.error("出现错误,请重试或联系管理员!");
				},
				success : function(data, textStatus){
					if(data.code =="1"){
						$("#checktable tr:not(:first)").remove();
						for(var i=0;i<data.obj.length;i++){
							var $tr = $("#checktable tr:last");
							$tr.after("<tr><td>"+data.obj[i].phoneNum+"</td><td>"+getDict("WORKTYPE",data.obj[i].workType)+"</td><td>"+getDict("NETTYPE",data.obj[i].netType)+"</td><td>"+data.obj[i].money+"</td><td>"+data.obj[i].createtime+"</td><td>"+data.obj[i].userName+"</td></tr>");
							
						}
						$("#check").modal();
						$("#statusFlag").val("1");
					}else{
						$("#statusFlag").val("2");
					}
				}
			});
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
<div  class="m-main">
	<div class="m-widget">
		<header class="header">
	  		<h4 class="title">申请退费</h4>
		</header>
	<div class="content content-inner">
	
	<form id="form" method="post" action="<%=path %>/record/apply-addsave_.do" class="form-horizontal" >
	<input type="hidden" name="addtels" id="addtels" value="" >
	<input type="hidden" name="mediaPath" id="mediaPath" >
	<input type="hidden" name="remark" id="remark" >
	<input type="hidden" name="statusFlag" id="statusFlag" value="2">
	  <div class="control-group">
	    <label class="control-label" for="recordNum">工单号</label>
		<div class="controls">
		  <input id="recordNum" type="text" name="recordNum" value="${recordNum }"  readonly >
		  <div></div>
	    </div>
	  </div>
	  
	  <div class="control-group">
	    <label class="control-label" for="phoneNum">电话号</label>
		<div id="tel1" class="controls">
		  <input id="phoneNum" type="text" name="phoneNum" validate="{required:true,maxlength:11}" onblur="checkPhoneNum()" >
		  <div></div>
	     <a href="javascript:void(0)" onclick="addrow()" role="button" class="btn btn-small  btn-primary" >追加</a>
	    </div>
	    
	  </div>
	  <div class="control-group">
	    <label class="control-label" for="groupId">审核部门</label>
		<div class="controls">
		  <select name="groupId" validate="{required:true}">
		  	<option value="">请选择</option>
		  	<c:forEach items="${glist}" var="g" >
		  		<option value="${g.id }" >${g.groupName }</option>
		  	</c:forEach>
		  </select>
		  <div></div>
	    </div>
	  </div>
	  <div class="control-group">
	    <label class="control-label" for="workType">业务类型</label>
		<div class="controls">
		  <select name="workType" validate="{required:true}">
		  	<option value="">请选择</option>
		  	<c:forEach items="${wlist}" var="w" >
		  		<option value="${w.value }" >${w.name }</option>
		  	</c:forEach>
		  </select>
		  <div></div>
	    </div>
	  </div>
	  <div class="control-group">
	    <label class="control-label" for="netType">网别</label>
		<div class="controls">
		  <input id="netType" type="radio" name="netType" value="1" ><strong>&nbsp;&nbsp;&nbsp;2G&nbsp;&nbsp;&nbsp;</strong>
		  <input id="netType" type="radio" name="netType" value="2" checked ><strong>&nbsp;&nbsp;&nbsp;3G&nbsp;&nbsp;&nbsp;</strong>
		  <input id="netType" type="radio" name="netType" value="3"  ><strong>&nbsp;&nbsp;&nbsp;4G&nbsp;&nbsp;&nbsp;</strong>
		   <input id="netType" type="radio" name="netType" value="4" ><strong>&nbsp;&nbsp;&nbsp;数固&nbsp;&nbsp;&nbsp;</strong>
		  <div></div>
	    </div>
	  </div>
	  <div class="control-group">
	    <label class="control-label" for="reason">退费原因</label>
		<div class="controls">
		  <textarea name="reason" style="height:100px;width:200px" ></textarea>
	    	<div></div>
	    </div>
	  </div>
	
	  
	  <div class="control-group">
	    <label class="control-label" for="money">申请金额</label>
		<div class="controls">
		  <input id="money" type="text" name="money" validate="{required:true,number:true}" >
		  <div></div>
	    </div>
	  </div>
	  
	  <div id="fujian" class="control-group">
	    <label class="control-label" for="mediaPath"></label>
		<div class="controls">
		  <a href="#upload" role="button" class="btn btn-large  btn-primary" data-toggle="modal">上传附件</a>
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
	
	
	
	<div id="upload" class="modal hide fade" style="display: none;" aria-hidden="true">
		<div id="alert" class="alert alert-success hide" >
		<button type="button" class="close" data-dismiss="alert">x</button>
	  		<strong> 上传成功 !</strong>
		 
		</div>
		<div class="modal-header">
			<div class="modal-header">
				<button class="close" aria-hidden="true" data-dismiss="modal" type="button" >×</button>
				<h3>上传附件</h3>
			</div>
		</div>
		<div class="modal-body">
		  <div class="control-group">
		    <label class="control-label" for="mediaPath">上传附件</label>
			<div class="controls">
			  <input type="file" name="file" id="file" />
		    </div>
		  </div>
		  <div id="beizhu" class="control-group">
		    <label class="control-label" for="mediaPath">备注</label>
			<div class="controls">
			  <input type="text" name="mediaPath" id="remark2"/>
		    </div>
		  </div>
		  <div class="control-group">
		    <label class="control-label" for="mediaPath"></label>
			<div class="controls">
			  <button type="button" class="btn btn-small  btn-primary" onclick="ajaxFileUpload()" >确定</button>
		    </div>
		  </div>
		<div class="alert alert-info">
            <strong>注意！</strong>
           		只能上传图片(.jpg) ，word(.doc) ，Excel（.xsl）,压缩文件（.zip） 
          </div>	
		</div>
		<div class="modal-footer">
			
			<a id="upload_close" class="btn" data-dismiss="modal" href="#"   >关闭</a>
		</div>
	</div>
	
	
	<div id="check" class="modal hide fade" style="display: none;" aria-hidden="true">
		
		<div class="modal-header">
			<div class="modal-header">
				<button class="close" aria-hidden="true" data-dismiss="modal" type="button" >×</button>
				<h3>近两个月重复的记录</h3>
			</div>
		</div>
		<div class="modal-body">
		  	<table id="checktable"  class="table table-striped table-hover">
			<thead>
				<tr>
				<th>电话</th>
				<th>业务类型</th>
				<th>网别</th>
				<th>金额</th>
				<th>日期</th>
				<th>退费员</th>
				</tr>
			</thead>
			<tbody>
				<tr></tr>
				
			</tbody>
			</table>
		</div>
		<div class="modal-footer">
			<a id="check_close" class="btn" data-dismiss="modal" href="#"   >关闭</a>
		</div>
	</div>

	
</body>
</html>