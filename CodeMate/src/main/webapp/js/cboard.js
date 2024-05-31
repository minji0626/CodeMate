$(document).ready(function() {
	

	let cb_num = $('#like').attr('data-num');
	
	// 서버와 통신
function getLike() {
    $.ajax({
        url: 'getLike.do',
        type: 'post',
        data: { cb_num: cb_num },
        dataType: 'json',
        success: function(param) {
            showLike(param);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert('네트워크 오류 발생: ' + textStatus + ', ' + errorThrown);
            console.error('Error details:', jqXHR.responseText);
        }
    });
}


	// 북마크 토글
	$('#like').click(function() {
		$.ajax({
			url: 'toggleLike.do',
			type: 'post',
			data: { cb_num: cb_num },
			dataType: 'json',
			success: function(param) {
				if (param.result == 'logout') {
					alert('로그인 후 좋아요할 수 있어요');
				} else if (param.result == 'success') {
					showLike(param);
				} else {
					alert('좋아요 추가/삭제 오류');
				}
			},
			error: function() {
				alert('네트워크 오류 발생');
			}
		});
	});

	// 북마크 보여주기
	function showLike(param) {
		if (param.status == 'yesLike') { // 북마크
			$('#like').attr('src', '../images/cje/yesLike.png');
		} else { // 북마크X
			$('#like').attr('src', '../images/cje/noLike.png');
		}
		$('#output_fcount').text(param.count);
	}

	// 초기 데이터 불러오기
	getLike();
});
