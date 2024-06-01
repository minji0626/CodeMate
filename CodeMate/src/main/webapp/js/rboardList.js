$(document).ready(function() {
	$('.feather-arrow-left').click(function() {
		history.go(-1);
	});

	$('.search-menu, select').on('change', function() {
		if ($(this).val()) {
			$(this).addClass('selected');
		} else {
			$(this).removeClass('selected');
		}
		fetchResults();
	});

	// 검색 기능을 처리하는 함수
	function fetchResults() {
		var searchData = {
/*			r_skills: [],
			r_fields: $('#r_fields').val(),
			rb_meet: $('#rb_meet').val(),
			search_key: $('#search_key').val(),*/
			bookmark_filter: $('#bookmark_filter').hasClass('selected'),
			recruiting_filter: $('#recruiting_filter').hasClass('selected')
		};

/*		$('input[name="r_skills"]:checked').each(function() {
			searchData.r_skills.push($(this).val());
		});*/

		$.ajax({
			type: 'GET',
			url: 'searchResults.do', // 서버 측 검색 결과를 처리하는 URL
			data: searchData,
			success: function(response) {
				$('#r_board').html(response); // 검색 결과를 r_board에 반영
			},
			error: function() {
				alert('네트워크 오류 발생');
			}
		});
	}

	/*=============================
	하드스킬 스크롤
	=============================*/
	$('.scrollable').hide();

	$('#scrollable_trigger').click(function() {
		$(this).siblings('.scrollable').toggle();
	});

	// 바탕을 클릭했을 때 scrollable 창이 닫히도록 설정
	$(document).on('click', function(event) {
		if (!$(event.target).closest('.scrollable').length && !$(event.target).is('#scrollable_trigger')) {
			$('.scrollable').hide();
		}
	});
	// 스크롤러 내부의 클릭 이벤트를 전파되지 않도록 설정
	$('.scrollable').on('click', function(event) {
		event.stopPropagation();
	});

	// 동적으로 생성된 .remove-btn에 이벤트 핸들러 추가
	$('#scrollable_trigger').on('click', '.skill-tag', function(event) {
		event.stopPropagation();
		removeSkillTag(this);
	});

	$('.scrollable input[type="checkbox"]').change(function() {
		updateSkillTags(); // 체크박스 상태 변경 시 함수 호출
	});

	// 페이지 로딩 시 초기 호출
	updateSkillTags();

	// 하드스킬 태그 업데이트
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

	// 하드스킬 태그 제거
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
});
