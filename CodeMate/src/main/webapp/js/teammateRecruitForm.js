$(document).ready(function() {
    $('.scrollable').hide();

    $('#scrollable_trigger').click(function() {
        $(this).siblings('.scrollable').show();
    });

    $('.scrollable input[type="checkbox"]').change(function() {
        updateSkillTags(); // 체크박스 상태 변경 시 함수 호출
    });

    // 동적으로 생성된 .remove-btn에 이벤트 핸들러 추가
    $('#scrollable_trigger').on('click', '.skill-tag', function() {
        removeSkillTag(this);
    });

    // 페이지 로딩 시 초기 호출
    updateSkillTags();
});

function updateSkillTags() {
    var $scrollableTrigger = $('#scrollable_trigger');
    var $checked = $('.scrollable input[type="checkbox"]:checked');

    // 기존의 태그 제거
    $scrollableTrigger.empty();

    // 선택된 태그가 없을 때 기본 문구 표시
    if ($checked.length === 0) {
        $scrollableTrigger.text('요구하는 기술 스택을 선택하세요');
    } else {
        // 선택된 태그 추가
        $checked.each(function() {
            var labelText = $(this).siblings('label').text(); // 체크된 체크박스의 label 텍스트 가져오기
            $scrollableTrigger.append('<span class="skill-tag">' + labelText + ' <span class="remove-btn">x</span></span>'); // 체크된 체크박스의 label을 span 태그로 추가
        });
    }
}

function removeSkillTag(element) {
    var skillTag = $(element).closest('.skill-tag');
    var labelText = skillTag.text().trim().replace(' x', '');

    // 해당 라벨에 맞는 체크박스를 체크 해제
    $('.scrollable label:contains(' + labelText + ')').siblings('input[type="checkbox"]').prop('checked', false);

    // 태그 제거
    skillTag.remove();

    // 태그가 없을 때 기본 문구 표시
    var $scrollableTrigger = $('#scrollable_trigger');
    if ($scrollableTrigger.find('.skill-tag').length === 0) {
        $scrollableTrigger.text('요구하는 기술 스택을 선택하세요');
    }
}
