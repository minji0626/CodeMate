$(window).on('pageshow', function(event) {
    if (event.originalEvent.persisted) {
        // 페이지가 캐시에서 불러와졌을 때 실행할 코드
        debugger;
        updateSkillTags();
        fetchResults();
        console.log($('#search_key').val());
    }
});

function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}

async function fetchResults() {
	debugger;
	var searchData = {
		r_skills: [],
		rb_category: $('select[name="rb_category"]').val(),
		r_fields: $('select[name="r_fields"]').val(),
		rb_meet: $('select[name="rb_meet"]').val(),
		search_key: $('#search_key').val(),
		recruiting_filter: $('#recruiting_filter').hasClass('selected')
	};

	console.log(searchData);

	$('input[name="r_skills"]:checked').each(function() {
		searchData.r_skills.push($(this).val());
	});

	try {
		let response = await $.ajax({
			type: 'GET',
			url: 'searchResults.do', // 서버 측 검색 결과를 처리하는 URL
			data: searchData,
			traditional: true,
			dataType: 'json'
		});

		let output = '';
		let contextPath = getContextPath();
		$(response.rboardList).each(function(index, item) {
			output += '<li class="r-item"';
			output += 'onclick="location.href=\'' + contextPath + '/rboard/detail.do?rb_num=' + item.rb_num + '\'">';
			output += '<div class="r-item-header">';
			output += '<span>마감일 | </span> <span>' + item.rb_endRecruit + '</span>';
			output += '</div>';
			output += '<div class="r-item-main">';
			if (item.rb_category == 0) {
				output += '<span>[스터디]</span>';
			} else {
				output += '<span>[프로젝트]</span>';
			}
			output += '<p>' + item.rb_title + '</p>';
			output += '</div>';
			output += '<div>';
			for (var i = 0; i < item.hs_photo_arr.length; i++) {
				output += '<img src="' + contextPath + '/images/hard_skill_logo/' + item.hs_photo_arr[i] + '"';
				output += 'title="' + item.hs_name_arr[i] + '" class="skill-logo"> ';
			}
			output += '</div>';
			output += '<span>진행방식 | </span> <span>';
			if (item.rb_meet == 0) {
				output += '온라인';
			} else if (item.rb_meet == 1) {
				output += '오프라인';
			} else {
				output += '온라인/오프라인';
			}
			output += '</span>';
			output += '<div>';
			output += '<span>모집필드 | </span>';
			$(item.f_name_arr).each(function(index, field) {
				output += '<span>' + field + '</span> ';
			});
			output += '</div>';
			output += '<div>';
			output += '<span>신청인원 | </span>';
			output += '<span>' + item.rb_apply_count + '/';
			if (item.rb_teamsize == 0) {
				output += '인원 미정';
			} else if (item.rb_teamsize == 10) {
				output += '10명 이상';
			} else {
				output += item.rb_teamsize;
			}
			output += '</span>';
			output += '</div>';
			output += '<div>';
			output += '<span>조회수 </span> <span>' + item.rb_hit + '</span>';
			output += '</div>';
			output += '</li>';
		});

		$('#r_board').empty().append(output);
	} catch (jqXHR) {
		var errorMessage = "네트워크 오류 발생~~";

		if (jqXHR.status === 0) {
			errorMessage += "\n네트워크가 연결되지 않았습니다.";
		} else if (jqXHR.status == 404) {
			errorMessage += "\n요청한 페이지를 찾을 수 없습니다. [404]";
		} else if (jqXHR.status == 500) {
			errorMessage += "\n서버 내부 오류가 발생했습니다. [500]";
		} else {
			errorMessage += "\n오류 코드: " + jqXHR.status;
		}

		errorMessage += "\n상태: " + textStatus;
		errorMessage += "\n오류 내용: " + errorThrown;

		alert(errorMessage);
	}
}

async function updateSkillTags() {
	var $scrollableTrigger = $('#scrollable_trigger');
	var $checked = $('.scrollable input[type="checkbox"]:checked');

	// 기존의 태그 제거
	$scrollableTrigger.empty();

	// 선택된 태그가 없을 때 기본 문구 표시
	if ($checked.length === 0) {
		$scrollableTrigger.text('기술 스택');
	} else {
		// 선택된 태그 추가
		$checked.each(function() {
			var labelText = $(this).siblings('label').text(); // 체크된 체크박스의 label 텍스트 가져오기
			$scrollableTrigger.append('<span class="skill-tag">' + labelText + ' <span class="remove-btn">x</span></span>'); // 체크된 체크박스의 label을 span 태그로 추가
		});
	}
	await fetchResults();
}

async function removeSkillTag(element) {
	var skillTag = $(element).closest('.skill-tag');
	var labelText = skillTag.text().trim().replace(' x', '');

	// 해당 라벨에 맞는 체크박스를 체크 해제
	$('.scrollable label').filter(function() {
		return $(this).text().trim() === labelText;
	}).siblings('input[type="checkbox"]').prop('checked', false);

	// 태그 제거
	skillTag.remove();

	// 태그가 없을 때 기본 문구 표시
	var $scrollableTrigger = $('#scrollable_trigger');
	if ($scrollableTrigger.find('.skill-tag').length === 0) {
		$scrollableTrigger.text('기술 스택');
	}
	await fetchResults();
}

$(document).ready(function() {
	//updateSkillTags();

	$('.feather-arrow-left').click(function() {
		history.go(-1);
	});

	$('.search-menu').on('change', async function() {
		if ($(this).val()) {
			$(this).addClass('selected');
		} else {
			$(this).removeClass('selected');
		}
		await fetchResults();
	});

	$('span.search-menu').on('click', async function() {
		$(this).toggleClass('selected');
		await fetchResults();
	});

	$('#search_key').keypress(async function(event) {
		if (event.keyCode === 13) {
			$('#search_key_div').addClass('selected');
			await fetchResults();
		}
	});

	$('.scrollable').hide();

	$('#scrollable_trigger').click(function() {
		$(this).siblings('.scrollable').toggle();
	});

	$(document).on('click', function(event) {
		if (!$(event.target).closest('.scrollable').length && !$(event.target).is('#scrollable_trigger')) {
			$('.scrollable').hide();
		}
	});

	$('.scrollable').on('click', function(event) {
		event.stopPropagation();
	});

	$('#scrollable_trigger').on('click', '.skill-tag', async function(event) {
		event.stopPropagation();
		await removeSkillTag(this);
	});

	$('.scrollable input[type="checkbox"]').change(async function() {
		await updateSkillTags(); // 체크박스 상태 변경 시 함수 호출
	});
});

// 전역에서 접근 가능한 데이터 출력 (테스트용)
console.log("r_fields =", $('select[name="r_fields"]').val());