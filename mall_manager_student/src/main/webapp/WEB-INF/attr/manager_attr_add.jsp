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
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>

<title>硅谷商城</title>
</head>
<body>

<script type="text/javascript">
	function add_attr_value_row(){
		var add_attr_value_row ='<tr><td>属性值：</td><td><input type="text" name=""></td><td>单位：</td><td><input type="text" name=""></td><td><a onclick="add_attr_value_row()">添加属性值</a></td></tr>'
		$("#table_1").append(add_attr_value_row);
		
	}
</script>


	${class_2_name}属性的添加页面
	<form action="save_attr.do" method="post">
		<input type="hidden" name="class_2_id" value="${class_2_id}"> 
		<input type="hidden" name="class_2_name" value="${class_2_name}"> 
		<table border="1" id="table_1">
			<tr>
				<td>属性名:</td>
				<td><input type="text" name="list_attr_set[0].shxm_mch"></td>
				<td colspan="2"></td>	
			</tr>
			<tr>
				<td>属性值:</td>
				<td><input type="text" name="list_attr_set[0].attr_values[0].shxzh"></td>
				<td>单位:</td>
				<td><input type="text" name="list_attr_set[0].attr_values[0].shxzh_mch"></td>
				<td><a onclick="add_attr_value_row()">添加属性值</a></td>
			</tr>
		</table>
		<input type="submit" value="提交">
	</form>
	

</body>
</html>