$(document).ready(function() {
    
    /*팀 설정에서 setting icon 눌렀을 때 dropdown Menu 불러오기*/
    $('.team_setting_btn').click(function(event) {
        const dropdownMenu = $(this).next();
        dropdownMenu.toggle();
        
        // 다른 드롭다운 메뉴 숨기기
        $('.dropdown_menu').not(dropdownMenu).hide();
        
        event.stopPropagation();
    });

    // 페이지를 클릭하면 모든 드롭다운 메뉴 숨기기
    $(document).click(function() {
        $('.dropdown_menu').hide();
    });

    // 드롭다운 메뉴 클릭 시 이벤트 전파 막기
    $('.dropdown_menu').click(function(event) {
        event.stopPropagation();
    });
    
    
    /*mate_review 리뷰 작성하는 모달*/
    const mate_review = $("#mate_review");
	
	/* 모달 on 함수 */
    function modalOn() {
        mate_review.css("display", "flex");
    }

	/* 모달 off 함수 */
    function modalOff() {
        mate_review.hide();
    }
	
	/* 리뷰 쓰기 메뉴 */
    $(".review-link").click(function(e) {
        e.preventDefault();
        
        
        // 개인의 닉네임 가져와서 innerText 시키기
        const nickname = $(this).closest('.mem_personal').find('.team_mem_nickname').data('nickname');
        $('.mate_review_profile .user_nickname').text(nickname);
        // 개인의 아이디 가져와서 innerText 시키기
        const id = $(this).closest('.mem_personal').find('.team_mem_nickname').data('id');
        $('.mate_review_profile .user_id').text(id);
        // 개인의 레벨 가져와서 innerText 시키기
        const level = $(this).closest('.mem_personal').find('.team_mem_nickname').data('level');
        $('.mate_review_profile .user_level').text(level);
        // 개인의 사진 가져와서 setAttribute 시키기
        const profileImage = $(this).closest('.mem_personal').find('.team_mem_profile_img');
        const imageUrl = profileImage.attr('src');
        $('.mate_review_profile .profile_image').attr('src', imageUrl);
        
        const mr_receiver = $(this).data('mem-num');
        $('#mr_receiver').val(mr_receiver);
        const team_num = $(this).data('team-num');
        $('#team_num').val(team_num);
        modalOn();
    });
	
	/* close-btn을 클릭하면 modal off 함수가 호출된다*/
	$('#close-btn, .mate_review_close').click(function() {
   		 modalOff(); // 모달창 닫기 함수 호출
   		 $('#mr_content').val('');
	});

    mate_review.click(function(e) {
        if ($(e.target).is(mate_review)) {
            modalOff();
        }
    });
    
});


$(function() {
	// 팀원 삭제하는 function
    $('.mem_delete_btn').click(function() {
        let team_num = $(this).data('team-num');
        let mem_num = $(this).data('mem-num');
        
        let choice = confirm('해당 멤버를 삭제하시겠습니까?');
        
        if (choice) {
            // 서버 통신
            $.ajax({
                url: 'deleteTmember.do',
                type: 'post',
                data: { team_num: team_num, mem_num: mem_num },
                dataType: 'json',
                success: function(param) {
                    if (param.result == 'logout') {
                        alert('로그인 후 사용해주세요');
                    } else if (param.result == 'wrongAccess') {
                        alert('잘못된 접근 정보입니다.');
                    } else if (param.result == 'success') {
                        alert('팀원이 삭제되었습니다');
                        location.href = 'teamSetting.do?team_num=' + team_num;
                    } else {
                        alert('팀원 삭제 처리 중 오류가 발생하였습니다.');
                    }
                },
                error: function() {
                    alert('네트워크 오류가 발생하였습니다.');
                }
            });
        }
    });
    
    $(".mem_auth_btn").click(function() {
    var teamNum = $("input[name='team_num']").val();
    var newLeaderMemNum = $(this).data("new-leader");

    $.ajax({
        type: 'post',
        url: 'updateTeamLeader.do',
        data: {
            team_num: teamNum,
            new_leader_mem_num: newLeaderMemNum
        },
        dataType: 'json',
        success: function(param) {
            if (param.result == 'success') {
                alert('팀장 위임이 완료되었습니다');
                location.reload();
            } else if (param.result == 'noAuth') {
                alert('권한이 없습니다. 다시 로그인 후 시도해주세요.')
            } else {
                alert('팀장 위임 중 오류가 발생하였습니다.');
            }
        },
        error: function() {
            alert('네트워크 오류가 발생하였습니다');
        }
    });
});

    
    
});






