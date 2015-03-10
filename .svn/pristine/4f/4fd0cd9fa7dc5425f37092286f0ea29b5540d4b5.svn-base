<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
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
   
	<!-- Ligerui -->
    <link rel="stylesheet" type="text/css" href="<%=path%>/js/ligerUI/skins/Aqua/css/ligerui-all.css" /> 
    <link rel="stylesheet" type="text/css" href="<%=path%>/js/ligerUI/skins/ligerui-icons.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/js/ligerUI/skins/Gray/css/grid.css" />
    <script type="text/javascript" src="<%=path%>/js/ligerUI/js/ligerui.min.js" ></script>
    <script type="text/javascript" src="<%=path%>/js/jquery.cookie.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/json2.js"></script>
	<script type="text/javascript">
    var tab = null;
    var accordion = null;
    var tree = null;
    var tabItems = [];
    $(function ()
          {

              //布局
              $("#layout1").ligerLayout({ leftWidth: 190, height: '100%',heightDiff:-22,space:4, onHeightChanged: f_heightChanged });

              var height = $(".l-layout-center").height();

              //Tab
              $("#framecenter").ligerTab({
                  height: height,
                  showSwitchInTab : true,
                  showSwitch: true,
                  onAfterAddTabItem: function (tabdata)
                  {
                      tabItems.push(tabdata);
                      saveTabStatus();
                  },
                  onAfterRemoveTabItem: function (tabid)
                  { 
                      for (var i = 0; i < tabItems.length; i++)
                      {
                          var o = tabItems[i];
                          if (o.tabid == tabid)
                          {
                              tabItems.splice(i, 1);
                              saveTabStatus();
                              break;
                          }
                      }
                  },
                  onReload: function (tabdata)
                  {
                      var tabid = tabdata.tabid;
                      tab.reload(tabid);
                  }
              });

              //面板
              $("#accordion1").ligerAccordion({  heightDiff:0, speed: null,changeHeightOnResize: true });

              
              

              tab = liger.get("framecenter");
              accordion = liger.get("accordion1");
              $("#pageloading").hide();

              //pages_init();
          });
          function f_heightChanged(options)
          {  
              if (tab)
                  tab.addHeight(options.diff);
             // if (accordion && options.middleHeight - 24 > 0)
                 // accordion.setHeight(options.middleHeight - 24);
          }
          function f_addTab(tabid, text, url)
          {
              tab.addTabItem({
                  tabid: tabid,
                  text: text,
                  url: url
              });
              tab.reload(tabid) ;
          }
         


          
          function pages_init()
          {
              var tabJson = $.cookie('liger-home-tab'); 
              if (tabJson)
              { 
                  var tabitems = JSON2.parse(tabJson);
                  for (var i = 0; tabitems && tabitems[i];i++)
                  { 
                      f_addTab(tabitems[i].tabid, tabitems[i].text, tabitems[i].url);
                  } 
              }
          }
          function saveTabStatus()
          { 
              $.cookie('liger-home-tab', JSON2.stringify(tabItems));
          }
          
         
          
          
     </script> 
<style type="text/css"> 
    body,html{height:100%;}
    body{ font-size: 13px;padding:0px; margin:0;   overflow:hidden;}  
    
    
    #l-scoll  li a {
	display: block;
	font-size: 13px;
	text-decoration: none;
	text-align: left;
	padding: 1px;
	margin-left:12px;
	color:rgba(0, 147, 255, 1);
	font-family:"Microsoft YaHei";
	}

	#l-scoll  li a:hover {
	background: url(<%=path%>/images/tab_bg.gif) repeat-x;
	border: solid 1px #adb9c2;
	margin-left:0px;
	}
    
 </style>
</head>
<body style="padding:0px;background:#EAEEF5;">  


  <div id="layout1" style="width:99.2%; margin:0 auto; margin-top:1px; "> 
        <div position="left"  title="主要菜单" id="accordion1" >
        	<security:authorize ifAnyGranted="AUTH_0201,AUTH_020101">
                   <div title="组织机构">
						<ul id="l-scoll">
							<security:authorize ifAnyGranted="AUTH_02"><li><a href="javascript:f_addTab('AUTH_02','组织机构列表','./group/group-list.do')">组织机构列表</a></li></security:authorize>
							<security:authorize ifAnyGranted="AUTH_0201"><li><a href="javascript:f_addTab('AUTH_0201','添加组织','./group/group-add.do')">添加组织</a></li></security:authorize>
							
						</ul>
					</div>
			</security:authorize>
			
        </div>
        <div position="center" id="framecenter"> 
            <div tabid="home" title="我的主页" style="height:300px" >
            	<table align="center" cellpadding="0" cellspacing="0" border="0" width="100%" height="100%" bgcolor="#E2F2FD">
  					<tr><td align="center" height="700px"></td></tr>
				</table> 
            </div> 
        </div> 
        
    </div>
    <div  style="height:25px; line-height:25px; text-align:center;">
            Copyright © 2014-2015&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 技术支持 三峡大学
    </div>
    <div style="display:none"></div>
</body>
</html>
