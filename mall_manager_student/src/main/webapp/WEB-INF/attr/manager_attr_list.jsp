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
	function goto_add_attr(){

		var class_2_id = $("#class_2_select_spu").val();
		var class_2_name = $("#class_2_select_spu option:selected").text();
		
// 		window.location.href="goto_add_attr.do?class_2_id="+class_2_id+"&class_2_name="+class_2_name;
		
		add_tab("goto_add_attr.do?class_2_id="+class_2_id+"&class_2_name="+class_2_name,"属性的添加页面");
	}
</script>
<title>硅谷商城</title>
</head>
<body>
	<h1>属性列表分类</h1>
	
	<a href="javascript:goto_add_attr();">添加分类的属性</a><br>

	
	<c:forEach items="${attt_values}" var="attr_1" >
		${attr_1.shxm_mch}
		<c:forEach items="${attr_1.attr_values}" var="values">
			${values.shxzh}${values.shxzh_mch}
		</c:forEach>
		<br>
	</c:forEach>
	
	

</body>
</html>