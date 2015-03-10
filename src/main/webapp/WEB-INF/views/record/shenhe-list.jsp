<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
	<title></title>
    <base href="<%=basePath %>" />
	<%@include file="/common/meta.jsp"%>
	<!-- jquery -->
    <script type="text/javascript" src="<%=path%>/js/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/jquery/jquery-migrate-1.2.1.min.js"></script>
   	
   	
    
    <!-- datepicker -->
    <link type="text/css" rel="stylesheet" href="<%=path%>/js/bootstrap-datepicker/datepicker.css">
    <script type="text/javascript" src="<%=path%>/js/bootstrap-datepicker/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="<%=path%>/js/bootstrap-datepicker/locales/bootstrap-datepicker.zh_CN.js"></script>
   
	<!-- Ligerui -->
    <link rel="stylesheet" type="text/css" href="<%=path%>/js/ligerUI/skins/Aqua/css/ligerui-all.css" /> 
    <link rel="stylesheet" type="text/css" href="<%=path%>/js/ligerUI/skins/ligerui-icons.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/js/ligerUI/skins/Gray/css/grid.css" />
    <script type="text/javascript" src="<%=path%>/js/ligerUI/js/ligerui.min.js" ></script>
    <script type="text/javascript" src="<%=path %>/js/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/jquery-validation/jquery.metadata.js" ></script>
	<script type="text/javascript" src="<%=path %>/js/jquery-validation/messages_cn.js" ></script>
	
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/default.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/style/css/style.css" />
	
	<script src="<%=path%>/js/config.js" type="text/javascript"></script>
	<script src="<%=path%>/js/dict.js" type="text/javascript"></script>
	<script type="text/javascript">
	var rows =null;
		$(function () {
			$("#time1,#time2").datepicker({
				language: 'zh_CN',
				format: 'yyyy-mm-dd',
		        autoclose: true
			});
			
		
			
			 var groupData = [];
			 <c:forEach items="${glist}" var="g">
			 	groupData.push({ groupName: "${g.id}", text: "${g.groupName}" })
			 </c:forEach>
			 	
		 	var workData = [];
			 <c:forEach items="${wlist}" var="w">
			 	workData.push({ workType: "${w.value}", text: "${w.name}" })
			 </c:forEach>
				 	
		 	var netData = [];
			 <c:forEach items="${nlist}" var="n">
			 	netData.push({ netType: "${n.value}", text: "${n.name}" })
			 </c:forEach>
			
			$("html").css("overflowY","hidden");
			var cfg = $.extend({}, gridConfig,{
				loadOnStart:false,
                url: "<%=path%>/record/shenhe-list-json_.do",
                enabledEdit: true,
                clickToEdit: true, 
               // detail:{ onShowDetail: f_onShowDetail,height:'50' },
                toolbar: { 
                	items: [
						<security:authorize ifAnyGranted="AUTH_0305">
						{ line: true },{ text: '同意', click : agree, icon: 'ok' },
						{ line: true },{ text: '不同意', click : disagree, icon: 'delete' },
						{ line: true },{ text: '转稽核', click : zhuanjihe, icon: 'back' },
						{ line: true },{ text: '转发', click : _openZhuanFa, icon: 'memeber' }
						</security:authorize>
						]
				},
				columns: [
		                	{ display: 'id ', name: 'id',  width:'10%',hide:true },
		                	{ display: '电话 ', name: 'phoneNum',width:'10%' ,render:function(item){
		                		return "<a title='最近提交' href='javascript:void(0);' onclick='recent_record("+item.phoneNum+")' >"+item.phoneNum+"</a>";
		                	}},
		                	{ display: '部门', name: 'groupName',width:'10%'},
		                	{ display: '业务类型', name: 'workType',width:'10%',
		                			editor: { type: 'select', data: workData, valueField: 'workType' },
			                		render:function(item){
			                			return getDict("WORKTYPE",item.workType);
		                	} },
		                	{ display: '网别', name: 'netType',width:'10%' ,
		                		editor: { type: 'select', data: netData, valueField: 'netType' },
		                		render:function(item){
		                			return getDict("NETTYPE",item.netType);
		                	}},
		                	{ display: '退费金额', name: 'money',width:'10%'},
		                	{ display: '确认金额', name: 'confirmMoney',width:'10%',
		                		editor: { type: 'float' }},
		                	{ display: '日期', name: 'createtime',width:'10%' },
		                	{ display: '查重意见', name: 'remark1',width:'10%' },
		                	{ display: '退费员', name: 'userName',width:'10%' }
		                	<security:authorize ifAnyGranted="AUTH_0305">
		                	,
		                	{ display: '操作', name: 'userName',width:'5%',render:function(item){
		                		return "<a href='javascript:void(0);' onclick='_openSearch("+item.id+","+item.confirmMoney+")' ><img title='审核' src='<%=path%>/js/ligerUI/skins/icons/memeber.gif'/></a>";
		                	} }
		                	,
		                	{ display: '操作', name: 'userName',width:'5%',render:function(item){
		                		return "<a href='javascript:void(0);' onclick='_save("+item.id+","+item.workType+","+item.netType+","+item.confirmMoney+")' ><img title='保存' src='<%=path%>/js/ligerUI/skins/icons/save.gif'/></a>";
		                	} }
		                	</security:authorize>
		                ],
                onSelectRow: function(rowdata, rowindex, rowDomElement){
					rows = this.getSelectedRows();},
				onUnSelectRow : function(rowdata, rowindex,rowDomElement) {
					rows = this.getSelectedRows();
				}
            });
			 window["g"] = manager = $("#grid").ligerGrid(cfg);
		});
		
		function f_onShowDetail(record, container) {
			 var html ="退费原因:"+record.reason+""; 
			 html+="查重意见:"+record.remark2+""

	         //$(".l-grid-detailpanel-inner").css('margin','20');
	        // $(".l-grid-detailpanel-inner").html(html);
	        $(container).addClass("l-grid-detailpanel-edit");

	        $(container).append(html);
		} 

		
	
		
		function _openSearch(id,confirmMoney){
			$("#recordid").val(id);
			$("#confirmMoney").val(confirmMoney);
			if(!window["searchDlg"]){
				window["searchDlg"] = $.ligerDialog.open({target:$("#searchBox"), title:"审核", width:300});
			}else{
				window["searchDlg"].show();
			}
		}
		
		function confirm_money(){
			
			
			var confirmMoney = $("#confirmMoney").val();
			var remark2 = $("#remark2").val();
			ids = $("#recordid").val();
			
			$.ajax({
				url : "<%=path%>/record/confirm-shenhe-money.do",
				data : {"ids":ids,"confirmMoney":confirmMoney,"remark2":remark2},
				type : "post",
				dataType : "json",
				cache : false,
				error : function(XMLHttpRequest, textStatus, errorThrown){
					$.ligerDialog.error("出现错误,请重试或联系管理员!");
				},
				success : function(data, textStatus){
					searchDlg.hidden();
					reloadGrid(g,null);	
					$.ligerDialog.success(data.info);
				}
			});
			
		}
		
		function _search(){
			var worktype = $("#worktype").val();
			var nettype = $("#nettype").val();
			var time1 = $("#time1").val();
			var time2 = $("#time2").val();
			
			reloadGrid(g, {"workType":worktype,"netType":nettype,"time1":time1,"time2":time2});
		}
		
		
		
		
		function recent_record(phoneNum){
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
						$("#recordtable tr:not(:first)").remove();
						for(var i=0;i<data.obj.length;i++){
							var $tr = $("#recordtable tr:last");
							$tr.after("<tr><td>"+data.obj[i].phoneNum+"</td><td>"+getDict("WORKTYPE",data.obj[i].workType)+"</td><td>"+getDict("NETTYPE",data.obj[i].netType)+"</td><td>"+data.obj[i].money+"</td><td>"+data.obj[i].createtime+"</td><td>"+data.obj[i].userName+"</td></tr>");
							
						}
						
						if(!window["recent"]){
							window["recent"] = $.ligerDialog.open({target:$("#recent_record"), title:"近两个月提交记录", width:600});
							
						}else{
							window["recent"].show();
						}
						
					}else{
						$.ligerDialog.warn("近两个月没有提交记录！");
					
					}
				}
			});
		}
		
		
		function agree(){
			rows = manager.getSelectedRows();
			if(rows == null || rows.length==0){
				$.ligerDialog.warn("请至少选择一条数据！");
				return;
			}
			$.ligerDialog.confirm('是否同意这些申请？', function (yes){
				if(yes){
					var ids ="";
					for(var i=0;i<rows.length-1;i++){
						ids+=rows[i].id;
						ids+=",";
					}
					ids+=rows[rows.length-1].id;
				$.ajax({
					url : "<%=path%>/record/confirm-shenhe-money-batch_.do",
					data : {"ids":ids,"flag":"1"},
					type : "post",
					dataType : "json",
					cache : false,
					error : function(XMLHttpRequest, textStatus, errorThrown){
						$.ligerDialog.error("出现错误,请重试或联系管理员!");
					},
					success : function(data, textStatus){
						reloadGrid(g,null);	
							
					}
				});
				}
			});
			
		}
		
		
		function disagree(){
			rows = manager.getSelectedRows();
			if(rows == null || rows.length==0){
				$.ligerDialog.warn("请至少选择一条数据！");
				return;
			}
			$.ligerDialog.confirm('是否不同意这些申请？', function (yes){
				if(yes){
					var ids ="";
					for(var i=0;i<rows.length-1;i++){
						ids+=rows[i].id;
						ids+=",";
					}
					ids+=rows[rows.length-1].id;
				$.ajax({
					url : "<%=path%>/record/confirm-shenhe-money-batch_.do",
					data : {"ids":ids,"flag":"2"},
					type : "post",
					dataType : "json",
					cache : false,
					error : function(XMLHttpRequest, textStatus, errorThrown){
						$.ligerDialog.error("出现错误,请重试或联系管理员!");
					},
					success : function(data, textStatus){
						reloadGrid(g,null);	
							
					}
				});
				}
			});
			
		}
		
		function _save(id,workType,netType,confirmMoney){
			 manager.isDataChanged = false;
			$.ajax({
				url : "<%=path%>/record/editsave_.do",
				data : {"id":id,"workType":workType,"netType":netType,"confirmMoney":confirmMoney},
				type : "post",
				dataType : "json",
				cache : false,
				error : function(XMLHttpRequest, textStatus, errorThrown){
					$.ligerDialog.error("出现错误,请重试或联系管理员!");
				},
				success : function(data, textStatus){
					reloadGrid(g,null);	
					$.ligerDialog.success(data.info);
						
				}
			});
		}
		
		function zhuanjihe(){
			rows = manager.getSelectedRows();
			if(rows == null || rows.length==0){
				$.ligerDialog.warn("请至少选择一条数据！");
				return;
			}
			$.ligerDialog.confirm('是否转发这些申请？', function (yes){
				if(yes){
					var ids ="";
					for(var i=0;i<rows.length-1;i++){
						ids+=rows[i].id;
						ids+=",";
					}
					ids+=rows[rows.length-1].id;
				$.ajax({
					url : "<%=path%>/record/turnback-record_.do",
					data : {"ids":ids},
					type : "post",
					dataType : "json",
					cache : false,
					error : function(XMLHttpRequest, textStatus, errorThrown){
						$.ligerDialog.error("出现错误,请重试或联系管理员!");
					},
					success : function(data, textStatus){
						reloadGrid(g,null);	
						$.ligerDialog.success(data.info);
					}
				});
				}
			});
			
		}
		
		function _openZhuanFa(){
			rows = manager.getSelectedRows();
			if(rows == null || rows.length==0){
				$.ligerDialog.warn("请至少选择一条数据！");
				return;
			}
			var ids ="";
			for(var i=0;i<rows.length-1;i++){
				ids+=rows[i].id;
				ids+=",";
			}
			ids+=rows[rows.length-1].id;
			$("#zfrecordid").val(ids);
			if(!window["ZFDlg"]){
				window["ZFDlg"] = $.ligerDialog.open({target:$("#ZFBox"), title:"转发", width:300});
			}else{
				window["ZFDlg"].show();
			}
		}
		
		function zhuanfa(){
			var ids = $("#zfrecordid").val();
			var group = $("#zfgroup").val();
			$.ajax({
				url : "<%=path%>/record/zhuanfa-record_.do",
				data : {"ids":ids,"group":group},
				type : "post",
				dataType : "json",
				cache : false,
				error : function(XMLHttpRequest, textStatus, errorThrown){
					$.ligerDialog.error("出现错误,请重试或联系管理员!");
				},
				success : function(data, textStatus){
					ZFDlg.hidden();
					reloadGrid(g,null);	
					$.ligerDialog.success(data.info);
						
				}
			});
		}
	
		</script>
