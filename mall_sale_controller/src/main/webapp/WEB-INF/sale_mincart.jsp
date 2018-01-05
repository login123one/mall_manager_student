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
		$.post("mincart_list.do",function(data){
			$("#mincart_list").html(data);
		});
		
	})
	
	function show_mincart_list(){
		$("#mincart_list").show();
	}
	function hidden_mincart_list(){
		
		$("#mincart_list").hide();
	}
	
</script>
<title>硅谷商城</title>
</head>
<body>
	
		<a href="goto_cart_list.do" target="_blank" onmousemove="show_mincart_list()"  onmouseout="hidden_mincart_list()">我的购物车</a>
		<div id="mincart_list" style="display:none;"></div>

</body>
</html>