/*$(window).on('pageshow', function(event) {

	//페이지 보여질때 초기화
	$('.scrollable input[type="checkbox"]').trigger('change');
	$('.search-menu').trigger('change');
});
*/
//콘텍스트경로 구하기
function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}

//검색 결과 불러오기
function fetchResults() {
	var searchData = {
		r_skills: [],
		rb_category: $('select[name="rb_category"]').val(),
		r_fields: $('select[name="r_fields"]').val(),
		rb_meet: $('select[name="rb_meet"]').val(),
		search_key: $('#search_key').val(),
		recruiting_filter: $('#recruiting_filter').is(':checked')
	};

	console.log(searchData);

	$('input[name="r_skills"]:checked').each(function() {
		searchData.r_skills.push($(this).val());
	});

	$.ajax({
		type: 'GET',
		url: 'searchResults.do', // 서버 측 검색 결과를 처리하는 URL
		data: searchData,
		traditional: true,
		dataType: 'json',
		success: function(response) {
			let output = '';
			let contextPath = getContextPath();
			$(response.rboardList).each(function(index, item) {
				output += '<li class="r-item"';
				output += 'onclick="location.href=\'' + contextPath + '/rboard/detail.do?rb_num=' + item.rb_num + '\'">';
				output += '<div class="r-item-header">';
				output += '<div class="rb_category_div">';
				if (item.rb_category == 0) {
					output += '<span class="rb_category study">스터디</span>';
				} else {
					output += '<span class="rb_category project">프로젝트</span>';
				}
				output += '</div>';
				output += '<span class="rb_endRecruit">';
				if (item.daysLeft < 0) {
					output += '모집 종료됨';
				} else {
					output += item.rb_endRecruit + ' 마감';
				}
				output += '</span>';
				output += '</div>';
				output += '<div class="r-item-main">';
				output += '<p class="rb-title">' + item.rb_title + '</p>';
				output += '<div class="skill-logo-div">';
				for (var i = 0; i < item.hs_photo_arr.length; i++) {
					output += '<img src="' + contextPath + '/images/hard_skill_logo/' + item.hs_photo_arr[i] + '"';
					output += 'title="' + item.hs_name_arr[i] + '" class="skill-logo"> ';
				}
				output += '</div>';
				output += '<div class="r-item-info"><span>진행방식 | </span> <span>';
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
				output += '</div></div>';
				output += '</div>';
				output += '<div class="hit-div">';
				output += '<span>조회수 </span> <span>' + item.rb_hit + '</span>';
				output += '</div>';
				output += '</li>';
			});

			$('#r_board').empty().append(output);
		},
		error: function() {
			alert('네트워크 오류 발생');
		}
	});
}

//스킬태그 업데이트 하기
function updateSkillTags() {
	var $scrollableTrigger = $('#scrollable_trigger');
	var $checked = $('.scrollable input[type="checkbox"]:checked');

	// 기존의 태그 제거
	$scrollableTrigger.empty();

	// 선택된 태그가 없을 때 기본 문구 표시
	if ($checked.length === 0) {
		$('div#scrollable_trigger').removeClass('selected');
		$scrollableTrigger.text('기술 스택');
	} else {
		$('div#scrollable_trigger').addClass('selected');
		// 선택된 태그 추가
		$checked.each(function() {
			var labelText = $(this).siblings('label').text(); // 체크된 체크박스의 label 텍스트 가져오기
			$scrollableTrigger.append('<span class="skill-tag">' + labelText + ' <span class="remove-btn">x</span></span>'); // 체크된 체크박스의 label을 span 태그로 추가
		});
	}
	fetchResults();
}

//스킬태그 지우기
function removeSkillTag(element) {
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
		$('div#scrollable_trigger').removeClass('selected')
		$scrollableTrigger.text('기술 스택');
	}
	fetchResults();
}

$(document).ready(function() {
	//페이지 보여질때 초기화
	$('.scrollable input[type="checkbox"]').trigger('change');
	$('.search-menu').trigger('change');

	//뒤로가기 화살표
	$('.feather-arrow-left').click(function() {
		history.go(-1);
	});

	//select 태그(모집필드,모집구분,진행방식)에 change 이벤트 일어날 때 클래스 추가/제거, 검색 다시하기
	$('.search-menu').on('change', function() {
		if ($(this).val()) {
			$(this).addClass('selected');
		} else {
			$(this).removeClass('selected');
		}
		fetchResults();
	});

	//모집중보기에 change 이벤트 일어날 때 클래스 추가, 검색 다시하기
	$('#recruiting_filter').on('change', function() {
		console.log($(this).is(':checked'));
		if ($(this).is(':checked')) {
			$('span#recruiting_filter_span.search-menu').addClass('selected');
			console.log('추가');
		} else {
			$('span#recruiting_filter_span.search-menu').removeClass('selected');
			console.log('삭제');
		}
		fetchResults();
	});

	//search 인풋 태그에서 엔터를 누르면 클래스 추가, 검색 다시하기
	$('#search_key').keyup(function(event) {
		if (event.keyCode === 13) {
			$('#search_key_div').addClass('selected');
			fetchResults();
		}
	});

	//search 인풋 태그에서 값이 ''가 됐을 때 클래스 제거, 검색 다시하기
	$('#search_key').on('input', function() {
		if ($(this).val() == '') {
			$('#search_key_div').removeClass('selected');
			fetchResults();
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

	$('#scrollable_trigger').on('click', '.skill-tag', function(event) {
		event.stopPropagation();
		removeSkillTag(this);
	});

	$('.scrollable input[type="checkbox"]').change(function() {
		updateSkillTags(); // 체크박스 상태 변경 시 함수 호출
	});
});