</head>
<body>
<div class="form_page">
	<div class="form_group"><img src="<%=path %>/images/communication.gif" alt="" /><span>审核</span></div>
	<div class="form_content">
		<table   cellpadding="0" cellspacing="0" border="0">
			<tr>
				
				<td width="70" align="center" class="query_title">业务类型</td>
				<td class="query_content"><span class="selectBox"><span class="bg">
					<select id="worktype" style="width: 100px;" >
					<option value=""></option>
					<c:forEach items="${wlist }" var="w"  >
						<option value="${w.value }">${w.name }</option>
					</c:forEach>
					</select></span></span>
				</td>
				<td width="70" align="center" class="query_title">网别</td>
				<td class="query_content"><span class="selectBox"><span class="bg">
					<select id="nettype" style="width: 100px;" >
					<option value=""></option>
					<c:forEach items="${nlist }" var="n"  >
						<option value="${n.value }">${n.name }</option>
					</c:forEach>
					</select></span></span>
				</td>
				<td width="70" align="center" class="query_title">时间</td>
				<td class="query_content">
					<span class="inputBox"><span class="bg"> <img src="<%=path %>/images/space.gif" class="iconBox_dateStar" />
			        <input id="time1" type="text" style="width:100px;" />
			        <img src="<%=path %>/images/space.gif" class="iconBox_dateEnd" />
			        <input id="time2" type="text" style="width:100px;"/>
			        </span></span>
				</td>
				
				<td  align="center" class="query_btn">
					<input type="submit" value="" title="确定" class="btnBox_introsearch" onclick="_search()" />
				</td>
			</tr>
		</table>
	</div>
