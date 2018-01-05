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
<title>硅谷商城</title>
</head>
<body>
	一级分类<select name="flbh1" id="class_1_select_spu" onchange="spu_get_class_2(this.value),spu_get_tm_class_1(this.value)"></select>
	二级分类<select name="flbh2" id="class_2_select_spu" ></select>
	商标<select name="pp_id" id="tm_select_spu"></select>
	<script type="text/javascript">
	
		$(function(){
			$("#class_1_select_spu").append("<option>请选择</option>"); 			
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
						$("#class_1_select_spu").append("<option value="+n.id+">"+n.flmch1+"</option>");	
					})
				}
				
			});
		});
		
		function spu_get_class_2(id){
			$("#class_2_select_spu").empty();
			
			$("#class_2_select_spu").append("<option>请选择</option>");
			$.ajax({
				url : "js/json/class_2_"+id+".js",
				type : "GET",
				dataType: "json",
				data : {},
				beforeSend : function(){
					return true;
				},
				success : function(data){
					
					$.each(data,function(i,n){
						$("#class_2_select_spu").append("<option value="+n.id+">"+n.flmch2+"</option>");	
					})
				}
				
			});	
		}
		function spu_get_tm_class_1(id){
			$("#tm_select_spu").empty();
			$("#tm_select_spu").append("<option>请选择</option>");
			$.ajax({
				url : "js/json/tm_class_1_"+id+".js",
				type : "GET",
				dataType: "json",
				data : {},
				beforeSend : function(){
					return true;
				},
				success : function(data){
					
					$.each(data,function(i,n){
						$("#tm_select_spu").append("<option value="+n.id+">"+n.ppmch+"</option>");	
					})
				}
				
			});	
		}
	</script>
</body>
</html>