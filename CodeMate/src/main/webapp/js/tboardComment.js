$(function() {
	let rowCount = 10;
	let currentPage;
	let count;
	
	 /*
	 =========
	 댓글 목록
	 =========
	 */
	// 댓글 목록
	function selectList(pageNum){
		currentPage = pageNum;

		// 서버와 통신
		$.ajax({
			url:'tboardCommentList.do',
			type:'post',
			data:{pageNum:pageNum,rowCount:rowCount,tb_num:$('#tb_num').val()},
			dataType:'json',
			success:function(param){
				count = param.count;
				
				if(pageNum == 1){
					// 처음 호출시는 해당 ID의 div의 내부 내용물을 제거
					$('#reList').empty();
				}
				
				$(param.list).each(function(index,item){
					let output = '<div class="re_writer">';
					output+= '<span>' + item.mem_nickname + '</span>';
					output+= '<div class="re_content">';
					output += '<p>' + item.tc_content + '</p>';
					
					if(item.tc_modify_date){
						output += '<span class="modify-date"> 최근 수정일 : ' + item.tc_modify_date + '</span>';
					} else {
						output += '<span class="reg-date"> 등록일 : ' + item.tc_reg_date + '</span>';
					}
					
					// 로그인한 회원번호와 작성자의 회원 번호 일치 여부 체크
					if(param.mem_num == item.mem_num){
						output += ' <input type="button" data-tcnum="'+item.tc_num+'" value="수정" class="modify-btn">';
						output += ' <input type="button" data-tcnum="'+item.tc_num+'" value="삭제" class="delete-btn">';
					}
					
					output+= '<hr size="1" noshade width="100%">';
					output+= '</div>';
					output+= '</div>';
					
					$('#reList').append(output);
				});
				
				if(currentPage >= Math.ceil(count/rowCount)){
					// 다음 페이지가 없음
					$('.paging-button').hide();
				} else {
					// 다음 페이지가 존재
					$('.paging-button').show();
				}
			},
			error:function(){
				alert('네트워크 오류가 발생하였습니다.');
			}
		})
	}
	
	// 페이지 처리 이벤트 연결(다음 댓글 보기 버튼 클릭시 데이터 추가)
	$('.paging-button input').click(function(){
		selectList(currentPage + 1);
	});
	
	
	
	
	/* 댓글 등록하기 */
	$('#tc_form').submit(function(event) {
    	event.preventDefault();

    if ($('#tc_content').val().trim() == '') {
        alert('내용을 입력하세요');
        $('#tc_content').val('').focus();
        return false;
    }

    let form_data = $(this).serialize();
    $.ajax({
        url: 'tboardCommentwrite.do',
        type: 'post',
        data: form_data,
        dataType: 'json',
        success: function(param) {
            if (param.result == 'logout') {
                alert('로그인 후 사용해주세요.');
            } else if (param.result == 'success') {
                initForm();
                selectList(1);
            } else {
                alert('댓글 등록 중 오류가 발생하였습니다.');
            }
        },
        error: function() {
            alert('네트워크 오류가 발생하였습니다.');
        }
    });
});

// 댓글 작성 폼 초기화
function initForm() {
    $('textarea').val('');
}



	 /*
	 =========
	 목록 호출
	 =========
	 */
	
	selectList(1);
});