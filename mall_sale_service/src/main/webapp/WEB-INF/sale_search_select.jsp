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
		$.ajax({
			url : "js/json/class_1.js",
			type : "GET",
			dataType: "json",
			data : {},
			beforeSend : function(){
				return true;
			},
			success : function(data){
				$.each(data,function(i,n){
					$("#class_1_select_spu").append("<li onmouseover='get_class_2_select(this.value)' value="+n.id+">"+n.flmch1+"</li>");	
				})
			}
			
		});		
	});
	
	function get_class_2_select(class_1_id){
		$.ajax({
			url : "js/json/class_2_"+class_1_id+".js",
			type : "GET",
			dataType: "json",
			data : {},
			beforeSend : function(){
				return true;
			},
			success : function(data){
				$("#class_2_select_spu").empty();
				$.each(data,function(i,n){
					$("#class_2_select_spu").append("<li value="+n.id+"><a href='goto_class_select.do?class_2_id="+n.id+"&class_2_name="+n.flmch2+"' target='_blank'>"+n.flmch2+"</a></li>");	
				})
			}
			
		});		
		
	}
</script>
<title>硅谷商城</title>
</head>
<body>
	
	<ul style="float:left;margin-left:10px;" name="flbh1" id="class_1_select_spu" ></ul>
	<ul  style="float:left;" name="flbh2" id="class_2_select_spu" ></ul>

</body>
</html>