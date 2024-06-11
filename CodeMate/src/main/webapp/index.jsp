<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	response.sendRedirect(request.getContextPath()+"/main/main.do");
%>   
 
<input class="tryAgain_btn" type="button" value="계정 찾기" 
						onclick="location.href='${pageContext.request.contextPath}/main/main.do'">