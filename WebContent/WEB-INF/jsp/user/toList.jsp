<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tags/el" prefix="e" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理员主界面</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css"></link>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
<!-- 
	切换上一页，下一页的js函数 
		把分页显示中的路径中的	href="javascript:void(query(${item}));"	,
		query中的item值传递到 function query(page) 的page中,
		然后把page的值传递到form表单中,并且使用		$("#query")[0].submit();	把id是query的表单提交,
		来完成上一页和下一页
-->
<script type="text/javascript">
	function query(page){
		$("#page").val(page);
		$("#query")[0].submit();
	}
</script>


</head>
<body>
<body>
	<div class="container">
	
		<!-- 查询消息传递 -->
		<form class="form-control" id="query" action="${pageContext.request.contextPath}/user/toList.htmlx">
			
			<input type="text" class="form-control" id="page" value="${pageVo.page}">
			
			<div class="row">
			  <div class="col-xs-3 col-md-4">
			  	  <div class="form-group">
				    <label for="exampleInputEmail1">用户名：</label>
				    <input type="text" name="params['uname']" value="${pageVo.params['uname'] }" 
				    	class="form-control" id="exampleInputEmail1" placeholder="uname">
				  </div>
			  </div>
			  <div class="col-xs-3 col-md-4">
			  	  <div class="form-group">
				    <label for="exampleInputEmail1">Email：</label>
				    <input type="text" name="params['email']" class="form-control" 
				    	id="exampleInputEmail1" placeholder="Email">
				  </div>
			  </div>
			  <div class="col-xs-3 col-md-4">
			  	  <div class="form-group">
				    <label for="exampleInputEmail1">手机号：</label>
				    <input type="text" name="params['phone']" class="form-control" 
				    id="exampleInputEmail1" placeholder="phone">
				  </div>
			  </div>
			  
			  <div class="col-xs-3 col-md-4">
			  	  <div class="form-group">
				    <label for="exampleInputEmail1">用户类型</label>
				    
				    
				    <select name="userType">
					    <c:forEach var="item" items="${e:getCodesByType('100')}" >
					    	<option value="${item.codeType}">${item.type}</option>
					    </c:forEach>
				    </select>
				    
				  </div>
			  </div>
			  
			  <div class="col-xs-4 col-md-3" >
			  	  <div class="form-group">
				    <button type="submit" class="btn btn-default">查询</button>
				  </div>
			  </div>
			</div>
			
			
			<table class="table table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th>用户名</th>
					<th>用户类型</th>
					<th>Email</th>
					<th>手机号</th>
					<th>登录状态</th>
					<th>家庭地址信息</th>
					<th>注册时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${pageVo.rows}" varStatus="status">
					<tr>
						<td>${status.index}</td>
						<td>${item.uname}</td>
						<td> ${e:formatSimpleCode(item.userType)} </td>
						<td>${item.email }</td>
						<td>${item.phone }</td>
						<td>${item.state }</td>
						<td>家庭地址信息</td>
						<td>${item.registerDate }</td>
					</tr>
				</c:forEach>
			</tbody>
			
			<!-- 显示分页的按钮 -->
			<tfoot>
			<tr> 
				<td colspan="8">
					<nav aria-label="...">
						<ul class="pagination">
							
								<c:choose>
									<c:when test="${pageVo.page == 1}">
										<li class="disabled">
											<a href="${pageContext.request.contextPath}/user/toList.htmlx?page=${pageVo.page}" 
											onclick="javascript:void(query(${page}));" aria-label="Previous"> 
												<span aria-hidden="true">&laquo;</span>
											</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="Previous">
											<a href="${pageContext.request.contextPath}/user/toList.htmlx?page=${pageVo.page-1}" 
											onclick="javascript:void(query(${page-1}));" aria-label="Previous"> 
												<span aria-hidden="false">&laquo;</span>
											</a>
										</li>
									</c:otherwise>
								</c:choose>
							
							
							<c:forEach begin="1" end="${pageVo.totalPage }" step="1" var="item">
								<li class="active">
									<a href="${pageContext.request.contextPath}/user/toList.htmlx?page=${item}">${item}<span class="sr-only">(current)</span></a>
								</li>
								<%-- <li><a href="javascript:void(query(${item+1}));">${item+1}</a></li>
								<li><a href="javascript:void(query(${item+2}));">${item+2}</a></li>
								<li><a href="javascript:void(query(${item+3}));">${item+3}</a></li>
								<li><a href="javascript:void(query(${item+4}));">${item+4}</a></li> --%>
							</c:forEach>
							
							
								<c:choose>
									<c:when test="${pageVo.page < pageVo.totalPage}">
										<li class="Previous">
											<a href="${pageContext.request.contextPath}/user/toList.htmlx?page=${pageVo.page+1}" 
												onclick="javascript:void(query(${page+1}));" aria-label="Next"> 
												<span aria-hidden="false">&raquo;</span>
											</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="disabled">
											<a href="${pageContext.request.contextPath}/user/toList.htmlx?page=${pageVo.totalPage}" 
											onclick="javascript:void(query(${totalPage}));" aria-label="Next"> 
												<span aria-hidden="true">&raquo;</span>
											</a>
										</li>
									</c:otherwise>
								</c:choose>
								
							
						</ul>
					</nav>
				</td>
			</tr>
		</tfoot>
			
		</table>
		</form>
	</div>
</body>




</html>