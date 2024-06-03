$(function(){
	/*나의 북마크 삭제*/
	$('.delete-green').on('click', function(event) {
		// '북마크 삭제' 버튼 클릭 이벤트
		event.stopPropagation();
        // 서버와 통신
        $.ajax({
            url: 'deleteMyPageBookMark.do', 
            type: 'post',
            data: { rb_num: $(this).data('rbnum') }, // 삭제할 북마크 번호를 데이터로 전달
            dataType: 'json', 
            success: function(param) {
                if (param.result === 'logout') {
                    alert('로그인 후 사용하세요!');
                } else if (param.result === 'success') {
                    alert('북마크 제거');
                    location.reload(); 
                } else {
                    alert('북마크 제거 실패');
                }
            },
            error: function() {
                alert('네트워크 오류 발생');
            }
        });
    });
});