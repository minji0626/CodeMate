$(function() {
    $('#mr_form').submit(function(event) {
        // 내용이 비어있는지 확인
        if ($('#mr_content').val().trim() == '') {
            alert('내용을 입력해주세요@@@@@@');
            $('#mr_content').val('').focus();
            return false;
        }

        // form 데이터 serialize
        let form_data = $(this).serialize();
        
        // 서버와 통신
        $.ajax({
            url: 'writeMateReview.do',
            type: 'post',
            data: form_data, 
            dataType: 'json',
            success: function(param) {
                if (param.result === 'logout') {
                    alert('로그인 후 사용하세요!');
                } else if (param.result === 'success') {
                    alert('리뷰를 작성했습니다');
                    location.reload();
                } else {
                    alert('리뷰 작성 실패했습니다.');
                }
            },
            error: function() {
                alert('네트워크 오류 발생');
            }
        });

        // 이벤트 전파 차단
        event.preventDefault();
    });
});
