<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>客户列表</title>
<meta charset="utf-8"/>
<link rel="stylesheet" href="css/bootstrap.css"/> 
</head>
<body>
<div class="container-fluid">

	<%@include file="header.jsp"%>
	

	<br>
	<!-- 
	<form class="form-inline" action="userSearch" id="serchform" method="post">
		<div class="form-group">
			<input type="text" class="form-control" id="name" name="name" placeholder="用户名">
		</div>
		<a href="javascript:;" onclick="document.getElementById('serchform').submit();" type="submit" class="btn btn-primary">点击搜索</a>
	</form>
	 -->
	<br>
	
	<table class="table table-bordered table-hover">

	<tr>
		<th width="5%">ID</th>
		<th width="10%">角色</th>
		<th width="10%">用户</th>
		<th width="10%">内容</th>
		<th width="10%">操作时间</th>
		<th width="10%">浏览器</th>
		
		<th width="10%">来源IP</th>
	
	</tr>
	
	<c:forEach var="log" items="${logList}">
         <tr>
         	<td><p>${log.id}</p></td>
         	<td><p>${log.roleid==1?'管理员':'销售人员'}</p></td>
         	<td><p>${log.admins.username}</p></td>
         	<td><p>${log.logcontent}</p></td>
         	<td><p>${log.operatetime}</p></td>
           <td><p>${log.broswer}</p></td>
           <td><p>${log.ip}</p></td>
			
       	</tr>
     </c:forEach>
     
</table>

<br>${pageTool}<br>
</div>
</body>
</html>