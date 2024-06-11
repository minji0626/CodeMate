<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
//팝업 열기
document.addEventListener('DOMContentLoaded', function () {
    var target = document.querySelectorAll('.btn_open');
    var btnPopClose = document.querySelectorAll('.pop_wrap .btn_close');

    // 팝업 열기
    for (var i = 0; i < target.length; i++) {
        target[i].addEventListener('click', function (event) {
            event.preventDefault();
            var title = this.getAttribute('data-cs-title');
            var date = this.getAttribute('data-cs-reg-date');
            var email = this.getAttribute('data-cs-email');
            var message = this.getAttribute('data-cs-content');
            var confirmCount = this.getAttribute('data-cs-confirmed');
            if(confirmCount == 0){
                var confirmText = "[답변처리중]";
            } else if(confirmCount == 1){
                var confirmText = "[답변완료]";
            } else {
                var confirmText = "";
            }
            document.querySelector('#popup-confirm').textContent = confirmText;
            document.querySelector('#popup-title').textContent = title;
            document.querySelector('#popup-date').textContent = date;
            document.querySelector('#popup-email').textContent = email;
            document.querySelector('#popup-message').textContent = message;
            document.querySelector('#pop_info').style.display = 'block';
        });
    }

    // 팝업 닫기
    for (var j = 0; j < btnPopClose.length; j++) {
        btnPopClose[j].addEventListener('click', function () {
            this.closest('.pop_wrap').style.display = 'none';
        });
    }

});

</script>
    
<div class="wrap">
  <div id="pop_info" class="pop_wrap" style="display:none;">
    <div class="pop_inner">
      <div class="header"><span id="popup-confirm"></span><h3 id="popup-title"> <!-- 문의 제목과 처리완료 유무 --></h3></div>
      <div class="message-details">
      	
      	
        
        <p>답변 받을 이메일 : <span id="popup-email"> </span></p>
      </div>
      <!-- 문의 내용 -->
      <div class="content">문의 내용 : <span  id="popup-message"></span></div>
      <!-- 문의 등록일 --><p>문의 등록일 : <span id="popup-date"></span> </p>
      <div class="footer">
        <button type="button" class="btn_close">닫기</button>
      </div>
    </div>
  </div>
</div>

