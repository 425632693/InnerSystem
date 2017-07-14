<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://name/cui" prefix="tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/js/EasyUI/themes/default/easyui.css"></link>


<title>添加根节点</title>
</head>
<body>
	<ul id="tree">
		
	</ul>
	
	<form id="saveAuthorRole" action="${pageContext.request.contextPath}/role/saveAuthorRole.htmlx">
		<input id="resoucesIds" name="resoucesIds" value="" /><br/>
		<input id="roleId" name="roleId" value="${arole.roleId}" /><br/>
		<input type="button" onclick="getSelects()" value="保存" />
	</form>
	
</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/EasyUI/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(function(){
		$("#tree").tree({
			url:"${pageContext.request.contextPath}/res/all.htmlx",
			checkbox:true,
			formatter:function(node){
				return node.name;
			}
		});
	});
	
	function getSelects(){
		var nodes = $('#tree').tree('getChecked',['checked','indeterminate']);
        var s = '';
        for (var i = 0; i < nodes.length; i++) {
            if (s != '')
            s += ',';
            s += nodes[i].resId;
        }
		$("#resoucesIds").val(s);
		$("#saveAuthorRole")[0].submit();
	}
	
	
</script>

</html>