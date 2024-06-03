<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID 찾기</title>
<link rel="stylesheet"
   href="${pageContext.request.contextPath}/css/share.css" type="text/css">
<link rel="stylesheet"
   href="${pageContext.request.contextPath}/css/findIdPw.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<div class="page-container">
<div class="page-main">
			<!-- 헤더 -->
			<div id="login_logo">
				<a href="${pageContext.request.contextPath}/main/main.do"
					class="logo"> <img id="logo_pic_login"
					src="${pageContext.request.contextPath}/images/로고1.png" height="80"
					width="80"> CODEMATE
				</a>
			</div>
				<div>
					회원님의 아이디는 <b>[${id}]</b> 입니다.
				</div>
				<div class="align-center">
				<input id="login_btn" type="submit" value="로그인">
			</div>
</div>
</div>
</body>
</html>