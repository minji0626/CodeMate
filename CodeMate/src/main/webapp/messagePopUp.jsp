<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
//팝업 열기
document.addEventListener('DOMContentLoaded', function () {
    var target = document.querySelectorAll('.btn_open');
    var btnPopClose = document.querySelectorAll('.pop_wrap .btn_close');
    var btnReply = document.querySelectorAll('.pop_wrap .btn_reply');

    // 팝업 열기
    for (var i = 0; i < target.length; i++) {
        target[i].addEventListener('click', function (event) {
            event.preventDefault();
            var sender = this.closest('tr').querySelector('[data-sender]').getAttribute('data-sender');
            var date = this.getAttribute('data-date');
            var time = this.getAttribute('data-time');
            var message = this.getAttribute('data-message');

            document.querySelector('#popup-sender').textContent = sender;
            document.querySelector('#popup-date').textContent = date;
            document.querySelector('#popup-time').textContent = time;
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
      <div class="header">쪽지 내용</div>
      <div class="message-details">
        <p><img alt="플필사진" src="${pageContext.request.contextPath}/images/face.png" width="20" height="20" style="border-radius: 50%;"> <span id="popup-sender"></span></p>
        <p><span id="popup-date"></span> <span id="popup-time"></span></p>
      </div>
      <div class="content" id="popup-message"></div>
      <div class="footer">
        <button type="button" class="btn_close">닫기</button>
        <button type="button" class="btn_reply">답장</button>
      </div>
    </div>
  </div>
</div>