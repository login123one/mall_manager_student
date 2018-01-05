<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

</script>
<title>硅谷商城</title>
</head>
<body>
	<div> </div>
	<div>欢迎来到6666商场登录页面</div><br>
	<a href="sale_index.do">主页</a>
	<div>
		<form action="sale_login.do" method="post" >
			用户名<input type="text" name="yh_mch" ><br>
			密码<input type="password" name="yh_mm"><br>
			数据源1 <input type="radio" name="dataSource_radio" value="d1">			
			数据源2 <input type="radio" name="dataSource_radio" value="d2">			
			<input type="submit" value="提交">	
		</form>
		<a href="">快速注册 </a>	
	</div>

</body>
</html>