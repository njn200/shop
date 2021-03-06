<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>登录</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
</head>

<!-- 设置背景图片 -->
<body >

	<div class="container">

		<c:if test="${msg!=null}"><div class="alert alert-danger text-center">${msg}</div></c:if>

		<form class="form-horizontal" action="login" method="post" style="margin-top:15%;">
			<h2 class="text-center">登录后台</h2>
			<div class="form-group">
				<div class="col-md-4 col-md-offset-4">
					<input type="text" class="form-control" style="height:auto;padding:10px;" placeholder="输入用户名" name="username" value="admin">
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-4 col-md-offset-4">
					<input type="password" class="form-control" style="height:auto;padding:10px;" placeholder="输入密码" name="password" value="admin">
				</div>
			</div>
			
      <div class="form-group"  style="margin-left: 400px">
   			<label class="radio-inline">
   			
            <input type="radio"  style="display:inline;"  value="0" name="role"   checked>管理员
   			</label>
   				&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
    	     <label class="radio-inline">
     	 		  <input type="radio"   style="display:inline;"    value="1"   name="role"  >销售人员
    	    </label>
     	
     	 </div>
			<div class="col-md-4 col-md-offset-4">			
				<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
			</div>
		</form>

	</div>

</body>
</html>
