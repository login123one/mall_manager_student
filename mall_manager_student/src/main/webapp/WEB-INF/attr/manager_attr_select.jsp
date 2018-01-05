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

<title>属性</title>
</head>
<body>
	
	
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
					$("#class_1_select_spu").append("<option value="+n.id+">"+n.flmch1+"</option>");	
				})
			}
			
		});
	});
	
	function attr_get_class_2(id){
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
	function get_attr_list(id){
		$.ajax({
			url : "get_attr_list.do",
			type : "post",
			dataType: "html",
			data : {
				class_2_id : id
			},
			beforeSend : function(){
				return true;
			},
			success : function(data){
				$("#attr_list").html(data);	
			}
		});
		//通过空间加载列表属性
		$('#attr_list').datagrid({    
		    url:'get_attr_list_json.do', 
		    queryParams :{
		    	class_2_id : id
		    },
		    columns:[[    
		        {field:'id',title:'属性编号',width:100},    
		        {field:'shxm_mch',title:'属性名称',width:100},   
		        {field:'chjshj',title:'创建时间',width:300,
		        	formatter: function(value,row,index){
		  				var date = new Date(value);
		  				return date.toLocaleString();
					}
		        },    
		        {field:'attr_values',title:'属性值',width:300,
		        	formatter: function(value,row,index){
		  				var name="";
						$(value).each(function(i,n){
							name=name+" "+ n.shxzh+n.shxzh_mch;	
						})
						return name;
					}
		        }    
		    ]]    
		});
	}
</script>
	一级分类<select name="flbh1" id="class_1_select_spu" onchange="attr_get_class_2(this.value)"></select>
	二级分类<select  name="flbh2" id="class_2_select_spu" onchange="get_attr_list(this.value)"></select><br>
	属性列表区域
	<div id="attr_list">
	
	</div>
</body>
</html>