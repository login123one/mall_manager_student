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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function show_sku_attr_cleck(attr_id,check){
		if(check){
			$("#sku_attr_id_"+attr_id).show();
		}else{
			$("#sku_attr_id_"+attr_id).hide();
		}
		
		
	}
</script>
<title>硅谷商城</title>
</head>
<body>
	<hr>
	属性列表
	<c:forEach items="${attr_list_sku}" var="attr" varStatus="index">
		<input type="checkbox" value="${attr.id}" name="list_av[${index.index }].shxm_id" onchange="show_sku_attr_cleck(${attr.id},this.checked)">${attr.shxm_mch}
	</c:forEach>
	<hr>
	属性值组	
	<c:forEach items="${attr_list_sku}" var="attr" varStatus="index">
		<div id="sku_attr_id_${attr.id }" style="display: none;">
			${attr.shxm_mch}:	
			<c:forEach items="${attr.attr_values}" var="val" >
				<input type="radio" name="list_av[${index.index}].shxzh_id" value="${val.id }">${val.shxzh }${val.shxzh_mch}
			</c:forEach>
		</div>	
	</c:forEach>
	<hr>
	商品库存名称<input type="text" name="sku_mch"/><br>
	商品库存数量<input type="text" name="kc"><br>
	商品库存价格<input type="text" name="jg"><br>
	商品库存地址<input type="text" name="kcdz"><br>
	<input type="submit" value="提交">
</body>
</html>