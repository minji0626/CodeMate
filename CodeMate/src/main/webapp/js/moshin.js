$(function() {
    $('.passForm').submit(function(event) {
        // form 데이터 serialize
        let form_data = $(this).serialize();

        // 서버와 통신
        $.ajax({
            url: 'passCodeMate.do',
            type: 'post',
            data: form_data, 
            dataType: 'json',
            success: function(param) {
                if (param.result =='logout') {
                    alert('로그인 후 사용하세요!');
                } else  if(param.result=='already'){
                    alert('이미 합격한 사용자입니다.');
                } else if(param.result=='last') {
					const msg = confirm("마지막 합격자입니다. 합격 시 팀이 생성됩니다. 합격시키겠습니까?");
					
					if(msg){
						location.href='lastPassCodeMate.do?' + form_data;
					} else {
						location.href='myPageMoShin.do?rb_num='+rb_num;
					}
				} else if(param.result=='max'){
					alert('최대 초과입니다');
				} else if (param.result == 'success') {
                    alert('합격시켰습니다');
                    location.reload();
				} else {
                    alert('합불합 실패했습니다.');
                }
            },
            error: function() {
                alert('네트워크 오류 발생');
            }
        });

        // 이벤트 전파 차단
        event.preventDefault();
    });
    
      $('.unPassForm').submit(function(event) {
        // form 데이터 serialize
        let form_data = $(this).serialize();

        // 서버와 통신
        $.ajax({
            url: 'unPassCodeMate.do',
            type: 'post',
            data: form_data, 
            dataType: 'json',
            success: function(param) {
                if (param.result === 'logout') {
                    alert('로그인 후 사용하세요!');
                } else  if(param.result=='already'){
                    alert('이미 불합격한 사용자입니다.');
                } else if(param.result=='no'){
					alert('이미 활성화된 프로젝트입니다.');
				} else if (param.result === 'success') {
                    alert('불합격시켰습니다');
                    location.reload();
                } else {
                    alert('합불합 실패했습니다.');
                }
            },
            error: function() {
                alert('네트워크 오류 발생');
            }
        });

        // 이벤트 전파 차단
        event.preventDefault();
    });
    
     // JavaScript 코드
	$(function() {
	    $('.myPage-line-box-moshin').each(function() {
	        let passValue = $(this).data('pass'); // data-pass 속성의 값 읽기
	        if (passValue == 0) {
	            $(this).css('border', '2px solid  red');
	        }
	         if (passValue == 1) {
	            $(this).css('border', '2px solid #5aca7c'); 
	        }
	          if(passValue == -1){
				$(this).css('border', '2px solid black');
			}
	    });
	});
	/*
	$(function(){
		$('close-green').click(function(){
			let rb_num = $(this).data('rbnum');
			$.ajax({
				url: 'activation.do',
				type: 'post',
	            data: rb_num, 
	            dataType: 'json',
	            success: function(param) {
	                if (param.result === 'logout') {
	                    alert('로그인 후 사용하세요!');
	                } else if (param.result === 'success') {
	                    alert('활성화시켰습니다');
	                    location.reload();
	                } else {
	                    alert('실패했습니다.');
	                }
	            },
	            error: function() {
	                alert('네트워크 오류 발생');
	            }
			});
		})
	});*/

});
