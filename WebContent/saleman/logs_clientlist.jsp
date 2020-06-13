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

<%@include file="saleheader.jsp" %>
	

	<br>
	
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
         	<td><p>${log.users.username}</p></td>
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