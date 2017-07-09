<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://name/kai" prefix="tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加根节点</title>
</head>
<body>

	<div class="container">
		<form method="post" action="${pageContext.request.contextPath}/res/add.htmlx">
		 <table class="table">
		 	<tr>
		 		<td>
		 			资源ID
		 		</td>
		 		<td>
		 			<input type="text" id="ares.resId" name="ares.resId" readonly="readonly" value="${ares.resId}" />
		 		</td>  
		 	</tr>
		 	<tr>
		 		<td>
		 			资源名称
		 		</td>
		 		<td>
		 			<input type="text" name="ares.name" value="${ares.name}" />
		 		</td>  
		 	</tr>
		 	<tr>
		 		<td>
		 			路径
		 		</td>
		 		<td>
		 			<input type="text" name="ares.path" value="${ares.path}" />
		 		</td>  
		 	</tr>
		 	<tr>
		 		<td>
		 			资源父节点
		 		</td>
		 		<td>
		 			<input type="text" name="ares.parentId" value="${ares.parentId}" readonly="readonly" />
		 		</td>  
		 	</tr>
		 	<tr>
		 		<td>
		 			排序
		 		</td>
		 		<td>
		 			<input type="text" name="ares.rorder" value="${ares.rorder}" />
		 		</td>  
		 	</tr>
		 	<tr>
		 		<td>
		 			创建时间
		 		</td>
		 		<td>
		 			
		 			<tags:ShowCurrentDate date="${ares.createTs}"/>	
		 			
<!-- 		 			<input type="text" name="ares.createTs" value="" readonly="readonly"/> -->
		 		</td>  
		 	</tr>
		 	
		 	<tr>
		 		<td>
		 			备注
		 		</td>
		 		<td>
		 			<textarea rows="5" cols="70" name="ares.note">${ares.note}</textarea>
		 		</td>  
		 	</tr>
		 	<tr>
		 		<td colspan="2" >
		 			<input type="submit" value="保存/更新">
		 		</td>
		 	</tr>
		 </table>
		 </form> 
	</div>

</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript">
	function realSysTime(clock){
		var now = new Date();
		clock.innerHTML = now;
	}
</script>

</html>