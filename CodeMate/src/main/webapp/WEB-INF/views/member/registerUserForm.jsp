<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
	let idChecked = 0;     //0:중복,아이디 중복 체크 미실시,1:미중복
	let phoneChecked = 0;
	let emailChecked = 0;
	
	//아이디 중복 체크
	$('#id_check').click(function(){
		if(!/^[A-Za-z0-9]{6,12}$/.test($('#id').val())){
			alert('영문 또는 숫자 사용, 최소 6자 ~ 최대 12자 사용');
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
					alert('아이디 중복 체크 오류 발생');
				}
			},
			error:function(){
				idChecked = 0;
				alert('네트워크 오류 발생');
			}
		});
		 
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
		
		$.ajax({
			url:'checkDuplicatedEmail.do',
			type:'post',
			data:{phone:$('#email').val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'emailNotFound'){
					emailChecked = 1;
					$('#message_email').css('color','black').text('등록 가능 이메일');
				}else if(param.result == 'emailDuplicated'){
					emailChecked = 0;
					$('#message_email').css('color','red').text('중복된 이메일');
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
		
	});//end of click
	
	 //아이디 중복 안내 메시지 초기화 및 아이디 중복값 초기화
	$('#register_form #id').keydown(function(){
		idChecked = 0;
		$('#message_id').text('');
	});//end of keydown
	
	 //전화번호 중복 안내 메시지 초기화 및 전화번호 중복값 초기화
	$('#register_form #phone').keydown(function(){
		phoneChecked = 0;
		$('#message_phone').text('');
	});//end of keydown
	
	 //이메일 중복 안내 메시지 초기화 및 이메일 중복값 초기화
	$('#register_form #email').keydown(function(){
		emailChecked = 0;
		$('#message_email').text('');
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
			if(items[i].phone == 'phone' && phoneChecked == 0){
				alert('전화번호 중복 체크 필수');
				return false;
			}
			if(items[i].email == 'email' && emailChecked == 0){
				alert('이메일 중복 체크 필수');
				return false;
			}
			
		}
	});
	
});
</script>
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<div class="content-main">
			<h2>회원 가입</h2>
			<form id="register_form" action="registerUser.do" method="post">
				<ul>
					<li>
						<label for="id">아이디</label> <input type="text" name="id"
						id="id" maxlength="12" autocomplete="off" class="input-check">
						<input type="button" value="ID중복체크" id="id_check"> 
						<span id="message_id"></span>
						<div class="form-notice">*영문 또는 숫자(6자~12자)</div></li>
					<li>
						<label for="name">이름</label> 
						<input type="text" name="name" id="name" maxlength="10" class="input-check">
					</li>
					<li><label for="passwd">비밀번호</label> <input type="password"
						name="passwd" id="passwd" maxlength="12" class="input-check">
					</li>
					<li><label for="phone">전화번호</label> <input type="text"
						name="phone" id="phone" maxlength="15" class="input-check">
						<input type="button" value="전화번호 중복체크" id="phone_check"> 
						<span id="message_phone"></span>
					</li>
					<li><label for="email">이메일</label> <input type="email"
						name="email" id="email" maxlength="50" class="input-check">
						<input type="button" value="이메일 중복체크" id="email_check"> 
						<span id="email_phone"></span>
					</li>
					<li>
						<label for="nickname">닉네임</label> 
						<input type="text" name="nickname" id="nickname" maxlength="10" class="input-check">
					</li>
				</ul>
				<div class="align-center">
					<input type="submit" value="등록"> <input type="button"
						value="홈으로"
						onclick="'${pageContext.request.contextPath}/main/main.do'">
				</div>
			</form>
			
		</div>
	</div>
</body>
</html>