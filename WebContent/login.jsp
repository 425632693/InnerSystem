<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/js/EasyUI/themes/default/easyui.css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/EasyUI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jqueryValidator/jquery.validate.min.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>


<script type="text/javascript">
	$(function(){
		$("#login").validate({
			rules:{
				uname:{
					required:true,
					minlength:7,
					maxlength:21
				},
				upass:{
					required:true,
					minlength:7,
					maxlength:21
				}
			},
			messages:{
				uname:{
					required:"请输入用户名",
					minlength:"用户名最小长度是7位",
					maxlength:"用户名最大长度是21位"
				},
				upass:{
					required:"请输入密码",
					minlength:"密码最小长度是7位",
					maxlength:"密码最大长度是21位"
				}
			},
			submitHandler: function(form) {
			      alert("提交事件!");
			      //不刷新提交表单
			      $.get("${pageContext.request.contextPath}/login.htmlx?"+$("#login").serialize(),function (data){
			    	  console.log(data+"---------------");
			    	  $(form)[0].submit();
			      });
			      
			      return false;
			      //return true;
			}
			
		});
	});
</script>

</head>
<body>
	<div class="easyui-panel" title="用户登录系统" style="width: 400px;">
		<form id="login" action="${pageContext.request.contextPath}/login.htmlx">
			<c:forEach var="item" items="${errors}">
				<p>${item.context}</p>
			</c:forEach>
			<table>
				<tr>
					<td>用户名</td>
					<td><input class="easyui-validatebox" type="text" name="uname"/></td>
				</tr>
				<tr>
					<td>登录密码</td>
					<td><input class="easyui-validatebox" type="password" name="upass"/></td>
				</tr>
				<tr>
					<td>认证码</td>
					<td>
						<input class="easyui-validatebox" type="text" id="loginCode" name="loginCode"/>
						<img id="vcode" src="${pageContext.request.contextPath}/code/vlogin.htmlx"/> 
						<!-- 
							使用void()方法，表示在本页面使用了一个函数来调用括号里面的方法来实现点击刷新验证码的效果 
						-->
						<a href="javascript:void(document.getElementById('vcode').src=
							'${pageContext.request.contextPath}/code/vlogin.htmlx?v='+new Date());" >看不清楚？</a>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="登录"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>