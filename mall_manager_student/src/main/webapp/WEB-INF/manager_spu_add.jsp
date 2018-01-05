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
	var rem_conut = 0;

	function image_click(index){
		$("#spu_file_"+index).click();
	}
	
	function show_image(index){
		
		//获取加载之后的图片缓存
		var file = $("#spu_file_"+index)[0].files[0];
		//转化为js的URL对象
		var url = window.URL.createObjectURL(file);
		//用URL替换src标签的内容
		$("#spu_tu_"+index).attr("src",url);
		
		var img_length = $(":file").length;
		
		if((index+1)==(img_length + rem_conut)){
			add_image(index);
			var image_remove ='<a href="javascript:;"  onclick="remove_image('+index+')">删除图片</a>';
			$("#img_div_"+index).append(image_remove);
		}
		
		
	}
	
	function add_image(index){

		var div_head = '<div id="img_div_'+(index+1)+'" style="border:1px red solid"> ';
		var image = '商品的图片<img  id="spu_tu_'+(index+1)+'" src="image/upload_hover.png" name="shp_tp" width="100px;" height="100px;" style="cursor: pointer;" onclick="image_click('+(index+1)+')" > ';
		var input = '<input id="spu_file_'+(index+1)+'" type="file" name="files" onchange="show_image('+(index+1)+')"> ';
		var div_foot = '</div>';
		
		$("#img_div_"+index).after(div_head+image+input+div_foot);	
		
		
	}
	
	function remove_image(index){
		alert(111);
		rem_conut = rem_conut+1; 
		$("#img_div_"+index).remove();
		
	}
	
</script>
	添加商品信息<br>
	分类静态列表<br><br>
	
	<form action="save_spu.do" method="post" enctype="multipart/form-data">
		<jsp:include page="manager_spu_select.jsp"></jsp:include><br><br>
		
		商品的名称<input type="text" name="shp_mch"><br><br>
		商品的描述<textarea rows="" cols="" name="shp_msh"></textarea><br><br>
		<div id="img_div_0" style="border:1px red solid">
			商品的图片<img  id="spu_tu_0" src="image/upload_hover.png" name="shp_tp" width="100px;" height="100px;" 
			style="cursor: pointer;" onclick="image_click(0)">
			<input id="spu_file_0" type="file" name="files" onchange="show_image(0)">
		</div>
		<input type="submit" value="spu发布">
	</form>	
</body>
</html>