$(function() {
	let emailChecked = 0;
	let idChecked = 0;     //0:중복,아이디 중복 체크 미실시,1:미중복
	let phoneChecked = 0;
	let nicknameChecked = 0;

	$('#mem_id').on('blur', function() {
		//아이디 중복 체크
		if (!/^[A-Za-z0-9]{4,12}$/.test($('#mem_id').val())) {
			$('#message_id').css('color', 'red').text('영문 또는 숫자 사용, 최소 4자 ~ 최대 12자 사용');
			$('#mem_id').val('').focus();
			return;
		}
		//서버와 통신
		$.ajax({
			url: 'checkDuplicatedId.do',
			type: 'post',
			data: { id: $('#mem_id').val() },
			dataType: 'json',
			success: function(param) {
				if (param.result == 'idNotFound') {
					idChecked = 1;
					$('#message_id').css('color', 'black').text('등록 가능 ID');
				} else if (param.result == 'idDuplicated') {
					idChecked = 0;
					$('#message_id').css('color', 'red').text('중복된 ID');
					$('#mem_id').val('').focus();
				} else {
					idChecked = 0;
					$('#message_id').text('아이디 중복 체크 오류 발생');
				}
			},
			error: function() {
				idChecked = 0;
				alert('네트워크 오류 발생');
			}
		});

	});//end of id_check

	//이메일 중복 체크
	$('#mem_email').on('blur', function() {
		if ($('#mem_email').val() == '') {
			$('#message_email').css('color', 'red').text('이메일을 입력하세요.');
			$('#mem_email').val('').focus();
			return;
		}
		//서버와 통신
		$.ajax({
			url: 'checkDuplicatedEmail.do',
			type: 'post',
			data: { mem_email: $('#mem_email').val() },
			dataType: 'json',
			success: function(param) {
				if (param.result == 'emailNotFound') {
					emailChecked = 1;
					$('#message_email').css('color', 'black').text('등록 가능 Email');
				} else if (param.result == 'emailDuplicated') {
					emailChecked = 0;
					$('#message_email').css('color', 'red').text('중복된 Email');
					$('#mem_email').val('').focus();
				} else {
					emailChecked = 0;
					alert('이메일 중복 체크 오류 발생');
				}
			},
			error: function() {
				emailChecked = 0;
				alert('네트워크 오류 발생');
			}
		});


	});//end of email check

	/* 닉네임 중복 체크 */
	$('#mem_nickname').on('blur', function() {
		var nickname = $(this).val();
		if (nickname == '') {
			$('#message_nickname').css('color', 'red').text('닉네임을 입력하세요.');
			$('#message_nickname').val('').focus();
			return;
		}

		// 닉네임 길이 체크
		if (nickname.length > 10) {
			$('#message_nickname').css('color', 'red').text('닉네임은 최대 10자까지 설정할 수 있습니다.');
			$('#mem_nickname').val('').focus();
			return;
		}
		$.ajax({
			url: 'checkDuplicatedNickname.do',
			type: 'post',
			data: { mem_nickname: nickname },
			dataType: 'json',
			success: function(param) {
				if (param.result == 'nicknameDuplicated') {
					nicknameChecked = 0;
					$('#message_nickname').css('color', 'red').text('이미 사용 중인 닉네임입니다.');
					$('#mem_nickname').val('').focus();
				} else if (param.result == 'nicknameNotFound') {
					nicknameChecked = 1;
					$('#message_nickname').css('color', 'black').text('사용 가능한 닉네임입니다.');
				} else {
					nicknameChecked = 0;
					$('#message_nickname').css('color', 'red').text('닉네임 중복 체크 중 오류 발생');
				}
			},
			error: function() {
				nicknameChecked = 0;
				$('#message_nickname').css('color', 'red').text('서버 오류가 발생했습니다.');
			}
		});
	});


	//전화번호 중복 체크
	$('#mem_phone').on('blur', function() {
		if (!/^[0-9]{11,11}$/.test($('#mem_phone').val())) {
			$('#message_phone').css('color', 'red').text('11자리의 숫자만 입력하세요!');
			$('#mem_phone').val('').focus();
			return false;
		}
		//서버와 통신
		$.ajax({
			url: 'checkDuplicatedPhone.do',
			type: 'post',
			data: { mem_phone: $('#mem_phone').val() },
			dataType: 'json',
			success: function(param) {
				if (param.result == 'phoneNotFound') {
					phoneChecked = 1;
					$('#message_phone').css('color', 'black').text('등록 가능 전화번호');
				} else if (param.result == 'phoneDuplicated') {
					phoneChecked = 0;
					$('#message_phone').css('color', 'red').text('중복된 전화번호');
					$('#mem_phone').val('').focus();
				} else {
					phoneChecked = 0;
					alert('전화번호 중복 체크 오류 발생');
				}
			},
			error: function() {
				phoneChecked = 0;
				alert('네트워크 오류 발생');
			}
		});

	});//end of click



	//아이디 중복 안내 메시지 초기화 및 아이디 중복값 초기화
	$('#register_form #mem_id').keydown(function() {
		idChecked = 0;
		$('#message_id').text('');
	});//end of keydown

	//이메일 중복 안내 메시지 초기화 및 이메일 중복값 초기화
	$('#register_form #mem_email').keydown(function() {
		emailChecked = 0;
		$('#message_email').text('');
	});//end of keydown

	//전화번호 중복 안내 메시지 초기화 및 전화번호 중복값 초기화
	$('#register_form #mem_phone').keydown(function() {
		phoneChecked = 0;
		$('#message_phone').text('');
	});//end of keydown

	//닉네임 중복 안내 메시지 초기화 및 닉네임 중복값 초기화
	$('#register_form #mem_nickname').keydown(function() {
		nicknameChecked = 0;
		$('#message_nickname').text('');
	});//end of keydown


	//회원 정보 등록 유효성 체크
	$('#register_form').submit(function() {
		const items = document.querySelectorAll('.input-check');
		for (let i = 0; i < items.length; i++) {
			if (items[i].value.trim() == '') {
				const label = document.querySelector('label[for="' + items[i].id + '"]');
				alert(label.textContent + ' 항목은 필수 입력');
				items[i].value = '';
				items[i].focus();
				return false;
			}
			if (items[i].id == 'mem_id' && !/^[A-Za-z0-9]{4,12}$/.test($('#mem_id').val())) {
				alert('영문 또는 숫자 사용, 최소 4자 ~ 최대 12자 사용');
				$('#mem_id').val('').focus();
				return false;
			}
			if (items[i].id == 'mem_id' && idChecked == 0) {
				alert('아이디 중복 체크 필수');
				return false;
			}
			if (items[i].id == 'mem_email' && emailChecked == 0) {
				alert('이메일 중복 체크 필수');
				return false;
			}
			if (items[i].id == 'mem_phone' && phoneChecked == 0) {
				alert('전화번호 중복 체크 필수');
				return false;
			}
			if (items[i].id == 'mem_nickname' && nicknameChecked == 0) {
				alert('닉네임 중복 체크 필수');
				return false;
			}
		}
	});
});