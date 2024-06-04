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
                    $.ajax({
                        url: 'howManyReview.do',
                        type: 'post',
                        data: form_data,
                        dataType: 'json',
                        success: function(param) {
                            if(param.result=='logout'){
								alert('로그인 후 사용하세요!')
							} else if(param.result=='finish'){
								alert('모든 팀원의 리뷰를 작성하였으므로 프로젝트가 완료되었습니다.');
								location.reload();
							} else if(param.result=='ing'){
								alert('리뷰 작성이 완료되었습니다.')
								location.reload();
							}
                        },
                        error: function() {
                            alert('리뷰 데이터 가져오기 실패');
                        }
                    });
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
