$(function() {
	/* 나의 모집글 삭제 */
	$('.delete-green').on('click', function(event) {
		
		event.stopPropagation();

		var check = confirm("정말 삭제하시겠습니까?");

		if (check) {
			// 서버와 통신
			$.ajax({
				url: 'deleteMyPageMo.do',
				type: 'post',
				data: { rb_num: $(this).data('rbnum') }, // 삭제할 코메 모집글의 번호를 데이터로 전달
				dataType: 'json',
				success: function(param) {
					if (param.result === 'logout') {
						alert('로그인 후 사용하세요!');
					} else if (param.result === 'success') {
						alert('코메 모집글을 삭제했습니다.');
						location.reload();
					} else {
						alert('코메 모집글 삭제에 실패했습니다.');
					}
				},
				error: function() {
					alert('네트워크 오류 발생');
				}
			});
		}

	});
});