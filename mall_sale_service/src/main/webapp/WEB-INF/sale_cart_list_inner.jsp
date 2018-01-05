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
	购物车列表页<br>
	<c:forEach items="${list_cart }" var="cart">
		<input type="checkbox" ${cart.shfxz == "1"?"checked":""} onclick="javascript:change_status(${cart.sku_id},this.checked);">
		库存的名称 :${cart.sku_mch}
		价格：￥ ${cart.sku_jg}
		数量 ： ${cart.tjshl}
		合计  ： ${cart.hj }	
		状态  ： ${cart.shfxz }	
		<br>
	</c:forEach>
	<br>

	总金额： ${sum}
	

</body>
</html>