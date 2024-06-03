$(function() {
    $('#pass').submit(function(event) {

        // form 데이터 serialize
        let form_data = $(this).serialize();
        
        // 서버와 통신
        $.ajax({
            url: 'passCodeMate.do',
            type: 'post',
            data: form_data, 
            dataType: 'json',
            success: function(param) {
                if (param.result === 'logout') {
                    alert('로그인 후 사용하세요!');
                } else  if(param.result=='already'){
					alert('이미 합격한 사용자입니다.')
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
