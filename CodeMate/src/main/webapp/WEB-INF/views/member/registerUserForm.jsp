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
<script type="text/javascript">
$(function(){
let emailChecked = 0;
let idChecked = 0;     //0:중복,아이디 중복 체크 미실시,1:미중복
let phoneChecked = 0;
let nicknameChecked = 0;

	$('#id').on('blur', function() {
	//아이디 중복 체크
		if(!/^[A-Za-z0-9]{4,12}$/.test($('#id').val())){
			$('#message_id').text('영문 또는 숫자 사용, 최소 4자 ~ 최대 12자 사용');
			$('#id').val('').focus();
			return;
		}	
		//서버와 통신
		$.ajax({
			url:'checkDuplicatedId.do',
			type:'post',
			data:{id:$('#id').val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'idNotFound'){
					idChecked = 1;
					$('#message_id').css('color','black').text('등록 가능 ID');
				}else if(param.result == 'idDuplicated'){
					idChecked = 0;
					$('#message_id').css('color','red').text('중복된 ID');
					$('#id').val('').focus();
				}else{
					idChecked = 0;
					$('#message_id').text('아이디 중복 체크 오류 발생');
				}
			},
			error:function(){
				idChecked = 0;
				alert('네트워크 오류 발생');
			}
		});
		
	});//end of id_check
	
	
	
	//이메일 중복 체크
	$('#email').on('blur', function() {
		//서버와 통신
		$.ajax({
			url:'checkDuplicatedEmail.do',
			type:'post',
			data:{email:$('#email').val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'emailNotFound'){
					emailChecked = 1;
					$('#message_email').css('color','black').text('등록 가능 Email');
				}else if(param.result == 'emailDuplicated'){
					emailChecked = 0;
					$('#message_email').css('color','red').text('중복된 Email');
					$('#email').val('').focus();
				}else{
					emailChecked = 0;
					alert('이메일 중복 체크 오류 발생');
				}
			},
			error:function(){
				emailChecked = 0;
				alert('네트워크 오류 발생');
			}
		});
		
	});//end of email check
	
	/* 닉네임 중복 체크 */
	$('#mem_nickname').on('blur', function() {
		var nickname = $(this).val();
		// 서버에 닉네임 중복 확인 요청을 보냄
		$.ajax({
			url: 'checkDuplicatedNickname.do',
			method: 'post',
			data: { mem_nickname: nickname },
			dataType: 'json',
			success: function(param) {
				if (param.result == 'nicknameDuplicated') {
					nicknameChecked = 0;
					$('#message_nickname').text('이미 사용 중인 닉네임입니다.');
				} else if (param.result == 'nicknameNotFound') {
					nicknameChecked = 1;
					$('#message_nickname').text('사용 가능한 닉네임입니다.');
				} else {
					nicknameChecked = 0;
					$('#message_nickname').text('닉네임 중복 체크 중 오류 발생');
				}
			},
			error: function() {
				nicknameChecked = 0;
				$('#message_nickname').text('서버 오류가 발생했습니다.');
			}
		});
	});
	
	
	//전화번호 중복 체크
	$('#phone').on('blur', function() {
	 if(!/^[0-9]{11,12}$/.test($('#phone').val())){
			alert('숫자만 입력하세요!');
			$('#phone').val('').focus();
			return false;
		}
		//서버와 통신
		$.ajax({
			url:'checkDuplicatedPhone.do',
			type:'post',
			data:{phone:$('#phone').val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'phoneNotFound'){
					phoneChecked = 1;
					$('#message_phone').css('color','black').text('등록 가능 전화번호');
				}else if(param.result == 'phoneDuplicated'){
					phoneChecked = 0;
					$('#message_phone').css('color','red').text('중복된 전화번호');
					$('#phone').val('').focus();
				}else{
					phoneChecked = 0;
					alert('전화번호 중복 체크 오류 발생');
				}
			},
			error:function(){
				phoneChecked = 0;
				alert('네트워크 오류 발생');
			}
		});
		
	});//end of click
	
	
	
	
	//아이디 중복 안내 메시지 초기화 및 아이디 중복값 초기화
	$('#register_form #id').keydown(function(){
		idChecked = 0;
		$('#message_id').text('');
	});//end of keydown
	
	//이메일 중복 안내 메시지 초기화 및 이메일 중복값 초기화
	$('#register_form #email').keydown(function(){
		emailChecked = 0;
		$('#message_email').text('');
	});//end of keydown
	
	//전화번호 중복 안내 메시지 초기화 및 전화번호 중복값 초기화
	$('#register_form #phone').keydown(function(){
		phoneChecked = 0;
		$('#message_phone').text('');
	});//end of keydown
	
	//닉네임 중복 안내 메시지 초기화 및 닉네임 중복값 초기화
	$('#register_form #mem_nickname').keydown(function(){
		phoneChecked = 0;
		$('#message_nickname').text('');
	});//end of keydown
	
	
	//회원 정보 등록 유효성 체크
	$('#register_form').submit(function(){
		const items = document.querySelectorAll('.input-check');
		for(let i = 0;i<items.length;i++){
			if(items[i].value.trim()==''){
				const label = document.querySelector('label[for="'+items[i].id+'"]');
				alert(label.textContent + ' 항목은 필수 입력');
				items[i].value = '';
				items[i].focus();
				return false;
			}
			if(items[i].id == 'id' && !/^[A-Za-z0-9]{4,12}$/.test($('#id').val())){
				alert('영문 또는 숫자 사용, 최소 4자 ~ 최대 12자 사용');
				$('#id').val('').focus();
				return false;
			}
			if(items[i].id == 'id' && idChecked == 0){
				alert('아이디 중복 체크 필수');
				return false;
			}
			if(items[i].email == 'email' && emailChecked == 0){
				alert('이메일 중복 체크 필수');
				return false;
			}
			if(items[i].phone == 'phone' && phoneChecked == 0){
				alert('전화번호 중복 체크 필수');
				return false;
			}
		}
	});
	});
</script>
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
						<label for="id">아이디</label> 
						<input type="text" name="id" id="id" maxlength="12" autocomplete="off" class="input-check">
						<span id="message_id"></span>
						<li><div class="form-notice">*영문 또는 숫자(4자~12자)</div></li>
		
					<li class="info">
						<label for="name">이름</label> 
						<input type="text" name="name" id="name" maxlength="10" class="input-check">
					</li>
					<li class="info">
					<label for="passwd">비밀번호</label> 
						<input type="password" name="passwd" id="passwd" maxlength="12" class="input-check">
					</li>
					<li class="info">
						<label for="mem_nickname" class="form_label">닉네임</label><br>
                    	<input type="text" id="mem_nickname" name="mem_nickname" maxlength="20" class="input-check" value="${member.mem_nickname}">
               			<!-- 닉네임 중복체크 -->
                    	<span id="message_nickname"></span>
					</li>
					<li class="info">
					<label for="email">이메일</label> 
						<input type="email" name="email" id="email" maxlength="50" class="input-check">
						<span id="message_email"></span>
					</li>
					
					<li class="info">
					<label for="phone">전화번호</label> 
						<input type="text" name="phone" id="phone" maxlength="30" class="input-check">
						<span id="message_phone"></span>
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