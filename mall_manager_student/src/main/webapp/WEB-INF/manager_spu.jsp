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
	function b(){}
</script>
<title>硅谷商城</title>
</head>
<body >
	   
        <div class="easyui-layout" data-options="fit:true">
           	<div data-options="region:'north',split:true" style="height:50px"></div>
            <div data-options="region:'west',split:true" style="width:100px">
            	商品信息查询<br>
				<a href="javascript:add_tab('goto_spu_add.do','商品信息添加');">商品信息添加</a><br>
            </div>   
            <div data-options="region:'center'"  style="width:100px">
            	  6666666666   
            </div>   
        </div>    


	
	
</body>
</html>