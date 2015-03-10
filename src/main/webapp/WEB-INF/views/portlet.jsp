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
		
		.ui-widget-content a  {
		    color: #77BACE;
		    text-decoration: none;
		}
	</style>

    <script>
    $(function() {
        $('#portlet').portlet({
            sortable: true,
            create: function() {
                //alert('created portlet.');
            },
            removeItem: function() {
               // alert('after remove');
            },
            columns: [{
                width: '30%',
                portlets: [{
                    attrs: {
                        id: 'portlet1'
                    },
                    title: '在线用户',
                    content: {
                    	style: {
                            height: '200'
                        },
                        type: 'text',
                        text: function() {
                            return $('#online').html();
                            //return $('#newsTemplate ul');
                        }
                    }
                },{
                attrs: {
                    id: 'portlet2'
                },
                title: '通知公告',
                content: {
                	style: {
                        height: '200'
                    },
                    type: 'text',
                    text: function() {
                        return $('#notice').html();
                        //return $('#newsTemplate ul');
                    }
                }
            }]
            }, {
                width: '30%',
                portlets: [{
                    title: '待处理任务',
                    content: {
                    	 style: {
                             height: '200'
                         },
                        attrs: {
                            'class': 'highlight-content'
                        },
                        type: 'text',
                        text: function() {
                            return $('#task').html();
                            //return $('#newsTemplate ul');
                        }
                    }
                },
                {
                    title: '常用功能',
                    content: {
                    	 style: {
                             height: '200'
                         },
                        attrs: {
                            'class': 'highlight-content'
                        },
                        type: 'text',
                        text: function() {
                            return $('#tools').html();
                            //return $('#newsTemplate ul');
                        }
                    }
                }]
            }, {
                width: '30%',
                portlets: [{
                    title: '流程管理',
                    content: {
                    	 style: {
                             height: '200'
                         },
                        type: 'text',
                        text: function() {
                            return $('#flow').html();
                        }
                    }
                }, {
                    title: '短信接收功能',
                    content: {
                    	 style: {
                             height: '200'
                         },
                         type: 'text',
                         text: function() {
                             return $('#message').html();
                         }
                    }
                }]
            }]
        });
    });
    </script>
</head>
<body>
    
    <div id='portlet'></div>
    
    <div id="online" style="width:100%; display: none">
    	<table class="table table-condensed table-bordered table-hover table-striped" >
    		<thead>
			  <tr>
				<th>当前用户</th>
				<th>角色</th>
				<th>部门</th>
			  </tr>
			</thead>

			<tbody>
			<c:forEach items="${alluser }" var="u" >
			<tr> 
				<td>${u.value.username }</td>
				<td>${u.value.role.roleName }</td>
				<td>${u.value.group.groupName }</td>
			</tr>
			</c:forEach>
			</tbody>
    	
    	</table>
    
    </div>
	
	<div id="notice" style="display: none">
		<marquee direction="up" scrollamount="1" >
		  <ol>
		  <c:forEach items="${list }" var="n" >
		  
		    <li>
			  <h3><a href="<%=path %>/notice/notice-info/${n.id }_.do">${n.title }</a></h3>
			  <p class="muted">
			  	<c:if test="${fn:length(n.content)>=100}">
					${fn:substring(n.content,0,100)}...
				</c:if>
				<c:if test="${fn:length(n.content)<100}">
					${n.content}
				</c:if>
			  </p>
			  <p class="text-right" >
				  <small>${n.createUser }</small>
			  </p>
			  <p class="text-right" >
				  <small>${n.createTime }</small>
			  </p>
			</li>
		  </c:forEach>
		  </ol>
		  </marquee>
	</div>
	
	<div id="flow" style="display: none">
		<table class="table" >
    		<tr>
    			<td width="80%">退费申请</td>
    			<td><a href="<%=path %>/record/manager.do?tab=1" class="btn btn-small btn-primary" >申请</a></td>
    		</tr>
    		<tr>
    			<td>查重区</td>
    			<td><a href="<%=path %>/record/manager.do?tab=2"  class="btn btn-small btn-primary" >进入</a></td>
    		</tr>
    		<tr>
    			<td>审核</td>
    			<td><a href="<%=path %>/record/manager.do?tab=3"  class="btn btn-small btn-primary" >审核</a></td>
    		</tr>
    		<tr>
    			<td>待处理</td>
    			<td><a href="<%=path %>/record/manager.do?tab=4"  class="btn btn-small btn-primary" >处理</a></td>
    		</tr>
    	</table>
	</div>
	
	<div id="message"  style="display: none">
	<p>
	  当前用户： ${loginUser.username }
	</p>
	<p>
	手机号码: ${loginUser.tel }
	</p>
	<p>
	短信接收功能：
		<input id="m" name="m" type="radio" <c:if test="${loginUser.flag == 1 }">checked</c:if>  /> 开
		
		<input id="m" name="m" type="radio" <c:if test="${loginUser.flag == 2 }">checked</c:if>  /> 关
	</p>
	</div>
	
	<div id="task" style="display: none">
		<table class="table table-condensed table-bordered table-hover table-striped">
		<thead>
		<th>电话号</th>
		<th>查重</th>
		<th>审核</th>
		<th>退费</th>
		</thead>
		<c:forEach items="${ rlist}" var="r" >
		
		<tr>
			<td>${r.phoneNum }</td>
			<td>${util:getDict('CCFLAG',r.ccflag) }</td>
			<td>${util:getDict('SHFLAG',r.shflag) }</td>
			<td>${util:getDict('STATUSFLAG',r.statusFlag) }</td>
		</tr>
		</c:forEach>
		</table>
	</div>
	
	<div id="tools" style="display: none">
		<table class="table" >
    		<tr>
    			<td width="80%">修改密码</td>
    			<td><a href="<%=path%>/user/editpw_.do" class="btn btn-small btn-primary" >修改</a></td>
    		</tr>
    		<tr>
    			<td>业务查询</td>
    			<td><a href="<%=path %>/record/manager.do?tab=5"  class="btn btn-small btn-primary" >查询</a></td>
    		</tr>
    		<tr>
    			<td>日历</td>
    			<td><a href="<%=path %>/workcal/workcal-view.do"  class="btn btn-small btn-primary" >查看</a></td>
    		</tr>
    	</table>
	</div>
    
</body>
</html>