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
		$('#rc_content').val('');
	}

	/* ===============================
	 * 댓글 목록 불러오기
	 * =============================== */
	function selectList() {
		$.ajax({
			url: 'commentsList.do',
			type: 'get',
			data: { rb_num: $('#rb_num').val() },
			dataType: 'json',
			success: function(param) {
				let output = '';
				$(param.commentsList).each(function(index, item) {
					output += '<div class="reList">';
					output += '<div class="re_writer">';
					if (item.mem_photo == null) {
						output += '<img src="../images/face.png" id="profile_pic" height="25" width="25">';
					} else {
						output += '<img src="../upload/' + item.mem_photo + '" id="profile_pic" height="25" width="25">';
					}
					output += '<span class="mem_nickname"> ' + item.mem_nickname + ' </span>';
					
					if (item.modify_date_string) {
						output += '<span class="rc_modify_date">' + item.modify_date_string + ' 수정됨</span>';
					} else {
						output += '<span class="rc_reg_date">' + item.reg_date_string + ' 작성</span>';
					}
					output += '</div>'
					output += '<div class="re_content">';
					output += '<p>' + item.rc_content + '</p>'
					output += '</div>';

					//로그인한 회원번호와 작성자의 회원번호 일치 여부 체크
					if (param.mem_num == item.mem_num) {
						output += '<div class="align-right btn-div">';
						output += ' <input type="button" data-rcnum="' + item.rc_num + '" value="수정" class="modify-btn btn">';
						output += ' <input type="button" data-rcnum="' + item.rc_num + '" value="삭제" class="delete-btn btn">';
						output += '</div>';
					}

					output += '</div>';
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
			data: { rc_num: rc_num },
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

	/* ===============================
	 * 댓글 수정
	 * =============================== */
	$(document).on('click', '.modify-btn', function() {
		//댓글 번호
		let rc_num = $(this).attr('data-rcnum');
		let content = $(this).closest('.reList').find('.re_content p').text();
		//(g:지정문자열 모두, i:대소문자 구분x)

		//댓글 수정폼 UI
		let modifyUI = '<form id="mrc_form">';
		modifyUI += `<input type="hidden" name="rc_num" id="mrc_num" value="${rc_num}">`;
		modifyUI += `<textarea rows="3" cols="50" name="rc_content" id="mrc_content" class="rc-content">${content}</textarea>`;
		modifyUI += '<div class="btn-div align-right">';
		modifyUI += ' <input type="submit" value="수정" class="rc-modify">';
		modifyUI += ' <input type="button" value="취소" class="rc-reset">';
		modifyUI += '</div>';
		modifyUI += '</form>';

		//이전에 이미 수정하는 댓글이 있을 경우 수정버튼을 클릭하면 sub-item 클래스로 지정된 div를 환원시키고
		//수정폼을 제거함
		initModifyForm();

		//수정버튼을 감싸고 있는 div 숨기기
		$(this).parent().hide();
		//댓글 원내용 숨기기
		$(this).parents('.reList').find('p').hide();

		//수정폼을 수정하고자 하는 데이터가 있는 div에 노출
		//parents() 내에 원하는 선택자를 넣으면 해당 부모를 검색할 수 있음
		$(this).parents('.reList').append(modifyUI);

	});

	//댓글 수정폼 초기화
	function initModifyForm() {
		$('.btn-div').show();
		$('#mrc_form').remove();
	}

	//수정폼에서 취소버튼 클릭시 수정폼 초기화
	$(document).on('click', '.rc-reset', function() {
		initModifyForm();
	});

	//댓글 수정
	$(document).on('submit', '#mrc_form', function(event) {
		if ($('#mrc_content').val().trim() == '') {
			alert('내용을 입력하세요');
			$('#mrc_content').val('').focus();
			return false;
		}

		//기본 이벤트 제거
		event.preventDefault();

		//폼에 입력한 데이터 반환
		let form_data = $(this).serialize();

		//서버와 통신
		$.ajax({
			url: 'modifyComment.do',
			type: 'post',
			data: form_data,
			dataType: 'json',
			success: function(param) {
				if (param.result == 'logout') {
					alert('로그인해야 수정할 수 있습니다.');
				} else if (param.result == 'success') {
					$('#mrc_form').parent().find('p').html($('#mrc_content').val().replace(/</g, '&lt;')
						.replace(/>/g, '&gt;').replace(/\n/g, '<br>'));
					$('#mrc_form').parent().find('.rc_modify_date').text('최근 수정일 : 5초미만');

					//수정폼 삭제 및 초기화
					initModifyForm();
				} else if (param.result == 'wrongAccess') {
					alert('타인의 글을 수정할 수 없습니다.');
				} else {
					alert('댓글 수정 오류 발생');
				}
			},
			error: function() {
				alert('네트워크 오류 발생');
			}
		});

	});

});