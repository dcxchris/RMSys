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
		a:link, a:visited {
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
                alert('after remove');
            },
            columns: [{
                width: '30%',
                portlets: [{
                    attrs: {
                        id: 'portlet1'
                    },
                    title: '在线用户',
                    beforeRefresh: function() {
                        alert("before refresh");
                    },
                    afterRefresh: function(data) {
                        //alert("after refresh: " + data);
                    },
                    content: {
                    	style: {
                            height: '200'
                        },
                        type: 'text',
                        text: function() {
                            return $('#newsTemplate').html();
                            //return $('#newsTemplate ul');
                        }
                    }
                },{
                attrs: {
                    id: 'portlet2'
                },
                title: '通知公告',
                beforeRefresh: function() {
                    alert("before refresh");
                },
                afterRefresh: function(data) {
                    //alert("after refresh: " + data);
                },
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
                        text: 'Shopping contens<br/>Shopping contens<br/>Shopping contens<br/>Shopping contens<br/>'
                    }
                },
                {
                    title: 'portlet4',
                    content: {
                    	 style: {
                             height: '200'
                         },
                        attrs: {
                            'class': 'highlight-content'
                        },
                        type: 'text',
                        text: 'Shopping contens<br/>Shopping contens<br/>Shopping contens<br/>Shopping contens<br/>'
                    }
                }]
            }, {
                width: '30%',
                portlets: [{
                    title: '退费申请',
                    content: {
                    	 style: {
                             height: '200'
                         },
                        type: 'ajax',
                        url: 'xxx.html'
                    }
                }, {
                    title: 'Ajax 错误',
                    content: {
                    	 style: {
                             height: '200'
                         },
                        type: 'ajax',
                        url: 'xxx.html',
                        error: function() {
                            $(this).append('<br/><br/>捕获到ajax错误');
                            // alert('出错了');
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
    
    <div id="online" style="display: none">
    	<table class="table table-hover" >
    		<thead>
			  <tr>
				<th>当前用户</th>
				<th>登录时间</th>
				<th>在线时长</th>
				<th>&nbsp;</th>
			  </tr>
			</thead>

			<tbody>
			
			</tbody>
    	
    	</table>
    
    </div>
	
	<div id="notice" style="display: none">
		<marquee direction="up" scrollamount="2" >
		  
		    <div>
			  <h3><a href="">title</a></h3>
			  <p>content</p>
			</div>
			
		  </marquee>
	</div>
    
</body>
</html>