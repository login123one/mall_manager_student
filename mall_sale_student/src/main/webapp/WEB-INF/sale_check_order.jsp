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
	function select_address(address_id,shjr){
		$("#select_address_div").empty();
		$("#select_address_div").append("配送地址 : <input type='hidden' value="+address_id+" name='address_id'> :"+shjr);
	}
</script>
<title>硅谷商城</title>
</head>
<body>
	结算页面
	<hr>
	地址的详情：<br>
		
	<c:forEach items="${list_address }" var="address">
		<input type="radio" name="address_radio" onclick="select_address(${address.id},'${address.shjr } : ${address.yh_dz}')"> 用户地区 ：${address.yh_dz }收件人 ： ${address.shjr }联系方式  ：${address.lxfsh }<br>
	</c:forEach>
	<hr>
	送货清单
	<c:forEach items="${order.list_flow}" var="flow" varStatus="index">
		<div style="border:red 1px solid;margin-top:10px;">
			配送的方式  : ${flow.psfsh } ${flow.psmsh }: ${index.count }
			<c:forEach items="${flow.list_info }" var="info">
				<img alt="" src="upload/image/${info.shp_tp}" width="100px" width="100px">
				商品的描述 ：${info.sku_mch}<br>	
				<input type="hidden" name="sku_shl" value="${info.sku_jg }">数量 ： ${info.sku_shl }<br>	
				<input type="hidden" name="sku_jg" value="${info.sku_jg }">价格 ：￥ ${info.sku_jg }<br>
				
			</c:forEach>
		</div>
	</c:forEach>
	<hr>
	
	应付金额：${sum }<br>
	
	<form action="save_order.do" method="post">
		<input type="hidden" name="zje" value="${sum}" >
		<div id="select_address_div"></div>	
		<input type="submit" value="提交订单">
	</form>
	
	

</body>
</html>