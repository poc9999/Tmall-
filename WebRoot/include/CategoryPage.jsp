<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  
  <body>
   	<div align="center">
    	<ul class="pager">
	    	<li><a href="#">&laquo;</a></li>
	    	<li><a href="${pageContext.request.contextPath}/admin_tmall/categorylist.action?type=first&crud=select">第一页</a></li>
	    	<li><a href="${pageContext.request.contextPath}/admin_tmall/categorylist.action?type=prev&crud=select">上一页</a></li>
	    	<li><a href="${pageContext.request.contextPath}/admin_tmall/categorylist.action?type=next&crud=select">下一页</a></li>
	    	<li><a href="${pageContext.request.contextPath}/admin_tmall/categorylist.action?type=last&crud=select">最末页</a></li>
	    	<li><a href="#">&raquo;</a></li>
	    	<li>当前第${page.currentPage}页，共${page.totalPage}页;</li>
    	</ul>
    </div>
  </body>
</html>
