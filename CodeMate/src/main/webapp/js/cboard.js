$(document).ready(function() {
    let cb_num = $('#like').attr('data-num');
    
    // 서버와 통신
    function getLike() {
        $.ajax({
            url: 'getLike.do',
            type: 'post',
            data: { cb_num: cb_num },
            dataType: 'json',
            success: function(param) {
                showLike(param);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert('네트워크 오류 발생: ' + textStatus + ', ' + errorThrown);
                console.error('에러 세부 정보:', jqXHR.responseText);
            }
        });
    }
    
    // 북마크 토글
    $('#like').click(function() {
        $.ajax({
            url: 'toggleLike.do',
            type: 'post',
            data: { cb_num: cb_num },
            dataType: 'json',
            success: function(param) {
                if (param.result == 'logout') {
                    alert('로그인 후 좋아요를 눌러주세요!');
                } else if (param.result == 'success') {
                    showLike(param);
                } else {
                    alert('좋아요 등록/삭제 오류 발생');
                }
            },
            error: function() {
                alert('네트워크 오류 발생');
            }
        });
    });
    
    // 좋아요 표시 함수
    function showLike(param) {
        let output;
        if (param.status == 'yesLike') { // 좋아요 선택
            output = '../images/cje/yesLike.png';
        } else { // 좋아요 미선택
            output = '../images/cje/noLike.png';
        }
        // 문서 객체에 설정
        $('#like').attr('src', output);
        $('#output_lcount').text(param.count);
    }
    
    // 초기 데이터 불러오기
    getLike();
});
