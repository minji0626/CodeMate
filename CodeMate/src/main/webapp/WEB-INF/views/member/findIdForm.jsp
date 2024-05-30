<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/cyy.css" type="text/css">
</head>
<body>
	<div class="page-container">
		<div class="page-main">
			<div id="login_logo">
				<a href="${pageContext.request.contextPath}/main/main.do"
					class="logo"> <img id="logo_pic_login"
					src="${pageContext.request.contextPath}/images/로고1.png" height="80"
					width="80"> CODEMATE
				</a>
			</div>
			<form id="idForm">
				<input type="email" id="emailInput" placeholder="이메일 주소" required>
				<button type="submit">아이디 찾기</button>
			</form>
			<div id="result"></div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/js/findeId.js"></script>
</body>
</html>