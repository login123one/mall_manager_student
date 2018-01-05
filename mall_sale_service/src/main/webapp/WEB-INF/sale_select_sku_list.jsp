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
	
	<c:forEach items="${sku_list}" var="sku">
		<div style="border:red 1px solid;height:350px;width:280px;margin-left:10px;margin-top:10px;float:left;">
			${sku.tm.ppmch}	
			
			<img alt="" src="upload/image/${sku.spu.shp_tp}" width="250px" height="200px">
			<br>
			<a href="sku_detail.do?sku_id=${sku.id }&spu_id=${sku.spu.id}">${sku.spu.shp_mch}</a><br>
			价格：${sku.jg}<br>
			库存：${sku.kc}<br>
			属性 ：${sku.sku_mch }
			
		</div>
	</c:forEach>
	

</body>
</html>