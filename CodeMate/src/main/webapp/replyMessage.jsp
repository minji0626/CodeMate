<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
document.addEventListener("DOMContentLoaded", function() {
    var replyButton = document.querySelector('.btn_reply');
    var replyPopup = document.getElementById('reply_info');

    replyButton.addEventListener('click', function() {
        // 기존 팝업 닫기
        var popupTemplate = document.querySelector('.pop_wrap');
        popupTemplate.style.display = 'none';

        // 받는 사람 정보 가져오기
        var sender = document.getElementById('popup-sender').textContent;

        // 답장 팝업 열기
        replyPopup.style.display = 'block';

        // 받는 사람 정보 설정
        var recipientInput = replyPopup.querySelector('#recipient');
        recipientInput.value = sender;
    });

    // 답장 팝업 닫기 버튼 이벤트 리스너 추가
    var cancelButton = replyPopup.querySelector('.btn_cancel');
    cancelButton.addEventListener('click', function() {
        replyPopup.style.display = 'none'; // 팝업 닫기
    });
});

</script>
    
<div class="wrap">
    <div id="reply_info" class="pop_wrap" style="display:none;">
        <div class="pop_inner">
            <div class="header">답장하기</div>
            <div class="content">
                <!-- 받는 사람 입력란 -->
                <div class="form-group">
                    <label for="recipient">받는 사람:</label>
                    <input type="text" id="recipient" placeholder="받는 사람" disabled>
                </div>
                <!-- 제목 입력란 -->
                <div class="form-group">
                    <label for="reply-title">제목:</label>
                    <input type="text" id="reply-title" placeholder="제목">
                </div>
                <!-- 내용 입력란 -->
                <div class="form-group">
                    <label for="reply-message">내용:</label>
                    <textarea id="reply-message" rows="8" cols="53" placeholder="답장 내용을 입력하세요..."></textarea>
                </div>
            </div>
            <div class="footer">
                <button type="button" class="btn_cancel">취소</button>
                <button type="button" class="btn_send">전송</button>
            </div>
        </div>
    </div>
</div>

