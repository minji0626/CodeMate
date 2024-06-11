<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<link href="${pageContext.request.contextPath}/images/로고1.png" rel="shortcut icon" type="image/x-icon">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css" type="text/css">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/register.js"></script>
</head>
<body>
<div class="page-container">
		<span id="greeting">팀원 구하기 제일 쉬운 곳 코메!</span>		
		<div id="login_logo">
			<a href="${pageContext.request.contextPath}/main/main.do" class="logo"> 
			<img id="logo_pic_login" src="${pageContext.request.contextPath}/images/로고1.png" height="80" width="80"> CODEMATE
		</a>
		</div>

			<form id="register_form" action="registerUser.do" method="post">
				<ul id="insert_register">
					<li class="info">
						<label for="mem_id">아이디</label> 
						<input type="text" name="mem_id" id="mem_id" maxlength="12" autocomplete="off" class="input-check">
						<div id="message_id" class="error-message"></div> <!-- 수정 -->
						<div class="form-notice">*영문 또는 숫자(4자~12자)</div>
		
					<li class="info">
						<label for="name">이름</label> 
						<input type="text" name="name" id="name" maxlength="10" class="input-check">
					</li>
					<li class="info">
					<label for="passwd">비밀번호</label> 
						<input type="password" name="passwd" id="passwd" maxlength="12" class="input-check">
					</li> 
					 <li class="info">
                   	 	<label for="mem_nickname" class="form_label">닉네임</label>
                    	<input type="text" id="mem_nickname" name="mem_nickname" maxlength="20" class="input-check">
                    	<div id="message_nickname" class="error-message"></div>
                    	<!-- 닉네임 중복체크 -->
                	</li>
					<li class="info">
					<label for="mem_email">이메일</label> 
						<input type="email" name="mem_email" id="mem_email" maxlength="50" class="input-check">
						<div id="message_email" class="error-message"></div> <!-- 수정 -->
					</li>
					
					<li class="info">
					<label for="mem_phone">전화번호</label> 
						<input type="text" name="mem_phone" id="mem_phone" maxlength="30" class="input-check">
						<div id="message_phone" class="error-message"></div> <!-- 수정 -->
					</li>
				</ul>
				
				<div id="btns" class="align-center">
					<input id="reg_btn" type="submit" value="회원 가입"> 
					<input id="cancel_btn" type="button" value="취소" onclick="window.location.href='${pageContext.request.contextPath}/main/main.do'">
				</div>
			</form>
	</div>
</body>
</html>