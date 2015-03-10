<%@ page language="java" pageEncoding="UTF-8" %>

<!-- start of header bar -->
    <div class="navbar navbar-inverse">
      <div class="navbar-inner">
        <div  class="container">
          <a data-target=".navbar-responsive-collapse" data-toggle="collapse" class="btn btn-navbar">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a href="<%=path %>/login.do" class="brand">退费审批系统 <sub><small>RMSys</small></sub></a>
          <div class="nav-collapse collapse navbar-responsive-collapse">
            <ul id="menu" class="nav">
              <li class="divider-vertical"></li>
              <li class=""><a href="javascript:void(0)" rel="<%=path %>/portlet_.do" ><i class=""></i>首页</a></li>
              <li class=""><a href="javascript:void(0)" rel="<%=path %>/record/manager.do?tab=5" ><i class=""></i>查询报表</a></li>
              <li class=""><a href="javascript:void(0)" rel="<%=path %>/workcal/workcal-view.do" ><i class=""></i>工作日历</a></li>
              <li class="dropdown">
                <a data-toggle="dropdown" class="dropdown-toggle" href="javascript:void(0)"><i class=""></i>系统管理 <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="javascript:void(0)" rel="<%=path%>/user/manager.do"><i class="icon-user"></i>用户管理</a></li>
                  <li><a href="javascript:void(0)" rel="<%=path%>/group/manager.do"><i class="icon-user"></i>组织机构</a></li>
                  <li class="divider"></li>
                  <li><a href="javascript:void(0)" rel="<%=path%>/role/role-list.do"><i class="icon-user"></i>权限管理</a></li>
                  <li class="divider"></li>
				  <li><a href="javascript:void(0)" rel="<%=path%>/record/manager.do"><i class="icon-user"></i>流程管理</a></li>
				  <%--<li><a href="javascript:void(0)"><i class="icon-user"></i>附件管理</a></li>
				  <li><a href="javascript:void(0)"><i class="icon-user"></i>任务管理</a></li>
                  --%><li class="divider"></li>
                  <li><a href="javascript:void(0)" rel="<%=path %>/notice/manager.do" ><i class="icon-user"></i>公告管理</a></li>
                  <%--<li><a href="javascript:void(0)"><i class="icon-user"></i>系统配置</a></li>
                --%></ul>
              </li>
            </ul>

            <ul class="nav pull-right">
              <li class="dropdown">
                <a data-toggle="dropdown" class="dropdown-toggle" href="javascript:void(0)">
			      	${loginUser.username}
                  <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                  <li><a href="javascript:void(0)" onclick="editpw()" ><i class="icon-user"></i>修改密码</a></li>
                  <li class="divider"></li>
				  <li><a href="<%=path%>/logout.do"><i class="icon-user"></i>退出</a></li>
                </ul>
              </li>
            </ul>
          </div><!-- /.nav-collapse -->
        </div>
      </div><!-- /navbar-inner -->
    </div>
    <!-- end of header bar -->
