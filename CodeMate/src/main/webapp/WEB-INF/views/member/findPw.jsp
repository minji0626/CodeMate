<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<!-- 헤더 -->
			<div id="login_logo">
				<a href="${pageContext.request.contextPath}/main/main.do"
					class="logo"> <img id="logo_pic_login"
					src="${pageContext.request.contextPath}/images/로고1.png" height="80"
					width="80"> CODEMATE
				</a>
			</div>
			<hr size="1px" class="line">
			<c:if test="${ckPw ==1}">
				<div>
				<span class="check"><b>비밀번호 재설정</b></span>
					<form id="password_form" action="completePw.do" method="post">
						<input type="text" id="passwd" class="input-check" name="NewPw" placeholder="새 비밀번호" required>
						<input type="text" id="cpasswd" class="input-check" placeholder="새 비밀번호 확인" required>
						 <input type="submit" id="pw_btn" name="NewPwUpd" value="비밀번호 재설정">
						 <input class="tryAgain_btn" type="button" value="취소하기" 
						 id="pwCancel"
						onclick="location.href='${pageContext.request.contextPath}/member/findIdPwForm.do'">
					</form>
				</div>
			</c:if>
			<c:if test="${ckPw ==0}">
			<span class="check"><b>해당 계정을 찾을 수 없습니다</b></span>
			<span id="PwNotFound">정확한 아이디, 전화번호와 이메일을 입력하세요!</span>
			<input class="tryAgain_btn" type="button" value="계정 찾기" 
						onclick="location.href='${pageContext.request.contextPath}/member/findIdPwForm.do'">
			</c:if>
</div>
<script type="text/javascript">
$(function(){
	//아이디,비밀번호 유효성 체크
	$('#password_form').submit(function(){
		const items = document.querySelectorAll('.input-check');
		for(let i = 0;i<items.length;i++){
			if(items[i].value.trim()==''){
				const label = document.querySelector('label[for="'+items[i].id+'"]');
				alert(label.textContent + ' 항목은 필수 입력');
				items[i].value = '';
				items[i].focus();
				return false;
			}
		}//end of for
		
		if($('#passwd').val()!=$('#cpasswd').val()){
			alert('새 비밀번호와 새 비밀번호 확인이 불일치');
			$('#passwd').val('').focus();
			$('#cpasswd').val('');
			return false;
		}
	});//end of submit
	
	//새 비밀번호 확인까지 한 후 다시 새 비밀번호를 수정하려고 하면 새비밀번호 확인을 
	//초기화
	$('#passwd').keyup(function(){
		$('#cpasswd').val('');
	});
	
});
</script>
</body>
</html>