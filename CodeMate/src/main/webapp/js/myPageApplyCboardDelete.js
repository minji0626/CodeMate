$(function() {
    $('.myDelete_btn').on('click', function(event) {
		event.stopPropagation();
		
		var check = confirm("정말 삭제하시겠습니까?");
		
		if(check){
	        // 서버와 통신
	        $.ajax({
	            url: 'deleteMyPageCboardApply.do',
	            type: 'post',
	            data: { cc_num: $(this).data('ccnum') }, // 삭제할 게시글 번호를 데이터로 전달
	            dataType: 'json',
	            success: function(param) {
	                if (param.result === 'logout') {
	                    alert('로그인 후 사용하세요!');
	                } else if (param.result === 'success') {
	                    alert('게시글 댓글을 삭제했습니다.');
	                    location.reload();
	                } else {
	                    alert('게시글 댓글 삭제에 실패했습니다.');
	                }
	            },
	            error: function() {
	                alert('네트워크 오류 발생');
	            }
	        });
        }
    });
});
