$(function() {
	selectList();

	/* ===============================
	 * 댓글 등록
	 * =============================== */
	$('#comment_form').submit(function(event) {
		// 댓글 입력 체크
		if ($('#tc_content').val().trim() == '') {
			alert('댓글을 입력해주세요');
			$('#tc_content').val('').focus();
			return false;
		}

		// form 이하의 태그에 입력한 데이터를 모두 읽어서 쿼리 스트링으로 반환
		let form_data = $(this).serialize();

		// 서버와 통신
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
	 * 댓글 목록 불러오기, 댓글 수정 폼
	 * =============================== */
	function selectList() {
		$.ajax({
			url: 'tboardCommentList.do',
			type: 'get',
			data: { tb_num: $('#tb_num').val() },
			dataType: 'json',
			success: function(param) {
				$('.container_reList').empty();
				let commentsCount = param.commentsList.length; // 댓글 수
   				 $('.container_reList').append('<h4> 댓글 <span id="comments-cnt">' + commentsCount + '</span> </h4>');
				$(param.commentsList).each(function(index, item) {
					let output = '<div class="reList">';
					output += '<div class="re_writer">';
					if (item.mem_photo == null) {
						output += '<img src="../images/face.png" id="profile_pic" height="25" width="25">';
					} else {
						output += '<img src="../upload/' + item.mem_photo + '" id="profile_pic" height="25" width="25">';
					}
					output += '<span>' + item.mem_nickname + '</span>';

					if (item.tc_modify_date) {
						output += '<span class="modify-date">' + item.tc_modify_date + ' 수정됨 </span>';
					} else {
						output += '<span class="reg-date">' + item.tc_reg_date + '</span>';
					}

					// 로그인한 회원번호와 작성자의 회원번호 일치 여부 체크
					if (param.mem_num == item.mem_num) {
						output += '<button class="comment_img"><img src="../images/dotmenu.png" id="comment_img" height="15" width="15"></button>';
						output += '<div class="dropdown_menu">';
						output += '<input type="button" data-tcnum="' + item.tc_num + '" value="수정" class="modify-btn btn btn-default">';
						output += '<input type="button" data-tcnum="' + item.tc_num + '" value="삭제" class="delete-btn btn btn-default">';
						output += '</div>';
					}
					output += '</div>';
					output += '<div class="re_content">';
					output += '<p>' + item.tc_content + '</p>';
					output += '</div>';
					output += '</div>';
					$('.container_reList').append(output);
				});

				// 수정 버튼 클릭 이벤트 처리
				$('.modify-btn').click(function() {

					let tc_num = $(this).data('tcnum');
					let content = $(this).closest('.reList').find('.re_content p').text();
					var reList = $(this).closest('.reList');
					let modifyUI = '<form id="mtc_form">';
					modifyUI += `<input type="hidden" name="tc_num" id="mtc_num" value="${tc_num}">`;
					modifyUI += `<textarea rows="3" cols="48" name="tc_content" id="mtc_content" class="tc-content">${content}</textarea>`;
					modifyUI += '<div id="btn-div" class="align-right">';
					modifyUI += ' <input type="submit" value="수정" class="tc-modify">';
					modifyUI += ' <input type="button" value="취소" class="tc-reset">';
					modifyUI += '</div>';
					modifyUI += '</form>';

					// 기존 수정폼 삭제 및 초기화
					initModifyForm();

					// 수정 버튼을 감싸고 있는 div 숨기기
					$(this).parent().hide();
					//댓글 원내용 숨기기
					$(this).parents('.reList').find('p').hide();

					// 수정폼을 수정하고자 하는 데이터가 있는 div에 노출
					$(this).parents('.reList').append(modifyUI);

				});

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
	 * 댓글 수정 폼 초기화
	 * =============================== */
	function initModifyForm() {
		$('#btn-div').show();
		$('#mtc_form').remove();
		$('.re_content p').show();
	}

	/* ===============================
	 * 댓글 수정 폼 취소 버튼 클릭 이벤트
	 * =============================== */
	$(document).on('click', '.tc-reset', function() {
		initModifyForm();
	});

	/* ===============================
	 * 댓글 수정 요청
	 * =============================== */
	$(document).on('submit', '#mtc_form', function(event) {
		if ($('#mtc_content').val().trim() == '') {
			alert('내용을 입력하세요');
			$('#mtc_content').val('').focus();
			return false;
		}

		// 기본 이벤트 제거
		event.preventDefault();

		// 폼에 입력한 데이터 반환
		let form_data = $(this).serialize();

		// 서버와 통신
		$.ajax({
			url: 'modifyTComment.do',
			type: 'post',
			data: form_data,
			dataType: 'json',
			success: function(param) {
				if (param.result == 'logout') {
					alert('로그인해야 수정할 수 있습니다.');
				} else if (param.result == 'success') {
					$('#mtc_form').parent().find('.re_content p').text($('#mtc_content').val());
					$('#mre_form').parent().find('.modify-date').text('5초 미만 전 수정됨');
					initModifyForm();
					selectList();
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
	
	
	$(document).on('click', '.delete-btn', function() {
	let tc_num = $(this).attr('data-tcnum');

	if (!confirm('해당 댓글을 삭제하시겠습니까?')) {
		return false;
	}

	$.ajax({
		url: 'tboardCommentDelete.do', // URL 확인
		type: 'post',
		data: { tc_num: tc_num },
		dataType: 'json',
		success: function(param) {
			if (param.result == 'logout') {
				alert('로그인해야 삭제할 수 있습니다.');
			} else if (param.result == 'success') {
				alert('댓글을 삭제했습니다.');
				selectList(); // 성공 시 리스트 갱신
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
});


});

// Dropdown 메뉴 토글
$(document).on('click', '.comment_img', function(event) {
	const dropdownMenu = $(this).next('.dropdown_menu');
	dropdownMenu.toggle();

	// 다른 드롭다운 메뉴 숨기기
	$('.dropdown_menu').not(dropdownMenu).hide();

	event.stopPropagation();
});

// 페이지 클릭 시 모든 드롭다운 메뉴 숨기기
$(document).click(function() {
	$('.dropdown_menu').hide();
});

// 드롭다운 메뉴 클릭 시 이벤트 전파 막기
$('.dropdown_menu').click(function(event) {
	event.stopPropagation();
});



