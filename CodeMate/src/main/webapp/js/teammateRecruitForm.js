$(document).ready(function() {
    $('.scrollable').hide();

    $('#scrollable_trigger').click(function() {
        $(this).siblings('.scrollable').toggle();
    });

    $('.scrollable input[type="checkbox"]').change(function() {
        selectskillsChecked(this); // 체크박스 상태 변경 시 함수 호출
    });
});

function selectskillsChecked() {
    var $scrollableTrigger = $('#scrollable_trigger');
    $scrollableTrigger.empty(); // 모든 내용 삭제

    var $checked = $('.scrollable input[type="checkbox"]:checked');

    if ($checked.length === 0) {
        $scrollableTrigger.text('요구하는 기술 스택을 선택하세요');
    } else {
        $checked.each(function() {
            var labelText = $(this).siblings('label').text(); // 체크된 체크박스의 label 텍스트 가져오기
            $scrollableTrigger.append('<span class="skill-tag">' + labelText + ' <span class="remove-btn">X</span></span>'); // 체크된 체크박스의 label을 span 태그로 추가
        });
    }
}

// 페이지 로딩 시 초기 호출
selectskillsChecked();

