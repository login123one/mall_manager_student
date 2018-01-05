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
	function cart_submit(){
		$("#add_cart").submit();
	}
</script>
<title>硅谷商城</title>
</head>
<body>
	库存名称：${sku_detail.sku_mch }<br>
	价格：${sku_detail.jg }<br>
	库存：${sku_detail.kc }<br>
	<hr>
	${sku_detail.spu.shp_mch }
	<br>
	<c:forEach items="${sku_detail.list_av_name }" var="attr">
		${attr.shxm_mch }:
		${attr.shxzh }
		${attr.shxzh_mch }<br>
	</c:forEach>
	
	<div>
		<form action="add_cart.do" method="post" id="add_cart">
			<input type="hidden" name="sku_mch" value="${sku_detail.sku_mch }" >
			<input type="hidden" name="sku_jg" value="${sku_detail.jg }" >
			<input type="hidden" name="tjshl" value="1" >
			<input type="hidden" name="hj" value="${sku_detail.jg }" >
			<c:if test="${not empty user }">
				<input type="hidden" name="yh_id" value="${user.id }" >
			</c:if>
			<input type="hidden" name="shp_id" value="${sku_detail.shp_id }" >
			<input type="hidden" name="sku_id" value="${sku_detail.id }" >
			<input type="hidden" name="shp_tp" value="${sku_detail.spu.shp_tp }" >
			<input type="hidden" name="kcdz" value="${sku_detail.kcdz }" >
			<input type="hidden" name="shfxz" value="1" />
			<img src="images/shop.jpg" onclick="cart_submit()" alt="" style="cursor:pointer;">
		</form>
	
	</div>
	
	<hr>
	<c:forEach items="${sku_detail.list_image}" var="img">
		图片：<img alt="" src="upload/image/${img.url }">
	</c:forEach>
	<hr>
	<br>
	<c:forEach items="${get_sku_list }" var="sku_list">
		 <a href="sku_detail.do?sku_id=${sku_list.id }&spu_id=${sku_list.shp_id}"> 
			${sku_list.sku_mch }
		 </a><br>
	</c:forEach>
	
	
	
	
</body>
</html>