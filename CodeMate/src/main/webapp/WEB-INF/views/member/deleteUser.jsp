<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${check==1}">
	<!DOCTYPE html>
	<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<link href="${pageContext.request.contextPath}/images/로고1.png"
	rel="shortcut icon" type="image/x-icon">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/findIdPw.css"
	type="text/css">
</head>
<body>
	<div id="login_logo">
		<a href="${pageContext.request.contextPath}/main/main.do" class="logo">
			<img id="logo_pic_login"
			src="${pageContext.request.contextPath}/images/로고1.png" height="80"
			width="80"> CODEMATE
		</a>
	</div>
	<hr size="1px" class="line">
	<span class="check">회원탈퇴가 완료되었습니다.</span>
	<p>
	<input class="tryAgain_btn" type="button" value="홈으로" 
			onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
</body>
	</html>
</c:if>
<c:if test="${check==0}">
	<script>
		alert('입력한 정보가 정확하지 않습니다.');
		history.go(-1);
	</script>
</c:if>