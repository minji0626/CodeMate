$(document).ready(function() {
	//회원 정지 버튼 이벤트 연결
	$('.lockMemberBtn').each(function() {
		$(this).on('click', function() {
			var mem_num = $(this).data('memnum'); // 버튼에 저장된 회원 ID 가져오기
			var locked = $(this).data('locked');

			$.ajax({
				type: 'POST',
				url: 'lockMemberToggle.do',
				data: { mem_num: mem_num, locked: locked },
				dataType: "json",
				success: function(param) {
					if (param.result == 'wrongAccess') {
						alert('접근 권한이 없습니다.')
					} else if (param.result == 'success') {
						alert(param.result_msg);
					} else {
						alert('회원 정지 관리에 실패했습니다.');
					}
				},
				error: function() {
					alert('네트워크 오류 발생');
				}
			});
		});
	});

	//관리자/일반회원 변경 버튼 이벤트 연결
	$('.changeAuthBtn').each(function() {
		$(this).on('click', function() {
			var mem_num = $(this).data('memnum'); // 버튼에 저장된 회원 ID 가져오기
			var mem_auth = $(this).data('memauth');

			// 클릭된 버튼의 부모 요소에서 changeAuthToAdmin 클래스를 가진 select 요소의 값을 가져옴
			var selectedValue = $(this).siblings('select[name="changeAuthToAdmin"]').val();

			if (mem_auth == selectedValue) {
				alert('변경사항이 없습니다.');
				return;
			}

			$.ajax({
				type: 'POST',
				url: 'changeAuthToAdmitToggle.do',
				data: { mem_num: mem_num, mem_auth: mem_auth },
				dataType: "json",
				success: function(param) {
					if (param.result == 'wrongAccess') {
						alert('접근 권한이 없습니다.')
					} else if (param.result == 'success') {
						alert(param.result_msg);
					} else {
						alert('회원 등급 변경에 실패했습니다.');
					}
				},
				error: function() {
					alert('네트워크 오류 발생');
				}
			});
		});
	});

});
