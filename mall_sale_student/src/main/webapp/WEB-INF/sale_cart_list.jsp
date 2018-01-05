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

	function change_status(sku_id,checked){
		var status ="0";
		if(checked){
			status="1";
		}
		
		$.post("change_status.do",{sku_id:sku_id,shfxz:status},function(data){
			$("#cart_list_by_change_status").html(data);
		});
	}

</script>
<title>硅谷商城</title>
</head>
<body>
	<div id="cart_list_by_change_status">
 		<jsp:include page="sale_cart_list_inner.jsp"></jsp:include>
	</div>
	<hr>
	
 		<a href="goto_check_account.do">去结算</a>

</body>
</html>