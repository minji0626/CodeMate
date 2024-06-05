$(document).ready(function() {
    // 팀원 삭제하는 function
    $('.mem_delete_btn').click(function() {
        let team_num = $(this).data('team-num');
        let mem_num = $(this).data('mem-num');
        let leader = $(this).data('current-leader');
        
        let choice = confirm('해당 멤버를 삭제하시겠습니까?');
        
        if (choice) {
            // 서버 통신
            $.ajax({
                url: 'deleteTmember.do',
                type: 'post',
                data: { team_num: team_num, mem_num: mem_num, leader: leader },
                dataType: 'json',
                success: function(param) {
                    if (param.result == 'logout') {
                        alert('로그인 후 사용해주세요');
                    } else if (param.result == 'wrongAccess') {
                        alert('잘못된 접근 정보입니다.');
                    } else if (param.result == 'isLeader'){
						alert('팀장은 삭제할 수 없습니다. 위임 후 시도해주세요.')
					} else if (param.result == 'success') {
                        alert('팀원이 삭제되었습니다');
                        location.href = 'checkTeamMember.do?team_num=' + team_num;
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
    
    // 팀장 위임 하는 function
    $(".mem_auth_btn").click(function() {
       let team_num = $(this).data('team-num');
        let mem_num = $(this).data('mem-num');
        let leader = $(this).data('current-leader');
		let choice = confirm('해당 멤버에게 팀장을 위임하시겠습니까?');
		if(choice){
			$.ajax({
            type: 'post',
            url: 'updateTeamLeader.do',
            data: {
                team_num: team_num,
                mem_num: mem_num,
                leader: leader
            },
            dataType: 'json',
            success: function(param) {
                if(param.result == 'success'){
                    alert('팀장 위임이 완료되었습니다');
                    location.reload();
                } else if (param.result == 'wrongAccess'){
                    alert('권한이 없습니다. 다시 로그인 후 시도해주세요.');
                } else if(param.result == 'already') {
					alert('이미 팀장입니다.')
				} else {
                    alert('팀장 위임 중 오류가 발생하였습니다.');
                }
            },
            error: function() {
                alert('네트워크 오류가 발생하였습니다');
            }
        });
		}
        
    });
});
