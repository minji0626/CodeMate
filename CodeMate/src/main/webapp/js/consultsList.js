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
	
	$('#getUnconfirmed').click(function() {
		var keyfield = $(this).data('keyfield');
		var keyword = $(this).data('keyword');
		
		$.ajax({
			url: 'consultsList.do',
			type: 'get',
			data: {keyword: keyword, keyfield: keyfield},
			dataType: 'json',
			success: function() {},
			error: function() {
				alert('네트워크 오류 발생');
			}
		});
	});


});