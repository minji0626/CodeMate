$(function() {
    /* 나의 모집글 삭제 */
    $('#delete_community').on('click', function() {
		let cb_num = $(this).data('cbnum');
        // 서버와 통신
        var check = confirm("커뮤니티 게시글을 삭제하시겠습니까?");
        
        if(check){
        $.ajax({
            url: 'deleteManageCommunity.do',
            type: 'post',
            data: { cb_num: cb_num }, // 삭제할 게시글 번호를 데이터로 전달
            dataType: 'json',
            success: function(param) {
                if (param.result === 'logout') {
                    alert('로그인 후 사용하세요!');
                } else if (param.result === 'success') {
                    alert('게시글을 삭제했습니다.');
                    location.reload();
                } else {
                    alert('게시글 삭제에 실패했습니다.');
                }
            },
            error: function() {
                alert('네트워크 오류 발생');
            }
        });
       } 
    });
});
