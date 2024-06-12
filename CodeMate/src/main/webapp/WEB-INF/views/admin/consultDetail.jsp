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
                var confirmText = "답변 처리중";
            } else if(confirmCount == 1){
                var confirmText = "답변 완료";
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
    
      <div class="header">
      	<span id="popup-confirm"></span>
      	<span id="popup-title"></span>
      	<div class="message-details">
        	<span id="consult-email">이메일 |</span>
        	<span id="popup-email"></span>
     	 </div>
      </div>
      
      <span id="consult-message">문의 내용</span>
      <div class="consult_content">
      	<span id="popup-message"></span>
      </div>
      
      
      <div class="footer">
      	<div class="consult_reg_date">
      		등록일 |
      		<span id="popup-date"></span> 
      	</div>
        <button type="button" class="btn_close"><img src="${pageContext.request.contextPath}/images/delete_icon.png"></button>
      </div>
      
    </div>
  </div>
</div>
