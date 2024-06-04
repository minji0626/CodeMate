<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 정보</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css">
	<link rel="stylesheet"
   href="${pageContext.request.contextPath}/css/findIdPw.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<div class="page-main">
	<div id="login_logo">
				<a href="${pageContext.request.contextPath}/main/main.do"
					class="logo"> <img id="logo_pic_login"
					src="${pageContext.request.contextPath}/images/로고1.png" height="80"
					width="80"> CODEMATE
				</a>
			</div>
	
	<div class="content-main">
	<c:if test="${auth == 1}">
		<h2>회원 정보</h2>
		<div class="result-display">
			정지된 회원 ID입니다.<br>
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</c:if>
	
	<c:if test="${member == null}">
	<script type="text/javascript">
		alert('아이디 또는 비밀번호가 불일치합니다.');
		history.go(-1);
	</script>
	</c:if>
</div>
</div>
</body>
</html>
	