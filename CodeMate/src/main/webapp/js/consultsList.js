$(document).ready(function() {
	//체크박스 선택 후 처리하기/처리취소 이벤트 연결
	$('#confirm_cs, #unconfirm_cs').click(function() {
		var cs_num_list = [];
		$('.consultCheck:checked').each(function() {
			cs_num_list.push($(this).data('csnum'));
		});

		if (cs_num_list.length == 0) {
			alert('처리할 문의를 선택해주세요');
			return;
		}

		$.ajax({
			url: 'confirmConsults.do',
			type: 'get',
			data: { cs_num_list: cs_num_list, confirm: $(this).data('confirm') },
			traditional: true,
			dataType: 'json',
			success: function(param) {
				if (param.result == 'wrongAccess') {
					alert('접근 권한이 없습니다.')
				} else if (param.result == 'success') {
					console.log("성공");
					alert(param.result_msg);
				} else {
					alert('문의 처리에 실패했습니다.');
				}
			},
			error: function() {
				alert('네트워크 오류 발생');
			}
		});
	});

	//미처리 문의만 보기 클릭시
	$('#getUnconfirmed').click(function() {
		var keyfield = $(this).data('keyfield');
		var keyword = $(this).data('keyword');

		// 현재 페이지의 URL을 가져옴
		var currentUrl = window.location.href;

		// 새로운 URL을 생성하여 GET 파라미터를 추가
		var newUrl = updateQueryStringParameter(currentUrl, 'keyfield', keyfield);
		newUrl = updateQueryStringParameter(newUrl, 'keyword', keyword);

		// 페이지를 새로고침하여 새로운 URL로 이동
		window.location.href = newUrl;
		$('#keyword').val('');
	});

	// 전체 버튼 클릭 시
	$('#getAll').click(function() {
		// 현재 URL에서 쿼리스트링 제거
		var url = window.location.href.split('?')[0];
		// 페이지 이동
		window.location.href = url;
	});

});

// URL의 GET 파라미터를 추가 또는 업데이트하는 함수
function updateQueryStringParameter(uri, key, value) {
	var re = new RegExp("([?&])" + key + "=.*?(&|$)", "i");
	var separator = uri.indexOf('?') !== -1 ? "&" : "?";
	if (uri.match(re)) {
		return uri.replace(re, '$1' + key + "=" + value + '$2');
	} else {
		return uri + separator + key + "=" + value;
	}
}