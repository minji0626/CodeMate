$(function() {
    $('.myDelete_btn').on('click', function(event) {
		event.stopPropagation();
        // 서버와 통신
        $.ajax({
            url: 'deleteMyPageConsult.do',
            type: 'post',
            data: { cs_num: $(this).data('csnum') },
            dataType: 'json',
            success: function(param) {
                if (param.result === 'logout') {
                    alert('로그인 후 사용하세요!');
                } else if (param.result === 'success') {
                    alert('문의를  삭제했습니다.');
                    location.reload();
                } else {
                    alert('문의 삭제에 실패했습니다.');
                }
            },
            error: function() {
                alert('네트워크 오류 발생');
            }
        });
    });
});
