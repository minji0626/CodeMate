$(document).ready(function() {

	/*=============================
	뒤로가기 화살표
	=============================*/
	
	$('.feather-arrow-left').click(function() {
		location.href = 'list.do';
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
