$(document).ready(function() {
	//버튼클릭시 처리하기/처리취소 이벤트 연결
	$('#confirm_cs, #unconfirm_cs').click(function() {
		var cs_num_list = [];
		var cs_num =$(this).data('csnum');
		cs_num_list.push(cs_num);
		$.ajax({
			url: 'confirmConsults.do',
			type: 'get',
			data: {cs_num_list: cs_num_list, confirm: $(this).data('confirm') },
			traditional: 'true',
			dataType: 'json',
			success: function(param) {
				if (param.result == 'wrongAccess') {
					alert('접근 권한이 없습니다.')
				} else if (param.result == 'success') {
					console.log("성공");
					alert(param.result_msg);
					location.href = 'consultDetail.do?cs_num=' +cs_num;
				} else {
					alert('문의 처리에 실패했습니다.');
				}
			},
			error: function() {
				alert('네트워크 오류 발생');
			}
		});

	});
});