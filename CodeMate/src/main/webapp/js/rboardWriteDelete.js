$(function() {
    /* 나의 모집글 삭제 */
    $('.delete-green').on('click', function() {
        // 서버와 통신
        $.ajax({
            url: 'deleteMyPageMo.do', // 삭제를 처리할 서버의 URL
            type: 'post', // POST 방식
            data: { rb_num: $(this).closest('.myPage-line-box').data('rbnum') }, // 삭제할 코메 모집글의 번호를 데이터로 전달
            dataType: 'json', // 응답 데이터 타입은 JSON
            success: function(param) {
                if (param.result === 'logout') {
                    alert('로그인 후 사용하세요!');
                } else if (param.result === 'success') {
                    alert('코메 모집글을 삭제했습니다.');
                    location.reload(); // 페이지 새로고침
                } else {
                    alert('코메 모집글 삭제에 실패했습니다.');
                }
            },
            error: function() {
                alert('네트워크 오류 발생');
            }
        });
    });
});