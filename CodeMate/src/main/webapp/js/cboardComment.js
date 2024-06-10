$(function() {
    selectList();

    /* ===============================
     * 댓글 등록
     * =============================== */
    $('#comment_form').submit(function(event) {
        //댓글 입력 체크
        if ($('#cc_content').val().trim() == '') {
            alert('댓글을 입력해주세요');
            $('#cc_content').val('').focus();
            return false;
        }

        //form 이하의 태그에 입력한 데이터를 모두 읽어서 쿼리 스트링으로 반환
        let form_data = $(this).serialize();

        //서버와 통신
        $.ajax({
            url: 'writeComment.do',
            type: 'post',
            data: form_data,
            dataType: 'json',
            success: function(param) {
                if (param.result == 'logout') {
                    alert('로그인 후 댓글을 작성해주세요');
                    location.href = '../member/loginForm.do';
                } else if (param.result == 'success') {
                    initForm();
                    selectList();
                } else {
                    alert('댓글 작성 오류');
                }
            },
            error: function() {
                alert('네트워크 오류 발생');
            }
        });

        event.preventDefault();
    });

    function initForm() {
        $('#cc_content').val('');
    }

    /* ===============================
     * 댓글 목록 불러오기
     * =============================== */
    function selectList() {
        $.ajax({
            url: 'commentsList.do',
            type: 'get',
            data: { cb_num: $('#cb_num').val(), cb_type: $('#cb_type').val()},
            dataType: 'json',
            success: function(param) {
                let output = '';
                $(param.commentsList).each(function(index, item) {
                    output += '<div class="reList">'
                    output += '<div class="re_writer">';
                    
                    if (item.mem_photo == null) {
						output += '<img src="../images/face.png" id="profile_pic" height="25" width="25">';
					} else {
						output += '<img src="../upload/' + item.mem_photo + '" id="profile_pic" height="25" width="25">';
					}
					
                    if(param.cb_type===0){
						output += 	'<span> 코메 </span>';
					} else if(param.cb_type==1) {
						output += '<span>' + item.mem_nickname + '</span>';
					}
                    
                    
                    if(item.cc_modify_date){
                        output += '<span class="modify-date">수정 : ' + item.cc_modify_date + '</span>';
                    }else{
                        output += '<span class="reg-date">' + item.cc_reg_date + '</span>';
                    }
                    
                    output += '</div>';
                    output += '<div class="re_content">';
                    output += '<p>' + item.cc_content + '</p>';
                    output += '</div>';

                    //로그인한 회원번호와 작성자의 회원번호 일치 여부 체크
                    if (param.mem_num == item.mem_num) {
                        output += '<div class="action_buttons">';
                        output += ' <input type="button" data-ccnum="' + item.cc_num + '" value="수정" class="modify-btn btn btn-default">';
                        output += ' <input type="button" data-ccnum="' + item.cc_num + '" value="삭제" class="delete-btn btn btn-default">';
                        output += '</div>';
                        // 수정 폼을 삽입할 컨테이너 추가
                        output += '<div class="modify-container"></div>';
                    }
                    output += '</div>';
                });
                $('.container_reList').empty().append(output);
                setCommentsCount(param.commentsList.length);
            },
            error: function() {
                alert('네트워크 오류 발생');
            }
        });
    }

    function setCommentsCount(commentsCount) {
        $('#comments-cnt').text(commentsCount);
    }

    /* ===============================
     * 댓글 삭제
     * =============================== */
    $('.container_reList').on('click', '.delete-btn', function() {
        let cc_num = $(this).attr('data-ccnum');

        $.ajax({
            url: 'deleteComment.do',
            type: 'post',
            data: { cc_num: cc_num },
            dataType: 'json',
            success: function(param) {
                if (param.result == 'logout') {
                    alert('로그인해야 삭제할 수 있습니다.');
                } else if (param.result == 'success') {
                    alert('댓글을 삭제했습니다.');
                    selectList();
                } else if (param.result == 'wrongAccess') {
                    alert('타인의 글을 삭제할 수 없습니다.');
                } else {
                    alert('댓글 삭제 오류 발생');
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert('네트워크 오류 발생: ' + textStatus + ' - ' + errorThrown);
                console.error('Error details:', jqXHR);
            }
        });
    });

    /* ===============================
     * 댓글 수정
     * =============================== */
    $(document).on('click', '.modify-btn', function() {
        // 댓글 번호
        let cc_num = $(this).attr('data-ccnum');
        let content = $(this).parent().parent().find('p').html().replace(/<br>/gi, '\n'); //<br>을 전부 검색해서 \n으로 바꿔라
        // (g:지정문자열 모두, i:대소문자 구분x)

        // 댓글 수정폼 UI
        let modifyUI = '<form id="mcc_form">';
        modifyUI += `<input type="hidden" name="cc_num" id="mcc_num" value="${cc_num}">`;
        modifyUI += `<textarea rows="3" cols="50" name="cc_content" id="mcc_content" class="cc-content">${content}</textarea>`;
        modifyUI += '<div id="btn-div" class="align-right">';
        modifyUI += ' <input type="submit" value="수정">';
        modifyUI += ' <input type="button" value="취소" class="cc-reset">';
        modifyUI += '</div>';
        modifyUI += '</form>';

        // 이전에 이미 수정하는 댓글이 있을 경우 수정버튼을 클릭하면 sub-item 클래스로 지정된 div를 환원시키고
        // 수정폼을 제거함
        initModifyForm();

        // 수정버튼을 감싸고 있는 div 숨기기
        $(this).parent().hide();

        // 수정폼을 수정하고자 하는 데이터가 있는 div에 노출
        $(this).closest('.reList').find('.modify-container').append(modifyUI);
    });

    // 댓글 수정폼 초기화
    function initModifyForm() {
        $('#btn-div').show();
        $('.modify-container').empty(); // 수정 폼을 담는 컨테이너 비우기
        $('.action_buttons').show(); // 숨겨진 액션 버튼 다시 보이기
    }

    // 수정폼에서 취소버튼 클릭시 수정폼 초기화
    $(document).on('click', '.cc-reset', function() {
        initModifyForm();
    });

    // 댓글 수정
    $(document).on('submit', '#mcc_form', function(event) {
        if ($('#mcc_content').val().trim() == '') {
            alert('내용을 입력하세요');
            $('#mcc_content').val('').focus();
            return false;
        }

        // 기본 이벤트 제거
        event.preventDefault();

        // 폼에 입력한 데이터 반환
        let form_data = $(this).serialize();

        // 서버와 통신
        $.ajax({
            url: 'modifyComment.do',
            type: 'post',
            data: form_data,
            dataType: 'json',
            success: function(param) {
                if (param.result == 'logout') {
                    alert('로그인해야 수정할 수 있습니다.');
                } else if (param.result == 'success') {
                    $('#mcc_form').parent().find('p').html($('#mcc_content').val().replace(/</g, '&lt;')
                        .replace(/>/g, '&gt;').replace(/\n/g, '<br>'));
                    $('#mcc_form').parent().find('.cc_modify_date').text('최근 수정일 : 5초미만');

                    // 수정폼 삭제 및 초기화
                    initModifyForm();
                    selectList();
                } else if (param.result == 'wrongAccess') {
                    alert('타인의 글을 수정할 수 없습니다.');
                } else {
                    alert('댓글 수정 오류 발생');
                }
            },
            error: function() {
                alert('네트워크 오류 발생');
            }
        });
    });
});
