<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://name/cui" prefix="tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<a href="${pageContext.request.contextPath}/res/toAdd.htmlx" class="btn btn-primary btn-lg active" role="button">新增根节点</a>
		<div class="bs-example" data-example-id="contextual-table">
		    <table class="table">
		      <thead>
		        <tr>
		          <th>资源ID</th>
		          <th>资源名称</th>
		          <th>父节点id</th>
		          <th>资源路径</th>
		          <th>排序</th>
		          <th>创建时间</th>
		          <th>备注</th>
		          <th>操作</th>
		        </tr>
		      </thead>
		      <c:forEach var="item" items="${pageVo.rows}">
		      	  <tbody>
			        <tr class="active">
				          <!-- <th scope="row">1</th> -->
				          <td>${item.resId}</td>
				          <td>${item.name},<a href="${pageContext.request.contextPath}/res/toList.htmlx?params['parentId']=${item.resId}">查看子节点</a></td>
				          <td>${item.parentId}</td>
				          <td>${item.path}</td>
				          <td>${item.rorder}</td>
				          <td><tags:ShowCurrentDate date="${item.createTs}"/></td>
				          <td>${item.note}</td>
				          <td>
				          	<a href="${pageContext.request.contextPath}/res/toAdd.htmlx?ares.parentId=${item.resId}">
				          		增加子节点/
				          	</a>
				          	<a href="${pageContext.request.contextPath}/res/delete.htmlx?ares.resId=${item.resId}">
				          		删除
				          	</a>
				          </td>
				          
			        </tr>
			      </tbody>
		      </c:forEach>
		    </table>
		  </div>
		  
		  <!-- 显示分页的按钮 -->
			<tfoot>
			<tr> 
				<td colspan="8">
					<nav aria-label="...">
						<ul class="pagination">
							
								<c:choose>
									<c:when test="${pageVo.page == 1}">
										<li class="disabled">
											<a href="${pageContext.request.contextPath}/res/toList.htmlx?page=${pageVo.page}" 
											onclick="javascript:void(query(${page}));" aria-label="Previous"> 
												<span aria-hidden="true">&laquo;</span>
											</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="Previous">
											<a href="${pageContext.request.contextPath}/res/toList.htmlx?page=${pageVo.page-1}" 
											onclick="javascript:void(query(${page-1}));" aria-label="Previous"> 
												<span aria-hidden="false">&laquo;</span>
											</a>
										</li>
									</c:otherwise>
								</c:choose>
							
							
							<c:forEach begin="1" end="${pageVo.totalPage }" step="1" var="item">
								<li class="active">
									<a href="${pageContext.request.contextPath}/res/toList.htmlx?page=${item}">${item}<span class="sr-only">(current)</span></a>
								</li>
								<%-- <li><a href="javascript:void(query(${item+1}));">${item+1}</a></li>
								<li><a href="javascript:void(query(${item+2}));">${item+2}</a></li>
								<li><a href="javascript:void(query(${item+3}));">${item+3}</a></li>
								<li><a href="javascript:void(query(${item+4}));">${item+4}</a></li> --%>
							</c:forEach>
							
							
								<c:choose>
									<c:when test="${pageVo.page < pageVo.totalPage}">
										<li class="Previous">
											<a href="${pageContext.request.contextPath}/res/toList.htmlx?page=${pageVo.page+1}" 
												onclick="javascript:void(query(${page+1}));" aria-label="Next"> 
												<span aria-hidden="false">&raquo;</span>
											</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="disabled">
											<a href="${pageContext.request.contextPath}/res/toList.htmlx?page=${pageVo.totalPage}" 
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
	</div>
</body>
</html>