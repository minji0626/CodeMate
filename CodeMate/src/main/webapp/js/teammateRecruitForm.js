$(document).ready(function() {
	/*=============================
	오늘 날짜부터 선택되도록
	=============================*/
	// 오늘 날짜 구하기
	var today = new Date().toISOString().split('T')[0];

	// 모든 date 타입 input 태그에 min 속성 설정
	$('input[type="date"]').attr('min', today);

	// 시작 예정일 변경 시 모집 종료일의 max 속성 업데이트
	$('#rb_start').change(function() {
		var startDate = $(this).val();
		$('#rb_endRecruit').attr('max', startDate);
	});


	/*=============================
	유효성 체크
	=============================*/
	$('#teammate_recruit_form').submit(function(event) {
		//셀렉트
		$('select.input-check').each(function() {
			let selectedValue = $(this).find('option:selected').val();
			if (selectedValue == '') {
				let labelText = $(this).parent().find('label').text();
				alert(labelText + '을 선택해주세요');
				return false;
			}
		});

		//날짜, 텍스트
		$('input.input-check, textarea.input-check').each(function() {
			if ($(this).val() == '') {
				let labelText = $(this).parent().find('label').text();
				alert(labelText + '을 입력해주세요');
				return false;
			}
		});

		// 시작 예정일과 모집 종료일 유효성 검사
		var startProject = $('#rb_start').val();
		var endRecruit = $('#rb_endRecruit').val();
		if (startProject && endRecruit && new Date(startProject) < new Date(endRecruit)) {
			alert('모집 종료일은 시작 예정일보다 늦을 수 없습니다.');
			return false;
		}

		//체크박스(요구스킬, 모집필드 하나이상 선택)
		// 요구 기술 스택 체크박스 유효성 검사
		if ($('input[name="r_skills"]:checked').length === 0) {
			alert('적어도 하나의 요구 기술을 선택해주세요');
			return false;
		}

		// 모집 필드 체크박스 유효성 검사
		if ($('input[name="r_fields"]:checked').length === 0) {
			alert('적어도 하나의 모집 필드를 선택해주세요');
			return false;
		}

	});





	/*=============================
	하드스킬 스크롤
	=============================*/
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

//하드스킬 태그 업데이트
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

//하드스킬 태그 제거
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
