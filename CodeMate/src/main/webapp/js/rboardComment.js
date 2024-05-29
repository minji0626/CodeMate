$(function() {
	/* ===============================
	 * 댓글 등록
	 * =============================== */
	$('#comment_form').submit(function(event) {
		//댓글 입력 체크
		if ($('#rc_content').val().trim() == '') {
			alert('댓글을 입력해주세요');
			$('#rc_content').val('').focus();
			return false;
		}
		
		//form 이하의 태그에 입력한 데이터를 모두 읽어서 쿼리 스트링으로 반환
		let form_data = $(this).serialize();
		
		//서버와 통신
		$.ajax({
			url: 'writeComment.do',
			type: 'post',
			data: form_data,
			dataType: 'json',
			success: function(param) {
				if (param.result == 'logout') {
					alert('로그인 후 댓글을 작성해주세요');
					location.href='../member/loginForm.do';
				} else if (param.result == 'success') {
					initForm();
					selectList();
				} else {
					alert('댓글 작성 오류');
				}
			},
			error: function() {
				alert('네트워크 오류 발생');
			}
		});

		event.preventDefault();
	});
	
	function initForm() {
		$('#rc_content').val('');
	}
	
	/* ===============================
	 * 댓글 목록 불러오기
	 * =============================== */
	function selectList() {
		$.ajax({
			
			
		});
		
		alert('댓글 작성 완료!');
	}

});