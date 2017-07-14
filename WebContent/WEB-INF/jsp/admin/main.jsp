<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理员主界面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/js/EasyUI/themes/default/easyui.css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/EasyUI/jquery.easyui.min.js"></script>
		<script type="text/javascript">
			function changgeRightContent(url){
				$("#rightContent").attr("src",url);
			}
	 	</script>
</head>
<body>
<body class="easyui-layout" onclick="test111('123')">
	<div data-options="region:'north',border:false"
		style="height: 60px; background: #B3DFDA; padding: 10px">
		欢迎用户：张三登录系统 用户角色：文件管理员 当前系统时间： 2016-11-26 11:21:21 <a href="#">登出</a>
	</div>
	<div data-options="region:'west',split:true,title:'功能菜单项'" style="width: 250px;">
		<!--功能菜单项	 -->
		<ul class="easyui-tree">
				<li>
					<span>用户管理</span>
					<ul>
						<li >
							<span>
								<a onclick="changgeRightContent('${pageContext.request.contextPath}/user/toList.htmlx')">用户管理</a>
							</span>
						</li>
					</ul>
				</li>
				<li>
					<span>权限管理</span>
					<ul>
						<li>
							<span>
								<a onclick="changgeRightContent('${pageContext.request.contextPath}/res/toList.htmlx')">
									资源管理
								</a>
							</span>
						</li>
						<li>
							<span>
								<a onclick="changgeRightContent('${pageContext.request.contextPath}/role/toList.htmlx')">
									角色管理
								</a>
							</span>
						</li>
						<li>
							<span>用户角色管理</span>
						</li>
					</ul>
				</li>
				<li>
					<span>简单代码</span>
					<ul>
						<li>
							<span>简单代码</span>
						</li>
					</ul>
				</li>
				<li>
					<span>常量管理</span>
					<ul>
						<li>
							<span>常量管理</span>
						</li>
					</ul>
				</li>
				<li>
					<span>省市县管理</span>
					<ul>
						<li>
							<span>省市县管理</span>
						</li>
					</ul>
				</li>
				<li>
					<span>日常管理</span>
					<ul>
						<li>
							<span>公告管理</span>
						</li>
						<li>
							<span>新闻管理</span>
						</li>
						<li>
							<span>新闻管理</span>
						</li>
					</ul>
				</li>
			</ul>
	</div>
	<div data-options="region:'center',title:'业务功能区'">
		<iframe id="rightContent" width="100%" height="100%" src="${pageContext.request.contextPath}/forward.htmlx?path=work.defaultWork" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no"   />
	</div>
</body>
	
</html>