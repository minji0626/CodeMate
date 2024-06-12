$(function() {
    /* 나의 모집글 삭제 */
    $('.delete-green').on('click', function(event) {
		// '모집글 삭제' 버튼 클릭 이벤트
		event.stopPropagation();
		
		var check = confirm("정말 신청 취소하시겠습니까?");
		
		if(check){
	        // 서버와 통신
	        $.ajax({
	            url: 'deleteMyPageShin.do',
	            type: 'post',
	            data: { ra_num: $(this).data('ranum') }, // 삭제할 코메 모집글의 번호를 데이터로 전달
	            dataType: 'json',
	            success: function(param) {
	                if (param.result === 'logout') {
	                    alert('로그인 후 사용하세요!');
	                } else if (param.result === 'success') {
	                    alert('코메 신청 취소');
	                    /*location.reload();*/
	                    window.location.href = 'myPageShin.do';
	                } else {
	                    alert('코메 신청 취소에 실패했습니다.');
	                }
	            },
	            error: function() {
	                alert('네트워크 오류 발생');
            	}
        	});
        }
    });
});