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
	//点击属性
	function show_attr_search(attr_id,shxzh_id,attr_shxmch) {
		var b ="<span id='span_"+attr_id+"' style='cursor:pointer'>";
		var a ="<a href='javascript:down_attr_search("+attr_id+")'>"+attr_shxmch+"</a>";
		var d = "<input type='hidden' name='attr_param' value='{\"attr_id\":"+attr_id+",\"shxzh_id\":"+shxzh_id+"}'>";//id_id
		var c ="</span>";
	

		$("#show_attr").append(b+a+d+c);
		//隐藏属性的列表
		$("#attr_list_"+attr_id).hide();
		
		
		attr_search();

	}
	//在列表点击，取消属性值，回显属性值组
	function down_attr_search(attr_id){
		//删除列表属性，显示下列属性
		$("#span_"+attr_id).remove();
		
		$("#attr_list_"+attr_id).show();
		attr_search();
	}
	//异步提交数据
	function attr_search(){
		//先获取参数
// 		array_list = new Array();
		
		class_2_id = ${class_2_id};
		param = "class_2_id="+class_2_id;
		
		var att_param_list =  $(":input[name='attr_param']").each(function(i,n){
// 			var att_param_list = n.value;
// 			array_list[i]=att_param_list;
			var obj = $.parseJSON(n.value);
			param =param + "&list_av["+i+"].shxm_id="+obj.attr_id+"&list_av["+i+"].shxzh_id="+obj.shxzh_id; 	
		});
		$.ajax({
			url : "attr_value_search.do?"+param,
			type : "GET",
			data : {
			},	
			beforeSend : function(){
				return true;
			},
			success : function(data){
				$("#sku_by_attr_search").html(data);
			}
		});
	}
	//点击多选，复选框显示
	function attr_grand(attr_id){
		$("#attr_list_"+attr_id).hide();
		$("#dx_attr_list_"+attr_id).show();
	}
	//点击，消失
	function attr_close(attr_id){
		$("#dx_attr_list_"+attr_id).hide();
		
		var attr_checked = $(":checkbox[name=checkbox_"+attr_id+"]");
		$(attr_checked).each(function(i,n){
			this.checked=false;
		})
		
		$("#attr_list_"+attr_id).show();
	}
	
	function sure_check(attr_id){
		var attr_checked = $(":checkbox[name=checkbox_"+attr_id+"]:checked");
		
		var b ="<span id='span_"+attr_id+"' style='cursor:pointer'>";
		
		var attr_shxmch =$("#checkbox_"+attr_id).value;
		alert(attr_shxmch)
		var a ="<a href='javascript:down_attr_search("+attr_id+")'>"+6666+"</a>";
		
		var d = " ";
		$(attr_checked).each(function(i,n){
			alert(n.value)
			d =d + "<input type='hidden' name='attr_param' value='{\"attr_id\":"+attr_id+",\"shxzh_id\":"+n.value+"}'>";//id_id
				
		})
		var c ="</span>";
		$("#show_attr").append(b+a+d+c);
		
		attr_search();
	}
</script>
<title>硅谷商城</title>
</head>
<body>
	属性列表区域
	<div id="show_attr"></div>
	<c:forEach items="${attr_list}" var="attr" varStatus="index">
		${attr.shxm_mch}:
		<div id="attr_list_${attr.id}">
			<c:forEach items="${attr.attr_values}" var="value">
				<a href="javascript:show_attr_search(${attr.id},${value.id },'${value.shxzh}${value.shxzh_mch }');">${value.shxzh}${value.shxzh_mch }</a>							
			</c:forEach>			
		</div>
		<div id="dx_attr_list_${attr.id}" style="display:none;">
			<c:forEach items="${attr.attr_values}" var="value">
				<input type="checkbox" name="checkbox_${attr.id }" value="${value.id }">${value.shxzh}${value.shxzh_mch }				
			</c:forEach>
			<br>
			<a href="javascript:;" onclick="sure_check(${attr.id})">确定</a>		
			<a href="javascript:;" onclick="attr_close(${attr.id})">取消</a><br>			
		</div>		
		<a href="javascript:;" onclick="attr_grand(${attr.id})">多选</a><br>	
	</c:forEach>

</body>
</html>