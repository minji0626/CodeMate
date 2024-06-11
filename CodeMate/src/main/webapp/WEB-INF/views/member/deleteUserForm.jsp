<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>
<link href="${pageContext.request.contextPath}/images/로고1.png" rel="shortcut icon" type="image/x-icon">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" type="text/css">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
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
		<div id="login_logo" class="delete">
			<a href="${pageContext.request.contextPath}/main/main.do" class="logo"> 
			<img id="logo_pic_login" src="${pageContext.request.contextPath}/images/로고1.png" height="80" width="80"> CODEMATE
			</a>
		</div>
		
		<div class="delete_user">
		<form id="login_form" action="deleteUser.do" method="post"><!-- login에서 폼 가져온거라 action만 logout.do로 보냄 -->
		<input type="hidden" name="mem_num" value="${mem_num}">
			<ul id="insert">
				<li class="floating-label">
					<input type="text" class="form-input" placeholder="아이디" 
					name="id" id="id" maxlength="12" autocomplete="off">
					<label for="id"></label>
				</li>
				<li class="floating-label">
					<input type="password" class="form-input" placeholder="비밀번호" 
					name="passwd" id="passwd" maxlength="12">
					<label for="passwd"></label>
				</li>
			</ul>
			<div class="align-center">
				<input type="submit" value="회원탈퇴" id="login_btn">
				<input type="button" value="My페이지" onclick="location.href='myPage.do'" id="login_btn"><!-- id는 css디자인 있으면 사용하려고 넣음 -->
			</div>
		</form>	
	</div>
	
</body>
</html>