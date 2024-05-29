$(function() {
	selectList();
	
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
			url: 'commentsList.do',
			type: 'get',
			data: {rb_num: $('#rb_num').val()},
			dataType: 'json',
			success: function(param) {
				let output = '';
				$(param.commentsList).each(function(index, item) {
					output += '<div class="comment-item">'
					output += '<div class="comment-item-header">';
					output += '<img src="../upload/' +item.mem_photo+'" class="profile-photo">';
					output += '<div class="flex-container">'
					output += '<span class="mem_nickname">'+item.mem_nickname+'</span>';
					if (item.modify_date_string) {
					output += '<span class="rc_reg_date">'+item.modify_date_string+' 수정</span>';						
					} else {
					output += '<span class="rc_reg_date">'+item.reg_date_string+' 작성</span>';						
					}
					output += '</div>'
					output += '</div>'
					output += '<p>'+ item.rc_content +'</p>'
					
					//로그인한 회원번호와 작성자의 회원번호 일치 여부 체크
					if (param.mem_num == item.mem_num) {
						output += ' <input type="button" data-rcnum="' + item.rc_num + '" value="수정" class="modify-btn btn">';
						output += ' <input type="button" data-rcnum="' + item.rc_num + '" value="삭제" class="delete-btn btn">';
					}
					
					output += '</div>'
				});
					$('#comments_list').empty().append(output);
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
	
	/* ===============================
	 * 댓글 삭제
	 * =============================== */
	$('#comments_list').on('click', '.delete-btn', function() {
		let rc_num = $(this).attr('data-rcnum');
		
		$.ajax({
			url: 'deleteComment.do',
			type: 'post',
			data: {rc_num: rc_num},
			dataType: 'json',
			success: function(param) {
				console.log(param);
				if (param.result == 'logout') {
					alert('로그인해야 삭제할 수 있습니다.');
				} else if (param.result == 'success') {
					alert('댓글을 삭제했습니다.');
					selectList();
				} else if (param.result == 'wrongAccess') {
					alert('타인의 글을 삭제할 수 없습니다.');
				} else {
					alert('댓글 삭제 오류 발생');
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
                alert('네트워크 오류 발생: ' + textStatus + ' - ' + errorThrown);
                console.error('Error details:', jqXHR);
			}
		});
		
		selectList();
	});

});