</div>
	<div id="grid"></div>
	<div id="searchBox" class="query_box hiden">
	<input type="hidden" id="recordid" >
		<table class="query_table" width="250" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td class="query_title">确认金额</td>
				<td class="query_content"><span class="inputBox"><span class="bg"><input id="confirmMoney" type="text" value="0" style="width: 150px;" /></span></span></td>
			</tr>
			<tr>
				<td class="query_title">留言</td>
				<td class="query_content"><span class="bg">
				<textarea id="remark2" style="width:150px;height:50px"></textarea>
				</span>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="query_btn">
					<input type="submit" value="" title="确定" class="btnBox_confirm" onclick="confirm_money()" />
					<input type="button" value="" title="取消" class="btnBox_cancel" style="margin-left: 20px;" onclick="searchDlg.hidden()" />
				</td>
			</tr>
		</table>
	</div>
	
	<div id="ZFBox" class="query_box hiden">
	<input type="hidden" id="zfrecordid" >
		<table class="query_table" width="250" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td class="query_title">部门</td>
				<td class="query_content"><span class="selectBox"><span class="bg">
				<select id="zfgroup" style="width: 150px;" >
					<c:forEach items="${glist }" var="g"  >
						<option value="${g.id }">${g.groupName }</option>
					</c:forEach>
					</select>
				</span></span></td>
			</tr>
			<tr>
				<td colspan="2" class="query_btn">
					<input type="submit" value="" title="确定" class="btnBox_confirm" onclick="zhuanfa()" />
					<input type="button" value="" title="取消" class="btnBox_cancel" style="margin-left: 20px;" onclick="ZFDlg.hidden()" />
				</td>
			</tr>
		</table>
	</div>
	
	<div id="recent_record" class="hide">
		<table id="recordtable"  class="listtable">
				<tr>
				<th>电话</th>
				<th>业务类型</th>
				<th>网别</th>
				<th>金额</th>
				<th>日期</th>
				<th>退费员</th>
				</tr>
		</table>
	
	</div>
	
</body>
</html>