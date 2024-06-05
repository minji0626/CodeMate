$(document).ready(function() {

	/*=============================
	뒤로가기 화살표
	=============================*/

	$('.feather-arrow-left').click(function() {
		location.href = 'list.do';
	});

	//신청하기 눌렀을 때 자기글인지/신청한 글인지 확인
	$('#btn-modal').click(function() {
		var mem_num = $(this).data('memnum');
		var rb_mem_num = $(this).data('rbmemnum');
		var alreadyapplied = $(this).data('alreadyapplied');
		if (mem_num == rb_mem_num) {
			alert('자신의 글에는 신청할 수 없어요');
			modalOff();
			return;
		}

		if (alreadyapplied) {
			alert('이미 신청한 코메모집이에요');
			modalOff();
			return;
		}
		
	});

	/*=============================
	북마크
	=============================*/
	let rb_num = $('#bookmark_img').attr('data-rbnum');

	// 서버와 통신
	function getBookmark() {
		$.ajax({
			url: 'getBookmark.do',
			type: 'post',
			data: { rb_num: rb_num },
			dataType: 'json',
			success: function(param) {
				showBookmark(param);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert('네트워크 오류 발생: ' + textStatus + ', ' + errorThrown);
				console.error('Error details:', jqXHR.responseText);
			}
		});
	}


	// 북마크 토글
	$('#bookmark_img').click(function() {
		$.ajax({
			url: 'toggleBookmark.do',
			type: 'post',
			data: { rb_num: rb_num },
			dataType: 'json',
			success: function(param) {
				if (param.result == 'logout') {
					alert('로그인 후 북마크할 수 있어요');
				} else if (param.result == 'success') {
					showBookmark(param);
				} else {
					alert('북마크 추가/삭제 오류');
				}
			},
			error: function() {
				alert('네트워크 오류 발생');
			}
		});
	});

	// 북마크 보여주기
	function showBookmark(param) {
		if (param.status == 'yesRbookmark') { // 북마크
			$('#bookmark_img').attr('src', '../images/yesRbookmark.png');
		} else { // 북마크X
			$('#bookmark_img').attr('src', '../images/noRbookmark.png');
		}
	}

	// 초기 데이터 불러오기
	getBookmark();
});
