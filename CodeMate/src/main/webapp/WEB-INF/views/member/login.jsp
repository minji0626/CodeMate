<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 정보</title>
<link href="${pageContext.request.contextPath}/images/로고1.png"
	rel="shortcut icon" type="image/x-icon">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/findIdPw.css"
	type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
	<div class="page-container">

		<c:if test="${auth == 1}">
			<script type="text/javascript">
				alert('정지된 계정입니다. 로그인이 불가합니다.');
				location.href = "${pageContext.request.contextPath}/main/main.do";
			</script>
		</c:if>

		<c:if test="${auth != 1}">
			<script type="text/javascript">
				alert('아이디 또는 비밀번호가 불일치합니다.');
				history.go(-1);
			</script>
		</c:if>

		<div id="login_logo">
			<a href="${pageContext.request.contextPath}/main/main.do"
				class="logo"> <img id="logo_pic_login"
				src="${pageContext.request.contextPath}/images/로고1.png" height="80"
				width="80"> CODEMATE
			</a>
		</div>
		<div class="content-main"></div>

	</div>
</body>
</html>
