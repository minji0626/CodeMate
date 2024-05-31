$(document).ready(function() {
	$('.feather-arrow-left').click(function() {
		history.go(-1);
	});

	// 드롭다운 메뉴에서 옵션을 선택할 때마다 검색 함수 실행
	function addEventListeners() {
		$('.search-menu').on('change', function() {
			if ($(this))
				search();
		});

		$('span.search-menu').on('click', function() {
			$(this).toggleClass('selected');
			search();
		});

		$('select').on('change', function() {
			if ($(this).val()) {
				$(this).addClass('selected');
			} else {
				$(this).removeClass('selected');
			}
		});
	}

	// 검색 기능을 처리하는 함수
	function search() {
		// 선택된 옵션 가져오기
		var selectedOptions = [];
		$('.search-menu').each(function() {
			selectedOptions.push($(this).val());
		});

		// 여기에 검색 기능 실행하는 코드 추가
		console.log('선택된 옵션:', selectedOptions);
		// 검색 결과를 화면에 표시하거나 검색 요청을 서버에 전송하는 등의 로직을 추가할 수 있습니다.
	}

	addEventListeners();

	/*=============================
	하드스킬 스크롤
	=============================*/
	$('.scrollable').hide();

	$('#scrollable_trigger').click(function() {
		$(this).siblings('.scrollable').show();
	});

	// 바탕을 클릭했을 때 scrollable 창이 닫히도록 설정
	$(document).on('click', function(event) {
		if (!$(event.target).closest('.scrollable').length && !$(event.target).is('#scrollable_trigger')) {
			$('.scrollable').hide();
		}
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
		$scrollableTrigger.removeClass('selected');
		$scrollableTrigger.text('기술 스택');
	} else {
		$scrollableTrigger.addClass('selected');
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
		$scrollableTrigger.removeClass('selected');
		$scrollableTrigger.text('기술 스택');
	}
}