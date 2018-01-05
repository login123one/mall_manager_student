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
	$(function(){
		//获取cookie
		var yh_nch = get_cookie_value("yh_nch");
		$("#cookie_value").append(yh_nch);
		
	});
	
	function get_cookie_value(key){
		//获取cookie的值
		var value="";
		var cookies = document.cookie;
		//替换其中的空格
		cookies = cookies.replace(" ","");
		//并且用;分隔成字符数组
		cookies_list = cookies.split(";");
		for(var i=0;i<cookies_list.length;i++){
			var cookie = cookies_list[i].split("=");
			if(cookie[0]==key){
				value = cookie[1];
			}
		}
		var return_value = decodeURIComponent(value);
		return return_value;
		
		
	}
</script>
<title>硅谷商城</title>
</head>
<body>
	
			<c:if test="${empty user }">
			<div id="cookie_value"></div>
				<a href="goto_login.do">你好,请登录</a>
				<a href="goto_register.do">免费注册</a>
			</c:if>
			<c:if test="${not empty user }">
				<a href="javascript:;">欢迎 ${user.yh_nch}, 我的订单</a>
				<a href="sale_logout.do">注销</a> 
			</c:if>
</body>
</html>