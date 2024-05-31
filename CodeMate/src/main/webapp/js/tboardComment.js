$(function() {
	selectList();

	/* ===============================
	 * 댓글 등록
	 * =============================== */
	$('#comment_form').submit(function(event) {
		  
		//댓글 입력 체크
		if ($('#tc_content').val().trim() == '') {
			alert('댓글을 입력해주세요');
			$('#tc_content').val('').focus();
			return false;
		}

		//form 이하의 태그에 입력한 데이터를 모두 읽어서 쿼리 스트링으로 반환
		let form_data = $(this).serialize();

		//서버와 통신
		$.ajax({
			url: 'tboardCommentwrite.do',
			type: 'post',
			data: form_data,
			dataType: 'json',
			success: function(param) {
				if (param.result == 'logout') {
					alert('로그인 후 댓글을 작성해주세요');
					location.href = '../member/loginForm.do';
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
		$('#tc_content').val('');
	}
	
	
	/* ===============================
	 * 댓글 목록 불러오기
	 * =============================== */
	function selectList() {
		$.ajax({
			url: 'tboardCommentList.do',
			type: 'get',
			data: { tb_num: $('#tb_num').val() },
			dataType: 'json',
			success: function(param) {
				let output = '';
				$(param.commentsList).each(function(index, item) {
					output += '<div class="reList">'
					output += '<div class="re_writer">';
					output += '<img src="../upload/' + item.mem_photo + '" id="profile_pic" height="25" width="25">';
					output += '<span>' + item.mem_nickname + '</span>';
					
					if(item.tc_modify_date){
						output += '<span class="modify-date">수정 : ' + item.tc_modify_date + '</span>';
					}else{
						output += '<span class="reg-date">' + item.tc_reg_date + '</span>';
					}
					
					output += '</div>';
					output += '<div class="re_content">';
					output += '<p>' + item.tc_content + '</p>';
					output += '</div>';

					//로그인한 회원번호와 작성자의 회원번호 일치 여부 체크
					if (param.mem_num == item.mem_num) {
						output += '<div class="action_buttons">';
						output += ' <input type="button" data-tcnum="' + item.tc_num + '" value="수정" class="modify-btn btn btn-default">';
						output += ' <input type="button" data-tcnum="' + item.tc_num + '" value="삭제" class="delete-btn btn btn-default">';
						output += '</div>'
					}
					output += '</div>'
				});
				$('.container_reList').empty().append(output);
				setCommentsCount(param.commentsList.length);
			},
			error: function() {
				alert('네트워크 오류 발생');
			}

		});
	}

	function setCommentsCount(commentsCount) {
		$('#comments-cnt').text(commentsCount);
	}

});
