<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css">
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/share.css" type="text/css">
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/cyy.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#login_form').submit(function(){
		if($('#id').val().trim()==''){
			alert('아이디를 입력하세요');
			$('#id').val('').focus();
			return false;
		}
		if($('#passwd').val().trim()==''){
			alert('비밀번호를 입력하세요');
			$('#passwd').val('').focus();
			return false;
		}
	})
});
</script>
</head>
<body>
<div class="page-main">

	<div id="login_header" class="header">		
		<div id="login_logo">
		<a href="${pageContext.request.contextPath}/main/main.do"
			class="logo"> <img id="logo_pic_login"
			src="${pageContext.request.contextPath}/images/로고1.png" height="80"
			width="80"> CODEMATE
		</a>
		</div>
		</div>
		
		
	<div class="content-main">
		<form id="login_form" action="login.do" method="post">
			<ul id="insert">
				<li class="floating-label">
					<ul><li>아이디</li></ul>
					<input type="text" class="form-input" placeholder="아이디" 
					name="id" id="id" maxlength="12" autocomplete="off">
					<label for="id"></label>
				</li>
				<li class="floating-label">
					<ul><li>비밀번호</li></ul>
					<input type="password" class="form-input" placeholder="비밀번호" 
					name="passwd" id="passwd" maxlength="12">
					<label for="passwd"></label>
				</li>
			</ul>
			<div class="align-center">
				<input id="login_btn" type="submit" value="로그인">
			</div>
			<div id="find">
				<a href=#>아이디</a>/<a href=#>비밀번호</a> 찾기
			</div>
			<div id="sign_up">
				코드메이트가 처음이세요? <a href=${pageContext.request.contextPath}/member/registerUserForm.do><b>회원가입</b></a>
			</div>
		</form>	
	</div>
</div>
</body>
</html>