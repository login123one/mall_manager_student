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
	一级分类<select name="flbh1" id="class_1_select_sku" onchange="sku_get_class_2(this.value)">
				<option>请选择</option>
			</select>
	二级分类<select name="flbh2" id="class_2_select_sku" onchange="sku_get_attr_list(this.value)">
			<option>请选择</option>
		</select>
	商标  <select name="pp_id" id="tm_select_sku" onchange="sku_get_shp_by_id(this.value)">
			<option>请选择</option>
		</select>
	商品名称<select name="shp_id" id="shp_select_sku">
			<option>请选择</option>
		</select>
	
	<script type="text/javascript">
	
		function sku_get_attr_list(id){
			$.ajax({
				url : "sku_get_attr_list.do",
				type : "post",
				dataType: "html",
				data : {
					class_2_id : id
				},
				beforeSend : function(){
					return true;
				},
				success : function(data){
					$("#sku_attr_list").html(data);	
				}
			});	
		}
	
	
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
						$("#class_1_select_sku").append("<option value="+n.id+">"+n.flmch1+"</option>");	
					})
				}		
			});
			
		});
		
		function sku_get_class_2(id){
			$("#class_2_select_sku").empty();
			
			$("#class_2_select_sku").append("<option>请选择</option>");
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
						$("#class_2_select_sku").append("<option value="+n.id+">"+n.flmch2+"</option>");	
					})
				}
				
			});	
			
			sku_get_tm_class_1(id);
		}
		function sku_get_tm_class_1(id){
			$("#tm_select_sku").empty();
			$("#tm_select_sku").append("<option>请选择</option>");
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
						$("#tm_select_sku").append("<option value="+n.id+">"+n.ppmch+"</option>");	
					})
				}
				
			});	
		}
		
		function sku_get_shp_by_id(tm_id){
			var class_1_id = $("#class_1_select_sku").val();
			var class_2_id = $("#class_2_select_sku").val();
			
			$.ajax({
				url : "sku_get_shp.do",
				type : "GET",
				dataType: "json",
				data : {
					pp_id : tm_id,
					flbh1 : class_1_id,
					flbh2 : class_2_id					
				},
				beforeSend : function(){
					return true;
				},
				success : function(data){
					$("#shp_select_sku").empty();
					
					$.each(data,function(i,n){
						$("#shp_select_sku").append("<option value="+n.id+">"+n.shp_mch+"</option>");	
					})
				}
				
			});
			
		}
	</script>
</body>
</html>