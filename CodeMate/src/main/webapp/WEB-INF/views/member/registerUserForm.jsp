<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css" type="text/css">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
	let idChecked = 0;     //0:중복,아이디 중복 체크 미실시,1:미중복
	
	//아이디 중복 체크
	$('#id').keyup(function(){
		let id = $(this).val();//이벤트가 발생하는 이 곳에서 값을 불러와 id라는 변수에 지정하려고 함
		
			//서버와 통신
			$.ajax({
				url:'checkDuplicatedId.do',
				type:'post',
				data:{id:id},
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
		}
		});
	
	//아이디가 영문,숫자 조건 맞지 않으면 회원가입 버튼이 안눌림
	$('#reg_btn').click(function(){
		if(!/^[A-Za-z0-9]{6,12}$/.test($('#id').val())){
			alert('영문 또는 숫자 사용, 최소 6자 ~ 최대 12자 사용');
			$('#id').val('').focus();
			return;
		}	
		
	
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
			
		}
	}); 
	
	
});

	});//end of click
	
	 //아이디 중복 안내 메시지 초기화 및 아이디 중복값 초기화
	$('#register_form #id').keydown(function(){
		idChecked = 0;
		$('#message_id').text('');
	});//end of keydown
	}
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
						<input type="text" name="id"
						id="id" maxlength="12" autocomplete="off" class="input-check">
							
					</li>
					<li class="info">
						<label for="name">이름</label> 
						<input type="text" name="name" id="name" maxlength="10" class="input-check">
					</li>
					<li class="info">
					<label for="passwd">비밀번호</label> 
						<input type="password" name="passwd" id="passwd" maxlength="12" class="input-check">
					</li>
					<li class="info">
						<label for="nickname">닉네임</label> 
						<input type="text" name="nickname" id="nickname" maxlength="10" class="input-check">
					</li>
					<li class="info">
					<label for="email">이메일</label> 
						<input type="email" name="email" id="email" maxlength="50" class="input-check">
					</li>
					<li class="info">
					<label for="phone">전화번호</label> 
						<input type="text" name="phone" id="phone" maxlength="30" class="input-check">
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